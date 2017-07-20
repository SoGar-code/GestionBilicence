package gestionBilicence.general;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class GTable extends JTable {
	private GeneralController gc = GeneralController.getInstance();

	public GTable(ListTableModel model) {
		super(model);
	}
	
	public ListTableModel getModel(){
		// since the only constructor involves a ListTableModel, 
		// the cast below should be safe...
		return (ListTableModel)this.dataModel;
	}

	public void addRow(){
		gc.addRow(this.getModel().getData());
	}
	
	public void saveTable(){
		gc.saveTable(this.getModel().getData());
	}
}
