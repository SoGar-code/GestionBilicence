package gestionBilicence.edition;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import gestionBilicence.general.Delete;
import gestionBilicence.general.GeneralController;
import gestionBilicence.general.GeneralPanel;
import gestionBilicence.general.GeneralWindow;
import gestionBilicence.general.ListTableModel;

public class EditionWindow extends GeneralWindow {
	/*
	 * Window to edit elements in the database
	 */
	private GeneralController gc = GeneralController.getInstance();

	public EditionWindow(){
		super();
		
		// Creation of student tab:
		gc.updateData();
		ListTableModel listTableModel = new ListTableModel(
				new Class[] {String.class, String.class, Delete.class},
				new String[] {"First name","Family Name","Delete"},
				gc.getCurrentData()
				);
		// include listTableModel as observer of gc (changes in the data).
		gc.addObserver(listTableModel);
		JPanel tabStudent = new GeneralPanel(listTableModel);		
		
		// Creation of exams tab:
		JTable tabExams = new JTable();
		
		// Final assembly into a tabbed panel.
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Students",tabStudent);
		tabbedPane.addTab("Exams",tabExams);
		
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
	}

	public void updateWindow(){
		// gc fetches updated data
		gc.updateData();
	}
}
