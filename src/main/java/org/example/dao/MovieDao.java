package org.example.dao;

import lombok.AllArgsConstructor;
import org.example.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//CRUD - create read update delete
//DAO - data access object

@AllArgsConstructor
public class MovieDao {
    private SessionFactory sessionFactory;

//    create
    public void  save(Movie movie) {
//        ten try-catch to jest tu dla przykładu
        try {
            //wyciągamy sesję z session-factory
            Session session = sessionFactory.openSession();
            //transakcja potrzebna jest jedynie dlatego, że chcemy zapisywać dane (modyfikować dane)
            Transaction transaction = session.beginTransaction();
            session.save(movie);
//        transakcję należy zamknąć transaction.commit() lub pozbyć się zmian transaction.rollback()
            transaction.commit();
            //prawdopodobnie session.close() nie jest potrzebne (najprawdopodobniej będzie zaimplementowane auto closeable), ale należy je dodawać dla bezpieczeństwa
            session.close();
        } catch (HibernateException e) {
            Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
            transaction.rollback();
        }
    }







}
