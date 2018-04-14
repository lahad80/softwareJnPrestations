package com.jnPrestations.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageMyBusiness;
import com.jnPrestations.beans.MyBusiness;


public class InformationsFrame extends TemplateFrame {

	private JLabel titleLabel = new JLabel("Données de JN PRESTATIONS ");
	
	private JTextField legalNameField = new JTextField();
	private JTextField addressField = new JTextField();
	private JFormattedTextField zipCodeField = new JFormattedTextField();
	private JFormattedTextField townField = new JFormattedTextField();
	private JFormattedTextField phoneNumberField = new JFormattedTextField();
	private JFormattedTextField emailField = new JFormattedTextField();
	private JTextField serenField = new JTextField();

	private JButton validateBtn = new JButton("Valider", new ImageIcon("images\\validateImg.png"));
	private JButton modifyBtn = new JButton("Modifier", new ImageIcon("images\\modifyImg.png"));
	
	private FactoryClass fc = new FactoryClass();

	
	public InformationsFrame(){

		//this.setTitle("Informations sur JN PRESTATIONS !");
		pan.setLayout(null);

		// labels and fields
		Font police = new Font("Arial", Font.BOLD, 19);
		titleLabel.setFont(police);
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(250, 50, 400, 14);
		pan.add(titleLabel);
		
		police = new Font("Arial", Font.BOLD, 12);
		legalNameField.setFont(police);
		legalNameField.setBounds(367, 98, 265, 27);
		pan.add(legalNameField);

		JLabel legalNameLabel = new JLabel("Dénomination");
		legalNameLabel.setFont(police);
		legalNameLabel.setBounds(206, 108, 88, 14);
		pan.add(legalNameLabel);
		

		JLabel addressLabel = new JLabel("Adresse");
		addressLabel.setFont(police);
		addressLabel.setBounds(206, 156, 88, 14);
		pan.add(addressLabel);
		addressField.setFont(police);
		addressField.setBounds(367, 153, 265, 21);
		pan.add(addressField);

		JLabel zipCodeLabel = new JLabel("Code postal");
		zipCodeLabel.setFont(police);
		zipCodeLabel.setBounds(206, 212, 88, 14);
		pan.add(zipCodeLabel);
		zipCodeField.setFont(police);
		zipCodeField.setBounds(367, 210, 95, 20);
		pan.add(zipCodeField);

		JLabel townLabel = new JLabel("Ville");
		townLabel.setFont(police);
		townLabel.setBounds(206, 261, 88, 14);
		pan.add(townLabel);
		townField.setBounds(367, 259, 180, 20);
		townField.setFont(police);
		pan.add(townField);

		JLabel phoneNumberLabel = new JLabel("Téléphone");
		phoneNumberLabel.setFont(police);
		phoneNumberLabel.setBounds(206, 312, 88, 14);
		pan.add(phoneNumberLabel);
		phoneNumberField.setBounds(367, 310, 180, 20);
		phoneNumberField.setFont(police);
		pan.add(phoneNumberField);

		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setFont(police);
		emailLabel.setBounds(206, 359, 88, 14);
		pan.add(emailLabel);
		emailField.setBounds(367, 357, 265, 20);
		emailField.setFont(police);
		pan.add(emailField);

		JLabel sirenLabel = new JLabel("Siret");
		sirenLabel.setFont(police);
		sirenLabel.setBounds(206, 406, 88, 14);
		pan.add(sirenLabel);
		serenField.setFont(police);
		serenField.setBounds(367, 403, 265, 21);
		pan.add(serenField);


		validateBtn.setBounds(514, 465, 120, 30);
		validateBtn.setVisible(false);;
		validateBtn.addActionListener(new ListenValidateBtn());
		pan.add(validateBtn);
		modifyBtn.setBounds(367, 465, 120, 30);
		modifyBtn.addActionListener(new ListenModifyBtn());
		pan.add(modifyBtn);


		this.disableFields();	
		this.setContentPane(pan);
		this.setVisible(true);
		this.loadAndDisplay();
	}

	public void disableFields(){
		legalNameField.setEnabled(false);
		addressField.setEnabled(false);
		zipCodeField.setEnabled(false);
		townField.setEnabled(false);
		phoneNumberField.setEnabled(false);
		emailField.setEnabled(false);
		serenField.setEnabled(false);
	}
	public void enableFields(){
		legalNameField.setEnabled(true);
		addressField.setEnabled(true);
		zipCodeField.setEnabled(true);
		townField.setEnabled(true);
		phoneNumberField.setEnabled(true);
		emailField.setEnabled(true);
		serenField.setEnabled(true);
	}
	// displays data from database
	public void loadAndDisplay(){
		ManageMyBusiness mmb = (ManageMyBusiness) fc.createClass("ManageMyBusiness");
		MyBusiness me =  mmb.find();
		legalNameField.setText(me.getLegalName());
		addressField.setText(me.getAddress());
		zipCodeField.setText(me.getZipCode());
		townField.setText( me.getTown());
		phoneNumberField.setText(me.getPhoneNumber());
		emailField.setText(me.getEmail());
		serenField.setText(me.getSiren());					
	}

	class ListenValidateBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			modifyBtn.setVisible(true);
			validateBtn.setVisible(false);
			disableFields();
			ManageMyBusiness mmb = (ManageMyBusiness) fc.createClass("ManageMyBusiness");
			mmb.update(legalNameField.getText(), addressField.getText(), zipCodeField.getText(),
					townField.getText(), phoneNumberField.getText(), emailField.getText(), serenField.getText());
		}
	}
	class ListenModifyBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			validateBtn.setVisible(true);
			modifyBtn.setVisible(false);
			enableFields();
		}
	}
}


