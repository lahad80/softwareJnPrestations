package com.jnPrestations.frames;



import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;


import com.jnPrestations.manages.ManageMyBusiness;

public class LandingFrame extends TemplateFrame{
	
	private JTextArea ta = new JTextArea();
	
	private ManageMyBusiness mmb = new ManageMyBusiness();
	
	public  LandingFrame() {
		
		pan.setLayout(null);	
		police = new Font("Arial", Font.BOLD, 14);
		ta.setFont(police);
		ta.setForeground(Color.BLUE);
		String presentation  = "\n Logiciel de gestion pour JN PRESTATIONS,\n\n crée par Mr DIAW Lahad\n dans le cadre "+
		"de son DUT Informatique.\n\n\n Technologies utilisées:\n\n \t- Java\n \t- Hibernate\n \t- Mysql\n \t- Junit\n \t- Itext\n \t- Eclipse\n \t- Git";
		ta.setText(presentation);
		ta.setBounds(230, 140, 330, 295);
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
