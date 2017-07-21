package gestionBilicence.general.dao;

import java.util.LinkedList;

import gestionBilicence.edition.Mark;
import gestionBilicence.edition.Student;

public abstract class AbstractMarkDao extends Dao<Mark> {
	
	// Returns an element of type Semester
	// either an already existing one or
	// we create and initialize a new one in the database
	public abstract Mark anyElement();
	
	public abstract LinkedList<Mark> getDataStudent(Student stud);
}
