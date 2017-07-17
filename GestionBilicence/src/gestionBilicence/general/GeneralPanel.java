package gestionBilicence.general;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/* 
 * Superclass for the edition panels. Includes: 
 * _ saving buttons and listeners
 * _ new line 
 * 
 */

public class GeneralPanel<T> extends JPanel{
	protected JTable table;
	protected JPanel pan;
	protected JButton saveButton;
	
	public GeneralPanel(DaoTableModel model){
		super(new BorderLayout());
		//this.model = model;
	    this.table = new JTable(model);
	    this.table.setRowHeight(30);
	    
		JButton newLine = new JButton("New line");
		saveButton = new JButton("Save/update");
	    
		class AddListener implements ActionListener{
			public void actionPerformed(ActionEvent event){		
				((DaoTableModel)table.getModel()).addRow();
			}
		}
		
		class SvgListener implements ActionListener{
			
			public void actionPerformed(ActionEvent event){
				// MàJ de la table (en part. calcul des prix)
				((DaoTableModel)table.getModel()).fireTableDataChanged();
				// Svg 
				svgMaJTableau();
			}
		}
	    
	    newLine.addActionListener(new AddListener());
	    saveButton.addActionListener(new SvgListener());
	    
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
	
	public JTable getTableau(){
		return this.table;
	}
	
	public DaoTableModel getModel(){
		return (DaoTableModel)table.getModel();
	}
	
	// Action effectuée par le bouton "Svg/MàJ" (modif. dans TableauTrans, TableauPlacement)
	public void svgMaJTableau(){
		// Sauvegarde des données modifiées
		int i = 0;
		DaoTableModel model = (DaoTableModel)table.getModel();
		for (Entity obj : model.getData()){
			if (model.getDaoT().update(obj)){
				i++;						
			} else {};
		};
		System.out.println("GeneralPanel.SvgListener : saved "+i+" lines.");
		model.updateData();
	}
}
