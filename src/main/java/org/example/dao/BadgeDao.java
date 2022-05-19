package org.example.dao;

import org.example.model.Badge;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BadgeDao extends EntityDao<Badge>{
    public BadgeDao(SessionFactory sessionFactory) {
        super(sessionFactory, Badge.class);
    }

    public List<Badge> getAllBadges() {
        //musieliśmy zmienić parametr private na protected w EntityDao aby użyć tu sessionFactory
        Session session = sessionFactory.openSession();
        //nativeQuery — zapytania SQL i dług technologiczny SQL
        //zwykłe Query to zapytania w HQL
        List<Badge> result = session.createQuery("From Badge", Badge.class).getResultList();
        session.close();
        return result;
    }
}
