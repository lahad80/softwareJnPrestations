package com.jnPrestations.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageEstateAgency;

public class EstateAgencyInputFrame extends TemplateFrame{

	private JLabel titleLabel = new JLabel("Saisissez les données du nouveau syndic !");

	private JTextField legalNameField = new JTextField();
	private JTextField addressField = new JTextField();
	private JFormattedTextField zipCodeField = new JFormattedTextField();
	private JFormattedTextField townField = new JFormattedTextField();
	private JFormattedTextField phoneNumberField = new JFormattedTextField();
	private JFormattedTextField emailField = new JFormattedTextField();
	private JTextField sirenField = new JTextField();

	private JButton validateButton = new JButton("Valider", new ImageIcon("images\\validateImg.png"));

	private JFrame frame;
	private FactoryClass fc = new FactoryClass();

	public EstateAgencyInputFrame (){

		frame = this;
		pan.setLayout(null);

		titleLabel.setBounds(206, 50, 410, 22);
		pan.add(titleLabel);
		titleLabel.setForeground(Color.BLUE);
		Font police = new Font("Arial", Font.BOLD, 20);
		titleLabel.setFont(police);

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
		sirenField.setFont(police);
		sirenField.setBounds(367, 403, 265, 21);
		pan.add(sirenField);

		validateButton.setBounds(367, 465, 120, 30);
		validateButton.setVisible(true);;
		validateButton.addActionListener(new ListenValiteButton());
		pan.add(validateButton);

		this.setContentPane(pan);
		this.setVisible(true);	
	}

	class ListenValiteButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(isEmpty(legalNameField) || isEmpty(addressField) ){
				JOptionPane jop = null;
				jop.showMessageDialog(frame, "Les champs dénomination et adresse sont obligatoires !", "Ajout d'un client ", JOptionPane.ERROR_MESSAGE);

			}else{
				ManageEstateAgency mc = (ManageEstateAgency) fc.createClass("ManageEstateAgency");
				mc.add(legalNameField.getText(), addressField.getText(), zipCodeField.getText(),
						townField.getText(), phoneNumberField.getText(), emailField.getText(), sirenField.getText());
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(frame, "Client enregistré !", "Ajout d'un client !", JOptionPane.INFORMATION_MESSAGE);
			
				
				legalNameField.setText("");
				addressField.setText("");
				zipCodeField.setText("");
				townField.setText("");
				phoneNumberField.setText("");
				emailField.setText("");
				sirenField.setText("");
			}

		}

		public boolean isEmpty(JTextField tf){
			boolean retour = true;
			String contenu = tf.getText().trim();

			for(int i = 0; i < contenu.length(); i++){
				if(contenu.charAt(i) != ' '){
					retour = false;
				}
			}
			return retour;
		}
	}
}
