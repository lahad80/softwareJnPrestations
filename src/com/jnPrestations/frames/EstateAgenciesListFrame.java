package com.jnPrestations.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.SessionFactory;

import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageEstateAgency;
import com.jnPrestations.beans.EstateAgency;



public class EstateAgenciesListFrame extends TemplateFrame {

	private static SessionFactory sf;

	private List <EstateAgency> estateAgenciesArray = new ArrayList<EstateAgency>();
	private int position = 0;
	
	private JLabel titleLabel = new JLabel("Liste des syndics");

	private JTextField legalNameField = new JTextField();
	private JTextField addressField = new JTextField();
	private JFormattedTextField zipCodeField = new JFormattedTextField();
	private JFormattedTextField townField = new JFormattedTextField();
	private JFormattedTextField phoneNumberField = new JFormattedTextField();
	private JFormattedTextField emailField = new JFormattedTextField();
	private JTextField sirenField = new JTextField();

	private JButton modifyButton = new JButton("Modifier", new ImageIcon("images\\modifyImg.png")); 
	private JButton validateButton= new JButton("Valider", new ImageIcon("images\\validateImg.png"));
	private JButton deleteButton = new JButton("Supprimer", new ImageIcon("images\\deleteImg.png"));

	private JButton nextElementButton;
	private JButton previousElementButton;
	private JButton firstElementButton;
	private JButton lasteElementButton;
	
	private JFrame frame;
	private JOptionPane jop = new JOptionPane();
	private FactoryClass fc = new FactoryClass();


	public EstateAgenciesListFrame() {
		frame = this;
		//this.setTitle("Liste des clients !");
		pan.setLayout(null);

		Font police = new Font("Arial", Font.BOLD, 20);
		titleLabel.setFont(police);
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(250, 50, 400, 22);
		pan.add(titleLabel);
		
		
		police = new Font("Arial", Font.BOLD, 12);
		legalNameField.setFont(police);
		legalNameField.setBounds(184, 98, 265, 27);
		pan.add(legalNameField);

		JLabel legalNameLabel = new JLabel("Dénomination");
		legalNameLabel.setFont(police);
		legalNameLabel.setBounds(51, 104, 88, 14);
		pan.add(legalNameLabel);

		JLabel addressLabel = new JLabel("Adresse");
		addressLabel.setFont(police);
		addressLabel.setBounds(51, 156, 88, 14);
		pan.add(addressLabel);
		addressField.setFont(police);
		addressField.setBounds(184, 153, 265, 21);
		pan.add(addressField);

		JLabel zipCodeLabel = new JLabel("Code postal");
		zipCodeLabel.setFont(police);
		zipCodeLabel.setBounds(51, 213, 88, 14);
		pan.add(zipCodeLabel);
		zipCodeField.setFont(police);
		zipCodeField.setBounds(184, 199, 95, 20);
		pan.add(zipCodeField);

		JLabel townLabel = new JLabel("Ville");
		townLabel.setFont(police);
		townLabel.setBounds(51, 262, 88, 14);
		pan.add(townLabel);
		townField.setBounds(184, 256, 180, 20);
		townField.setFont(police);
		pan.add(townField);

		JLabel labTelephone = new JLabel("Téléphone");
		labTelephone.setFont(police);
		labTelephone.setBounds(51, 313, 88, 14);
		pan.add(labTelephone);
		phoneNumberField.setBounds(184, 307, 180, 20);
		phoneNumberField.setFont(police);
		pan.add(phoneNumberField);

		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setFont(police);
		emailLabel.setBounds(51, 360, 88, 14);
		pan.add(emailLabel);
		emailField.setBounds(184, 357, 265, 20);
		emailField.setFont(police);
		pan.add(emailField);

		JLabel sirenLabel = new JLabel("Siret");
		sirenLabel.setFont(police);
		sirenLabel.setBounds(51, 406, 88, 14);
		pan.add(sirenLabel);
		sirenField.setFont(police);
		sirenField.setBounds(184, 403, 265, 21);
		pan.add(sirenField);

		validateButton.setBounds(536, 98, 120, 30);
		validateButton.addActionListener(new ListenValidateButton());

		modifyButton.setBounds(536, 98, 120, 30);
		modifyButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				enableFields();
			}
		});
		deleteButton.setBounds(536, 150, 120, 30);
		deleteButton.addActionListener(new listenDeleteButton());

		pan.add(deleteButton);
		pan.add(validateButton);
		pan.add(modifyButton);

		ImageIcon nextElementImg = new ImageIcon("images/img_next.png");
		ImageIcon previousElementImg = new ImageIcon("images/img_previous.png");
		ImageIcon lastElementImg = new ImageIcon("images/img_last.png");
		ImageIcon firstElementImg = new ImageIcon("images/img_first.png");


		nextElementButton = new JButton(nextElementImg);
		nextElementButton.setBounds(343, 452, 34, 33);
		nextElementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (estateAgenciesArray.size() == 0 ){
					jop.showMessageDialog(frame, "La liste est vide !", "Liste des clients", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					if(position < estateAgenciesArray.size() - 1){
						position++;
						displayData(estateAgenciesArray.get(position));
					}
					else {
						JOptionPane.showMessageDialog(frame, "Dernier client !", "Client suivant", JOptionPane.ERROR_MESSAGE);
					}			
				}
			}
		});
		pan.add(nextElementButton);

		previousElementButton = new JButton(previousElementImg);
		previousElementButton.setBounds(250, 452, 34, 33);
		previousElementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (estateAgenciesArray.size() == 0 ){
					jop.showMessageDialog(frame, "La liste est vide !", "Liste des clients", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					if(position > 0){
						position--;
						displayData(estateAgenciesArray.get(position));
					}
					else {
						JOptionPane.showMessageDialog(frame, "Premier client !", "Client précédent", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		pan.add(previousElementButton);

		lasteElementButton = new JButton(lastElementImg);
		lasteElementButton.setBounds(413, 452, 34, 33);
		lasteElementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (estateAgenciesArray.size() == 0 ){
					jop.showMessageDialog(frame, "La liste est vide !", "Liste des clients", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					position = estateAgenciesArray.size() - 1;
					displayData(estateAgenciesArray.get(position));
				}
			}
		});
		pan.add(lasteElementButton);

		firstElementButton = new JButton(firstElementImg);
		firstElementButton.setBounds(184, 452, 34, 33);
		firstElementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (estateAgenciesArray.size() == 0 ){
					jop.showMessageDialog(frame, "La liste est vide !", "Liste des clients", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					position = 0;
					displayData(estateAgenciesArray.get(position));
				}
			}
		});
		pan.add(firstElementButton);

		this.setContentPane(pan);
		this.setVisible(true);
		disableFields();
		ManageEstateAgency mc = (ManageEstateAgency) fc.createClass("ManageEstateAgency");
		estateAgenciesArray =  mc.listAll();
		
		if(estateAgenciesArray.size() > 0){
			displayData(estateAgenciesArray.get(position));
		}
		else{
			deleteButton.setEnabled(false);
			modifyButton.setEnabled(false);
			jop.showMessageDialog(frame, "La liste est vide !", "Liste des clients", JOptionPane.INFORMATION_MESSAGE);
		}		
	}
	class listenDeleteButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int reponse = jop.showConfirmDialog(frame, "Voulez vous supprimer " + legalNameField.getText() + " ?",
					"Suppression d'un client", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(reponse == JOptionPane.OK_OPTION){
				ManageEstateAgency mc = (ManageEstateAgency) fc.createClass("ManageEstateAgency");
				mc.delete(estateAgenciesArray.get(position).getId());
				estateAgenciesArray.remove(position);
				// after removal, if the list is not empty
				if (estateAgenciesArray.size() >= 1){
					if(position != 0){
						position--;
						displayData(estateAgenciesArray.get(position));
					}
					else{
						displayData(estateAgenciesArray.get(position));
					}
				}
				else{
					emptyFields();
					modifyButton.setEnabled(false);
					deleteButton.setEnabled(false);
					jop.showMessageDialog(frame, "La liste est vide !", "Liste des clients", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	class ListenValidateButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// updating the database
			ManageEstateAgency mc = (ManageEstateAgency) fc.createClass("ManageEstateAgency");
			mc.update(estateAgenciesArray.get(position).getId(), legalNameField.getText(), addressField.getText(), zipCodeField.getText(),
					townField.getText(), phoneNumberField.getText(), emailField.getText(), sirenField.getText());

			// update estateAgenciesArray
			estateAgenciesArray.get(position).setLegalName(legalNameField.getText());
			estateAgenciesArray.get(position).setAddress(addressField.getText());
			estateAgenciesArray.get(position).setZipCode(zipCodeField.getText());
			estateAgenciesArray.get(position).setTown(townField.getText());
			estateAgenciesArray.get(position).setPhoneNumber(phoneNumberField.getText());
			estateAgenciesArray.get(position).setEmail(emailField.getText());
			estateAgenciesArray.get(position).setSiren(sirenField.getText());
			disableFields();
		}
	}

	public void displayData(EstateAgency estateAgency){
		legalNameField.setText(estateAgency.getLegalName());
		addressField.setText(estateAgency.getAddress());
		zipCodeField.setText(estateAgency.getZipCode());
		townField.setText(estateAgency.getTown());
		phoneNumberField.setText(estateAgency.getPhoneNumber());
		emailField.setText(estateAgency.getEmail());
		sirenField.setText(estateAgency.getSiren());
	}
	
	public void disableFields(){
		legalNameField.setEnabled(false);
		addressField.setEnabled(false);
		zipCodeField.setEnabled(false);
		townField.setEnabled(false);
		phoneNumberField.setEnabled(false);
		emailField.setEnabled(false);
		sirenField.setEnabled(false);

		validateButton.setVisible(false);
		modifyButton.setVisible(true);
		deleteButton.setVisible(true);
	}
	
	public void enableFields(){
		legalNameField.setEnabled(true);
		addressField.setEnabled(true);
		zipCodeField.setEnabled(true);
		townField.setEnabled(true);
		phoneNumberField.setEnabled(true);
		emailField.setEnabled(true);
		sirenField.setEnabled(true);

		validateButton.setVisible(true);
		modifyButton.setVisible(false);
		deleteButton.setVisible(false);
	}
	
	public void emptyFields(){
		legalNameField.setText("");
		addressField.setText("");
		zipCodeField.setText("");
		townField.setText("");
		phoneNumberField.setText("");
		emailField.setText("");
		sirenField.setText("");
	}
}
