package com.jnPrestations.miscellaneous;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageInvoice;




public class DeleteBoutonEditor extends DefaultCellEditor {

	protected JButton button;
	private DeleteButtonListener bListener = new DeleteButtonListener();
	private FactoryClass fc = new FactoryClass();


	public DeleteBoutonEditor (JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(bListener);
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 
		bListener.setRow(row);
		bListener.setTable(table);
		button.setText( (value ==null) ? "" : value.toString() );
		return button;
	}

	class DeleteButtonListener implements ActionListener {
		
		
		private int row;
		private JTable table;
		
		public void setRow(int row){this.row = row;}
		public void setTable(JTable table){this.table = table;}

		public void actionPerformed(ActionEvent event) {
		
			int idToRemove = (int) this.table.getModel().getValueAt(this.row, 0);
		    ManageInvoice mFacture=  (ManageInvoice) fc.createClass("ManageFacture");
		    mFacture.brandAsSettled(idToRemove);
		   
		    ((MyTableModel)table.getModel()).removeRow(this.row);
		}
		
	} 

}
