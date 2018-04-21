package com.jnPrestations.manages;


import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jnPrestations.beans.EstateAgency;
import com.jnPrestations.beans.Property;
import com.jnPrestations.singletons.HibernateUtil;
import com.jnPrestations.factories.FactoryClass;

public class ManageProperty {

	private FactoryClass fc = new FactoryClass();
	private Property property = (Property) fc.createClass("Property");
	
	public void add(String address,String service, double basicFee, EstateAgency estateAg){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t ;
		try{
			t = session.beginTransaction();
			
			property.setAddress(address);
			property.setService(service);
			property.setBasicFee(basicFee);
			property.setEstateAgency(estateAg);
			session.save(property);
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			session.close();
		}
	}
	public void update(String address, String service, double basicFee, int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t;
		try{
			t = session.beginTransaction();
			property = this.find(id);
			property.setAddress(address);
			property.setService(service);
			property.setBasicFee(basicFee);
			session.update(property);
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			session.close();
		}
	}
	public void delete(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t;
		try{
			t = session.beginTransaction();
			Property toDelete = this.find(id);
			session.delete(toDelete);
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			session.close();
		}
	}
	public Property find(int id){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t;
		try{
			t = session.beginTransaction();
			property = (Property) session.get(Property.class, id);
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			session.close();
		}
		return property;
	}
	
	public List listAll(){
		
		List<Property> list = new LinkedList<Property>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t;
		try{
			t = session.beginTransaction();
			String hql = "FROM Property ORDER BY BasicFee DESC";
			Query query = session.createQuery(hql);
			list =  query.list();
			t.commit();
		}catch(HibernateException he){

		}
		finally{
			session.close();
		}
		return list;
	}
	
}
