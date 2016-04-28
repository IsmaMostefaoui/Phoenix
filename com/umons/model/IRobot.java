package com.umons.model;

public interface IRobot {
	
	public void play(Game game, IPathFinder finder, Player opponent);
	public void move();
	public boolean putWall(Location[] tabWall, IPathFinder finder);
	
	

}
