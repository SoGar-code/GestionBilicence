package gestionBilicence.general.dao;

import gestionBilicence.edition.Exams;
import gestionBilicence.edition.Mark;
import gestionBilicence.edition.Semester;
import gestionBilicence.edition.Student;
import gestionBilicence.general.DialogConnection;

public abstract class AbstractDaoFactory {
	/*
	 * Factory class for Dao classes
	 * Includes getDao(int i)
	 */

	public abstract Dao<Student> getStudentDao();
	
	public abstract Dao<Exams> getExamsDao();
	
	public abstract Dao<Semester> getSemesterDao();
	
	public abstract Dao<Mark> getMarkDao();
	
	// to get Dao class indexed by an integer
	public Dao getDao(int i){
		switch (i){
			case 0:
				return getStudentDao();
			case 1:
				return getExamsDao();
			case 2:
				return getSemesterDao();
			case 3:
				return getMarkDao();
			default:
				System.out.println("AbstractDaoFactory.getDao -- type not found!");
				return null;
		}
	}
	
	public static AbstractDaoFactory getFactory(){
		DialogConnection dialogConn = new DialogConnection();
		String[] infoConn = dialogConn.showDialogConnection();
		
		// Compare with available options in DialogConnection
		// Deduce what kind of Database is used
		if (new String("testbilicence").equals(infoConn[3])){
			return new PostgreSQLFactory(infoConn);
		}
		else if (new String("mysql").equals(infoConn[3])){
			return new MySQLFactory(infoConn);
		}
		else {
			System.out.println("AbstractDaoFactory.getFactory() -- string pas reconnu");			
			return null;			
		}

	}
}
