package com.umons.model;

public class Wall extends Square{
	
	/**
	 * Initisalise un mur comme étant vide
	 */
	public Wall(Location loc) {
		super(loc);
	}
	
	/**
	 * Initialise un mur avec le parametre full
	 * @param full true si le mur doit etre plein, false sinon
	 */
	public Wall(Location loc, boolean full) {
		super(loc, full);
	}
	
	/**
	 * Surcharge de la méthode de la classe Object
	 * Affiche l'etat d'un mur pour le mode console
	 */
	public String toString() {
		
		if (getFull()) {
			return "***";
		}else if (!getFull()){
			return "   ";
		}else {
			return null;
		}
	}
	
}
