package com.umons.model;

public class Location {

	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static Location pixelToCoord(double x, double y) {
		int xtemp; int ytemp;
		x = x -25; y = y -25;
		if (x < 0 || x > 650 || y < 0 || y > 650) {
			return new Location(-1, -1);
		}
		if (x > 0 && x <=50) {
			System.out.println("in");
			xtemp = 0;
		}else {
			xtemp = (int)Math.abs((x/50) - (x/25));
		}
		if (y > 0 && y <= 50) {
			ytemp = 0;
		}else {
			ytemp = (int)(Math.abs((y/50) - (y/25)));
		}
		return new Location(xtemp, ytemp);
	}
	
	public Location coordToPixel() {
		System.out.println("x: " + (50*this.x-50) );
		return new Location((40*this.x)-50, (50*this.y)-50);
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
	 * 
	 * @return true si la case est square, false si c'est un wall
	 */
	public boolean lSquare() {
		return getLocX() % 2 == 0 && getLocY() % 2 == 0;
	}
	
	public boolean isWallHorizontal() {
		return getLocX() % 2 == 0;
	}
	
	public boolean isWallVertical() {
		return getLocY() % 2 == 0;
	}
	
	/**
	 * vérifie si le pion ne sort pas de la grille
	 * @param tabCoord la future position du pion
	 * @return true si le déplacement est autorisé,sinon false
	 */
	public boolean inGrid(Grid board) {
		return ((getLocX() >= 0) && (getLocX() < board.getLen()) && (getLocY() >= 0 && getLocY() < board.getLen()));
	}
	
	public boolean isEquals(Location loc) {
		return getLocX() == loc.getLocX() && getLocY() == loc.getLocY();
	}
	
	public int getLocX() { return this.x; }
	public int getLocY() { return this.y; }
	
	public void setLocX(int x) {
		this.x = x;
	}
	public void setLocY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return this.x + ", " + this.y;
	}
}
