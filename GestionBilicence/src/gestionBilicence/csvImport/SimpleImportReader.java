package gestionBilicence.csvImport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import gestionBilicence.edition.Exams;
import gestionBilicence.edition.ExtraInfoStudent;
import gestionBilicence.edition.Mark;
import gestionBilicence.edition.Student;
import gestionBilicence.general.GeneralController;

public class SimpleImportReader extends AbstractImportReader {

	
	/**
	 * Simplest import reader possible: 
	 * creates new Student and new Mark, 
	 * without checking whether they already exist in the database.
	 * 
	 * Using the simple solution provided by https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	 */

	@Override
	public void importReader(String path) {
		GeneralController gc = GeneralController.getInstance();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            /* Assuming the format of the csv file is:
            * _ a single header line
            * _ values separated by commas
            * _ header="N° APOGEE,NOM,PRENOM,E-mail,numéro dans notre classement APB,Note S1,Note S2"
            */
        	
        	br.readLine(); // header line
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] elements = line.split(cvsSplitBy);
                
                Student stud = new Student(
                		elements[2].toLowerCase(), //first_name
                		elements[1]  //last_name
                		);
                gc.getStudentDao().create(stud); // should update index accordingly
                
                ExtraInfoStudent info = new ExtraInfoStudent(
                		false, //	boolean hasApbNum;
                		"", //String apbNum;
                		true, // boolean hasApogeeNum;
                		elements[0], // String apogeeNum;
                		!(elements[3].equals("")), // boolean hasEmail;
                		elements[3] //private String email;
                		);
                gc.getStudentDao().updateInfo(stud, info);
                
                // elements[5] corresponds to the exam with index 1
                Exams exam = gc.getExamsDao().find(1);
                // case when elements[5] cannot be parsed to a float:
                // NumberFormatException
                
                Mark mark = new Mark(
                		Float.parseFloat(elements[5]),// float mark;
                		stud, // Student student;
                		exam // Exams exam;
                		);
                gc.getMarkDao().create(mark);
                
             // elements[6] corresponds to the exam with index 2
                Exams exam1 = gc.getExamsDao().find(2);
                
                Mark mark1 = new Mark(
                		Float.parseFloat(elements[6]),// float mark;
                		stud, // Student student;
                		exam1 // Exams exam;
                		);
                gc.getMarkDao().create(mark);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
