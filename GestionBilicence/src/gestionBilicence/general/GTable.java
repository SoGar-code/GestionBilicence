package gestionBilicence.general;

import java.util.LinkedList;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gestionBilicence.edition.Semester;
import gestionBilicence.general.editorsRenderers.ButtonDeleteEditor;
import gestionBilicence.general.editorsRenderers.ButtonRenderer;
import gestionBilicence.general.editorsRenderers.Delete;

public class GTable extends JScrollPane{
	/*
	 * A table adapted to our needs, with:
	 * _ scrolling
	 * _ a copy of the general controller
	 * _ a cast on the TableModel we use.
	 * _ suitable editors and renderers.
	 *  
	 * NB: the delete function is implemented in ButtonDeleteEditor
	 */
	private GeneralController gc = GeneralController.getInstance();
	private JTable table;

	public GTable(ListTableModel model) {
		super();
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		table = new JTable(model);
		
		table.setRowHeight(30);
	    //==================================
	    // Custom editors and renderers
	    //==================================
	    table.setDefaultEditor(Delete.class, new ButtonDeleteEditor(new JCheckBox()));
	    table.setDefaultRenderer(Delete.class, new ButtonRenderer());
	    
	    LinkedList<Semester> listSemester = gc.getSemesterDao().getData();
	    JComboBox<Semester> comboSemester = new JComboBox<Semester>(listSemester.toArray(new Semester[listSemester.size()]));
	    table.setDefaultEditor(Semester.class, new DefaultCellEditor(comboSemester));

	    this.setViewportView(table);
	}
	
	public ListTableModel getModel(){
		// since the only constructor involves a ListTableModel, 
		// the cast below should be safe...
		return ((ListTableModel)this.table.getModel());
	}
	
	public void updateComboSemester(){
		// Method called by GeneralPanel when currentEntity==2 (Semester)
		// when "Save/update" button is pushed.
		// Only for the Exams table.
	    System.out.println("GTable.updateComboSemester has been called");
	    LinkedList<Semester> listSemester = gc.getSemesterDao().getData();
	    JComboBox<Semester> comboSemester = new JComboBox<Semester>(listSemester.toArray(new Semester[listSemester.size()]));
	    this.table.setDefaultEditor(Semester.class, new DefaultCellEditor(comboSemester));
	}
	
}
