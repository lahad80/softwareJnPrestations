package com.jnPrestations.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jnPrestations.connections.HibernateUtil;
import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.main.Main;
import com.jnPrestations.manages.ManageEstateAgency;
import com.jnPrestations.manages.ManageMyBusiness;
import com.jnPrestations.manages.ManageProperty;
import com.jnPrestations.miscellaneous.MyTableModel;
import com.jnPrestations.beans.EstateAgency;
import com.jnPrestations.beans.MyBusiness;
import com.jnPrestations.beans.Property;

public class InvoiceInputFrame extends TemplateFrame {

	private boolean fistTimeOpening = true;
	
	private JLabel titleLabel = new JLabel("Saisir une facture");
	
	private JLabel theEstateAgLabel = new JLabel("Choisir le syndic à facturer :");
	private JLabel thePropertyLabel = new JLabel("Choisir la résidence concernée :");

	private JComboBox<String> customerComboBox = new JComboBox<String>();


	private List<EstateAgency> estateAgList = new LinkedList<EstateAgency>();


	private Object [][] data ;
	private String [] columnNames = {"ID", "ADRESSE", "TARIF DE BASE"};

	private JTable propertiesTable ;
	private MyTableModel myTableModel ;

	private JScrollPane scrollPane ;
	
	private MyBusiness myBussiness;
	private Property relevantProperty;
	private FactoryClass fc = new FactoryClass();
	private ManageEstateAgency mea = (ManageEstateAgency) fc.createClass("ManageEstateAgency");
	private EstateAgency billedEstateAg = (EstateAgency) fc.createClass("EstateAgency");

	public InvoiceInputFrame(){
		pan.setLayout(null);
		
		Font police = new Font("Arial", Font.BOLD, 20);
		titleLabel.setFont(police);
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(250, 60, 400, 14);
		pan.add(titleLabel);

		theEstateAgLabel.setBounds(125, 140, 165, 20);
		pan.add(theEstateAgLabel);
		customerComboBox.setBounds(330, 140, 200, 20);
		customerComboBox.addActionListener(new listenComboBox());
		pan.add(customerComboBox);


		fillComboBox();
		fistTimeOpening = false;

		thePropertyLabel.setBounds(125, 220, 185, 20);
		pan.add(thePropertyLabel);

		
		this.setContentPane(pan);
		this.setVisible(true);


	}
	public void fillComboBox(){
		estateAgList = mea.listAll();
		for(int i = 0; i < estateAgList.size(); i++ ){
			customerComboBox.addItem(estateAgList.get(i).getLegalName());
		}
	}
	
	class listenComboBox implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			loadProperties();
			if(fistTimeOpening == false){
				remove(scrollPane);
			}
			propertiesTable = new JTable(data.length, 2);
			myTableModel = new MyTableModel(data, columnNames);
			propertiesTable.setModel(myTableModel);
			propertiesTable.setSize(400, 300);
			propertiesTable.removeColumn(propertiesTable.getColumn("ID"));
			propertiesTable.getColumn("ADRESSE").setPreferredWidth(180);
			
			DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
			dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
			propertiesTable.getColumn("TARIF DE BASE").setCellRenderer(dtcrCenter);
			propertiesTable.getTableHeader().setForeground(Color.white);
			propertiesTable.getTableHeader().setBackground(Color.blue);
			
			Font headerFont = new Font("Arial", Font.BOLD, 14);
			propertiesTable.getTableHeader().setFont(headerFont);
			
			scrollPane = new JScrollPane(propertiesTable);
			scrollPane.setBounds(125, 250, 400, 150);
			pan.add(scrollPane);
			
			ListSelectionModel selectModele = propertiesTable.getSelectionModel();
			selectModele.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			selectModele.addListSelectionListener(new Ecoute_table());

		}
	}
	public void loadProperties(){
	
		billedEstateAg = mea.find(estateAgList.get(customerComboBox.getSelectedIndex()).getId());
		List<Property> propertiesList = mea.listHandledProperties(billedEstateAg);
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
	class Ecoute_table implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			
			// TODO Auto-generated method stub
		
			ManageMyBusiness mmb = (ManageMyBusiness) fc.createClass("ManageMyBusiness");
			myBussiness = mmb.find();
			ManageProperty mr = (ManageProperty) fc.createClass("ManageProperty");
			relevantProperty = mr.find((int) propertiesTable.getModel().getValueAt(propertiesTable.getSelectedRow(), 0));
		 	Main.point = getLocation();
			InvoiceViewingFrame ivf = new InvoiceViewingFrame(myBussiness, billedEstateAg, relevantProperty);
			dispose();
			
		}		
	}		
}
