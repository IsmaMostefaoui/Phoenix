package com.umons.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.umons.model.AMode;
import com.umons.model.ARules;
import com.umons.model.Mode1Vs1;
import com.umons.model.board.Grid;
import com.umons.model.board.Location;
import com.umons.model.pathFinding.AStarHeuristic;
import com.umons.model.pathFinding.AStarPathFinder;
import com.umons.model.pathFinding.IPathFinder;
import com.umons.model.playerAbstraction.Player;



public class TestWall {
	
	private Grid board;
	private Location locTest;
	private Player player1;
	private Player player2;
	private AMode mode;
	private IPathFinder finder;
	private AStarHeuristic heuristic;
	
	@Before
	public void setUp() throws Exception {
		
		this.locTest = new Location(0,0);
		this.mode = new Mode1Vs1(0,2);
		this.heuristic = new AStarHeuristic();
		this.player1 = mode.getPlayer()[0];
		this.player2 = mode.getPlayer()[1];
		this.board = mode.getBoard();
		this.finder = new AStarPathFinder(board, 500, heuristic);
		ARules.setBoard(board);
		
	}
	
	@Test
	public void crossWAll() {
		player1.putWall(new Location(0,1), finder);
		assertFalse("Impossible de posser des murs en croit", player2.putWall(new Location(1,0),finder));
		mode.reset();
		
	}
	
	@Test
	public void superimposeWall () {
		player1.putWall(new Location (0,1), finder);
		assertFalse("Impossible de superposer des murs ", player2.putWall(new Location (0,1), finder));
		mode.reset();
	}
	
	@Test
	public void pathFindingWall() {
		player1.setLoc(new Location (8,8));
		board.setItemInGrid(player1.getLoc(), true);
		player1.putWall(new Location(8,9), finder);
		player2.putWall(new Location(9,6), finder);
		player1.putWall(new Location(8,5), finder);
		assertFalse("Impossible de bloquer un joueur ", player2.putWall(new Location (7,6), finder));
		mode.reset();
	}
	
	@Test
	public void wallNotInGrid() {
		assertFalse("Impossible de placer un mur en dehors de la grille", player1.putWall(new Location(42,42), finder));
		
	}


}
