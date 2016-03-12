package com.umons.model;
import java.util.*;
/**
 * Modélise l'ensemble des règles communes à tous les modes de jeux
 * @author isma
 * @author robin
 *
 */
public abstract class ARules {

	protected static Grid board;
	List<Location> squareAvaliable = new ArrayList<Location>();
	
	//joueur doit être une variable d'instance, on l utilise partout
	
	/**
	 * Initialise un board (celui sur lequel sera appliquer toutes les règles.
	 * @param grid la board
	 */
	public ARules(Grid board) {
		this.board = board;
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
	
	
	/*surcharge pour pouvoir utiliser la méthode avec des coordonnées indépendante
	public static boolean rMovePion(Player joueur, int x, int y) {
		boolean inGrid = rStillInGrid(x, y);
		return (inGrid && !Rules.rCheckWall(joueur.getPawnX(), joueur.getPawnY() , x, y) && !board.getItem(y, x).getFull());
	}*/
	
	/**
	 * @param joueur une instance de Player
	 * @param loc un objet location correspondant à la case cible
	 * @return un tableau comprenant la 'distance' entre la position actuelle du pion en la position souhaitée
	 */
	public static int[] rDeplacement(Player joueur, Location loc) {
		int xtemp = Math.abs(loc.getLocX() - joueur.getLoc().getLocX());
		int ytemp = Math.abs(loc.getLocY() - joueur.getLoc().getLocY());
		//on verra, a determiner entre location et tableau ?? !! ?? !!
		int[] tabDeplacement = { xtemp, ytemp };
		return tabDeplacement;
	} 
		
	
	
	
	/**
	 * Vérifie si le joueur est dans une situation de faceToFace
	 * @param joueur une instance de la class Player
	 * @param tabCoord un tableau de type int contenant les coordonées (x, y) correspondant à la position ou le pion compte se rendre
	 * @return true si le joueur peu sauter au dessus du Pion adverse(pas de mur derriere,pas en dehors du board de jeu),sinon false
	 */
	public static boolean rFaceToFace (Player joueur, Location loc) {
		return true;
	}
	
	
	/*
	 * INUTILE FINALEMENT
	 */
	/*
	public int[] rDiagDirection(Player joueur, int[]tabCoord) {
		int[] tabDirection = new int [2];
		if (tabCoord[0] - joueur.getPawnX() == 2 && tabCoord[1] - joueur.getPawnY() == -2) {
			tabDirection[0] = RIGHT; tabDirection[1] = UP;
		}else if (tabCoord[0] - joueur.getPawnX() == -2 && tabCoord[1] - joueur.getPawnY() == -2) {
			tabDirection[0] = UP; tabDirection[1] = LEFT;
		}else if (tabCoord[0] - joueur.getPawnX() == -2 && tabCoord[1] - joueur.getPawnY() == 2) {
			tabDirection[0] = LEFT; tabDirection[1] = DOWN;
		}else if (tabCoord[0] - joueur.getPawnX() == 2 && tabCoord[1] - joueur.getPawnY() == 22) {
			tabDirection[0] = DOWN; tabDirection[1] = RIGHT;
		}else{
			tabDirection[0] = null; tabDirection[1] = null;
		}return tabDirection;
	}
	*/
	/*
	public static boolean rDiagFace(Player joueur, Location loc) {
		int xPion = joueur.getLoc().getLocX(); //parce que c'est chiant d ecrire a chaque joueur.getPawnX()
		int yPion = joueur.getLoc().getLocY();
		int xDest = loc.getLocX();
		int yDest = loc.getLocY();
		Location locUp, locDown, locRight, locLeft;
		if (xDest - xPion == 2 && yDest - yPion == -2) { //soit il est bloqué en haut, soit a droite
			//donc on vérifie si c'est le cas
			//attention je ne test pas le stillingrid, je dois encore trouver une façon de raccourcir ce bordel
			//en gros l'idée c'est qu'il peut bouger en diagonal que si la case en face de lui, ou a droite de lui est occupé avec un mur en face
			return (board.getItem(yPion-2, xPion).getFull() && rCheckWall(xPion, yPion-2, xPion, yPion-4)) || (board.getItem(yPion, xPion+2).getFull() && rCheckWall(xPion+2, yPion, xPion+4, yPion));
		}else if (tabCoord[0] - joueur.getPawnX() == -2 && tabCoord[1] - joueur.getPawnY() == -2) {
			return (board.getItem(yPion-2, xPion).getFull() && rCheckWall(xPion, yPion-2, xPion, yPion-4)) || (board.getItem(yPion, xPion-2).getFull() && rCheckWall(xPion-2, yPion, xPion-4, yPion));
		}else if (tabCoord[0] - joueur.getPawnX() == -2 && tabCoord[1] - joueur.getPawnY() == 2) {
			return (board.getItem(yPion, xPion-2).getFull() && rCheckWall(xPion-2, yPion, xPion-4, yPion)) || (board.getItem(yPion+2, xPion).getFull() && rCheckWall(xPion, yPion+2, xPion, yPion+4));
		}else if (tabCoord[0] - joueur.getPawnX() == 2 && tabCoord[1] - joueur.getPawnY() == 2) {
			return (board.getItem(yPion+2, xPion).getFull() && rCheckWall(xPion, yPion+2, xPion, yPion+4)) || (board.getItem(yPion, xPion+2).getFull() && rCheckWall(xPion+2, yPion, xPion+4, yPion));
		}return false;
	}
	*/
	/**
	 * Vérifie si le mur peut être posé aux coordonées souhaitées
	 * @return un boolean, true si le mur peut potentiellement être posé à cette position, sinon false
	 */
	public static boolean rPutWall(Location loc) {
		return !loc.lSquare();
	}
	
	/**
	 * Vérifie si le mur est placé dans une fente(Slot) libre
	 * @param loc un objet location representant la case cible
	 * @return un boolean, true si le mur peut potentiellement être posé à cette position, sinon false
	 */
	public static boolean rSlotFull (Location loc) {
		Location loctemp;
		if (loc.isWallHorizontal()) {
			for (int j = loc.getLocX(); j < loc.getLocX() + 3; j++) {
				loctemp = new Location(j, loc.getLocY());
				if (!loctemp.inGrid(board) && board.getItem(new Location(j, loc.getLocY())).getFull())
					return false;	
			}return true;
		}else if (loc.isWallVertical()){
			for (int i = loc.getLocY(); i < loc.getLocY() + 3; i++) {
				loctemp = new Location(loc.getLocX(), i);
				if (!loctemp.inGrid(board) && board.getItem(new Location(loc.getLocX(), i)).getFull())
					return false;	
			}return true;
		}
		return false;
	}

	
	/**
	 * Check si un mur se situe dans la direction souhaitée
	 * @param player l'instance du joueur qui doit verifier le mur
	 * @param loc un objet Location représentant la position de la case cible
	 * @return true s'il y a un mur false sinon
	 */
	public static boolean rCheckWall(Player player, Location loc) {
		int ytemp = loc.getLocY() - player.getLoc().getLocY();
		int xtemp = loc.getLocX() - player.getLoc().getLocX();
		ytemp = loc.getLocY() + ytemp/2;
		xtemp = loc.getLocX() + xtemp/2;
		Location loctemp = new Location(xtemp, ytemp);
		return loctemp.inGrid(board) && board.getItem(new Location(xtemp, ytemp)).getFull();
	}
	
	/**
	 * Check si un mur se situe dans la direction souhaitée
	 * @param player l'instance du joueur qui doit verifier le mur
	 * @param loc un objet Location représentant la position de la case cible
	 * @return true s'il y a un mur false sinon
	 */
	public static boolean rCheckWall(int xPion, int yPion, Location loc) {
		int ytemp = loc.getLocY() - yPion;
		int xtemp = loc.getLocX() - xPion;
		ytemp = loc.getLocY() + ytemp/2;
		xtemp = loc.getLocX() + xtemp/2;
		Location loctemp = new Location(xtemp, ytemp);
		return loctemp.inGrid(board) && board.getItem(new Location(xtemp, ytemp)).getFull();
	}
	
	/*
	//surcharge de checkWall pour pourvoir l'utiliser avec des coordonnées indépendante
	/**
	 * Check si un mur se situe dans la direction souhaitée
	 * @param xPion la coordonnée x du pion
	 * @param yPion la coordonnée y du pion
	 * @param x et y les coordonées (x, y) correspondant à la position sur laquelle le pion compte se rendre
	 * @return true s'il y a un mur false sinon
	 *
	public static boolean rCheckWall(int xPion, int yPion, int x, int y) {
		if (rStillInGrid(x, y)) { //ATTENTION, quand on regarde s'il n'y a pas de mur, on ne regarde plus si la postion est dans la grille
								  // car quand on fait !rChecWall(), on aura vrai si l'item est vide en position ytemp et xtemp
								  // mais on a aussi vrai quand rStillInGrid(x, y) retourne faux, et qu'on entre pas de le if !!!
								  // a regler urgent !
			int ytemp = y - yPion;
			int xtemp = x - xPion; // inutile non ? Puisque c'est le meme calcul que dans facetoface
			ytemp = yPion + ytemp/2; // mettre un test de stillingrid des qu'un param peut potentielment nous causer probleme
			xtemp = xPion + xtemp/2; // donc a changer, je crois
			return board.getItem(ytemp, xtemp).getFull();
		}else {
			return false;
		}
	}*/
	
}
	

	
