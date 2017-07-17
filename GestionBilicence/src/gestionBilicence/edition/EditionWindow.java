package gestionBilicence.edition;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import gestionBilicence.general.DaoTableModel;
import gestionBilicence.general.Entity;
import gestionBilicence.general.GeneralController;
import gestionBilicence.general.GeneralPanel;
import gestionBilicence.general.GeneralWindow;
import gestionBilicence.general.dao.Dao;

public class EditionWindow extends GeneralWindow {
	
	/*
	 * Window to edit elements in the database
	 */

	public EditionWindow(){
		super();
		
		// Creation of student tab:
		DaoTableModel daoTableModel = new DaoTableModel(
				new Class[] {String.class, String.class, String.class},
				new String[] {"First name","Family Name","Suppr."},
				GeneralController.getStudentDao()
				);
		JPanel tabStudent = new GeneralPanel(daoTableModel);		
		
		// Creation of exams tab:
		JTable tabExams = new JTable();
		
		// Final assembly into a tabbed panel.
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Students",tabStudent);
		tabbedPane.addTab("Exams",tabExams);
		
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
	}

}
