package com.jnPrestations.miscellaneous;

import javax.swing.table.AbstractTableModel;

public class MyTableModel  extends AbstractTableModel{

	private Object[][] data;
	private String[] columnNames;

	public  MyTableModel(Object [][] data, String [] columnNames) {
		this.data = data;
		this.columnNames = columnNames;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return data[row][col];
	}
	public String getColumnName(int col) {
		return columnNames[col];
	}
	public Class getColumnClass(int col){

		return data[0] [col].getClass();
	}
	
	public boolean isCellEditable(int p_row, int p_col){
		if(this.getColumnName(p_col).equals("")){
			return true;
		}
		
		return false;
	}
	
	public void setValueAt(Object value, int row, int col) {
		//On interdit la modification sur certaines colonnes (les boutons par exemple)
				if(!this.getColumnName(col).equals(""))
					this.data[row][col] = value;
	}
	
	public void removeRow(int position){
		int indice = 0, indice2 = 0, nbRows = this.getRowCount(), nbCols = this.getColumnCount();
		Object [][] temp = new Object[nbRows][nbCols];
		for(Object[] value: this.data){
			if(indice != position){
				temp[indice2++] = value;
			}
			indice++;
		}
		this.data = temp;
		temp = null;
		this.fireTableDataChanged();
		
		
	}
}
