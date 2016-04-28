package com.umons.model;

import java.util.ArrayList;
import java.util.List;
import com.umons.view.BoardGUI;

public class RegularIA extends Player implements IRobot{
	
	public RegularIA(Grid grid, Location loc, int NbreOfWall ,int OrderNumber, AMode mode) {
		super(grid, loc, NbreOfWall, OrderNumber, mode);
		this.human = false;
	}
	
	public RegularIA(Grid grid, Location loc, int OrderNumber, AMode mode) {
		super(grid, loc, OrderNumber, mode);
		this.human = false;	
	}
	
	
	public Path[] testFinder(Player[] players,IPathFinder finder) {
	Path[] path = {new Path(), new Path()};
	int[] pathLen = new int[players.length];
	for (int j = 0; j < players.length; j++) {
		for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
			if (players[j].getOrder()==1 || players[j].getOrder()==2) {
				if ((finder.findPath(players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()).getLength() > path[j].getLength())) {
					path[j] = finder.findPath(players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish());
				}
			}
		}
	}
	return path;
	}
	
	public void move() {
		//TODO
	}
	
	public void play(Game game, IPathFinder finder, Player opponent){
		Player[] players = {this, opponent};
		Path[] path = testFinder(players, finder);
		
		if (path[0].getLength() <= path[1].getLength()) {
			Location nextLocation = path[0].getStep(0);
			board.setItemInGrid(this.getLoc(), false);
			board.setItemInGrid(nextLocation, true);
			this.setLoc(nextLocation);
			BoardGUI.locPawn1 = nextLocation;
			
		}	
		
	}
	
	
	
	
	
	public boolean putWall(Location[] tabWall, IPathFinder finder){
		//TODO
		return true;
	}
	
	
					
				
}
