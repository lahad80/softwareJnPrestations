package com.jnPrestations.miscellaneous;



import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.jnPrestations.factories.FactoryClass;
import com.jnPrestations.manages.ManageInvoice;





public class Delete_BoutonEditor extends DefaultCellEditor {

	protected JButton button;
	private DeleteButtonListener bListener = new DeleteButtonListener();
	private FactoryClass fc = new FactoryClass();


	public Delete_BoutonEditor (JCheckBox checkBox) {
		//By default, this type of object works with a JCheckBox
		super(checkBox);
		//create again the button
		button = new JButton();
		button.setOpaque(true);
		//attribute it a listener
		button.addActionListener(bListener);
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 
		//define le line number to our listener
		bListener.setRow(row);
		//On passe aussi en paramètre le tableau pour des actions potentielles
		//table as parameter pour potential actions
		bListener.setTable(table);
		//assign label to the button
		button.setText( (value ==null) ? "" : value.toString() );
		button.setBackground(Color.RED);
		//return the butotn
		return button;
	}

	class DeleteButtonListener implements ActionListener {


		private int row;
		private JTable table;

		public void setRow(int row){this.row = row;}
		public void setTable(JTable table){this.table = table;}

		public void actionPerformed(ActionEvent event) {

			if(this.table.getModel().getValueAt(this.row, 0) != null){
				int idToRemove = (int) this.table.getModel().getValueAt(this.row, 0);
				ManageInvoice mi=  (ManageInvoice) fc.createClass("ManageInvoice");
				mi.brandAsSettled(idToRemove);

				((MyTableModel)table.getModel()).removeRow(this.row);
			}
		}

	} 

}
