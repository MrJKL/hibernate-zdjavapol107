package org.example.dao;

import org.example.model.Author;
import org.hibernate.SessionFactory;

public class AuthorDao extends EntityDao<Author>{
    public AuthorDao(SessionFactory sessionFactory) {
        super(sessionFactory, Author.class);
    }
}
