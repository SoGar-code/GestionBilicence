package gestionBilicence.general;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import gestionBilicence.general.observer.Observer;

public class ListTableModel extends AbstractTableModel implements Observer{
	  /*
	   * A superclass for the table models of the GestionBilicence project
	   * Includes methods for:
	   * _ adding and deleting data (in the table)
	   * _ converting String into Date and Float
	   * _ updating data
	   */
	protected LinkedList<Entity> data;


	protected Class[] listClass;
	protected String[] title;
	protected GeneralController gc = GeneralController.getInstance();

	public ListTableModel(Class[] listClass, String[]  title, LinkedList<Entity> data){
		super();
		this.data=data;
		this.listClass=listClass;
		this.title=title;
	}

	public String getColumnName(int col) {
		return this.title[col];
	}

	public int getColumnCount(){
		return this.title.length;
	}

	public int getRowCount(){
		return this.data.size();
	}

	public Class getColumnClass(int col){
		return listClass[col];
	}

	// Default implementation, to be changed in different cases
	public boolean isCellEditable(int row, int col){
		return true;
	}

	public Object getValueAt(int row, int column){
		return data.get(row).getEntry(column);
	}

	public void setValueAt(Object value, int row, int col){
		System.out.println("ListTableModel.setValueAt - col="+col+", value="+value.toString());
		data.get(row).setEntry(col, value);
	}

	public LinkedList<Entity> getData(){
		return this.data;
	}

	public void setData(LinkedList<Entity> data) {
		this.data = data;
	}

	// data update, provided by Observer pattern 
	//(data flowing from gc to the model)
	public void updateObserver(LinkedList<Entity> currentData){
		this.data = currentData;
		this.fireTableDataChanged();
	}

	/*
	  // fonction utilis�e pour les champs num�riques comme "Add (�)"
	  public float convertStringToFloat(Object value){
		  NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		  float val = 0.f;
		  try{
			  val = format.parse((String)value).floatValue();
		  } catch (Exception e){
			  e.printStackTrace();
			  JOptionPane jop = new JOptionPane();
			  jop.showMessageDialog(null, e.getMessage(),"ERREUR ds ZModel.convertStringToFloat",JOptionPane.ERROR_MESSAGE);
		  }
		  return val;
	  }

	  // fonction utilis�e pour la date dans TransModel
	  public static Date convertStringToDate(Object value){
		  Date date = new Date(0);
		  try{
			  date = Date.valueOf((String)value);
		  } catch (IllegalArgumentException e){
			  e.printStackTrace();
			  JOptionPane jop = new JOptionPane();
			  jop.showMessageDialog(null, "Date pas au format yyyy-mm-dd ? "+e.getMessage(),"ERREUR ds ZModel.convertStringToDate",JOptionPane.ERROR_MESSAGE);
		  } catch (Exception e){
			  e.printStackTrace();
			  JOptionPane jop = new JOptionPane();
			  jop.showMessageDialog(null, e.getMessage(),"ERREUR ds ZModel.convertStringToDate",JOptionPane.ERROR_MESSAGE);
		  }
		  return date; 
	  }
	 */
}
