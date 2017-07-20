package gestionBilicence.general.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import gestionBilicence.edition.Semester;
import gestionBilicence.edition.Student;

public abstract class AbstractSemesterDao extends Dao<Semester> {
	
	// Returns an element of type Semester
	// either an already existing one or
	// we create and initialize a new one in the database
	public abstract Semester anyElement();
}
