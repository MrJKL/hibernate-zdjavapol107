package org.example.dao;

import lombok.AllArgsConstructor;
import org.example.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

//CRUD - create read update delete
//DAO - data access object

@AllArgsConstructor
public class MovieDao {
    private SessionFactory sessionFactory;

    //    create
    // metoda save Hibernate zwraca obiekt Serializable i to jest id naszego rekordu (obiektu)
    public void save(Movie movie) {
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

    // read one
    public Optional<Movie> getById(Long id) {
        //optional jest tylko po to, aby coś zwrócić lub nie
        //chroni on przed null pointer exception, ale nie całkowicie
        //optional używamy, gdy spodziewamy się, że coś może być null
        //optional rzczej nie jest szkodliwy, powinien być obsłużony
        Session session = sessionFactory.openSession();
        //ta metoda zawsze szuka po kluczu głównym tabeli
        Movie movie = session.find(Movie.class, id);
        session.close();
        //poniższe zwróci Optional i w środku będzie Movie albo Null
        return Optional.ofNullable(movie);
    }
    //update
    public void update(Movie movie) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(movie);
        transaction.commit();
        session.close();
    }
    //read all

}
