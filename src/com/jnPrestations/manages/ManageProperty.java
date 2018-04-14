package com.jnPrestations.manages;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jnPrestations.beans.EstateAgency;
import com.jnPrestations.beans.Property;
import com.jnPrestations.factories.FactoryClass;

public class ManageProperty {

	private static SessionFactory sf;
	private FactoryClass fc = new FactoryClass();
	private Property property = (Property) fc.createClass("Property");
	
	public void add(String address,String service, double basicFee, EstateAgency estateAg){

		sf = new Configuration().configure().buildSessionFactory();
		Session s  = sf.openSession();
		Transaction t ;
		try{
			t = s.beginTransaction();
			
			property.setAddress(address);
			property.setService(service);
			property.setBasicFee(basicFee);
			property.setEstateAgency(estateAg);
			s.save(property);
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			s.close();
			sf.close();
		}
	}
	public void update(String address, String service, double basicFee, int id){
		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t;
		try{
			t = s.beginTransaction();
			property = this.find(id);
			property.setAddress(address);
			property.setService(service);
			property.setBasicFee(basicFee);
			s.update(property);
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			s.close();
			sf.close();
		}
	}
	public void delete(int id){
		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t;
		try{
			t = s.beginTransaction();
			Property toDelete = this.find(id);
			s.delete(toDelete);
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			s.close();
			sf.close();
		}
	}
	public Property find(int id){

		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t;
		try{
			t = s.beginTransaction();
			property = (Property) s.get(Property.class, id);
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			s.close();
			sf.close();
		}
		return property;
	}
	
	public List listAll(){
		
		List<Property> list = new LinkedList<Property>();
		
		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t;
		try{
			t = s.beginTransaction();
			String hql = "FROM Property ORDER BY BasicFee DESC";
			Query query = s.createQuery(hql);
			list =  query.list();
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			s.close();
			sf.close();
		}
		return list;
	}
	
}
