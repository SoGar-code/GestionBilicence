package gestionBilicence.csvImport;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Trivy
 * A class to import CSV files
 */
public class CsvImport extends JDialog{
	AbstractImportReader air = new SimpleImportReader();
	
	public CsvImport(){
		super();
	}

	public void importCsv(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"*.csv", "*.*");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);
		String path = "";
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("You chose to open this file: " +
					path);
		}
		air.importReader(path);
		System.out.println("CsvImport.importCsv completed!");
	}
}
