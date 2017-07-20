package gestionBilicence.general;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class GTable extends JTable {
	/*
	 * A table adapted to our needs, with:
	 * _ a copy of the general controller
	 * _ a cast on the TableModel we use.
	 */
	private GeneralController gc = GeneralController.getInstance();

	public GTable(ListTableModel model) {
		super(model);
	}
	
	public ListTableModel getModel(){
		// since the only constructor involves a ListTableModel, 
		// the cast below should be safe...
		return (ListTableModel)this.dataModel;
	}
	
}
