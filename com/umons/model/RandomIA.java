package com.umons.model;
import java.util.Random;

public class RandomIA extends Player {
	
	public RandomIA(Location loc, int OrderNumber) {
		super(loc, OrderNumber);	
	}
	
	public static boolean move(Grid grid) {
		Random rand = new Random();
		boolean choix= rand.nextBoolean();
		if (choix == true) {
			if (ARules.rMovePion(Player, loc.squareUP)){ //comment instanceier un player et une loc ????  #TODO
			}	
		}
	}

}
