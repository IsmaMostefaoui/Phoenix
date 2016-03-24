package com.umons.model;

import com.umons.view.BoardGUI;

public class Location {

	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	public Location pixelToCoord() {
		int xtemp; int ytemp;
		x = x -BoardGUI.lWall; y = y -BoardGUI.lWall;
		if (x < 0 || x > 775 || y < 0 || y > 775) {
			return new Location(-1, -1);
		}
		if (x >= 0 && x <= BoardGUI.lSquare) {
			xtemp = 0;
		}else {
			System.out.println("test dans location pixetocoord x: " + x + " x/board: " + ((x/BoardGUI.lSquare)- (x/BoardGUI.lWall)));
			xtemp = (int)Math.abs((x/BoardGUI.lSquare) - (x/BoardGUI.lWall));
		}
		if (y >= 0 && y <= BoardGUI.lSquare) {
			ytemp = 0;
		}else {
			ytemp = (int)(Math.abs((y/BoardGUI.lSquare) - (y/BoardGUI.lWall)));
		}
		return new Location(xtemp, ytemp);
	}*/
	
	public Location pixelToCoord() {
		int xtemp = -1;
		int ytemp = -1;
		if (x < BoardGUI.lWall || x > 810 || y < BoardGUI.lWall || y > 810) {
			return new Location(-1, -1);
		}
		for (int i = 0; i < BoardGUI.SQUARE_NUMBER; i++) {
			//carre
			if (x >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.START_X && x <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + BoardGUI.START_X)) {
				xtemp = 2*i;
			//fente
			}else if (x >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.START_X+BoardGUI.lSquare && x <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + BoardGUI.START_X + BoardGUI.lWall)){
				xtemp = (2*i)+1;
			}
			if (y >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.START_Y && y <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + BoardGUI.START_Y)) {
				ytemp = 2*i;
			}else if (y >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.START_Y+BoardGUI.lSquare && y <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare +  BoardGUI.START_Y + BoardGUI.lWall)){
				ytemp = (2*i)+1;
			}
		}return new Location(xtemp, ytemp);
	}

	public Location coordToPixel() {
		int xtemp;
		int ytemp;
		if (this.x == 0) {
			xtemp = BoardGUI.START_X;
		}else if (this.x == 1){
			xtemp = BoardGUI.START_X + BoardGUI.lSquare;
		}else if (this.x % 2 == 0) {
			xtemp = BoardGUI.START_X + x/2*(BoardGUI.lSquare + BoardGUI.lWall);
		}else {
			xtemp = BoardGUI.START_X + BoardGUI.lSquare + (x/2)*(BoardGUI.lSquare + BoardGUI.lWall);
		}
		if (this.y == 0) {
			ytemp = BoardGUI.START_Y;
		}else if (this.y == 1) {
			ytemp = BoardGUI.START_Y + BoardGUI.lSquare;
		}else if (this.y % 2 == 0) {
			ytemp = BoardGUI.START_Y + y/2*(BoardGUI.lSquare + BoardGUI.lWall);
		}else {
			ytemp = BoardGUI.START_Y + BoardGUI.lSquare + (y/2)*(BoardGUI.lSquare + BoardGUI.lWall);
		}
		return new Location(xtemp, ytemp);
	}
	
	/**
	 * Permet d'obtenir l'objet Location au dessus de la location actuelle
	 * @return un Objet Location 
	 */
	public Location squareUp() {
		return new Location(x, y-2);
	}
	
	
	/**
	 * Permet d'obtenir l'objet Location en dessous de la location actuelle
	 * @return un Objet Location 
	 */
	public Location squareDown() {
		return new Location(x, y+2);
	}
	
	
	/**
	 * Permet d'obtenir l'objet Location à gauche de la location actuelle
	 * @return un Objet Location 
	 */
	public Location squareLeft() {
		return new Location (x-2, y);
	}
	
	
	/**
	 * Permet d'obtenir l'objet Location a droite de la location actuelle
	 * @return un Objet Location 
	 */
	public Location squareRight() {
		return new Location (x+2, y);
	}
	
	

	/**
	 * Modifie les entrées consoles en coordonées de type Location
	 * @param direction String représentant la direction du pion
	 * @return Location représentant les coordonnées de l'endroit ou le player (le pion) va se déplacer
	 */
	public Location stringToCoord(String direction) {
		// tableau de la forme {x, y}
		switch (direction) {
		case "z":
			return new Location(getLocX(), getLocY()-2);
		case "s":
			return new Location(getLocX(), getLocY()+2);
		case "d":
			return new Location(getLocX()+2, getLocY());
		case "q":
			return new Location(getLocX()-2, getLocY());
		case "zz":
			return new Location(getLocX(), getLocY()-4);
		case "ss":
			return new Location(getLocX(), getLocY()+4);
		case "dd":
			return new Location(getLocX()+4, getLocY());
		case "qq":
			return new Location(getLocX()-4, getLocY());
		case "dbd":
			return new Location(getLocX()+2, getLocY()+2);
		case "dbg":
			return new Location(getLocX()-2, getLocY()+2);
		case "dhg":
			return new Location(getLocX()-2, getLocY()-2);
		case "dhd":
			return new Location(getLocX()+2, getLocY()-2);
		default:
			return null;
		}
	}
	
	
	/**
	 * Verifie si l'objet Location est une case
	 * @return true si la case est square, false si c'est un wall
	 */
	public boolean isSquare() {
		return getLocX() % 2 == 0 && getLocY() % 2 == 0;
	}
	
	
	/**
	 * Verifie si un Mur est horizontal
	 * @return true si il est horizontal,sinon false
	 */
	public boolean isWallHorizontal() {
		return getLocX() % 2 == 0;
	}
	
	
	/**
	 * Verifie si un Mur est vertical
	 * @return true si il est vertical,sinon false
	 */
	public boolean isWallVertical() {
		return getLocY() % 2 == 0;
	}
	
	
	/**
	 * Verifie si la Location est dans la grillze
	 * @return true si le déplacement est autorisé,sinon false
	 */
	public boolean inGrid(Grid board) {
		return ((getLocX() >= 0) && (getLocX() < board.getLen()) && (getLocY() >= 0 && getLocY() < board.getLen()));
	}

	
	/**
	 * Verifie si deux objets Locations ont les même attributs x et y
	 * @param loc Objet location
	 * @return true si les Objets Location ont les mêmes attributs x et y, sinon false
	 */
	public boolean equals(Location loc) {
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
		return this.x + ", " + this.y;
	}
}
