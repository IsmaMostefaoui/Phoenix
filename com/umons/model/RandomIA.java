package com.umons.model;
import java.util.Random;

public class RandomIA extends Player {
	
	public RandomIA(Location loc, int OrderNumber) {
		super(loc, OrderNumber);	
	}
	
	public boolean move(Grid grid, Player player) {
		Random rand = new Random();
		boolean choix= rand.nextBoolean();
		if (choix == true) {
			if (ARules.rMovePion(player, this.getLoc().squareDown())) { 
				this.setLoc(this.getLoc().squareDown());
				grid.setItemInGrid(this.getLoc(), false);
				grid.setItemInGrid(this.getLoc().squareDown(), true);
				return true;
			}	
		}
		return false;
	}

}
