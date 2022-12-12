package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.University;

public class UniversityTableModel  extends AbstractTableModel {
	
	private String  [] columnNames =  {"Name", "Cost of Attendance", "Book Supply Fees"};
	private List<University> data;
	
	
	
	public UniversityTableModel(List<University> data) {
		super();
		this.data = data;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		 int size;
	      if (data == null) {
	         size = 0;
	      }
	      else {
	         size = data.size();
	      }
	      return size;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	 // needed to show column names in JTable
	   public String getColumnName(int col) {
	      return columnNames[col];
	   }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object temp = null;
		if(data.isEmpty()) {
			return temp;
		}
		University uni = data.get(rowIndex);
	      if (columnIndex == 0) {
	         temp = uni.getName();
	      }
	      else if (columnIndex == 1) {
	         temp = uni.getOverallAvg();
	      }
	      else if (columnIndex == 2) {
		         temp = uni.getBooksupply();
		  }
	   
	      return temp;
	}
	
    public Class getColumnClass(int c) {
    	
        return getValueAt(0, c) != null  ? getValueAt(0, c).getClass() : Object.class;
    }
    
    

	public List<University> getData() {
		return data;
	}

	public void setData(List<University> list) {
		this.data = list;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	
	
	
	

}
