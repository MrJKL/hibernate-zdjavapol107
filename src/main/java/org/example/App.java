package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.MovieDao;
import org.example.model.Author;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
// Ponieważ utworzyliśmy DAO nie potrzebujemy za komentowanej części
        ////      Tworzenie nowego rekordu
//        Movie movie = new Movie();
//        movie.setTitle("Titanic");
//        movie.setReleaseDate(LocalDate.now());
//
//        Author author = new Author("Machael", "Bay", "CGI Lane 253, CA");
//
////        otwarcie sesji
//        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
//        Session session = sessionFactory.openSession();
////      Poniżej transakcja SQL
//        Transaction transaction = session.beginTransaction();
////        transaction.rollback(); - przywrócenie stanu sprzed rozpoczęcia transakcji
////        poniżej zapisujemy nowe rekordy, sam zapis wykonywany jest dopiero przy poleceniu transaction.commit()
//        session.save(movie);
//        session.save(author);
//
//        transaction.commit();
//
//        session.close();
        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        AuthorDao authorDao = new AuthorDao(sessionFactory);
        MovieDao movieDao = new MovieDao(sessionFactory);

//        przykład zapisu nr 1
        Author author = new Author("Marek", "Dudek", "Kowalna 34");
        authorDao.save(author);
//       przykład zapisu nr 2
        authorDao.save(new Author("Derek", "Derkowski", "Derdkowo D3"));
//       przykład zapisu nr 3
        authorDao.save(Author.builder()
                .firstName("Robert")
                .lastName("Robertowski")
                .address("Robotnicza 1")
                .build());

        sessionFactory.close();
    }
}
