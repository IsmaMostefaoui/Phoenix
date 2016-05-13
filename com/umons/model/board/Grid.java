package com.umons.model.board;


import java.util.ArrayList;
import java.io.Serializable;



/**
 * Modélise une grille avec ces setter d'item et ses accesseurs (+fonction de cout)
 * @author isma
 * @author robin
 *
 */
public class Grid implements Serializable{

	private static final long serialVersionUID = 690341445697163612L;

	private Item[][] board;
	private static final int LENGTH = 17; //sans les bords
	private ArrayList<Location>wall = new ArrayList<Location>();
	
	
	/**
	 * Initialise la grille avec 2 joueurs. Remplis aussi la grille !
	 * @param joueur1
	 * @param joueur2
	 */
	public Grid() {

		board = new Item[LENGTH][LENGTH];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new Item(new Location(j, i));
			}
		}
	}
	
	/**
	 * Calcule le cout d'un mouvement
	 * @param current la position de la case de depart
	 * @param target la position de la case d arrive, celle ou l objet va se deplacer
	 * @return un int 2 ou 4 (pour les saut)
	 */
	public float getMovementCost(Location current, Location target) {
		if(Math.abs(current.getLocX() - target.getLocX()) == 4 ^ Math.abs(current.getLocY() - target.getLocY()) == 4) {
			return 2f;
		}
		else if ((Math.abs(current.getLocX() - target.getLocX()) == 2 && Math.abs(current.getLocY() - target.getLocY()) == 2)){
			return 1.4f;
		}else {
			return 1.f;
		}
	}
	
	
	/**
	 * @return la longueur du board
	 */
	public static int getLen() {
		
		return LENGTH;
	}
	
	/**
	 * 
	 * @param i le numéro de la ligne
	 * @param j le numéro de la colonne
	 * @return un objet de type Item à l'emplacement i, j
	 */
	public Item getItem(Location loc) {
		return board[loc.getLocY()][loc.getLocX()];
	}

	/**
	 * Rempli ou non  la case
	 * @param x est l'abcisse
	 * @param y l'ordonnée
	 * @param full le caractère rempli de la case associé à la position (i, j)
	 */
	public void setItemInGrid(int x, int y, boolean full) {
		
		board[y][x].setFull(full);
	}
	
	/**
	 * surcharge de setItemIngrid
	 * @param loc un objet Location représentant la position de l'item cible dans la grile
	 * @param b un boolean true si la case doit etre remplie, false sinon
	 */
	public void setItemInGrid(Location loc, boolean b) {
		this.getItem(loc).setFull(b);
		
	}
	/**
	 * Getteur de Wall (contientla Location de tous les murs de la grille)
	 * @param wallLoc La Location du mur
	 */
	public void setWall(Location wallLoc) {
		wall.add(wallLoc);
	}
	
	public ArrayList<Location> getWall() {
		return wall;
	}
	
	public void resetGrid() {		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new Item(new Location(j, i));
			}
		}
		
	}
	
}
