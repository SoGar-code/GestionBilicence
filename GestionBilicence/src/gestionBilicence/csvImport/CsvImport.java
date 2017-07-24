package gestionBilicence.csvImport;
/**
 * 
 * @author Trivy
 * A class to import CSV files
 */
public class CsvImport {
	AbstractImportReader air = new SimpleImportReader();
	
	public void importCsv(){
		air.importReader("E:\\git\\GestionBilicence\\GestionBilicence\\bin\\gestionBilicence\\bilicence-notes_hors_rattrapage.csv");
	}
}
