package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App
{
    public static void main( String[] args ) {

//        otwarcie sesji
        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        Session session = sessionFactory.openSession();


        session.close();
        sessionFactory.close();
    }
}
