package com.umons.model;

import java.io.Serializable;
import java.util.List;

import com.umons.view.BoardGUI;

public class Player implements Serializable{


	private static final long serialVersionUID = 1321463430487427409L;
	
	protected AMode mode;
	protected Location loc;
	protected int numberOfWall;
	protected final int NB_WALL = 10;
	protected Grid board;
	protected final int orderNumber;
	protected boolean human;
	//L'ensemble des postions pour chacun des joueurs selon les modes de jeu
	public final static Location POS1 = new Location(8, 16);
	public final static Location POS2 = new Location(8, 0);
	public final static Location POS3 = new Location(0, 8);
	public final static Location POS4 = new Location(16, 8);
	
	/**
	 * Initialise un joueur avec une position
	 * @param loc un objet de type Location, la position du joueur sur la grille
	 * @param orderNumbrer Le numero d'ordre du joueur
	 * 
	 */
	public Player(Grid board, Location loc, int orderNumber, AMode mode) {
		this.loc = loc;
		this.board = board;
		numberOfWall = NB_WALL;
		this.orderNumber = orderNumber;
		this.mode = mode;
		this.human = true;
	}


	/**
	 * Initialise un joueur avec une position
	 * @param loc un objet de type Location, la position du joueur sur la grille
	 * @param nbreOfWall le nombre de Mur d'un joueur en debut de partie 
	 * @param orderNumbrer Le numero d'ordre du joueur
	 */
	public Player(Grid board, Location loc, int nbreOfWall, int orderNumber, AMode mode) {
		this.loc = loc;
		this.board = board;
		this.numberOfWall = nbreOfWall;
		this.orderNumber = orderNumber;
		this.mode = mode;
		this.human = true;
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
				System.out.println(board);
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
		System.out.println("test dans player pour putwall: ");
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
	
	public void play(Game game, Location temp, IPathFinder finder) {
	
		if (temp.isSquare() && this.move(temp)) {
			switch (this.orderNumber) {
			case 1:
				BoardGUI.locPawn1 = temp;
				break;
			case 2:
				BoardGUI.locPawn2 = temp;
				break;
			case 3:
				BoardGUI.locPawn3 = temp;
				break;
			case 4:
				BoardGUI.locPawn4 = temp;
				break;
			}
			game.nextPlayer();
		}else if (temp.isWallHorizontal() && this.putWall(temp, finder)){
			System.out.println("locwall rempli");
			BoardGUI.locWallHorizontal.add(temp);
			game.nextPlayer();
			//aud.run();
		}else if (temp.isWallVertical() && this.putWall(temp, finder)){
			System.out.println("locwall rempli");
			BoardGUI.locWallVertical.add(temp);
			game.nextPlayer();
			//aud.run();
		}
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
			return 0;
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
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	
	/**
	 * getter 
	 * @retrun le nombre de mur restant du joueur
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
	
	public boolean isHumanPlayer() {
		return human;
	}
}
