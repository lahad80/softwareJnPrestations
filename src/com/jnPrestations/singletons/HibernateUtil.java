package com.jnPrestations.singletons;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private HibernateUtil (){
		
	}
	
	public static SessionFactory getSessionFactory(){		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;	
	}
}
  