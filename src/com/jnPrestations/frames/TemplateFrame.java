package com.jnPrestations.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.main.Main;


public class TemplateFrame extends JFrame {

	protected Font police = new Font("Arial", Font.BOLD, 20);
	protected JPanel pan = new JPanel();
	protected FactoryClass fc = new FactoryClass();
	protected JFrame frame;
	

	public TemplateFrame(){
		
		frame = this;
		this.setSize(750, 600);
		this.setLocation(Main.point);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		
		this.addWindowListener(new Listen_closing());
		
		pan.setLayout(null);
		pan.setBackground(Color.white);

		// add a menu
		JMenuBar menuBar = new JMenuBar();
		
		
		
		JMenu jnPrestations = new JMenu("Jn Prestations");
		jnPrestations.setIcon(new ImageIcon("images\\jnpres.png"));
		addMenuItem(jnPrestations, "Accueil", "LandingFrame");
		addMenuItem(jnPrestations, "Informations", "InformationsFrame");
		menuBar.add(jnPrestations);
		
		JMenu estateAgences = new JMenu("Syndics");
		estateAgences.setIcon(new ImageIcon("images\\customer.png"));
		addMenuItem(estateAgences, "Nouveau syndic", "EstateAgencyInputFrame");
		addMenuItem(estateAgences, "Liste des syndics", "EstateAgenciesListFrame");
		menuBar.add(estateAgences);
		
		JMenu residencies = new JMenu("Résidences");
		residencies.setIcon(new ImageIcon("images\\building.png"));
		addMenuItem(residencies, "Nouvelle résidence", "PropertyInputFrame");
		addMenuItem(residencies, "Liste des résidences", "PropertiesListFrame");
		menuBar.add(residencies);

		JMenu invoices = new JMenu("Factures");
		invoices.setIcon(new ImageIcon("images\\calculator.png"));
		addMenuItem(invoices, "Etablir une facture", "InvoiceInputFrame");
		addMenuItem(invoices, "Factures impayées"  , "outstandingInvoicesFrame");
		menuBar.add(invoices);
		
		this.setJMenuBar(menuBar);

		this.setContentPane(pan);
		this.setVisible(true);

	}
 
	// this class asks you to confirm when you try to close the software
	class Listen_closing implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			String [] choice = {"Fermer", "Ne pas fermer"};
			JOptionPane jop = new JOptionPane();
			int response = jop.showOptionDialog(frame, "Voulez vous vraiment fermer le logiciel ?", "Fermeture !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, choice, choice[1]);
			if(response == JOptionPane.YES_NO_OPTION){
				System.exit(0);
			}
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
	
	private void addMenuItem(JMenu nomMenu, String nomMenuItem, String frameToDisplay){
		JMenuItem jmenuItem = new JMenuItem(nomMenuItem);
		jmenuItem.setBackground(new Color(124, 67, 30));
		jmenuItem.setForeground(Color.WHITE);
		jmenuItem.setFont(new Font("Arial", Font.BOLD, 14));
		jmenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Main.point = getLocation();
				fc.createClass(frameToDisplay);
				dispose();
			}
		});
		nomMenu.setFont(police);
		nomMenu.setForeground(new Color(124, 67, 30));
		nomMenu.add(jmenuItem);
		
	}


}
