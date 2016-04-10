package com.umons.model;

import java.util.List;

public class Player {

	private Mode mode;
	private Location loc;
	private int numberOfWall;
	private final int NB_WALL = 10;
	private Grid board;
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
	public Player(Grid board, Location loc, int orderNumber, Mode mode) {
		this.loc = loc;
		this.board = board;
		numberOfWall = NB_WALL;
		this.orderNumber = orderNumber;
		this.mode = mode;
	}
	
	public Player(Grid board, Location loc, int nbreOfWall, int orderNumber, Mode mode) {
		this.loc = loc;
		this.board = board;
		this.numberOfWall = nbreOfWall;
		this.orderNumber = orderNumber;
		this.mode = mode;
	}
	
	/**
	 * Deplace le pion sur la position donne. Retourne vrai si le deplacement s est bien passe
	 * Sinon retourne faux, et ne deplace pas le pion
	 * @param board un objet de type Grid representant le tableau sur lequel le pion doit se deplacer
	 * @param loc un objet Location correspondant à la case cible
	 * @return true si le deplacement est autorisé, sinon false
	 */
	public boolean move(Location loc) {
		List<Location> list = ARules.rSquareAvailable(this);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(loc)) {
				board.setItemInGrid(this.getLoc(), false);
				this.loc = loc;
				board.setItemInGrid(loc, true);
				return true;
			}
		}
		return false;
	}


	/**
	 * Pose un mur sur la grille en remplissant les wall
	 * @param board grille du jeu
	 * @param loc la position du début du mur (le premier bloc à gauche [horizontal], le premier bloc en haut[vertical]
	 * @return un boolean, true si le mur à été placé, sinon false
	 */
	public boolean putWall(Location loc, IPathFinder finder){
		if (numberOfWall > 0 && loc.isWallHorizontal() && ARules.rPutWall(loc) && ARules.rSlotFull(loc) && mode.testFinder(this, loc, finder)) {
			for (int j = loc.getLocX(); j < loc.getLocX() + 3; j++) {
				System.out.println("Apres l activation du bloc n° " + j + " du mur");
				board.setItemInGrid(new Location(j, loc.getLocY()), true);
			}
			numberOfWall--;
			return true;
			
		}else if (numberOfWall > 0 && loc.isWallVertical() && ARules.rPutWall(loc) && ARules.rSlotFull(loc) && mode.testFinder(this, loc, finder)) {
			for (int i = loc.getLocY(); i < loc.getLocY() + 3; i++) {
				board.setItemInGrid(new Location(loc.getLocX(), i), true);
				System.out.println("Apres l activation du bloc n° " + i + " du mur");
			}
			numberOfWall--;
			return true;
		}
		return false; 
	}
	
	/**
	 * A changer de place(quelque part ou on a acces a tous les player)
	 * Test si il y a un chemin
	 * @param finder
	 * @param loc la position du mur qui risque de bloquer un joueur
	 * @return vrai si il y a un chemin, faux sinon
	 */
	public boolean testFinder(Location coordWall, IPathFinder finder){
		for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
			if ((orderNumber == 1 || orderNumber == 2) && finder.findPath(coordWall, 8, 0, 6, 16) == null){
				System.out.println("------------------------------------\n------------------------------\n---------------------------\n-------------------\n");
				return false;
			}else if((orderNumber == 3 || orderNumber == 4) && finder.findPath(coordWall, this.loc.getLocX(), this.loc.getLocY(), getCoordFinish(), i) == null){
				return false;
			}break;
		}return true;
	}
	
	/**
	 * Retourne la position de la ligne d arriv� du joueur selon son numero
	 */
	public int getCoordFinish() {
		if (orderNumber==1) {
			return POS2.getLocY();
		}else if (orderNumber==2) {
			return POS1.getLocY();
		}else if (orderNumber==3) {
			return POS4.getLocX();
		}else if (orderNumber==4) {
			return POS3.getLocX();
		}else {
			return (Integer) null;
		}
	}
	
	
	/**
	 * getter
	 * @return un objet Location représentant la position du joueur(pion) sur la grille
	 */
	public Location getLoc() { return new Location(loc.getLocX(), loc.getLocY()); }
	
	/**
	 * getter
	 * @return le nombre de mur actuel du joueur
	 */
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
	@Override
	public boolean equals(Object other) {
		Player p = (Player)other;
		return this.getLoc().equals(p.getLoc());
	}
}
