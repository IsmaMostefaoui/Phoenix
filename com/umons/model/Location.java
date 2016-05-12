package com.umons.model;

import java.io.Serializable;

public class Location implements Serializable{

	private static final long serialVersionUID = -414141962435812650L;

	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	
	/**
	 * Permet d'obtenir l'objet Location situé 2 coordonnées au dessus de la location actuelle
	 * @return un Objet Location 
	 */
	public Location itemUp() {
		return new Location(x, y-2);
	}
	
	
	/**
	 * Permet d'obtenir l'objet Location situé 2 coordonnées en bas de la location actuelle
	 * @return un Objet Location 
	 */
	public Location itemDown() {
		return new Location(x, y+2);
	}
	
	
	/**
	 * Précondition: l'objet item est dans la grille
	 * Permet d'obtenir l'objet Location situé 2 coordonnées à gauche de la location actuelle
	 * @return un Objet Location 
	 */
	public Location itemLeft() {
		return new Location (x-2, y);
	}
	
	
	/**
	 * Précondition: l'objet item est dans la grille
	 * Permet d'obtenir l'objet Location situé 2 coordonnées à droite de la location actuelle
	 * @return un Objet Location 
	 */
	public Location itemRight() {
		return new Location (x+2, y);
	}
	
	/**
	 * Verifie si l'objet Location est une case
	 * @return true si la case est square, false si c'est un wall
	 */
	public boolean isSquare() {
		return getLocX() % 2 == 0 && getLocY() % 2 == 0;
	}
	
	
	/**
	 * Précondition: l'objet Item simule un mur
	 * Verifie si un Mur est horizontal
	 * @return true si il est horizontal,sinon false
	 */
	public boolean isWallHorizontal() {
		return getLocX() % 2 == 0 && getLocY()%2 != 0;
	}
	
	
	/**
	 * Précondition: l'objet Item simule un mur
	 * Verifie si un Mur est vertical
	 * @return true si il est vertical,sinon false
	 */
	public boolean isWallVertical() {
		return getLocY() % 2 == 0 && getLocX()%2 != 0;
	}
	
	
	/**
	 * Verifie si la Location est dans la grillze
	 * @return true si le déplacement est autorisé,sinon false
	 */
	public boolean inGrid(Grid board) {
		return ((getLocX() >= 0) && (getLocX() < Grid.getLen()) && (getLocY() >= 0 && getLocY() < Grid.getLen()));
	}

	
	/**
	 * Verifie si deux objets Locations ont les même attributs x et y
	 * @param loc Objet location
	 * @return true si les Objets Location ont les mêmes attributs x et y, sinon false
	 */
	public boolean equals(Object obj) {
		Location loc = (Location) obj;
		return getLocX() == loc.getLocX() && getLocY() == loc.getLocY();
	}
	
	
	/**
	 * Accesseur de L'attribut x (Correspond aux colones dans la grille)
	 * @return la valeur de x
	 */
	public int getLocX() { return this.x; }
	
	
	/**
	 * Accesseur de L'attribut y (Correspond aux lignes dans la grille)
	 * @return la valeur de y
	 */
	public int getLocY() { return this.y; }
	
	
	/**
	 * Mutateur de l'attribut x(Correspond aux colones dans la grille)
	 * @param x nouvelle valeur de x 
	 */
	public void setLocX(int x) {
		this.x = x;
	}
	
	
	/**
	 * Mutateur de l'attribut y(Correspond aux lignes dans la grille)
	 * @param x (int) nouvelle valeur de y 
	 */
	public void setLocY(int y) {
		this.y = y;
	}
	
	/**
	 * Override de la methode toString
	 * Ex : " 5, 2 " 
	 */
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	/**
	 * @return ls coordonnees Location du mur du dessus
	 */
	public Location wallUp() {
		return (new Location(x , y -1));
	}
	
	/**
	 * @return ls coordonnees Location du mur du dessus
	 */
	public Location wallLeft() {
		return (new Location(x - 1, y));
	}
	
	/**
	 * Précondition: l'objet item est une case
	 * @return ls coordonnees Location du mur du dessus
	 */
	public Location wallRight() {
		return (new Location(x + 1, y));
	}
	
	/**
	 * @return ls coordonnees Location du mur du dessus
	 */
	public Location wallDown() {
		return (new Location(x , y +1));
	}
	
	
	
	
	
}
