package com.umons.model;

import java.util.List;

import com.umons.view.BoardGUI;

public class Player {


	private Location loc;
	private int numberOfWall;
	private final int NB_WALL = 10;
	private final int orderNumber;
	//L'ensemble des postions pour chacun des joueurs selon les modes de jeu
	public final static Location POS1 = new Location(8, 16);
	public final static Location POS2 = new Location(8, 0);
	public final static Location POS3 = new Location(0, 8);
	public final static Location POS4 = new Location(16, 8);
	
	/**
	 * Initialise un joueur avec une position
	 * @param loc un objet de type Location, la position du joueur sur la grille
	 */
	public Player(Location loc, int orderNumber) {
		this.loc = loc;
		numberOfWall = NB_WALL;
		this.orderNumber = orderNumber;
	}
	
	public Player(Location loc, int nbreOfWall, int orderNumber) {
		this.loc = loc;
		this.numberOfWall = nbreOfWall;
		this.orderNumber = orderNumber;
	}
	
	/**
	 * Deplace le pion selon une direction (donné en tableau de coordonnées (x, y))
	 * @param grid un objet de type Grid representant le tableau sur lequel le pion doit se deplacer
	 * @param loc un objet Location correspondant à la case cible
	 * @return true si le deplacement est autorisé, sinon false
	 */
	public boolean move(Grid board, Location loc) {
		List<Location> list = ARules.rSquareAvailable(this);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == loc) {
				board.setItemInGrid(this.getLoc(), false);
				this.setLoc(loc);
				board.setItemInGrid(loc, true);
				return true;
			}
		}
		return false;
	}


	/**
	 * Pose un mur sur la grille en remplissant les wall
	 * @param grid grille du jeu
	 * @param position prend "horizontal" ou "vertical"
	 * @param x prend la position en x de l'extremite gauche du mur horizontal, ou la colonne pour un mur vertical
	 * @param y prend la position en y de l'extremite supperieur du mur vertical, ou la ligne pour un mur horizontal
	 * @return un boolean, true si le mur à été placé, sinon false
	 */
	public boolean putWall(Grid board, Location loc){
		if (numberOfWall > 0 && loc.isWallHorizontal() && ARules.rPutWall(loc) && ARules.rSlotFull(loc)) {
			for (int j = loc.getLocX(); j < loc.getLocX() + 3; j++) {
				board.setItemInGrid(new Location(j, loc.getLocY()), true);
			}
			numberOfWall--; //anticipation pour quand on va devoir check si il a encore des murs etc...
			return true;
			
		}else if (numberOfWall > 0 && loc.isWallVertical() && ARules.rPutWall(loc) && ARules.rSlotFull(loc)) {
			for (int i = loc.getLocY(); i < loc.getLocY() + 3; i++) {
				board.setItemInGrid(new Location(loc.getLocX(), i), true);
			}
			numberOfWall--;
			return true;
		}
		return false; 
	}
	
	
	/**
	 * getter
	 * @return un objet Location représentant la position du joueur(pion) sur la grille
	 */
	public Location getLoc() { return new Location(loc.getLocX(), loc.getLocY()); }
	
	/**
	 * setter
	 * @param loc un objet Location représentant la position du joueur(pion) sur la grille
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	
	public int getNbreOfWall() {
		return numberOfWall;
	}
	
	/**
	 * getter
	 * @return le "numero" du joueur (joueur 1, 2, 3 ou 4)
	 */
	public int getOrder() {
		return orderNumber;
	}
}
