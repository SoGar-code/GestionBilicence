package gestionBilicence.csvImport;
/**
 * 
 * @author Trivy
 * A class to import CSV files
 */
public class CsvImport {
	AbstractImportReader air = new SimpleImportReader();
	
	public void importCsv(){
		air.importReader("E:\\Data\\bilicence-notes_hors_rattrapage.csv");
		System.out.println("CsvImport.importCsv completed!");
	}
}
