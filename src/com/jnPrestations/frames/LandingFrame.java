package com.jnPrestations.frames;



import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jnPrestations.connections.HibernateUtil;
import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageMyBusiness;

public class LandingFrame extends TemplateFrame{
	
	private JTextArea ta = new JTextArea();
	
	private FactoryClass fc = new FactoryClass();
	private ManageMyBusiness mmb = (ManageMyBusiness) fc.createClass("ManageMyBusiness");
	
	public  LandingFrame() {
		
		pan.setLayout(null);	
		police = new Font("Arial", Font.BOLD, 14);
		ta.setFont(police);
		ta.setForeground(Color.BLUE);
		String presentation  = "\n Logiciel de gestion pour JN PRESTATIONS,\n\n crée par Mr DIAW Abdou\n dans le cadre "+
		"de son DUT Informatique.\n\n\n Technologies utilisées:\n\n \t- Java, XML\n \t- Mysql\n \t- Hibernate\n \t- Eclipse\n \t- Itext\n \t- Git";
		ta.setText(presentation);
		ta.setBounds(230, 140, 330, 290);
		ta.setEditable(false);
			
		TitledBorder title = BorderFactory.createTitledBorder("A propos");
		title.setTitleColor(new Color(124, 67, 30));
		ta.setBorder(title);
		pan.add(ta);
	
		this.setContentPane(pan);
		this.setVisible(true);
		mmb.logIn();
	}
}
