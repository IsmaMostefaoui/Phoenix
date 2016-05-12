package com.umons.model.playerAbstraction;

import com.umons.model.Game;
import com.umons.model.board.Location;
import com.umons.model.pathFinding.IPathFinder;

public interface IRobot {
	
	public void play(Game game, IPathFinder finder, Player opponent);
	public void move();
	public boolean putWall(Location[] tabWall, IPathFinder finder);
	
	

}
