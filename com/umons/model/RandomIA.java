package com.umons.model;

import java.util.*;
import com.umons.view.BoardGUI;

public class RandomIA extends Player {
	
	public RandomIA(Grid board, Location loc, int orderNumber, IMode mode) {
		super(board, loc, orderNumber, mode);
		this.human = false;
	}
	
	public RandomIA(Grid board, Location loc, int nbreOfWall, int orderNumber, IMode mode) {
		super(board, loc, nbreOfWall, orderNumber, mode);
		this.human = false;
	}
	
	public void Play(Game game, IPathFinder finder, Player opponent) {
		Random rand = new Random();
		boolean choise = rand.nextBoolean();
		if (choise == true) {
			List<Location> squares = ARules.rSquareAvailable(this);
			int i =rand.nextInt(squares.size());
			board.setItemInGrid(this.getLoc(), false);
			this.setLoc(squares.get(i));
			board.setItemInGrid(this.getLoc(), true);
			switch (this.getOrder()) {
			case 1 :
				BoardGUI.locPawn1 = this.getLoc();
			case 2 :
				BoardGUI.locPawn2 = this.getLoc();
			case 3 :
				BoardGUI.locPawn3 = this.getLoc();
			case 4 :
				BoardGUI.locPawn4 = this.getLoc();
				
			}
		}else {
			Location[] tabWall = {opponent.getLoc().wallUp(), opponent.getLoc().wallLeft(), opponent.getLoc().wallRight(), opponent.getLoc().wallDown() };
			int index;
			do {
				index = rand.nextInt(tabWall.length);
			}while(!super.putWall(tabWall[index], finder));
		}
			
			
			
				
	}
}


	


