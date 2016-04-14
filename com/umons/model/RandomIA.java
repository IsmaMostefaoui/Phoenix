package com.umons.model;
import java.util.List;
import java.util.Random;

import com.umons.view.BoardGUI;

public class RandomIA extends Player {
	
	public RandomIA(Grid grid, Location loc, int NbreOfWall ,int OrderNumber, Mode mode) {
		super(grid, loc, NbreOfWall, OrderNumber, mode);	
	}
	
	public RandomIA(Grid grid, Location loc, int OrderNumber, Mode mode) {
		super(grid, loc, OrderNumber, mode);	
	}
	
	/**
	 * Choisis aleatoirement entre se deplacer et poser un mur, si impossible de placer un mur -> se deplace
	 * @param grid palteau de jeu
	 * @param opponent Instance Player du joueur adverse
	 */
	public void move(Game game, IPathFinder finder, Player opponent) {
		//Il faut ajouter les coordonne de sa position (une fois qu il a bouger) ou les coord du mur qu il a place
		//dans la variable static locpawn ou locwall car sinon l affichage ne se fait pas
		Random rand = new Random();
		boolean choice = rand.nextBoolean();
		if (choice == true) {
			this.play();
		}
		if (choice == false) {
			if (!this.putwall(board, opponent, finder)) { // si ne peut poser aucun mur, faire un deplacement
				this.play();
			}
		}
	}
	
	
	
	/**
	 * L'IA choissit la position la plus proche de la ligne d'arrivée
	 * @param grid Grille de jeu
	 */
	public void play() {
		List<Location> list = ARules.rSquareAvailable(this);
		Location nextLocation = this.getLoc();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getLocY() >= nextLocation.getLocY()) {
				nextLocation = list.get(i);	
			}
		}
		board.setItemInGrid(this.getLoc(), false);
		board.setItemInGrid(nextLocation, true);
		if (orderNumber == 1){
			BoardGUI.locPawn1 = nextLocation;
		}else {
			//ajouter les locpawn3 et 4 dans boardgui pour les 3 et 4 joueurs
			BoardGUI.locPawn2 = nextLocation;
		}
		this.setLoc(nextLocation);

	}
	
	
	/**
	 * Pose un mur autour du Pion adverse si possible (ordre : up,left, right, down)
	 * @param grid plateau de jeu
	 * @param opponent Instance Player du joueur adverse
	 * @return true si il peut poser un mur, sinon false
	 */
	public boolean putwall(Grid grid, Player opponent, IPathFinder finder) {
		Location[] tabWall = {opponent.getLoc().wallUp(), opponent.getLoc().wallLeft(), opponent.getLoc().wallRight(), opponent.getLoc().wallDown() };
		int numb = 0;
		while (numb < 4) {
			if (super.putWall(tabWall[numb], finder)) {
				if (tabWall[numb].isWallHorizontal()){
					BoardGUI.locWallHorizontal.add(tabWall[numb]);
				}else {
					BoardGUI.locWallVertical.add(tabWall[numb]);
				}
				return true;
			}
			numb++;
		}
		return false;
	}
}

