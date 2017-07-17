package gestionBilicence.statistics;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import gestionBilicence.general.GeneralWindow;

public class StatisticsWindow extends GeneralWindow {
	/*
	 * Window to display statistics
	 */

	public StatisticsWindow(){
		super();
		
		
		// tabs for the central view
		JTabbedPane tabbedPane = new JTabbedPane();
		JTable tabAverage = new JTable();
		JTable tabRate = new JTable();
		
		tabbedPane.addTab("Average",tabAverage);
		tabbedPane.addTab("Success rate",tabRate);
		
		// selection of relevant semesters
		DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("L1S1");
        listModel.addElement("L1S2");
        listModel.addElement("L2S1");
        listModel.addElement("L2S2");
        listModel.addElement("L3S1");
        listModel.addElement("L3S2");
        
		JList semesterList = new JList(listModel);
		semesterList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane semesterScroll = new JScrollPane(semesterList);
		semesterScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// selection of relevant academic years
		DefaultListModel listModel2 = new DefaultListModel();
        listModel2.addElement("2017-2018");
        listModel2.addElement("2016-2017");
        listModel2.addElement("2015-2016");
        
		JList ayList = new JList(listModel2);
        ayList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane ayScroll = new JScrollPane(ayList);
		ayScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// final assembly of westPan
		JLabel semesterLabel = new JLabel("Select semesters:");
		JLabel ayLabel = new JLabel("Select academic year:");
		JPanel westPan = new JPanel();
		westPan.setLayout(new BoxLayout(westPan, BoxLayout.PAGE_AXIS));
		westPan.add(semesterLabel);
		westPan.add(semesterScroll);
		westPan.add(ayLabel);
		westPan.add(ayScroll);
		
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
		this.add(westPan, BorderLayout.WEST);
	}
}
