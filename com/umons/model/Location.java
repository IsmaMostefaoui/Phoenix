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
		if (x < 0 || x > 810 || y < 0 || y > 810) {
			return new Location(-1, -1);
		}
		for (int i = 0; i < BoardGUI.SQUARE_NUMBER; i++) {
			//carre
			if (x >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.lWall && x <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + BoardGUI.lWall)) {
				xtemp = 2*i;
			//fente
			}else if (x >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.lWall+BoardGUI.lSquare && x <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + 2*BoardGUI.lWall)){
				xtemp = (2*i)+1;
			}
			if (y >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.lWall && y <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + BoardGUI.lWall)) {
				ytemp = 2*i;
			}else if (y >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.lWall+BoardGUI.lSquare && y <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + 2*BoardGUI.lWall)){
				ytemp = (2*i)+1;
			}
		}return new Location(xtemp, ytemp);
	}
	
	public Location coordToPixel() {
		int xtemp, ytemp;
		if (this.x == 0) {
			xtemp = BoardGUI.lWall;
		}else if (this.x % 2 == 0) {
			xtemp = BoardGUI.lWall + x/2*(BoardGUI.lSquare + BoardGUI.lWall);
		}else {
			xtemp = (BoardGUI.lWall + BoardGUI.lSquare) + x*BoardGUI.lWall + BoardGUI.lSquare;
		}
		if (this.y == 0) {
			ytemp = BoardGUI.lWall;
		}else if (this.y % 2 == 0) {
			ytemp = BoardGUI.lWall + y/2*(BoardGUI.lSquare + BoardGUI.lWall);
		}else {
			ytemp = (BoardGUI.lWall + BoardGUI.lSquare) + y*BoardGUI.lWall + BoardGUI.lSquare;
		}
		return new Location(xtemp, ytemp);
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
