package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.MovieDao;
import org.example.model.Author;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Optional;

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

        saveAuthorExample(authorDao);
        getByIdWithOptional(movieDao);
        updateExample(movieDao);

        deleteExample(authorDao);


        sessionFactory.close();
    }

    private static void deleteExample(AuthorDao authorDao) {
        Long id = authorDao.save(new Author("Leszek", "Leszczynski", "Reda"));
        authorDao.delete(id);
        System.out.println("Dodano i usunięto wpis o id: " + id);
    }

    private static void updateExample(MovieDao movieDao) {
        //testujemy update z MovieDao
        Optional<Movie> movieById = movieDao.getById(1L);
        if(movieById.isPresent()) {
            Movie movie = movieById.get();
            System.out.println("Movie before update" + movie);
            movie.setTitle("Updated movie");
            movieDao.update(movie);
        }
        Optional<Movie> updatedMovieById = movieDao.getById(1L);
        if (updatedMovieById.isPresent()) {
            System.out.println("updatedMovieById = " + updatedMovieById);
        }
        updatedMovieById.ifPresent(movie -> System.out.println("updated movie: " + movie));
    }

    private static void getByIdWithOptional(MovieDao movieDao) {
        //get movie by id - MovieDao.getById()
        //poniższe na 100% działa
        Optional<Movie> optionalMovie = movieDao.getById(1L);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            System.out.println(movie);
        }

        Movie movie = movieDao.getById(99L)
                        .orElse(new Movie("NIE ZNALEZIONO FILMU", LocalDate.MAX));

        optionalMovie.ifPresent(movie2 -> {
            System.out.println("NIE ZNALEZIONO →S☻Ć­éřć}♀÷cQf×╚Ç╣");
            System.out.println(movie2);
        });
    }

    private static void saveAuthorExample(AuthorDao authorDao) {
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
    }
}
