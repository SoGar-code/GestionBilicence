package gestionBilicence.dao.postgreSqlDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import gestionBilicence.dao.AbstractDaoFactory;
import gestionBilicence.dao.AbstractMarkDao;
import gestionBilicence.dao.AbstractStudentDao;
import gestionBilicence.dao.Dao;
import gestionBilicence.edition.Exams;
import gestionBilicence.edition.Semester;

public class PostgreSQLFactory extends AbstractDaoFactory {
	/*
	 * Create a connection to a PostgreSQL database
	 */
	private static Connection conn;

	public PostgreSQLFactory(String[] infoConn) {
		try {
	        Class.forName("org.postgresql.Driver");
	        String url = "jdbc:postgresql://"+infoConn[2]+"/"+infoConn[3];
	        String user = infoConn[0];
	        String passwd = infoConn[1];
	        conn = DriverManager.getConnection(url, user, passwd);
	        // commits automatiques ou pas
	        conn.setAutoCommit(true);
			System.out.println("PostgreSQLFactory ready!");
		} catch (ClassNotFoundException e) {
	        e.printStackTrace();
		} catch (PSQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null , "Wrong password?", "PostGreSQLFactory -- PSQLException", JOptionPane.ERROR_MESSAGE );
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public AbstractStudentDao getStudentDao() {
		return new PostgreSQLStudentDao(conn);
	}

	@Override
	public Dao<Exams> getExamsDao() {
		return new PostgreSQLExamDao(conn);
	}
	
	@Override
	public Dao<Semester> getSemesterDao() {
		return new PostgreSQLSemesterDao(conn);
	}
	
	@Override
	public AbstractMarkDao getMarkDao() {
		return new PostgreSQLMarkDao(conn);
	}
}
