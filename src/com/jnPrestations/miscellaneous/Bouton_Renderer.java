package com.jnPrestations.miscellaneous;



import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Bouton_Renderer extends JButton implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int p_row,
			int p_col) {
		// TODO Auto-generated method stub
		this.setText(value == null ? "": value.toString());
		return this;
	}

}
