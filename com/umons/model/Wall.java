package com.umons.model;

import java.util.ArrayList;

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
	
	public static ArrayList<Location> allWallVertical(){
		ArrayList<Location> wallVer = new ArrayList<Location>();
		for (int x = 1; x < 15; x = x+2) {
			for (int y = 0; y < 16; y = y+2) {
				wallVer.add(new Location(x,y));
			}
		}
		return wallVer;
	}
	
	public static ArrayList<Location> allWallHorizontal(){
		ArrayList<Location> wallHor = new ArrayList<Location>();
		for (int x = 0; x <13; x = x+2) {
			for (int y = 1; y < 13; y = y+2) {
				wallHor.add(new Location(x,y));
			}
		}
		return wallHor;
	}
	
	public static ArrayList<Location> allWall() {
		ArrayList<Location> wallHor = allWallHorizontal();
		ArrayList<Location> wallVer = allWallVertical();
		ArrayList<Location> allWall = new ArrayList<Location>();
		allWall.addAll(wallVer);
		allWall.addAll(wallHor);
		for (Location wall : allWall) {
			System.out.println(wall);
		}
		return allWall;
		
		
	}
}

