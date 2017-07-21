package gestionBilicence.statistics;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import gestionBilicence.edition.Exams;
import gestionBilicence.edition.Semester;
import gestionBilicence.edition.Student;
import gestionBilicence.general.Entity;
import gestionBilicence.general.GeneralController;
import gestionBilicence.general.GeneralWindow;
import gestionBilicence.general.ListTableModel;
import gestionBilicence.general.editorsRenderers.Delete;

public class StatisticsWindow extends GeneralWindow {
	/*
	 * Window to display statistics
	 */
	private GeneralController gc = GeneralController.getInstance();
	private DefaultListModel<Student> listStudent;
	private DefaultListModel<Semester> listSemester;
	private ListTableModel[] tableModelVect = new ListTableModel[3];
	
	private JTabbedPane tabbedPane;

	public StatisticsWindow(){
		super();
		    
		// ====================================
		// tabEvolution: all marks per student
        tableModelVect[0] = new EvolutionModel(
        		new Class[] {Exams.class,float.class},
        		new String[] {"Exam","Mark"},
        		new LinkedList<Entity>()
        		);
        listStudent = new DefaultListModel<Student>();
        updateListStudent();
		StatisticsPanel tabEvolution = new StatisticsPanel(listStudent, tableModelVect[0]);
		// add listener on currentStudent (NB: listener in ListTableModel!)
		tabEvolution.getWestList().addListSelectionListener(tableModelVect[0].getStudentAction());

		// ====================================
		// tabAverage: average per student (on selected semesters)
        tableModelVect[1] = new ListTableModel(
        		new Class[] {Student.class,float.class},
        		new String[] {"Student","Average"},
        		new LinkedList<Entity>()
        		);
        listSemester = new DefaultListModel<Semester>();
        updateListSemester();
		StatisticsPanel tabAverage = new StatisticsPanel(listSemester, tableModelVect[1]);
		tabAverage.getWestList().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// add listener on currentSemester
		tabAverage.getWestList().addListSelectionListener(tableModelVect[1].getSemesterAction());

		// ====================================
		// tabRate: success rate (on selected semesters?)
        tableModelVect[2] = new ListTableModel(
        		new Class[] {Semester.class,float.class},
        		new String[] {"Semester","Average"},
        		new LinkedList<Entity>()
        		);
        listSemester = new DefaultListModel<Semester>();
        updateListSemester();
		StatisticsPanel tabRate = new StatisticsPanel(listSemester, tableModelVect[2]);
		tabRate.getWestList().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// add listener on currentSemester
		tabRate.getWestList().addListSelectionListener(tableModelVect[2].getSemesterAction());

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
