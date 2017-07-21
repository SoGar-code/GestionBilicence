package gestionBilicence.statistics;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import gestionBilicence.general.GeneralController;
import gestionBilicence.general.GeneralWindow;

public class StatisticsWindow extends GeneralWindow {
	/*
	 * Window to display statistics
	 */
	private GeneralController gc = GeneralController.getInstance();

	public StatisticsWindow(){
		super();
		
		// selection of relevant semesters
		DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("L1S1");
        listModel.addElement("L1S2");
        listModel.addElement("L2S1");
        listModel.addElement("L2S2");
        listModel.addElement("L3S1");
        listModel.addElement("L3S2");
        
        
		// Definition of tableModel
        DefaultTableModel tableModel = new DefaultTableModel();
		
		// tabs for the central view
		JTabbedPane tabbedPane = new JTabbedPane();
		StatisticsPanel tabEvolution = new StatisticsPanel(listModel, tableModel);
		StatisticsPanel tabAverage = new StatisticsPanel(listModel, tableModel);
		StatisticsPanel tabRate = new StatisticsPanel(listModel, tableModel);
		
		tabbedPane.addTab("Evolution",tabEvolution);
		tabbedPane.addTab("Average",tabAverage);
		tabbedPane.addTab("Success rate",tabRate);
		
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
	}
}
