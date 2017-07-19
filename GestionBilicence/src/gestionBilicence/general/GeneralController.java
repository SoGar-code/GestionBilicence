package gestionBilicence.general;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gestionBilicence.edition.Exams;
import gestionBilicence.edition.Semester;
import gestionBilicence.edition.Student;
import gestionBilicence.general.dao.AbstractDaoFactory;
import gestionBilicence.general.dao.Dao;
import gestionBilicence.general.observer.Observable;
import gestionBilicence.general.observer.Observer;

public class GeneralController implements Observable, ChangeListener{
	/*
	 * A class used as local storage of data (as opposed to distant DB).
	 * Doubles as a singleton class giving access to the different Dao classes.
	 */
	
	private static GeneralController gc = new GeneralController();
	// Implicitly, df encodes which type of Database we are using in this instance.
	private static AbstractDaoFactory df;
	private LinkedList<Entity> currentData = new LinkedList<Entity>();
	private int currentEntity=0;
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	private GeneralController(){
		df = AbstractDaoFactory.getFactory();
	}
	
	public static GeneralController getInstance(){
		return gc;
	}

	public void updateData(){
		this.currentData = this.getDao(currentEntity).getData();
		System.out.println("GC.updateData()-- current elements:======");
		for (Entity ent:currentData){
			System.out.println(ent.toString());			
		}
	}

	public LinkedList<Entity> getCurrentData() {
		return currentData;
	}

	public void removeRow(int position){
		boolean test = this.getDao(currentEntity).delete(currentData.get(position));
		if (test){
			currentData.remove(position);
			// To notify the table of the change of data
			this.updateObservable(currentData);
		} else {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "GeneralController.removeRow","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	// Take a non-initialized element
	public void addRow(Entity obj){
		boolean test = this.getDao(currentEntity).create(obj);
		if (test){
			currentData.add(obj);
			// To notify the table of the change of data
			this.updateObservable(currentData);
		} else {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "DaoTableModel.addRow failed","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	// Create a new element
	public void addRow(){
		Entity obj = (Entity)this.getDao(currentEntity).newElement();
		if (obj == null){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "DaoTableModel.addRow failed","ERROR",JOptionPane.ERROR_MESSAGE);
		} else {
			currentData.add(obj);
			// To notify the table of the change of data
			this.updateObservable(currentData);
		} 
	}

	// Action corresponding to the listener of the "Save/update" button
	public void saveTable(){
		// Saves modified data
		int i = 0;
		for (Entity obj : currentData){
			if (gc.getDao(currentEntity).update(obj)){
				i++;						
			} else {
				System.out.println("GeneralController.saveTable: skipped one line.");
			};
		};
		System.out.println("GeneralController.saveTable: saved "+i+" lines.");
		this.updateObservable(currentData);
	}
	
	//===================================
	// management of Dao
	//===================================

	public Dao<Student> getStudentDao(){
		return df.getStudentDao();
	}
	
	public Dao<Exams> getExamsDao(){
		return df.getExamsDao();
	}
	
	public Dao<Semester> getSemesterDao(){
		return df.getSemesterDao();
	}
	
	public Dao<Entity> getDao(int i){
		return df.getDao(i);
	}
	
	//=================================
	// Listeners and observable
	//=================================

	// listener of the JTabbed pane
	// (recovers the currentEntity)
	@Override
	public void stateChanged(ChangeEvent e) {
		this.currentEntity=((JTabbedPane)e.getSource()).getSelectedIndex();
		System.out.println("GC.stateChanged - current Entity = "+currentEntity);
		this.updateData();
		
	}
	
	// methods related to the Observer pattern
	@Override
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	@Override
	public void updateObservable(LinkedList<Entity> currentData) {
		for (Observer obs: listObserver){
			obs.updateObserver(currentData);
		}
	}

}
