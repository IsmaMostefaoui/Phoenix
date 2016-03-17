package com.umons.model;

public class Player {

	private Location loc;
	private int numberOfWall;
	private final int NB_WALL = 10;
	//L'ensemble des postions pour chacun des joueurs selon les modes de jeu
	public final static Location POS1 = new Location(8, 16);
	final static Location POS2 = new Location(8, 0);
	final static Location POS3 = new Location(0, 8);
	final static Location POS4 = new Location(16, 8);
	
	/**
	 * Initialise un joueur avec une position
	 * @param loc un objet de type Location, la position du joueur sur la grille
	 */
	public Player(Location loc) {
		this.loc = loc;
		numberOfWall = NB_WALL;
	}
	
	public Player(Location loc, int nbreOfWall) {
		this.loc = loc;
		this.numberOfWall = nbreOfWall;
	}
	
	/**
	 * Deplace le pion selon une direction (donné en tableau de coordonnées (x, y))
	 * @param grid un objet de type Grid representant le tableau sur lequel le pion doit se deplacer
	 * @param loc un objet Location correspondant à la case cible
	 */
	public boolean move(Grid board, Location loc) {
		int[] tabDep = ARules.rDeplacement(this, loc);
		if (tabDep[0] > 4 || tabDep[1] > 4) { return false; } //si l'utilisateur clique sur une  des case en dehors du carré autour du pion.
		if ((tabDep[0] == 4 ^ tabDep[1] == 4) && ARules.rFaceToFace(this, loc)) {
			board.setItemInGrid(this.loc, false);
			setLoc(loc);
			board.setItemInGrid(this.loc, true);
			return true;
			
		    // ^ corespond a l'operateur XOR en bit a bit, aussi connu sous le nom : "ou exclusif"
		}else if ((tabDep[0] == 2 ^ tabDep[1] == 2) && ARules.rMovePion(this, loc)) {	
			board.setItemInGrid(this.loc, false);
			setLoc(loc);
			board.setItemInGrid(this.loc, true);
			return true;
			
		}/*else if (tabDep[0] == 2 && tabDep[1] == 2 && ARules.rDiagFace(this, loc)) {
			grid.setItemInGrid(loc.getLocY(), loc.getLocX(), false);
			setLoc(loc);
			grid.setItemInGrid(loc, true);
			return true;	
			
		}*/else{
			return false;
		}
	}
	
	/**
	 * Pose un mur sur la grille en remplissant les items de type 2. Affiche du texte !
	 * @param grid grille du jeu
	 * @param position prend "horizontal" ou "vertical"
	 * @param x prend la position en x de l'extremite gauche du mur horizontal, ou la colonne pour un mur vertical
	 * @param y prend la position en y de l'extremite supperieur du mur vertical, ou la ligne pour un mur horizontal
	 * @return un boolean, true si le mur à été placé, sinon false
	 */
	public boolean putWall(Grid board, Location loc){
		System.out.println("test in putwall: " + ARules.rSlotFull(loc));
		if (loc.isWallHorizontal() && ARules.rPutWall(loc) && ARules.rSlotFull(loc)) {
			for (int j = loc.getLocX(); j < loc.getLocX() + 3; j++) {
				board.setItemInGrid(new Location(j, loc.getLocY()), true);
			}
			numberOfWall--; //anticipation pour quand on va devoir check si il a encore des murs etc...
			return true;
			
		}else if (loc.isWallVertical() && ARules.rPutWall(loc) && ARules.rSlotFull(loc)) {
			for (int i = loc.getLocY(); i < loc.getLocY() + 3; i++) {
				board.setItemInGrid(new Location(loc.getLocX(), i), true);
			}
			numberOfWall--;
			return true;
		}
		System.out.println("impossible de placer un mur à cet endroit");
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
}
