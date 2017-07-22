package gestionBilicence.dao.abstractDao;

import java.util.LinkedList;
import java.util.List;

import gestionBilicence.dao.Dao;
import gestionBilicence.edition.Mark;
import gestionBilicence.edition.Semester;
import gestionBilicence.edition.Student;
import gestionBilicence.statistics.Average;

public abstract class AbstractMarkDao extends Dao<Mark> {
	
	// Returns an element of type Semester
	// either an already existing one or
	// we create and initialize a new one in the database
	public abstract Mark anyElement();
	
	public abstract LinkedList<Mark> getDataOnStudent(Student stud);
	
	public abstract LinkedList<Average> getAverage(List<Semester> listCurrentSemester);
	
}
