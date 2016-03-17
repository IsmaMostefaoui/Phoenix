package com.umons.model;

public class Rules extends ARules{

	public Rules(Grid board) {
		super(board);
	}
	
	
	/**
	 * Vérifie si le joueur est dans une situation de faceToFace
	 * @param joueur une instance de la class Player
	 * @param tabCoord un tableau de type int contenant les coordonées (x, y) correspondant à la position ou le pion compte se rendre
	 * @return true si le joueur peu sauter au dessus du Pion adverse(pas de mur derriere,pas en dehors du board de jeu),sinon false
	 */
	public static boolean rFaceToFace (Player joueur, Location loc) {
		//ici, directement tester que tabcoord n'est pas hors indice, ca evite des test inutile
		int xtemp = (loc.getLocX() - joueur.getLoc().getLocX()) / 2 + joueur.getLoc().getLocX();
		int ytemp = (loc.getLocY() - joueur.getLoc().getLocY()) / 2 + joueur.getLoc().getLocY();
		Location loctemp = new Location(xtemp, ytemp);
		return (loctemp.inGrid(board) && loc.inGrid(board) && !rCheckWall(xtemp, ytemp, loc) && board.getItem(loctemp).getFull());
			
	}
	
	/**
	 * Vérifie si le pion peut sauter sur la case cible (pas de pion et pas de mur)
	 * @param joueur l'instance courante du joueur qui veut bouger son pion
	 * @param loc la position de la case cible
	 * @return un boolean vrai si le pion peut bouger, faux sinon
	 */
	public static boolean rMovePion(Player player, Location loc) {
		boolean inGrid = loc.inGrid(board);
		return (inGrid && !ARules.rCheckWall(player, loc) && !board.getItem(loc).getFull());
	}

}
