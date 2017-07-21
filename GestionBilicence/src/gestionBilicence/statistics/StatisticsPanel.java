package gestionBilicence.statistics;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

	/**
	 * 
	 * @author Trivy
	 * Basic class for the statistic panel, including:
	 * _ a small panel on the west, including a JLabel and a JList
	 * _ a large JTable in the center
	 * Models for list and table are initialized elsewhere
	 */

public class StatisticsPanel extends Panel {
	protected String textList;
	protected JPanel westPan;
	protected JTable mainTable;

	public StatisticsPanel(ListModel listModel,TableModel tableModel) {
		super();
		
		// Definition of westPan
		textList = "Select:";
		JLabel westLabel = new JLabel(textList);
		JList westList = new JList(listModel);
		westList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane0 = new JScrollPane(westList);
		scrollPane0.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane0.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		westPan = new JPanel();
		westPan.setLayout(new BoxLayout(westPan, BoxLayout.PAGE_AXIS));
		westPan.add(westLabel);
		westPan.add(scrollPane0);
		
		// Definition of the central panel
		mainTable = new JTable(tableModel);
		JScrollPane scrollPane1 = new JScrollPane(mainTable);
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.setLayout(new BorderLayout());
		this.add(scrollPane1, BorderLayout.CENTER);
		this.add(westPan, BorderLayout.WEST);
	}

	public String getTextList() {
		return textList;
	}

	public void setTextList(String textList) {
		this.textList = textList;
	}

	public JPanel getWestPan() {
		return westPan;
	}

	public void setWestPan(JPanel westPan) {
		this.westPan = westPan;
	}

	public JTable getMainTable() {
		return mainTable;
	}

	public void setMainTable(JTable mainTable) {
		this.mainTable = mainTable;
	}

}
