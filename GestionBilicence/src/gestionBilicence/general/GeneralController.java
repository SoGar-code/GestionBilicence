package gestionBilicence.general;

import gestionBilicence.general.dao.AbstractDaoFactory;
import gestionBilicence.general.dao.Dao;

public class GeneralController{
	/*
	 * A singleton class giving access to the different Dao classes
	 * and centralizing data about the current state of the interface
	 */
	
	private static GeneralController gc = new GeneralController();
	// Implicitly, df encodes which type of Database we are using in this instance.
	private static AbstractDaoFactory df;
	
	private GeneralController(){
		df = AbstractDaoFactory.getFactory();
	}
	
	public static GeneralController getInstance(){
		return gc;
	}
	
	public static Dao getStudentDao(){
		return df.getStudentDao();
	}
	
	public static Dao getExamsDao(){
		return df.getExamsDao();
	}
	
	public static Dao getDao(int i){
		return df.getDao(i);
	}
}
