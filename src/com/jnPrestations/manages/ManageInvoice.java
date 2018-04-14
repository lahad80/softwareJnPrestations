package com.jnPrestations.manages;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jnPrestations.beans.Invoice;
import com.jnPrestations.beans.Property;
import com.jnPrestations.factories.FactoryClass;


public class ManageInvoice {
	
	private static SessionFactory sf;
	private FactoryClass fc = new FactoryClass();
	private Invoice invoice = (Invoice)fc.createClass("Invoice");

	
	public ManageInvoice(){
		
	}
	

	public void register(int invNumber, String dateOfIssue, String period, String status, Property property){
		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		try{
			Transaction t = s.beginTransaction();
			invoice.setInvNumber(invNumber);
			invoice.setDateOfIssue(dateOfIssue);
			invoice.setPeriod(period);
			invoice.setStatus(status);
			invoice.setProperty(property);
			
			s.save(invoice);
			t.commit();
		}
		catch(HibernateException he){
			
		}
		finally{
			s.close();
			sf.close();
		}
	}

	public void brandAsSettled(int id){
		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t;
		try{
			t = s.beginTransaction();
			invoice = (Invoice) s.get(Invoice.class, id);
			invoice.setStatus("settled");
			s.update(invoice);
			t.commit();
		}catch(HibernateException he){
			
		}
		finally{
			s.close();
			sf.close();
		}
	}
	
	public List pickUnsettledOnes(){
		List <Invoice>list = new LinkedList<Invoice>();
		sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = null;
		try{
			t = s.beginTransaction();
			Query query = s.createQuery("FROM Invoice WHERE status =:unsettled");
			query.setParameter("unsettled", "unsettled");
			list = query.list();
		}catch(HibernateException he){
			
		}finally{
			s.close();
			sf.close();
		}
		return list;
	}

}
