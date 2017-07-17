package gestionBilicence.general;

public abstract class Entity {
	private int index;

	/*
	 * Superclass for the POJO used for DAO
	 * 
	 */
	
	// Initialisation of index, 
	// to be set by associated DAO class
	public Entity(){
		index = 0;
	}
	
	public abstract Entity defaultElement();

	public void setIndex(int index){
		index = index;
	}
	
	public int getIndex(){
		return index;
	}
}
