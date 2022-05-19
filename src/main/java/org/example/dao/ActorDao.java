package org.example.dao;

import org.example.model.Actor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ActorDao extends EntityDao<Actor> {
    public ActorDao(SessionFactory sessionFactory) {
        super(sessionFactory, Actor.class);
    }

    public List<Actor> getByName(String name) {
        Session session = sessionFactory.openSession();
        List<Actor> resultList;
        // liście nie jest potrzebny Optional WSZYSTKIE KOLEKCJE MAJĄ METODY TYPU isEmpty które służą do sprawdzania zawartości
        // Optional tylko do typów prostych
        //Nigdy nie używać StringBuilder do zapytań, bo wtedy łatwo o SQL-injection
        //zawsze używamy JDBC - "?" lub HQL - ":" itd., ponieważ sa tam odpowiednie metody chroniące przed SQL-injection
        //wersja nr 1
        Query<Actor> query = session.createQuery("From Actor a Where a.name = :nameParameter", Actor.class);
        Query<Actor> nameParameter = query.setParameter("nameParameter", name);
        resultList = nameParameter.getResultList();

        //wersja nr 2
        resultList = session.createQuery("From Actor a Where a.name = :nameParameter", Actor.class)
                .setParameter("nameParameter", name)
                .getResultList();

        // przy relacji typu lazy poniższe zapewnia "dociągnięcie" filmów
        // poniższe powoduje wysyłanie dodatkowych selektów, dla każdego aktora 1 selekt z zapytaniem o filmy,
        // w których występuje, te selekty WIDAĆ TYLKO W LOGACH
        // jest to problem typu n + 1 zapytań, w Hibernate nie zawsze widać ile select poleci do bazy danych
        // stwarza problemy, z wydajnością
        // za to fetchJoin zrobi 1 zapytanie, pobierając Aktorów i ich filmy
        for (Actor actor : resultList) {
            Hibernate.initialize(actor.getMovies());
        }


        session.close();
        return resultList;
    }

    public void updateAllSimilarNames(String nameFrom, String nameTo){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("Update Actor set name = :nameTo Where name = :nameFrom")
                .setParameter("nameTo", nameTo)
                .setParameter("nameFrom", nameFrom)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

//    moja własna twórczość
    public void updateNameById(List<Integer> listOfId, String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("Update Actor set name = :name Where id = :listOfId")
                .setParameter("name", name)
                .setParameterList("listOfId", listOfId)
                .executeUpdate();
        transaction.commit();
        session.close();
    }



}


