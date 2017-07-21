package gestionBilicence.statistics;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import gestionBilicence.edition.Semester;
import gestionBilicence.edition.Student;
import gestionBilicence.general.GeneralController;
import gestionBilicence.general.GeneralWindow;

public class StatisticsWindow extends GeneralWindow {
	/*
	 * Window to display statistics
	 */
	private GeneralController gc = GeneralController.getInstance();
	private DefaultListModel<Student> listStudent;
	private DefaultListModel<Semester> listSemester;
	
	private JTabbedPane tabbedPane;

	public StatisticsWindow(){
		super();
		        
		// Definition of tableModel
        DefaultTableModel tableModel = new DefaultTableModel();
		
		// tabEvolution: all marks per student
        listStudent = new DefaultListModel<Student>();
        updateListStudent();
		StatisticsPanel tabEvolution = new StatisticsPanel(listStudent, tableModel);
		
        // list of Semester
        listSemester = new DefaultListModel<Semester>();
        updateListSemester();
		
		StatisticsPanel tabAverage = new StatisticsPanel(listSemester, tableModel);
		StatisticsPanel tabRate = new StatisticsPanel(listSemester, tableModel);

		// final assembly of tabbedPane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Evolution",tabEvolution);
		tabbedPane.addTab("Average",tabAverage);
		tabbedPane.addTab("Success rate",tabRate);
		
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void updateWindow(){
		updateListSemester();
		updateListStudent();
	}
	
	public void updateListSemester(){
		listSemester.removeAllElements();
        LinkedList<Semester> dataSemester = gc.getSemesterDao().getData();
        for(Semester semester:dataSemester){
        	listSemester.addElement(semester);
        }
	}
	
	public void updateListStudent(){
		listStudent.removeAllElements();
        LinkedList<Student> dataStudent = gc.getStudentDao().getData();
        for(Student stud:dataStudent){
        	listStudent.addElement(stud);
        }
	}
}
