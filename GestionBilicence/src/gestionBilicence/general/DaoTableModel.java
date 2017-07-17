package gestionBilicence.general;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import gestionBilicence.general.dao.Dao;

public class DaoTableModel extends AbstractTableModel {
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
	  protected Dao<Entity> daoT;
	  
	  public DaoTableModel(Class[] listClass, String[]  title, Dao<Entity> daoT){
		  super();
		  this.data=daoT.getData();
		  this.listClass=listClass;
		  this.title=title;
		  this.daoT=daoT;
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
	  
	  public void removeRow(int position){
		  boolean test = daoT.delete(data.get(position));
		  if (test){
			  data.remove(position);
		  } else {
			  JOptionPane jop = new JOptionPane();
			  jop.showMessageDialog(null, "removeRow dans ZModel a échoué","ERREUR",JOptionPane.ERROR_MESSAGE);
		  }

		  // Pour avertir le tableau que les données ont changé.
		  this.fireTableDataChanged();
	  }
	  
	  // Take a non-initialized element
	  public void addRow(Entity obj){
		  boolean test = daoT.create(obj);
		  if (test){
			  	data.add(obj);
		  } else {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, "DaoTableModel.addRow failed","ERROR",JOptionPane.ERROR_MESSAGE);
		  }
		  // To notify the table of the change of data
		  this.fireTableDataChanged();
	  }
	  
	  // Create a new element
	  public void addRow(){
		  Entity obj = daoT.newElement();
		  if (obj == null){
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, "DaoTableModel.addRow failed","ERROR",JOptionPane.ERROR_MESSAGE);
		  } else {
			  	data.add(obj);
		  } 
		  // To notify the table of the change of data
		  this.fireTableDataChanged();
	  }
	  
	  // Default implementation, to be changed in different cases
	  public boolean isCellEditable(int row, int col){
		  return true;
	  }
	  
	  public Object getValueAt(int row, int column){
		  if (data.size()<row){
			  System.out.println("Error in DaoTableModel.getValueAt -- row > list.size()!");
			  return null;
		  } else {
			  return data.get(row).getEntry(column);
		  }
	  }

	  public void setValueAt(Object value, int row, int col){
		  if (data.size()<row){
			  System.out.println("Error in DaoTableModel.setValueAt -- row > list.size()!");
		  } else {
			  data.get(row).setEntry(col, value);
		  }
	  }
	  
	  public LinkedList<Entity> getData(){
		  return this.data;
	  }
	  
	  public Dao<Entity> getDaoT(){
		  return this.daoT;
	  }
	  
	  // mise à jour des données
	  // à partir de la base de données
	  public void updateData(){
		  this.data = daoT.getData();
		  this.fireTableDataChanged();
	  }
	  
	  // fonction utilisée pour les champs numériques comme "Add (€)"
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
	  
	  // fonction utilisée pour la date dans TransModel
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
}
