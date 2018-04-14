package com.jnPrestations.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageProperty;
import com.jnPrestations.miscellaneous.MyTableModel;
import com.jnPrestations.beans.Property;

public class PropertiesListFrame extends TemplateFrame{


	private List <Property> propertiesList = new LinkedList<Property>();

	private Object [][] data ;
	private String [] columnNames = {"ID", "ADRESSE", "TARIF DE BASE"};
	private JTable propertiesTable ;
	private MyTableModel myTableModel ;
	private JScrollPane scrollPane ;



	private JLabel titleLabel = new JLabel("Liste des résidences");
	
	// hidden on the interface
	private JTextField idField = new JTextField();

	private JLabel addressLabel = new JLabel("Adresse");
	private JLabel basicFeeLabel = new JLabel("Tarif de base");
	private JLabel serviceLabel = new JLabel("Prestation");

	private JTextField addressField = new JTextField();
	private JTextField serviceField = new JTextField();
	private JFormattedTextField basicFeeField ;

	private  NumberFormat basicFeeFormat ;
	private double basicFee;


	private JButton modifyButton = new JButton("Modifier", new ImageIcon("images\\modifyImg.png")); 
	private JButton validateButton= new JButton("Valider", new ImageIcon("images\\validateImg.png"));
	private JButton deleteButton = new JButton("Supprimer", new ImageIcon("images\\deleteImg.png"));


	private JFrame frame;
	private JOptionPane jop = new JOptionPane();

	private FactoryClass fc = new FactoryClass();
	private ManageProperty mp =  (ManageProperty) fc.createClass("ManageProperty");
	private Property currentProperty = (Property) fc.createClass("Property");

	public PropertiesListFrame(){
		frame = this;
		setUpFormats();
		pan.setLayout(null);

		Font police = new Font("Arial", Font.BOLD, 20);
		titleLabel.setFont(police);
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(250, 50, 400, 14);
		pan.add(titleLabel);
		
	    police = new Font("Arial", Font.BOLD, 12);

		//hidden on interface
		idField.setBounds(0, 0, 0, 0);
		pan.add(idField);

		addressLabel.setFont(police);
		addressLabel.setBounds(50, 115, 90, 15);
		pan.add(addressLabel);
		addressField.setFont(police);
		addressField.setBounds(120, 110, 250, 30);
		pan.add(addressField);


		serviceLabel.setFont(police);
		serviceLabel.setBounds(40, 165, 90, 15);
		pan.add(serviceLabel);
		serviceField.setFont(police);
		serviceField.setBounds(120, 160, 250, 30);
		pan.add(serviceField);

		basicFeeLabel.setFont(police);
		basicFeeLabel.setBounds(40, 215, 90, 15);
		pan.add(basicFeeLabel);
		basicFeeField = new JFormattedTextField(basicFeeFormat);
		basicFeeField.setBounds(120, 210, 80, 30);;
		basicFeeField.setFont(police);
		pan.add(basicFeeField);


		validateButton.setBounds(480, 110, 120, 30);
		validateButton.addActionListener(new ListenValidateButton());

		modifyButton.setBounds(480, 110, 120, 30);
		modifyButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dedisableFields();
			}
		});
		deleteButton.setBounds(480, 170, 120, 30);
		deleteButton.addActionListener(new ListenDeleteButon());

		pan.add(deleteButton);
		pan.add(validateButton);
		pan.add(modifyButton);

		this.setContentPane(pan);
		this.setVisible(true);

		loadData();
		
		populateTable();
		disableFields();

	}

	class Listen_table implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			currentProperty = mp.find((int) propertiesTable.getModel().getValueAt(propertiesTable.getSelectedRow(), 0));
			displayData(currentProperty);
		}
	}


	class ListenDeleteButon implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int reponse = jop.showConfirmDialog(frame, "Voulez vous supprimer " + addressField.getText() + " ?",
					"Suppression d'une résidence", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(reponse == JOptionPane.OK_OPTION){
				mp.delete(Integer.parseInt(idField.getText()));
				remove(scrollPane);
				loadData();
				populateTable();				
			}
		}
	}
	class ListenValidateButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			basicFee = ((Number)basicFeeField.getValue()).doubleValue();
			// update database
			ManageProperty mp = (ManageProperty) fc.createClass("ManageProperty");
			mp.update(addressField.getText(),serviceField.getText(), basicFee, Integer.parseInt(idField.getText()));

			remove(scrollPane);
			loadData();
			populateTable();
			disableFields();
		}
	}
	
	public void displayData(Property Property){
		idField.setText(  String.valueOf(Property.getId()) );
		addressField.setText(Property.getAddress());
		serviceField.setText(Property.getService());
		basicFeeField.setValue(Property.getBasicFee()) ;

	}
	
	public void disableFields(){
		addressField.setEnabled(false);
		serviceField.setEnabled(false);
		basicFeeField.setEnabled(false);

		validateButton.setVisible(false);
		modifyButton.setVisible(true);
		deleteButton.setVisible(true);
	}
	
	public void dedisableFields(){
		addressField.setEnabled(true);
		serviceField.setEnabled(true);
		basicFeeField.setEnabled(true);

		validateButton.setVisible(true);
		modifyButton.setVisible(false);
		deleteButton.setVisible(false);
	}
	
	public void emptyFields(){

		addressField.setText("");
		serviceField.setText("");
		basicFeeField.setText("");
	}
	public void setUpFormats(){
		basicFeeFormat = NumberFormat.getNumberInstance();

		basicFeeFormat.setMinimumFractionDigits(2);
		basicFeeFormat.setMaximumFractionDigits(2);
	}


	public void loadData(){
		propertiesList =  mp.listAll();

		int nbCols = 3;
		data = new Object[propertiesList.size()][nbCols];
		for(int l = 0; l < propertiesList.size(); l++){
			for(int c = 0; c < nbCols; c++){
				if(c == 0){
					data[l][c] = propertiesList.get(l).getId();
				}
				if(c == 1){
					data[l][c] = propertiesList.get(l).getAddress();
				}
				if(c == 2){
					data[l][c] = propertiesList.get(l).getBasicFee() + " €";
				}
			}
		}
	}

	public void populateTable(){
		if(data.length > 0){
		propertiesTable = new JTable(data.length, 2);
		myTableModel = new MyTableModel(data, columnNames);
		propertiesTable.setModel(myTableModel);
		propertiesTable.setSize(400, 300);
		propertiesTable.removeColumn(propertiesTable.getColumn("ID"));
		propertiesTable.getColumn("ADRESSE").setPreferredWidth(180);

		//just to put column 1 right in the center and a couple of others details
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		propertiesTable.getColumn("TARIF DE BASE").setCellRenderer(dtcrCenter);
		propertiesTable.getTableHeader().setForeground(Color.white);
		propertiesTable.getTableHeader().setBackground(Color.blue);

		Font enTete = new Font("Arial", Font.BOLD, 14);
		propertiesTable.getTableHeader().setFont(enTete);

		scrollPane = new JScrollPane(propertiesTable);
		scrollPane.setBounds(120, 320, 400, 150);
		pan.add(scrollPane);
		propertiesTable.setRowSelectionInterval(0, 0);
		currentProperty = mp.find((int) propertiesTable.getModel().getValueAt(propertiesTable.getSelectedRow(), 0));
		displayData(currentProperty);

		ListSelectionModel selectModele = propertiesTable.getSelectionModel();
		selectModele.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectModele.addListSelectionListener(new Listen_table());
		}
	}

}



