package com.jnPrestations.connections;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sf;
	
	public static SessionFactory getSessionFactory(){
		Configuration conf = new Configuration().configure();
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		
		sf = conf.buildSessionFactory(sr);
		return sf;	
		//sf = new Configuration().configure().buildSessionFactory();
		//return sf;
	}
}
  