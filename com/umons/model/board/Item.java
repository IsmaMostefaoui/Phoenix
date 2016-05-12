package com.umons.model.board;

import java.io.Serializable;

import com.umons.model.ARules;

public class Item implements Serializable{

	private static final long serialVersionUID = -1346066133271840641L;
	
	private boolean full; //placé en protected parce que besoin d'y acceder dans wall, une classe derivée de square
	private Location loc;
	
	/**
	 * Initialise une case avec des coordonnes x et y
	 * @param x la coordonnees x de la case
	 * @param y la coordonnees y de la case
	 */
	public Item(int x, int y) {
		loc = new Location(x, y);
	}
	
	
	/**
	 * Initialise une case comme étant vide
	 */
	public Item(Location loc) {
		//Constructeur par defaut, initialise l'item à false
		this.loc = loc;
		this.full = false;
	}
	
	/**
	 * Initialise une case
	 * @param full true si la case est remplie, false sinon
	 */
	public Item(Location loc, boolean full) {
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
	public boolean isBlocked() {
		return ARules.rSquareAvailable(this.getLocation()) == null;
	}

}
