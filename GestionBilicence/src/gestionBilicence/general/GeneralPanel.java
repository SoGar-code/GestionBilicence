package gestionBilicence.general;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import gestionBilicence.general.editorsRenderers.ButtonDeleteEditor;
import gestionBilicence.general.editorsRenderers.ButtonRenderer;
import gestionBilicence.general.editorsRenderers.Delete;

public class GeneralPanel<T> extends JPanel{
	/* 
	 * Superclass for the edition panels. Includes: 
	 * _ a table with predefined operations (addRow, saveTable)
	 * _ saving buttons and listeners
	 * _ new line 
	 */
	protected GTable table;
	protected JPanel pan;
	protected JButton saveButton;
	protected GeneralController gc = GeneralController.getInstance();
	
	public GeneralPanel(ListTableModel model){
		super(new BorderLayout());
	    this.table = new GTable(model);
	    this.table.setRowHeight(30);
	    this.table.setDefaultEditor(Delete.class, new ButtonDeleteEditor(new JCheckBox()));
	    this.table.setDefaultRenderer(Delete.class, new ButtonRenderer());
	    
		JButton newLine = new JButton("New line");
		saveButton = new JButton("Save/update");
	    
		// Keeping the listeners as generic as possible
		class AddListener implements ActionListener{
			public void actionPerformed(ActionEvent event){	
				table.addRow();
			}
		}
		
		class SaveListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				table.saveTable();
			}
		}
	    
	    newLine.addActionListener(new AddListener());
	    saveButton.addActionListener(new SaveListener());
	    
	    GridLayout gl1 = new GridLayout(1,2);
	    gl1.setHgap(5);
	    JPanel pan1 = new JPanel(gl1);
	    pan1.add(newLine);
	    pan1.add(saveButton);
	    pan = new JPanel( new BorderLayout());
	    pan.add(pan1, BorderLayout.SOUTH);
	    
	    this.add(new JScrollPane(table), BorderLayout.CENTER);
	    this.add(pan, BorderLayout.SOUTH);
	}
	
	public GTable getTable(){
		return table;
	}
}
