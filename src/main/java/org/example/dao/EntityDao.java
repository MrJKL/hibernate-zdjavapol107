package org.example.dao;

import org.example.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public abstract class EntityDao<T> {
//    Nasze uniwersalne DAO z generycznymi metodami
//    stworzone na podstawie author DAO
//    zmieniliśmy EntityDao na abstrakcyjne, żeby nikt nie mógł utworzyć jego instancji, a jedynie tworzyć klasy dziedziczące
//    ta klasa powinna implementować podstawowe działania na encjach, a bardziej wymagające specyficzne rzeczy będą obsługiwane przez specyficzne dla danego entity DAO np.: MovieDao


    protected SessionFactory sessionFactory;
    //    poniższa zmienna przechowuje typ zmiennej, jaki przyjmie metoda
    private Class<T> clazz;  // piszemy clazz, ponieważ class jest zarezerwowane przez java

    public EntityDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public Long save(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    public Optional<T> getById(Long id) {
        Session session = sessionFactory.openSession();
        T entity = session.find(clazz, id);
        session.close();
        return Optional.ofNullable(entity);
    }

    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        getById(id).ifPresent(session::delete); //method reference (session::delete)
//        getById(id).ifPresent(entity -> session.delete(entity)); <- lambda, efekt taki jak powyżej
//        lambdę można zamienić na referencję tylko i wyłącznie, jeżeli wszystkie argumenty lambdy trafiają do referencji funkcji

        transaction.commit();
        session.close();
    }
}
