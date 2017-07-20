package gestionBilicence.edition;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gestionBilicence.general.Entity;
import gestionBilicence.general.GeneralPanel;
import gestionBilicence.general.GeneralWindow;
import gestionBilicence.general.ListTableModel;
import gestionBilicence.general.editorsRenderers.Delete;

public class EditionWindow extends GeneralWindow {
	/*
	 * Window to edit elements in the database
	 * NB: GeneralWindow provides a copy of gc (GeneralController)
	 */

	public EditionWindow(){
		super();
		
		LinkedList<Entity>[] dataVect = (LinkedList<Entity>[]) new LinkedList<?>[3];
		ListTableModel[] listTableModelVect = new ListTableModel[3];
		JPanel[] tabEntityVect = new JPanel[3];
		
		// Creation of student tab (already initialized):
		listTableModelVect[0] = new ListTableModel(
				new Class[] {String.class, String.class, Delete.class},
				new String[] {"First name","Family Name","Delete"},
				gc.getCurrentData() // at that point, we should have currentEntity=0
				);
		// include listTableModel as observer of gc (changes in the data).
		gc.addObserver(listTableModelVect[0]);
		tabEntityVect[0] = new GeneralPanel(listTableModelVect[0]);
		
		// Creation of exams tab:
		listTableModelVect[1] = new ListTableModel(
				new Class[] {String.class, String.class, Integer.class,Delete.class},
				new String[] {"Name","Semester","Coefficient", "Delete"},
				new LinkedList<Entity>() // at that point, we should have currentEntity=0
				);
		// include listTableModel as observer of gc (changes in the data).
		gc.addObserver(listTableModelVect[1]);
		tabEntityVect[1] = new GeneralPanel(listTableModelVect[1]);
		
		// Creation of semester tab:
		listTableModelVect[2] = new ListTableModel(
				new Class[] {String.class, Delete.class},
				new String[] {"Name","Delete"},
				new LinkedList<Entity>() // at that point, we should have currentEntity=0
				);
		// include listTableModel as observer of gc (changes in the data).
		gc.addObserver(listTableModelVect[2]);
		tabEntityVect[2] = new GeneralPanel(listTableModelVect[2]);
		
		// Final assembly into a tabbed panel.
		JTabbedPane tabbedPane = new JTabbedPane();
		String[] listTabs = {"Students","Exams","Semesters"};
		for (int i = 0; i <= 2; i++){
			tabbedPane.addTab(listTabs[i],tabEntityVect[i]);
		}
		
		// Listeners for 
		tabbedPane.addChangeListener(gc);
		
		//
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
	}

	/*
	public void updateWindow(){
		// gc fetches updated data
		gc.updateData();
	}
	*/
}
