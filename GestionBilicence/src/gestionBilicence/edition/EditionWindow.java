package gestionBilicence.edition;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import gestionBilicence.general.GeneralWindow;

public class EditionWindow extends GeneralWindow {
	
	/*
	 * Window to edit elements in the database
	 */

	public EditionWindow(){
		super();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		JTable tabStudent = new JTable();
		JTable tabExams = new JTable();
		
		tabbedPane.addTab("Students",tabStudent);
		tabbedPane.addTab("Exams",tabExams);
		
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
	}

}
