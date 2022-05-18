package org.example.dao.old;


import lombok.AllArgsConstructor;
import org.example.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

@AllArgsConstructor
public class OldAuthorDao {
    private SessionFactory sessionFactory;

    // metoda save Hibernate zwraca obiekt Serializable i to jest id naszego rekordu (obiektu)
    public Long save(Author author) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(author);
        transaction.commit();
        session.close();
        return id;
    }

    public Optional<Author> getById(Long id) {
        Session session = sessionFactory.openSession();
        Author author = session.find(Author.class, id);
        session.close();
        return Optional.ofNullable(author);
    }

    public void update(Author author) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(author);
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
