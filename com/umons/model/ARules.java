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
	
	/**
	 * Vérifie si le pion peut sauter sur la case cible (pas de pion et pas de mur)
	 * @param locPion un objet Location representant les coordonnées de la case cible
	 * @param loc la position de la case cible
	 * @return un boolean vrai si le pion peut bouger, faux sinon
	 */
	public static boolean rMovePion(Location locPion, Location loc) {
		boolean inGrid = loc.inGrid(board) && locPion.inGrid(board);
		return (inGrid && !ARules.rCheckWall(locPion, loc) && !board.getItem(loc).getFull());
	}
	
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
		
	
	
	
	/*
	 * Vérifie si le joueur est dans une situation de faceToFace
	 * @param joueur une instance de la class Player
	 * @param tabCoord un tableau de type int contenant les coordonées (x, y) correspondant à la position ou le pion compte se rendre
	 * @return true si le joueur peu sauter au dessus du Pion adverse(pas de mur derriere,pas en dehors du board de jeu),sinon false
	 *
	public abstract boolean rFaceToFace (Player joueur, Location loc);
	*/
	
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
		int xPion = player.getLoc().getLocX();
		int yPion = player.getLoc().getLocX();
		int ytemp = loc.getLocY() - yPion;
		int xtemp = loc.getLocX() - xPion;
		ytemp = loc.getLocY() + ytemp/2;
		xtemp = loc.getLocX() + xtemp/2;
		Location loctemp = new Location(xtemp, ytemp);
		return loctemp.inGrid(board) && board.getItem(loctemp).getFull();
	}
	
	
	/**
	 * Check si un mur se situe dans la direction souhaitée
	 * @param locPion laocattion du pion courant
	 * @param loc un objet Location représentant la position de la case cible
	 * @return true s'il y a un mur false sinon
	 */
	public static boolean rCheckWall(Location locPion, Location loc) {
		int ytemp = loc.getLocY() - locPion.getLocY();
		int xtemp = loc.getLocX() - locPion.getLocX();
		ytemp = loc.getLocY() + ytemp/2;
		xtemp = loc.getLocX() + xtemp/2;
		Location loctemp = new Location(xtemp, ytemp);
		return loctemp.inGrid(board) && board.getItem(loctemp).getFull();
	}
	
	/**
	 * Check si un mur se situe dans la direction souhaitée
	 * @param xPion la coordonnée x du pion
	 * @param yPion la coordonnée y du pion
	 * @param x et y les coordonées (x, y) correspondant à la position sur laquelle le pion compte se rendre
	 * @return true s'il y a un mur false sinon
	 */
	public static boolean rCheckWall(int xPion, int yPion, int x, int y) {
		int ytemp = y - yPion;
		int xtemp = x - xPion;
		ytemp = yPion + ytemp/2;
		xtemp = xPion + xtemp/2;
		Location loctemp = new Location(xtemp, ytemp);
		return board.getItem(loctemp).getFull();
	}
	
	
	public static List<Location> rSquareAvailable(Player player) {
		List<Location> squareAvailable = new ArrayList<Location>();
		int x = player.getLoc().getLocX();
		int y = player.getLoc().getLocY();
		Location locUP = new Location(x, y -2);
		if (!locUP.inGrid(board)) { locUP = null; }
		Location locDOWN = new Location(x, y +2);
		if (!locDOWN.inGrid(board)) { locDOWN = null; }
		Location locLEFT = new Location(x-2, y);
		if (!locLEFT.inGrid(board)) { locLEFT = null; }
		Location locRIGHT = new Location(x+2, y);
		if (!locRIGHT.inGrid(board)) { locRIGHT = null; }
		//UP
		if (locUP != null && !rCheckWall(player, locUP)) {
			System.out.println("test dans arules in locup verification");
			if (!board.getItem(locUP).getFull()) {
				System.out.println("test de getitem");
				squareAvailable.add(locUP);
			}else if (locUP.inGrid(board) && !rCheckWall(locUP, locUP.squareUp()) && !board.getItem(locUP.squareUp()).getFull()){
				squareAvailable.add(locUP.squareUp());
			}else if (ARules.rMovePion(locUP, locUP.squareRight()) && !ARules.rCheckWall(locUP, locUP.squareRight()) && !squareAvailable.contains(locUP.squareRight())){
				squareAvailable.add(locUP.squareRight());
			}
		}
		//DOWN
		if (locDOWN != null && !rCheckWall(player, locDOWN)) {
			if (!board.getItem(locDOWN).getFull()) {
				squareAvailable.add(locDOWN);
			}else if (locDOWN.inGrid(board) && !rCheckWall(locDOWN, locDOWN.squareUp()) && !board.getItem(locDOWN.squareUp()).getFull()){
				squareAvailable.add(locDOWN.squareUp());
			}else if (ARules.rMovePion(locDOWN, locDOWN.squareRight()) && !ARules.rCheckWall(locDOWN, locDOWN.squareRight()) && !squareAvailable.contains(locDOWN.squareRight())){
				squareAvailable.add(locDOWN.squareRight());
			}
		}
		//RIGHT
		if (locRIGHT != null && !rCheckWall(player, locRIGHT)) {
			if (!board.getItem(locRIGHT).getFull()) {
				squareAvailable.add(locRIGHT);
			}else if (locRIGHT.inGrid(board) && !rCheckWall(locRIGHT, locRIGHT.squareUp()) && !board.getItem(locRIGHT.squareUp()).getFull()){
				squareAvailable.add(locRIGHT.squareUp());
			}else if (ARules.rMovePion(locRIGHT, locRIGHT.squareRight()) && !ARules.rCheckWall(locRIGHT, locRIGHT.squareRight()) && !squareAvailable.contains(locRIGHT.squareRight())){
				squareAvailable.add(locRIGHT.squareRight());
			}
		}
		//LEFT
		if (locLEFT != null && !rCheckWall(player, locLEFT)) {
			if (!board.getItem(locLEFT).getFull()) {
				squareAvailable.add(locLEFT);
			}else if (locLEFT.inGrid(board) && !rCheckWall(locLEFT, locLEFT.squareUp()) && !board.getItem(locLEFT.squareUp()).getFull()){
				squareAvailable.add(locLEFT.squareUp());
			}else if (ARules.rMovePion(locLEFT, locLEFT.squareRight()) && !ARules.rCheckWall(locLEFT, locLEFT.squareRight()) && !squareAvailable.contains(locLEFT.squareRight())){
				squareAvailable.add(locLEFT.squareRight());
			}
		}
		return squareAvailable;
		
		
		
		/*
		//pas bon parce qu'alors, meme s'il y a un mur en face, tu l'autorise a aller dessus
		if (rMovePion(player, locUP)) {
			squareAvailable.add(locUP);		
		}
		if(rMovePion(player, locDOWN)) {
			squareAvailable.add(locDOWN);
		}
		if(rMovePion(player, locLEFT)) {
			squareAvailable.add(locLEFT);
		}
		if(rMovePion(player, locRIGHT)) {
			squareAvailable.add(locRIGHT);
		}
		//Diago haut gauche et haut Droite + faceToFace UP
		if (!rCheckWall(player, locUP)) { //si pas de mur UP
			System.out.println("pas de mur");
			if (locUP.inGrid(board) && board.getItem(locUP).getFull()) { // et que la case UP est remplie
				Location locUPUP = new Location(locUP.getLocX(), locUP.getLocY() - 2); //LOC de la case upup (saut)
				Location locDHG = new Location(locUP.getLocX() - 2, locUP.getLocY()); //loc de la pos en haut à gauche
				Location locDHD = new Location(locUP.getLocX() + 2, locUP.getLocY()); //loc de la pos en haut à droite
				Player joueurtempUP = new Player(locUP);//Instance player de la case UP pour utiliser les autres méthodes
				System.out.println("jtUP = " + "[" + joueurtempUP.getLoc().getLocX()+ ", " + joueurtempUP.getLoc().getLocY() + "]");
				if (rMovePion(joueurtempUP, locUPUP)) { //si on peut bouger de la case up à la case UPUP -> Saut
					squareAvailable.add(locUPUP);
					System.out.println("UPUP  " + "[" + locUPUP.getLocX()+ ", " + locUPUP.getLocY() + "]");
				
				}else if (rCheckWall(joueurtempUP, locUPUP) && (rMovePion(joueurtempUP, locDHG))) { //si il y a un mur au dessus de la case UP et case à gauche libre diagHG ok 
					squareAvailable.add(locDHG);
					System.out.println("DHG  " + "[" + locDHG.getLocX()+ ", " + locDHG.getLocY() + "]");
					
				}else if (rCheckWall(joueurtempUP,locUPUP) && (rMovePion(joueurtempUP, locDHD))) { //si il y a un mur au dessus de la case UP et case à droite libre diagHD ok
					squareAvailable.add(locDHD);
					System.out.println("DHD " + "[" + locDHD.getLocX()+ ", " + locDHD.getLocY() + "]");
				}	
			}	
		}
		
		//Diago haut droite et bas droite + faceToFace Right 
		else if (!rCheckWall(player, locRIGHT)) { //si pas de mur RIGHT
			if (locRIGHT.inGrid(board) && board.getItem(locRIGHT).getFull()) { // et que la case RIGHT est remplie
				Location locRIGHTRIGHT = new Location(locRIGHT.getLocX() + 2, locRIGHT.getLocY()); //LOC de la case RIGHT (saut)
				Location locDHD = new Location(locRIGHT.getLocX(), locRIGHT.getLocY() -2); //loc de la pos en haut à droite VIA RIGHT
				Location locDBD = new Location(locRIGHT.getLocX(), locRIGHT.getLocY() +2 ); //loc de la pos en Bas à droite
				Player joueurtempRIGHT = new Player(locRIGHT);//Instance player de la case RIGHT pour utiliser les autres méthodes
				if (rMovePion(joueurtempRIGHT, locRIGHTRIGHT)) { //si on peut bouger de la case RIGHT à la case RIGHRIGHT -> Saut
					squareAvailable.add(locRIGHTRIGHT);
					
				}else if (rCheckWall(joueurtempRIGHT, locRIGHTRIGHT) && (rMovePion(joueurtempRIGHT, locDHD))) { //si il y a un mur au dessus de la case RIGHT et case en haut libre diagHD ok 
					squareAvailable.add(locDHD);
					System.out.println("DHD  " + "[" + locDHD.getLocX()+ ", " + locDHD.getLocY() + "]");
					
				}else if (rCheckWall(joueurtempRIGHT,locRIGHTRIGHT) && (rMovePion(joueurtempRIGHT, locDBD))) { //si il y a un mur en dessous  de la case right et case en bas libre diagHD ok
					squareAvailable.add(locDBD);
					System.out.println("DBD  " + "[" + locDBD.getLocX()+ ", " + locDBD.getLocY() + "]");
				}		
			}		
		}
		//Diago bas droite et bas gauche + faceToFace DOWN
		else if (!rCheckWall(player, locDOWN)) { //si pas de mur DOWN
			if (locDOWN.inGrid(board) && board.getItem(locDOWN).getFull()) { // et que la case DOWN est remplie
				Location locDOWNDOWN = new Location(locDOWN.getLocX(), locDOWN.getLocY() +2 ); //LOC de la case DOWNDOWN (saut)
				Location locDBG = new Location(locDOWN.getLocX() -2, locDOWN.getLocY()); //loc de la pos en BAS  à GAUCHE VIA RIGHT
				Location locDBD = new Location(locDOWN.getLocX() +2, locDOWN.getLocY()); //loc de la pos en Bas à droite
				Player joueurtempDOWN = new Player(locDOWN);//Instance player de la case DOWN pour utiliser les autres méthodes
				if (rMovePion(joueurtempDOWN, locDOWNDOWN)) { //si on peut bouger de la case DOWN à la case DOWN -> Saut
					squareAvailable.add(locDOWNDOWN);
					System.out.println("DOWNDOWN  " + "[" + locDOWNDOWN.getLocX()+ ", " + locDOWNDOWN.getLocY() + "]");
				
				}else if (rCheckWall(joueurtempDOWN, locDOWNDOWN) && (rMovePion(joueurtempDOWN, locDBD))) { //si il y a un mur EN dessOus de la case DOWN et case en BAS DROITE libre diagBD ok 
					squareAvailable.add(locDBD);
					System.out.println("DBD  " + "[" + locDBD.getLocX()+ ", " + locDBD.getLocY() + "]");
					
				}else if (rCheckWall(joueurtempDOWN,locDOWNDOWN) && (rMovePion(joueurtempDOWN, locDBG))) { //si il y a un mur en dessous  de la case right et case en bas A GAUCHE libre diagBG ok
					squareAvailable.add(locDBG);
					System.out.println("DBG  " + "[" + locDBG.getLocX()+ ", " + locDBG.getLocY() + "]");
				}	
			}
		}
		//Diago HAUT GAUCHE et bas gauche + faceToFace LEFT
		else if (!rCheckWall(player, locLEFT)) { //si pas de mur LEFT
			if (locLEFT.inGrid(board) && board.getItem(locLEFT).getFull()) { // et que la case LEFT est remplie
				Location locLEFTLEFT = new Location(locLEFT.getLocX() - 2, locLEFT.getLocY()); //LOC de la case LEFTLEFT (saut)
				Location locDBG = new Location(locLEFT.getLocX(), locLEFT.getLocY() +2); //loc de la pos en BAS A GAUCHE VIA RIGHT
				Location locDHG = new Location(locLEFT.getLocX(), locLEFT.getLocY() -2 ); //loc de la pos en HAUT A GAUCHE
				Player joueurtempLEFT = new Player(locLEFT);//Instance player de la case LEFT pour utiliser les autres méthodes
				if (rMovePion(joueurtempLEFT, locLEFTLEFT)) { //si on peut bouger de la case LEFT à la case LEFTLEFT -> Saut
					squareAvailable.add(locLEFTLEFT);
					System.out.println("LEFLEFT  " + "[" + locLEFTLEFT.getLocX()+ ", " + locLEFTLEFT.getLocY() + "]");
				
				}else if (rCheckWall(joueurtempLEFT, locLEFTLEFT) && (rMovePion(joueurtempLEFT, locDBG))) { //si il y a un mur A GAUCHE de la case LEFT et case BAS GAUCHE LIBRE -> diagBG ok 
					squareAvailable.add(locDBG);
					System.out.println("DBG  " + "[" + locDBG.getLocX()+ ", " + locDBG.getLocY() + "]"); 
					
				}else if (rCheckWall(joueurtempLEFT,locLEFTLEFT) && (rMovePion(joueurtempLEFT, locDHG))) { //si il y a un mur A GAUCHE  de la case LEFT et case en HAUT GAUCHE  libre diagHG ok
					squareAvailable.add(locDHG);
					System.out.println("DHG  " + "[" + locDHG.getLocX()+ ", " + locDHG.getLocY() + "]");
				}
				
			}
		}
		
		for (int i =0; i < squareAvailable.size(); i++) {
			System.out.println("[" + squareAvailable.get(i).getLocX()+ ", " + squareAvailable.get(i).getLocY() + "]" ); //Test pour afficher les loc dans la listes
		}
		
		squareAvailable.clear();
		return null;*/
	}
	
}
	

	

	

	
