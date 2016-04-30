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
	
	
	public Path[] testFinderMove(Player[] players,IPathFinder finder) {
	Path[] path = {finder.findPath(players[0].getLoc().getLocX(), players[0].getLoc().getLocY(), 0, players[0].getCoordFinish()), finder.findPath(players[1].getLoc().getLocX(), players[1].getLoc().getLocY(),0, players[1].getCoordFinish())};
	for (int j = 0; j < players.length; j++) {
		for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
			if (players[j].getOrder()==1 || players[j].getOrder()==2) {
				//System.out.println("players[j].getLoc().getLocX() = " + players[j].getLoc().getLocX());
				//System.out.println("players[j].getLoc().getLocY() = " + players[j].getLoc().getLocY());
				//System.out.println("players[j].getCoordFinish()).getLength() = " + players[j].getCoordFinish());
				//System.out.println("finder.findPath(players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()).getLength() =  " + finder.findPath(players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()).getLength());
				//System.out.println("path[j].getLength() = " + path[j].getLength());
				if ((finder.findPath(players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()).getLength() < path[j].getLength())) {
					path[j] = finder.findPath(players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish());
				}
			}
		}
	}
	return path;
	}
	

	
	public void play(Game game, IPathFinder finder, Player opponent){
		Player[] players = {this, opponent};
		Path[] path = testFinderMove(players, finder);
		Location nextWall = testFinderWall(players, finder);
		
		if (path[0].getLength() <= path[1].getLength()) {
			System.out.println("path[0].getLength() = " + path[0].getLength() );
			System.out.println("path[1].getLength() = " + path[1].getLength() );
			Location nextLocation = path[0].getStep(1);
			System.out.println("NextLocation = " + nextLocation);
			board.setItemInGrid(this.getLoc(), false);
			board.setItemInGrid(nextLocation, true);
			this.setLoc(nextLocation);
			BoardGUI.locPawn2 = nextLocation;
			
		}else if(nextWall != null){
			board.setItemInGrid(nextWall, true);
			if (nextWall.isWallHorizontal()){
				BoardGUI.locWallHorizontal.add(nextWall);
			}else {
					BoardGUI.locWallVertical.add(nextWall);
			}
			
			
		}
		
	}
	
	public Location testFinderWall(Player[] players, IPathFinder finder) {
		ArrayList<Location> allWall = Wall.allWall();
		Path path = finder.findPath(players[1].getLoc().getLocX(), players[1].getLoc().getLocY(),0, players[1].getCoordFinish());
		Location nextWall = null;
		for (Location wall : allWall) {
			System.out.println("WALLLLLLLLL = "  + wall);
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				if ((finder.findPath(wall, this.getLoc().getLocX(), this.getLoc().getLocY(), 2*i, this.getCoordFinish()).getLength() < path.getLength())) {
						nextWall = wall;
				}
			}
				
		}		
		return nextWall;
			
	}
	
	
	
	
	@Override
	public boolean putWall(Location[] tabWall, IPathFinder finder){
		//TODO
		return true;
	}
	@Override
	public void move() {
		//TODO
	}
	
	
					
				
}
