package gestionBilicence.edition;

import gestionBilicence.general.Entity;

public class Student extends Entity {
	private String first_name;
	private String last_name;
	
	public Student(String first_name, String last_name) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public Student defaultElement(){
		return new Student("Jean","Par Défaut");
	}
}
