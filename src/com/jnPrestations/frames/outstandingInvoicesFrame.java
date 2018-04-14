package com.jnPrestations.frames;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jnPrestations.miscellaneous.Bouton_Renderer;
import com.jnPrestations.miscellaneous.Delete_BoutonEditor;
import com.jnPrestations.miscellaneous.MyTableModel;
import com.jnPrestations.beans.Invoice;
import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageInvoice;




public class outstandingInvoicesFrame extends TemplateFrame{

	
	private JLabel titleLabel = new JLabel("Les factures impayées");
	
	private JTable table;
	private MyTableModel model;
	private String [] title = {"ID", "N° fact", "Période", "Résidence", "Client", ""};
	private Object [][] data;
	private JScrollPane scrollpane;
	private Color color = new Color(229, 152, 102);
	private FactoryClass fc = new FactoryClass();
	private ManageInvoice mi = (ManageInvoice) fc.createClass("ManageInvoice");

	public outstandingInvoicesFrame(){
		//this.setTitle("Factures impayées");
		pan.setLayout(null);
		
		
		Font police = new Font("Arial", Font.BOLD, 20);
		titleLabel.setFont(police);
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setBounds(250, 90, 400, 25);
		pan.add(titleLabel);

		populateTable();
		
		model = new MyTableModel(data, title);
		table = new JTable(model);
		
		table.getColumn("N° fact").setPreferredWidth(30);
		table.getColumn("Période").setPreferredWidth(80);
		table.getColumn("Résidence").setPreferredWidth(200);
		table.getColumn("Client").setPreferredWidth(150);
		table.getColumn("").setPreferredWidth(60);
		Font headerFont = new Font("Arial", Font.BOLD, 14);
		table.getTableHeader().setFont(headerFont);
		table.getTableHeader().setBackground(Color.BLUE);
		table.getTableHeader().setForeground(Color.WHITE);
		

		DefaultTableCellRenderer dtcr_center = new DefaultTableCellRenderer();
		dtcr_center.setHorizontalAlignment(JLabel.CENTER);
		for(int i = 0; i < 6; i++){
			table.getColumn(title[i]).setCellRenderer(dtcr_center);
		}
		DefaultTableCellRenderer dtcr_orange = new DefaultTableCellRenderer();
		dtcr_orange.setBackground(color);
		dtcr_orange.setForeground(Color.WHITE);
		dtcr_orange.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("N° fact").setCellRenderer(dtcr_orange);
		table.getColumn("Résidence").setCellRenderer(dtcr_orange);

		
		table.getColumn("").setCellRenderer(new Bouton_Renderer());
		table.getColumn("").setCellEditor(new Delete_BoutonEditor(new JCheckBox() ));

		
		table.removeColumn(table.getColumn("ID"));

		
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(50, 180, 650, 150);
		pan.add(scrollpane);
		

		this.setContentPane(pan);
		this.setVisible(true);
		
	
	}

	public void populateTable (){
		List <Invoice>outstandingInvoicesList  = mi.pickUnsettledOnes();
			data = new Object[outstandingInvoicesList.size()][6];
			for (int i = 0; i< outstandingInvoicesList.size(); i++){
				data[i][0] = outstandingInvoicesList.get(i).getId();
				data[i][1] = outstandingInvoicesList.get(i).getInvNumber();
				data[i][2] = outstandingInvoicesList.get(i).getPeriod();	  
				data[i][3] = outstandingInvoicesList.get(i).getProperty().getAddress();
				data[i][4] = outstandingInvoicesList.get(i).getProperty().getEstateAgency().getLegalName();
				data[i][5] = "Retirer";
			}
	}
}
