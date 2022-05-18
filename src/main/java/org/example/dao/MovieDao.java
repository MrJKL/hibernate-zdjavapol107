package org.example.dao;

import org.example.model.Movie;
import org.hibernate.SessionFactory;

public class MovieDao extends EntityDao<Movie>{
    public MovieDao(SessionFactory sessionFactory) {
        super(sessionFactory, Movie.class);
    }

    /*
    tak wyglądało, jak wygenerował to inteliJ, ale
    w 1 linii usuwamy parametr class, aby ktoś inny nam go nie ustawiał dowolnie
    i następnie w linii super określamy klasę na sztywno
    public MovieDao(SessionFactory sessionFactory, Class<Movie> clazz) {
        super(sessionFactory, clazz);
    }
    */
}
