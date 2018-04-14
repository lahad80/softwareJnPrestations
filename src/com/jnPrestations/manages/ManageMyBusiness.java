package com.jnPrestations.manages;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jnPrestations.connections.HibernateUtil;
import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.beans.MyBusiness;

public class ManageMyBusiness {

	private static SessionFactory sf;
	private FactoryClass fc = new FactoryClass();
	private MyBusiness mb = (MyBusiness) fc.createClass("MyBusiness");


	public void update( String legalName, String address, String zipCode, String town,
			String phoneNumber, String email, String siren){
		sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		try{
			Transaction t = s.beginTransaction();
			int id = 1;
			mb = (MyBusiness) s.get(MyBusiness.class, id);
			mb.setLegalName(legalName);
			mb.setAddress(address);
			mb.setZipCode(zipCode);
			mb.setTown(town);
			mb.setPhoneNumber(phoneNumber);
			mb.setEmail(email);
			mb.setSiren(siren);
			
			s.update(mb);
			t.commit();
		}catch(HibernateException he){
			
		}finally{
			s.close();
			sf.close();
		}
	}
	public MyBusiness find(){
		
	 	sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		
		try{
			Transaction t = s.beginTransaction();
		
			int id = 1;
			mb = (MyBusiness) s.get(MyBusiness.class, id);
			t.commit();
		}catch(HibernateException he){
			
		}finally{
			s.close();
			sf.close();
		}
		return mb;
	}
	
	public void incrementBillNumber (){
		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = null;
		int id = 1;
		int billNumber = 0;
		try{
			t = s.beginTransaction();
			mb = (MyBusiness) s.get(MyBusiness.class, id);
			billNumber = mb.getBillNumber();
			billNumber++;
			mb.setBillNumber(billNumber);
			t.commit();
		}
		catch(HibernateException he){
			
		}
		finally{
			s.close();
			sf.close();
		}
	}
	
	public void logIn (){
		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = null;
		try{
			t = s.beginTransaction();
			t.commit();
		}
		catch(HibernateException he){
			
		}
		finally{
			s.close();
			sf.close();
		}
	}

}
