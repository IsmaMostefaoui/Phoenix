package com.umons.model;

/**
 * Modélise une grille.
 * Possède :
 * -Une méthode pour afficher la grille
 * @author isma
 * @author robin
 *
 */
public class Grid {
	//Herite des methodes de Rules

	private Square[][] board;
	Player player1, player2, player3, player4;
	private final int LENGTH = 17; //sans les bords
	
	
	/**
	 * Initialise la grille avec 2 joueurs. Remplis aussi la grille !
	 * @param joueur1
	 * @param joueur2
	 */
	public Grid(Player player1, Player player2) {

		board = new Square[LENGTH][LENGTH];
		this.player1 = player1; this.player2 = player2;
		
		for (int i = 0; i < board.length; i+=2) {
			for (int j = 0; j < board.length; j+=2) {
				board[i][j] = new Square(new Location(j, i));
			}
		}
		for (int i = 0; i < board.length; i+=2) {
			for (int j = 1; j < board.length; j+=2) { 
				board[i][j] = new Wall(new Location(j, i));
			}
		}
		for (int i = 1; i < board.length; i+=2) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new Wall(new Location(j, i));
			}
		}
	}
	
	/**
	 * Initialise la grille avec 4 joueurs. Rempli aussi la grille !!
	 * @param joueur1
	 * @param joueur2
	 * @param joueur3
	 * @param joueur4
	 */
	public Grid(Player player1, Player player2, Player player3, Player player4) {

		board = new Square[LENGTH][LENGTH];
		this.player1 = player1; this.player2 = player2;
		this.player3 = player3; this.player4 = player4;
		
		for (int i = 0; i < board.length; i+=2) {
			for (int j = 0; j < board.length; j+=2) {
				board[i][j] = new Square(new Location(j, i));
			}
		}
		for (int i = 1; i < board.length; i+=2) {
			for (int j = 1; j < board.length; j+=2) { 
				board[i][j] = new Wall(new Location(j, i));
			}
		}
		for (int i = 1; i < board.length; i+=2) {
			for (int j = 1; j < board.length; j++) {
				board[i][j] = new Wall(new Location(j, i));
			}
		}
	}
	
	public void afficheGrid(Player player1, Player player2){
		for (int i = 0; i < board.length; i++) {
			//Affiche les numéros de lignes pour faciliter les tests
			if (i<10){
				System.out.print("" + i + "  ");
			}else{
				System.out.print("" + i + " ");
			}
			for (int j = 0; j < board.length; j++) {
				if (player1.getLoc() == board[i][j].getLocation()) {
					System.out.print("[1]");
				}else if (player2.getLoc() == board[i][j].getLocation()) {
					System.out.print("[2]");
				}else {
					System.out.print(board[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println("    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16");
	}
	
	/**
	 * @return la longueur du board
	 */
	public int getLen() {
		
		return LENGTH;
	}
	
	/**
	 * 
	 * @param i le numéro de la ligne
	 * @param j le numéro de la colonne
	 * @return un objet de type Item à l'emplacement i, j
	 */
	public Square getItem(Location loc) {
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
	
	
}
