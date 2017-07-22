package gestionBilicence.dao.postgreSqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import gestionBilicence.dao.abstractDao.AbstractStudentDao;
import gestionBilicence.edition.Exams;
import gestionBilicence.edition.Student;

public class PostgreSQLStudentDao extends AbstractStudentDao {
	
	public PostgreSQLStudentDao(Connection conn){
		super();
		this.conn = conn;
	}

	@Override
	public boolean create(Student obj) {
		try{
			String query="INSERT INTO students(stud_firstname, stud_lastname) VALUES(?,?)";
			PreparedStatement state = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			state.setString(1, obj.getFirst_name());
			state.setString(2, obj.getLast_name());
			int nb_rows = state.executeUpdate();
			
			// Update of the index (should be 0 up to this point)
			ResultSet genKey = state.getGeneratedKeys();
			if (genKey.next()){
				obj.setIndex(genKey.getInt(1));
			};
			state.close();
			return true;
		} catch (SQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, e.getMessage(),"PostgreSQLStudentDao.create -- ERROR!",JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Student obj) {
		try{
			String query="UPDATE students SET stud_firstname = ?, stud_lastname = ? WHERE id_stud = ?";
			PreparedStatement state = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			state.setString(1, obj.getFirst_name());
			state.setString(2, obj.getLast_name());
			state.setInt(3, obj.getIndex());
			int nb_rows = state.executeUpdate();
			state.close();
			return true;
		} catch (SQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, e.getMessage(),"PostgreSQLStudentDao.update -- ERROR!",JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Student obj) {
		try{
			String query="DELETE FROM students WHERE id_stud = ?";
			PreparedStatement state = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			state.setInt(1, obj.getIndex());
			int nb_rows = state.executeUpdate();
			System.out.println("Deleted "+nb_rows+" lines");
			state.close();
			return true;
		} catch (SQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, e.getMessage(),"PostgreSQLStudentDao.delete -- ERROR!",JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public Student find(int index) {
		try{
			String query="SELECT id_stud, stud_firstname, stud_lastname FROM students WHERE id_stud = ?";
			PreparedStatement state = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			state.setInt(1, index);
			ResultSet res = state.executeQuery();
			res.first();
			Student stud = new Student(res.getInt("id_stud"),res.getString("stud_firstname"),res.getString("stud_lastname"));
			res.close();
			state.close();
			return stud;
		} catch (SQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, e.getMessage(),"PostgreSQLStudentDao.find -- ERROR!",JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	// Code to create a new element.
	// NB: create updates the index
	public Student newElement(){
		Student stud = Student.defaultElement();
		this.create(stud);
		return stud;
	}

	
	public LinkedList<Student> getData() {
		LinkedList<Student> data = new LinkedList<Student>();
		try{
			String query="SELECT id_stud, stud_firstname, stud_lastname FROM students ORDER BY stud_lastname";
			PreparedStatement state = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet res = state.executeQuery();
			while(res.next()){
				Student stud = new Student(
						res.getInt("id_stud"),
						res.getString("stud_firstname"),
						res.getString("stud_lastname")
						);
				data.add(stud);
			}
			System.out.println("PostgreSQLStudentDao.getData(): found "+data.size()+" lines.");
			res.close();
			state.close();
			return data;
		} catch (SQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, e.getMessage(),"PostgreSQLStudentDao.getData -- ERROR!",JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Student anyElement(){
		try{
			String query="SELECT id_stud, stud_firstname, stud_lastname FROM students ORDER BY id_stud LIMIT 1";
			PreparedStatement state = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet res = state.executeQuery();
			Student stud;
			if (res.first()){
				stud = this.find(res.getInt("id_stud"));
			} else {
				stud = this.newElement();
			}
			res.close();
			state.close();
			return stud;
		} catch (SQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, e.getMessage(),"PostgreSQLStudentDao.anyElement -- ERROR!",JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<Student> getData(boolean inverseSort) {
		LinkedList<Student> data = new LinkedList<Student>();
		try{
			String query="SELECT id_stud, stud_firstname, stud_lastname FROM students ORDER BY stud_lastname";
					if (inverseSort)
						query=query+" DESC";
			PreparedStatement state = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet res = state.executeQuery();
			while(res.next()){
				Student stud = new Student(
						res.getInt("id_stud"),
						res.getString("stud_firstname"),
						res.getString("stud_lastname")
						);
				data.add(stud);
			}
			System.out.println("PostgreSQLStudentDao.getData(): found "+data.size()+" lines.");
			res.close();
			state.close();
			return data;
		} catch (SQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, e.getMessage(),"PostgreSQLStudentDao.getData -- ERROR!",JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
