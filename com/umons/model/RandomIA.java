package com.umons.model;
import java.util.List;
import java.util.Random;

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
	public void play(Game game, IPathFinder finder, Player opponent) {
		Random rand = new Random();
		boolean choice = rand.nextBoolean();
		if (choice == true) {
			this.move(grid);
		}
		if (choice == false) {
			if (!this.putwall(grid, opponent, finder)) { // si ne peut poser aucun mur, faire un deplacement
				this.move(grid);
			}
		}
	}
	
	
	
	/**
	 * L'IA choissit la position la plus proche de la ligne d'arrivée
	 * @param grid Grille de jeu
	 */
	public void move(Grid grid) {
		List<Location> list = ARules.rSquareAvailable(this);
		Location nextLocation = this.getLoc();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getLocY() >= nextLocation.getLocY()) {
				nextLocation = list.get(i);	
			}
		}
		grid.setItemInGrid(this.getLoc(), false);
		grid.setItemInGrid(nextLocation, true);
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
				return true;
			}
			numb++;
		}
		return false;
	}
}

