package gestionBilicence.general.dao;

import gestionBilicence.general.DialogConnection;

public abstract class AbstractDaoFactory {

	public abstract Dao getStudentDao();
	
	public abstract Dao getExamsDao();
	
	// to get Dao class indexed by an integer
	public abstract Dao getDao(int i);
	
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
