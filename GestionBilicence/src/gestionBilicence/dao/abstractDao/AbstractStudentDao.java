package gestionBilicence.dao.abstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import gestionBilicence.dao.Dao;
import gestionBilicence.edition.Semester;
import gestionBilicence.edition.Student;

public abstract class AbstractStudentDao extends Dao<Student> {
	
	// Returns an element of type Semester
	// either an already existing one or
	// we create and initialize a new one in the database
	public abstract Student anyElement();
	
	public abstract LinkedList<Student> getData(boolean inverseSort);
}
