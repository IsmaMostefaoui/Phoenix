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
	
	//joueur doit être une variable d'instance, on l utilise partout
	
	
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
	
	
	/**
	 * Vérifie si le pion peut sauter sur la case cible (pas de pion et pas de mur)
	 * @param locPion un objet Location representant les coordonnées d'une case
	 * @param loc la position de la case cible
	 * @return un boolean vrai si le pion peut bouger, faux sinon
	 */
	public static boolean rMovePion(Location locPion, Location loc) {
		boolean inGrid = loc.inGrid(board) && locPion.inGrid(board);
		return (inGrid && !ARules.rCheckWall(locPion, loc) && !board.getItem(loc).getFull());
	}
	
	
	/**
	 * Vérifie si le mur peut être posé aux coordonées souhaitées
	 * @param loc Un objet Location representant la fente cible
	 * @return un boolean, true si le mur peut potentiellement être posé à cette position, sinon false
	 */
	public static boolean rPutWall(Location loc) {
		return !loc.isSquare();
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
				if (!loctemp.inGrid(board) || board.getItem(new Location(j, loc.getLocY())).getFull())
					return false;	
			}return true;
		}else if (loc.isWallVertical()){
			for (int i = loc.getLocY(); i < loc.getLocY() + 3; i++) {
				loctemp = new Location(loc.getLocX(), i);
				if (!loctemp.inGrid(board) || board.getItem(new Location(loc.getLocX(), i)).getFull())
					return false;	
			}return true;
		}
		return false;
	}
	
	
	/**
	 * Verifie  si un mur se situe entre le joueur et  dans la position souhaitée souhaitée
	 * @param player l'instance du joueur qui doit verifier le mur
	 * @param loc un objet Location représentant la position de la case cible
	 * @return true s'il y a un mur, false sinon
	 */
	public static boolean rCheckWall(Player player, Location loc) {
		int xPion = player.getLoc().getLocX();
		int yPion = player.getLoc().getLocY();
		int ytemp = loc.getLocY() - yPion;
		int xtemp = loc.getLocX() - xPion;
		ytemp = yPion + ytemp/2;
		xtemp = xPion + xtemp/2;
		Location loctemp = new Location(xtemp, ytemp);
		return loctemp.inGrid(board) && board.getItem(loctemp).getFull();
	}
	
	
	/**
	 * Check si un mur se situe dans la direction souhaitée
	 * @param locPion la locattion du pion courant
	 * @param loc un objet Location représentant la position de la case cible
	 * @return true s'il y a un mur false sinon
	 */

	public static boolean rCheckWall(Location locPion, Location loc) {
		int ytemp = loc.getLocY() - locPion.getLocY();
		int xtemp = loc.getLocX() - locPion.getLocX();
		ytemp = locPion.getLocY() + ytemp/2;
		xtemp = locPion.getLocX() + xtemp/2;
		Location loctemp = new Location(xtemp, ytemp);
		return loctemp.inGrid(board) && board.getItem(loctemp).getFull();
	}

	/**
	 * Store dans une liste les positions accessibles par un joueur
	 * @param player l'instance du joueur 
	 * @return une liste d'objet Location des cases accecibles par le joueur
	 */
	public static List<Location> rSquareAvailable(Player player) {
		List<Location> squareAvailable = new ArrayList<Location>();
		Location locPlayer = player.getLoc();
		//Location de toutes les cases autour du joueur
		Location locUP = locPlayer.itemUp();
		Location locUPUP = locUP.itemUp(); //LOC de la case upup (saut)

		Location locDOWN = locPlayer.itemDown() ;
		Location locDOWNDOWN = locDOWN.itemDown(); //LOC de la case DOWNDOWN (saut)
	
		Location locLEFT = locPlayer.itemLeft();
		Location locLEFTLEFT = locLEFT.itemLeft(); //LOC de la case LEFTLEFT (saut)
	
		Location locRIGHT = locPlayer.itemRight();
		Location locRIGHTRIGHT = locRIGHT.itemRight(); //LOC de la case RIGHT (saut)

		Location locDHG = locUP.itemLeft(); //loc de la pos en haut à gauche
		Location locDHD = locUP.itemRight(); //loc de la pos en haut à droite
		Location locDBD = locDOWN.itemRight(); //loc de la pos en Bas à droite
		Location locDBG = locDOWN.itemLeft(); //loc de la pos en BAS  à gauche
		
		//Diago haut gauche et haut Droite + faceToFace UP
		if (!rCheckWall(player, locUP)) { //si pas de mur UP
			if (locUP.inGrid(board) && board.getItem(locUP).getFull()) { // et que la case UP est remplie
				if (rMovePion(locUP, locUPUP)) { //si on peut bouger de la case up à la case UPUP -> Saut
					squareAvailable.add(locUPUP);
				} 
				if ((!locUPUP.inGrid(board) || rCheckWall(locUP, locUPUP)) && (rMovePion(locUP, locDHG))) { //si il y a un mur au dessus de la case UP(ou bord du board) et case à gauche libre diagHG ok 
					squareAvailable.add(locDHG);
				} 
				if ((!locUPUP.inGrid(board) || rCheckWall(locUP,locUPUP)) && (rMovePion(locUP, locDHD))) { //si il y a un mur au dessus de la case UP(ou bord du board) et case à droite libre diagHD ok
					squareAvailable.add(locDHD);
				}	
			}else if(locUP.inGrid(board)) {
				squareAvailable.add(locUP);	
			}
		}
		
		//Diago haut droite et bas droite + faceToFace Right 
		if (!rCheckWall(player, locRIGHT)) { //si pas de mur RIGHT
			if (locRIGHT.inGrid(board) && board.getItem(locRIGHT).getFull()) { // et que la case RIGHT est remplie
				if (rMovePion(locRIGHT, locRIGHTRIGHT)) { //si on peut bouger de la case RIGHT à la case RIGHRIGHT -> Saut
					squareAvailable.add(locRIGHTRIGHT);	
				} 
				if ((!locRIGHTRIGHT.inGrid(board) || rCheckWall(locRIGHT, locRIGHTRIGHT)) && (rMovePion(locRIGHT, locDHD))) { //si il y a un mur au dessus(ou bord du board) de la case RIGHT et case en haut libre diagHD ok 
					squareAvailable.add(locDHD);	
				} 
				if ((!locRIGHTRIGHT.inGrid(board) || rCheckWall(locRIGHT,locRIGHTRIGHT)) && (rMovePion(locRIGHT, locDBD))) { //si il y a un mur en dessous(ou bord du board)  de la case right et case en bas libre diagHD ok
					squareAvailable.add(locDBD);
				}		
			}else if(locRIGHT.inGrid(board)) {
				squareAvailable.add(locRIGHT);
			}
		}
		//Diago bas droite et bas gauche + faceToFace DOWN
		if (!rCheckWall(player, locDOWN)) { //si pas de mur DOWN
			if (locDOWN.inGrid(board) && board.getItem(locDOWN).getFull()) { // et que la case DOWN est remplie
				if (rMovePion(locDOWN, locDOWNDOWN)) { //si on peut bouger de la case DOWN à la case DOWN -> Saut
					squareAvailable.add(locDOWNDOWN);
				} 
				if ((!locDOWNDOWN.inGrid(board) || rCheckWall(locDOWN, locDOWNDOWN)) && (rMovePion(locDOWN, locDBD))) { //si il y a un mur EN dessOus de la case DOWN(ou bord du board) et case en BAS DROITE libre diagBD ok 
					squareAvailable.add(locDBD);
				} 
				if ((!locDOWNDOWN.inGrid(board) || rCheckWall(locDOWN,locDOWNDOWN)) && (rMovePion(locDOWN, locDBG))) { //si il y a un mur en dessous  de la case right(ou bord du board) et case en bas A GAUCHE libre diagBG ok
					squareAvailable.add(locDBG);
				}	
			}else if (locDOWN.inGrid(board)) {
				squareAvailable.add(locDOWN);
			}
		}
		//Diago HAUT GAUCHE et bas gauche + faceToFace LEFT
		if (!rCheckWall(player, locLEFT)) { //si pas de mur LEFT	
			if (locLEFT.inGrid(board) && board.getItem(locLEFT).getFull()) { // et que la case LEFT est remplie
				if (rMovePion(locLEFT, locLEFTLEFT)) { //si on peut bouger de la case LEFT à la case LEFTLEFT -> Saut
					squareAvailable.add(locLEFTLEFT);
				} 
				if ((!locLEFTLEFT.inGrid(board) || rCheckWall(locLEFT, locLEFTLEFT)) && (rMovePion(locLEFT, locDBG))) { //si il y a un mur A GAUCHE de la case LEFT(ou bord du board) et case BAS GAUCHE LIBRE -> diagBG ok 
					squareAvailable.add(locDBG);
				} 
				if ((!locLEFTLEFT.inGrid(board) || rCheckWall(locLEFT,locLEFTLEFT)) && (rMovePion(locLEFT, locDHG))) { //si il y a un mur A GAUCHE  de la case LEFT(ou bord du board) et case en HAUT GAUCHE  libre diagHG ok
					squareAvailable.add(locDHG);
				}	
			}else if(locLEFT.inGrid(board)) { //si pas de mur et que la case n'est pas remplie
				squareAvailable.add(locLEFT);
			}
		}
		return squareAvailable;
	}
	
	
	/**
	 * Surcharge pour utiliser avec une location et pas un player
	 * Store dans une liste les positions accessibles par un joueur
	 * @param loc l'instance de la position cible
	 * @return une liste d'objet Location des cases accecibles par le joueur
	 */
	public static ArrayList<Location> rSquareAvailable(Location player) {
		//utiliser dans square pour la methode blocked (//path finidng)
		ArrayList<Location> squareAvailable = new ArrayList<Location>();
		Location locPlayer = player;
		//Location de toutes les cases autour du joueur
		Location locUP = locPlayer.itemUp();
		Location locUPUP = locUP.itemUp(); //LOC de la case upup (saut)

		Location locDOWN = locPlayer.itemDown() ;
		Location locDOWNDOWN = locDOWN.itemDown(); //LOC de la case DOWNDOWN (saut)
	
		Location locLEFT = locPlayer.itemLeft();
		Location locLEFTLEFT = locLEFT.itemLeft(); //LOC de la case LEFTLEFT (saut)
	
		Location locRIGHT = locPlayer.itemRight();
		Location locRIGHTRIGHT = locRIGHT.itemRight(); //LOC de la case RIGHT (saut)
		
		Location locDHG = locUP.itemLeft(); //loc de la pos en haut à gauche
		Location locDHD = locUP.itemRight(); //loc de la pos en haut à droite
		Location locDBD = locDOWN.itemRight(); //loc de la pos en Bas à droite
		Location locDBG = locDOWN.itemLeft(); //loc de la pos en BAS  à gauche
		
		//Diago haut gauche et haut Droite + faceToFace UP
		if (!rCheckWall(player, locUP)) { //si pas de mur UP
			if (locUP.inGrid(board) && board.getItem(locUP).getFull()) { // et que la case UP est remplie
				if (rMovePion(locUP, locUPUP)) { //si on peut bouger de la case up à la case UPUP -> Saut
					squareAvailable.add(locUPUP);
				} 
				if ((!locUPUP.inGrid(board) || rCheckWall(locUP, locUPUP)) && (rMovePion(locUP, locDHG))) { //si il y a un mur au dessus de la case UP(ou bord du board) et case à gauche libre diagHG ok 
					squareAvailable.add(locDHG);
				} 
				if ((!locUPUP.inGrid(board) || rCheckWall(locUP,locUPUP)) && (rMovePion(locUP, locDHD))) { //si il y a un mur au dessus de la case UP(ou bord du board) et case à droite libre diagHD ok
					squareAvailable.add(locDHD);
				}	
			}else if(locUP.inGrid(board)) {
				squareAvailable.add(locUP);	
			}
		}
		
		//Diago haut droite et bas droite + faceToFace Right 
		if (!rCheckWall(player, locRIGHT)) { //si pas de mur RIGHT
			if (locRIGHT.inGrid(board) && board.getItem(locRIGHT).getFull()) { // et que la case RIGHT est remplie
				if (rMovePion(locRIGHT, locRIGHTRIGHT)) { //si on peut bouger de la case RIGHT à la case RIGHRIGHT -> Saut
					squareAvailable.add(locRIGHTRIGHT);	
				} 
				if ((!locRIGHTRIGHT.inGrid(board) || rCheckWall(locRIGHT, locRIGHTRIGHT)) && (rMovePion(locRIGHT, locDHD))) { //si il y a un mur au dessus(ou bord du board) de la case RIGHT et case en haut libre diagHD ok 
					squareAvailable.add(locDHD);	
				} 
				if ((!locRIGHTRIGHT.inGrid(board) || rCheckWall(locRIGHT,locRIGHTRIGHT)) && (rMovePion(locRIGHT, locDBD))) { //si il y a un mur en dessous(ou bord du board)  de la case right et case en bas libre diagHD ok
					squareAvailable.add(locDBD);
				}		
			}else if(locRIGHT.inGrid(board)) {
				squareAvailable.add(locRIGHT);
			}
		}
		//Diago bas droite et bas gauche + faceToFace DOWN
		if (!rCheckWall(player, locDOWN)) { //si pas de mur DOWN
			if (locDOWN.inGrid(board) && board.getItem(locDOWN).getFull()) { // et que la case DOWN est remplie
				if (rMovePion(locDOWN, locDOWNDOWN)) { //si on peut bouger de la case DOWN à la case DOWN -> Saut
					squareAvailable.add(locDOWNDOWN);
				} 
				if ((!locDOWNDOWN.inGrid(board) || rCheckWall(locDOWN, locDOWNDOWN)) && (rMovePion(locDOWN, locDBD))) { //si il y a un mur EN dessOus de la case DOWN(ou bord du board) et case en BAS DROITE libre diagBD ok 
					squareAvailable.add(locDBD);
				} 
				if ((!locDOWNDOWN.inGrid(board) || rCheckWall(locDOWN,locDOWNDOWN)) && (rMovePion(locDOWN, locDBG))) { //si il y a un mur en dessous  de la case right(ou bord du board) et case en bas A GAUCHE libre diagBG ok
					squareAvailable.add(locDBG);
				}	
			}else if (locDOWN.inGrid(board)) {
				squareAvailable.add(locDOWN);
			}
		}
		//Diago HAUT GAUCHE et bas gauche + faceToFace LEFT
		if (!rCheckWall(player, locLEFT)) { //si pas de mur LEFT	
			if (locLEFT.inGrid(board) && board.getItem(locLEFT).getFull()) { // et que la case LEFT est remplie
				if (rMovePion(locLEFT, locLEFTLEFT)) { //si on peut bouger de la case LEFT à la case LEFTLEFT -> Saut
					squareAvailable.add(locLEFTLEFT);
				} 
				if ((!locLEFTLEFT.inGrid(board) || rCheckWall(locLEFT, locLEFTLEFT)) && (rMovePion(locLEFT, locDBG))) { //si il y a un mur A GAUCHE de la case LEFT(ou bord du board) et case BAS GAUCHE LIBRE -> diagBG ok 
					squareAvailable.add(locDBG);
				} 
				if ((!locLEFTLEFT.inGrid(board) || rCheckWall(locLEFT,locLEFTLEFT)) && (rMovePion(locLEFT, locDHG))) { //si il y a un mur A GAUCHE  de la case LEFT(ou bord du board) et case en HAUT GAUCHE  libre diagHG ok
					squareAvailable.add(locDHG);
				}	
			}else if(locLEFT.inGrid(board)) { //si pas de mur et que la case n'est pas remplie
				squareAvailable.add(locLEFT);
			}
		}
		return squareAvailable;
	}
	
	
	
	public static ArrayList<Location> rWallAvailable() {
		ArrayList<Location> wall = new ArrayList<Location>();
		for (int x = 0; x <= 16; x =x+2) {
			for (int y = 0; y<= 16; y = y+2) {
				Location locSquare = new Location(x,y);
				if (!wall.contains(locSquare.wallUp()) && locSquare.wallUp().inGrid(board) && !board.getItem(locSquare.wallUp()).getFull()) {
					wall.add(locSquare.wallUp());
				}
				if (!wall.contains(locSquare.wallDown()) && locSquare.wallDown().inGrid(board) && !board.getItem(locSquare.wallDown()).getFull()) {
					wall.add(locSquare.wallDown());
				}
				if (!wall.contains(locSquare.wallLeft()) && locSquare.wallLeft().inGrid(board) && !board.getItem(locSquare.wallLeft()).getFull()) {
					wall.add(locSquare.wallLeft());
				}
				if (!wall.contains(locSquare.wallRight()) && locSquare.wallRight().inGrid(board) && !board.getItem(locSquare.wallRight()).getFull()) {
					wall.add(locSquare.wallRight());
				}
			}
		}
	
		return wall;
	}
	
	/**
	 * Permet d'initaliser l'attribut board de ARules
	 * @param board une instance de grille représentant la grille du jeu sur laquelle les règle vons s'effectuer
	 */
	public static void setBoard(Grid board) {
		ARules.board = board;
	}
	

}
	
