package org.example;

import org.example.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
	private Configuration getHibernateConfig() {
//		poniżej dane dla SessionFactory do utworzenia połączenia z bazą
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:db-data/mydatabase; hsqldb.write_delay_millis=0");
		configuration.setProperty("hibernate.connection.username", "admin123");
		configuration.setProperty("hibernate.connection.password", "admin123");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//		configuration.setProperty("hibernate.show_sql", "true"); //pokazuje wszystkie wygenerowane przez Hibernate zapytania SQL

//		Należy dodać utworzoną klasę (Entity)
//		Dodane Entity posłużą do utworzenia nowych tabeli
//		configuration.addAnnotatedClass(Wheel.class);
		configuration.addAnnotatedClass(Badge.class);
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(Author.class);
		configuration.addAnnotatedClass(Actor.class);

		return configuration;
	}

//	Poniższe się kopiuje do projektu np. z googla
//	to implementacja i utworzenie SessionFactory
	public SessionFactory getSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
											   .applySettings(getHibernateConfig().getProperties()).build();
		return getHibernateConfig().buildSessionFactory(registry);
	}
}