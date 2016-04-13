package com.umons.model;
import java.util.Random;

public class RandomIA extends Player {
	
	public RandomIA(Grid board, Location loc, int OrderNumber) {
		super(board ,loc, OrderNumber);	
	}
	
	public boolean move(Grid grid, Player player) {
		Random rand = new Random();
		boolean choix= rand.nextBoolean();
	}
}
