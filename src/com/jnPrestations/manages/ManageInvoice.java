package com.jnPrestations.manages;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jnPrestations.beans.Invoice;
import com.jnPrestations.beans.Property;
import com.jnPrestations.singletons.HibernateUtil;


public class ManageInvoice {
	
	private Invoice invoice = new Invoice();

	
	public ManageInvoice(){
		
	}
	

	public void register(int invNumber, String dateOfIssue, String period, String status, Property property){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			Transaction t = session.beginTransaction();
			invoice.setInvNumber(invNumber);
			invoice.setDateOfIssue(dateOfIssue);
			invoice.setPeriod(period);
			invoice.setStatus(status);
			invoice.setProperty(property);
			
			session.save(invoice);
			t.commit();
		}
		catch(HibernateException he){
			
		}
		finally{
			session.close();
			
		}
	}

	public void brandAsSettled(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t;
		try{
			t = session.beginTransaction();
			invoice = (Invoice) session.get(Invoice.class, id);
			invoice.setStatus("settled");
			session.update(invoice);
			t.commit();
		}catch(HibernateException he){
			
		}
		finally{
			session.close();
		}
	}
	
	public List<Invoice> pickUnsettledOnes(){
		List <Invoice>list = new LinkedList<Invoice>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			Query query = session.createQuery("FROM Invoice WHERE status =:unsettled");
			query.setParameter("unsettled", "unsettled");
			list = query.list();
		}catch(HibernateException he){
			
		}finally{
			session.close();
		}
		return list;
	}

}
