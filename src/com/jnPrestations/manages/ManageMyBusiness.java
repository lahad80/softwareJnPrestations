package com.jnPrestations.manages;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jnPrestations.singletons.HibernateUtil;
import com.jnPrestations.beans.MyBusiness;

public class ManageMyBusiness {

	private MyBusiness mb = new MyBusiness();


	public void update( String legalName, String address, String zipCode, String town,
			String phoneNumber, String email, String siren){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			int id = 1;
			mb = (MyBusiness) session.get(MyBusiness.class, id);
			mb.setLegalName(legalName);
			mb.setAddress(address);
			mb.setZipCode(zipCode);
			mb.setTown(town);
			mb.setPhoneNumber(phoneNumber);
			mb.setEmail(email);
			mb.setSiren(siren);
			
			session.update(mb);
			t.commit();
		}catch(HibernateException he){
			
		}finally{
			session.close();
		}
	}
	public MyBusiness find(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			Transaction t = session.beginTransaction();
		
			int id = 1;
			mb = (MyBusiness) session.get(MyBusiness.class, id);
			t.commit();
		}catch(HibernateException he){
			
		}finally{
			session.close();
		}
		return mb;
	}
	
	public void incrementBillNumber (){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		int id = 1;
		int billNumber = 0;
		try{
			t = session.beginTransaction();
			mb = (MyBusiness) session.get(MyBusiness.class, id);
			billNumber = mb.getBillNumber();
			billNumber++;
			mb.setBillNumber(billNumber);
			t.commit();
		}
		catch(HibernateException he){
			
		}
		finally{
			session.close();
		}
	}
	
	public void logIn (){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			t.commit();
		}
		catch(HibernateException he){
			
		}
		finally{
			session.close();
		}
	}

}
