package com.umons.model;

import java.util.*;
import com.umons.view.BoardGUI;

public class RandomIA extends Player implements IRobot{
	
	public RandomIA(Grid board, Location loc, int orderNumber, IMode mode) {
		super(board, loc, orderNumber, mode);
		this.human = false;
	}
	
	public RandomIA(Grid board, Location loc, int nbreOfWall, int orderNumber, IMode mode) {
		super(board, loc, nbreOfWall, orderNumber, mode);
		this.human = false;
	}
	
	public void play(Game game, IPathFinder finder, Player opponent) {
		Random rand = new Random();
		boolean choise = rand.nextBoolean();
		if (choise == true) {
			this.move();
		}else {
			Location[] tabWall = {opponent.getLoc().wallUp(), opponent.getLoc().wallLeft(), opponent.getLoc().wallRight(), opponent.getLoc().wallDown() };
			int i = rand.nextInt(tabWall.length);
			if (!super.putWall(tabWall[i], finder)){
				this.move();
			}else {
				if (tabWall[i].isWallHorizontal()){
					BoardGUI.locWallHorizontal.add(tabWall[i]);
				}else {
					BoardGUI.locWallVertical.add(tabWall[i]);
				}
			}	
		}
	}
	
	public void move(){
		Random rand = new Random();
		List<Location> squares = ARules.rSquareAvailable(this);
		int i =rand.nextInt(squares.size());
		board.setItemInGrid(this.getLoc(), false);
		this.setLoc(squares.get(i));
		board.setItemInGrid(this.getLoc(), true);
		switch (this.getOrder()) {
		case 1 :
			BoardGUI.locPawn1 = this.getLoc();
			break;
		case 2 :
			BoardGUI.locPawn2 = this.getLoc();
			break;
		case 3 :
			BoardGUI.locPawn3 = this.getLoc();
			break;
		case 4 :
			BoardGUI.locPawn4 = this.getLoc();
			break;
		}
		
	}
	
	public boolean putWall(Location[] tabWall, IPathFinder finder){
		return true;
	}
			
}


	


