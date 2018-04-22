package com.jnPrestations.manages;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jnPrestations.beans.EstateAgency;
import com.jnPrestations.beans.Property;
import com.jnPrestations.singletons.HibernateUtil;

public class ManageEstateAgency {

	private EstateAgency estateAg = new EstateAgency();

	public ManageEstateAgency (){}

	
	public void add(String legalName, String address, String zipCode, String town, String phoneNumber,
			String email, String siren){

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			estateAg.setLegalName(legalName);
			estateAg.setAddress(address);
			estateAg.setZipCode(zipCode);
			estateAg.setTown(town);
			estateAg.setPhoneNumber(phoneNumber);
			estateAg.setEmail(email);
			estateAg.setSiren(siren);

			session.save(estateAg);
			t.commit();
		}catch (HibernateException he){

		}finally{
			session.close();
		}
	}
	public void delete(int id){

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			
			estateAg = (EstateAgency)session.get(EstateAgency.class, id);
			session.delete(estateAg);
			t.commit();
		}catch (HibernateException he){

		}finally{
			session.close();
		}
	}
		public void update(int id, String legalName, String address, String zipCode, String town, String phoneNumber,
				String email, String siren){

			Session session = HibernateUtil.getSessionFactory().openSession();
			try{
				Transaction t = session.beginTransaction();
				estateAg = (EstateAgency) session.get(EstateAgency.class, id);
				
				estateAg.setLegalName(legalName);
				estateAg.setAddress(address);
				estateAg.setZipCode(zipCode);
				estateAg.setTown(town);
				estateAg.setPhoneNumber(phoneNumber);
				estateAg.setEmail(email);
				estateAg.setSiren(siren);

				session.update(estateAg);
				t.commit();
			}catch (HibernateException he){

			}finally{
				session.close();
			}
		}
		
		public EstateAgency find(int id){
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = null ;
			try{
				t = session.beginTransaction();
				estateAg = (EstateAgency) session.get(EstateAgency.class, id);
				t.commit();
			}catch(HibernateException he){

			}
			finally{	
				session.close();
			}
			return estateAg;
		}	
		
		public List listAll(){
			List<EstateAgency> list = new ArrayList<EstateAgency>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = null ;
			String hql = "FROM EstateAgency ORDER BY legalName";
			try{
				t = session.beginTransaction();
				Query query = session.createQuery(hql);
				list = query.list();
				t.commit();
			}catch(HibernateException he){

			}
			finally{	
				session.close();
			}
			return list;
		}
		
		public List<Property> listHandledProperties(EstateAgency ea){
			List<Property> list = new ArrayList<Property>();

			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = null ;
			try{
				t = session.beginTransaction();
				Query q = session.createQuery("FROM Property WHERE estateAgency = :ea");
				q.setParameter("ea", ea);
				list = q.list();
			}catch(HibernateException he){

			}
			finally{	
				session.close();
			}
			return list;
		}
}
