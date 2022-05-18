package org.example.dao;

import org.example.model.Badge;
import org.hibernate.SessionFactory;

public class BadgeDao extends EntityDao<Badge>{
    public BadgeDao(SessionFactory sessionFactory) {
        super(sessionFactory, Badge.class);
    }
}
