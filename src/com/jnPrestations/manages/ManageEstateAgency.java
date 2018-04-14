package com.jnPrestations.manages;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jnPrestations.beans.EstateAgency;
import com.jnPrestations.beans.Property;
import com.jnPrestations.connections.HibernateUtil;
import com.jnPrestations.factories.FactoryClass;

public class ManageEstateAgency {

	private FactoryClass fc = new FactoryClass();
	private EstateAgency estateAg = (EstateAgency)fc.createClass("EstateAgency");

	private static SessionFactory sf;

	public ManageEstateAgency (){}

	
	public void add(String legalName, String address, String zipCode, String town, String phoneNumber,
			String email, String siren){

		sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		try{
			Transaction t = s.beginTransaction();
			estateAg.setLegalName(legalName);
			estateAg.setAddress(address);
			estateAg.setZipCode(zipCode);
			estateAg.setTown(town);
			estateAg.setPhoneNumber(phoneNumber);
			estateAg.setEmail(email);
			estateAg.setSiren(siren);

			s.save(estateAg);
			t.commit();
		}catch (HibernateException he){

		}finally{
			s.close();
			sf.close();
		}
	}
	public void delete(int id){

		sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		try{
			Transaction t = s.beginTransaction();
			
			estateAg = (EstateAgency)s.get(EstateAgency.class, id);
			s.delete(estateAg);
			t.commit();
		}catch (HibernateException he){

		}finally{
			s.close();
			sf.close();
		}
	}
		public void update(int id, String legalName, String address, String zipCode, String town, String phoneNumber,
				String email, String siren){

			sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			try{
				Transaction t = s.beginTransaction();
				estateAg = (EstateAgency) s.get(EstateAgency.class, id);
				
				estateAg.setLegalName(legalName);
				estateAg.setAddress(address);
				estateAg.setZipCode(zipCode);
				estateAg.setTown(town);
				estateAg.setPhoneNumber(phoneNumber);
				estateAg.setEmail(email);
				estateAg.setSiren(siren);

				s.update(estateAg);
				t.commit();
			}catch (HibernateException he){

			}finally{
				s.close();
				sf.close();
			}
		}
		
		public EstateAgency find(int id){
			Configuration conf = new Configuration();
			sf = conf.configure().buildSessionFactory();
			Session s = sf.openSession();
			Transaction t = null ;
			try{
				t = s.beginTransaction();
				estateAg = (EstateAgency) s.get(EstateAgency.class, id);
				t.commit();
			}catch(HibernateException he){

			}
			finally{	
				s.close();
				sf.close();
			}
			return estateAg;
		}	
		
		public List listAll(){
			List<EstateAgency> list = new ArrayList<EstateAgency>();
			Configuration conf = new Configuration();
			sf = conf.configure().buildSessionFactory();
			Session s = sf.openSession();
			Transaction t = null ;
			String hql = "FROM EstateAgency ORDER BY legalName";
			try{
				t = s.beginTransaction();
				Query query = s.createQuery(hql);
				list = query.list();
				t.commit();
			}catch(HibernateException he){

			}
			finally{	
				s.close();
				sf.close();
			}
			return list;
		}
		
		public List<Property> listHandledProperties(EstateAgency ea){
			List<Property> list = new ArrayList<Property>();
			Configuration conf = new Configuration();
			sf = conf.configure().buildSessionFactory();
			Session s = sf.openSession();
			Transaction t = null ;
			try{
				t = s.beginTransaction();
				Query q = s.createQuery("FROM Property WHERE estateAgency = :ea");
				q.setParameter("ea", ea);
				list = q.list();
			}catch(HibernateException he){

			}
			finally{	
				s.close();
				sf.close();
			}
			return list;
		}
}
