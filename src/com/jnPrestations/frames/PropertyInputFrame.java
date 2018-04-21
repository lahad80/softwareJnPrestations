package com.jnPrestations.frames;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.SessionFactory;

import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageEstateAgency;
import com.jnPrestations.manages.ManageProperty;
import com.jnPrestations.beans.EstateAgency;


public class PropertyInputFrame extends TemplateFrame{

	private SessionFactory sf;

	private JLabel titleLabel = new JLabel("Entrez les données de la résidence");
	
	private JLabel theEstateAgeLabel = new JLabel("Choisir le syndic");
	private JComboBox<String> customersComboBox = new JComboBox();
	
	private JLabel addressLabel = new JLabel("Adresse de la résidence");
	private JTextField addressField = new JTextField();
	private JLabel serviceLabel = new JLabel("Prestation");
	private JTextField serviceField = new JTextField();
	private JLabel basicFeeLabel = new JLabel("Tarif de base");
	private JFormattedTextField basicFeeField;
	
	private NumberFormat basicFeeFormat;
	private double basicFee;

	private JButton validateButton = new JButton("Valider", new ImageIcon("images\\validateImg.png"));
	private JFrame frame;
	private JOptionPane jop = new JOptionPane();

	private List <EstateAgency> estaAgArray = new LinkedList<EstateAgency>();
	
	private FactoryClass fc = new FactoryClass();
	private ManageEstateAgency mea = (ManageEstateAgency) fc.createClass("ManageEstateAgency");
	private ManageProperty mp = (ManageProperty) fc.createClass("ManageProperty");

	public PropertyInputFrame(){
		frame = this;
		setUpFormats();
		pan.setLayout(null);

		Font police = new Font("Arial", Font.BOLD, 20);
		titleLabel.setFont(police);
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(250, 70, 400, 14);
		pan.add(titleLabel);
		
		theEstateAgeLabel.setBounds(150, 140, 200, 20);
		pan.add(theEstateAgeLabel);
		customersComboBox.setBounds(300, 140, 200, 20);
		pan.add(customersComboBox);
		addressLabel.setBounds(150, 220, 200, 20);
		pan.add(addressLabel);
		addressField.setBounds(300, 220, 200, 25);
		pan.add(addressField);

		serviceLabel.setBounds(150, 270, 200, 20);
		pan.add(serviceLabel);
		serviceField.setBounds(300, 270, 200, 25);
		pan.add(serviceField);
		
		basicFeeLabel.setBounds(150, 320, 200, 20);
		pan.add(basicFeeLabel);
		basicFeeField = new JFormattedTextField(basicFeeFormat);
		basicFeeField.setBounds(300, 320, 200, 25);
		pan.add(basicFeeField);

		validateButton.setBounds(300, 420, 120, 30);
		validateButton.addActionListener(new listenValidate());
		pan.add(validateButton);

		this.setContentPane(pan);
		this.setVisible(true);

		ManageEstateAgency mea = (ManageEstateAgency) fc.createClass("ManageEstateAgency");
		List<EstateAgency> estateAgList = mea.listAll();
		Iterator<EstateAgency> it = estateAgList.iterator();		
		while (it.hasNext()){
			estaAgArray.add((EstateAgency) it.next());
		}
		for(int i = 0; i < estaAgArray.size(); i++){
			customersComboBox.addItem(estaAgArray.get(i).getLegalName());
		}
		customersComboBox.setSelectedItem(null);
	}
	
	class listenValidate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(customersComboBox.getSelectedIndex() != -1){ 
				if (isEmpty(addressField) ||isEmpty(serviceField) || isEmpty(basicFeeField)){
					jop.showMessageDialog(frame, "Tous les champs sont obligatoires !", "Ajout d'une résidence ", JOptionPane.ERROR_MESSAGE);
				}
				else{
						EstateAgency selectedEstateAg = (EstateAgency) fc.createClass("EstateAgency");
						selectedEstateAg = mea.find(estaAgArray.get(customersComboBox.getSelectedIndex()).getId());

						basicFee = ((Number) basicFeeField.getValue()).doubleValue();
						mp.add(addressField.getText(), serviceField.getText(),basicFee, selectedEstateAg);

						emptyFields();
						jop.showMessageDialog(frame, "Résidence  ajoutée !", "Ajout d'une résidence", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else{
				jop.showMessageDialog(frame, "Il faut choisir un client dans la liste !", "Ajout d'une résidence ", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void emptyFields(){
		customersComboBox.setSelectedItem(null);
		addressField.setText("");
		serviceField.setText("");
		basicFeeField.setText("");
	}
	public boolean isEmpty(JTextField tf){
		boolean returnedValue = true;
		String content = tf.getText().trim();
		
		for(int i = 0; i < content.length(); i++){
			if(content.charAt(i) != ' '){
				returnedValue = false;
			}
		}
		return returnedValue;
	}
	
	
	private void setUpFormats(){
		basicFeeFormat = NumberFormat.getNumberInstance();

		basicFeeFormat.setMaximumFractionDigits(2);
		basicFeeFormat.setMinimumFractionDigits(2);
	}
}
