package com.umons.model;

public class Square {

	protected boolean full; //placé en protected parce que besoin d'y acceder dans wall, une classe derivée de square
	protected Location loc;
	
	
	/**
	 * Initialise une case comme étant vide
	 */
	public Square(Location loc) {
		//Constructeur par defaut, initialise l'item à false
		this.loc = loc;
		this.full = false;
	}
	
	/**
	 * Initialise une case
	 * @param full true si la case est remplie, false sinon
	 */
	public Square(Location loc, boolean full) {
		this.loc = loc;
		this.full = full;
	}
	
	/**
	 *@return true si la case courante est remplie, false sinon
	 */
	public boolean getFull() {
		
		return full;
	}
	
	/**
	 * Rempli une case
	 * @param full true si la case doit être remplie, false sinon
	 */
	public void setFull(boolean full) {
		
		this.full = full;
	}
	
	/**
	 * @return un objet Location représentant la position du mur dans la grille
	 */
	public Location getLocation() {
		return new Location(loc.getLocX(), loc.getLocY());
	}
	
	/**
	 * @return un boolean true si la case est complétement bloquée de tous les côtés
	 */
	public boolean blocked() {
		return ARules.rSquareAvailable(new Player(this.getLocation(), 1)) == null;
	}
	
	/**
	 * Surcharge de la méthode de la classe Object
	 * Affiche l'etat d'un mur
	 */
	public String toString(){
		return "[ ]";
	}

}
