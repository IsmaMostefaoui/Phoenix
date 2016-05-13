package com.umons.test;

import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

import com.umons.model.AMode;
import com.umons.model.ARules;
import com.umons.model.Game;
import com.umons.model.Mode1Vs1;
import com.umons.model.board.Grid;
import com.umons.model.board.Location;
import com.umons.model.pathFinding.AStarHeuristic;
import com.umons.model.pathFinding.AStarPathFinder;
import com.umons.model.pathFinding.IPathFinder;
import com.umons.model.playerAbstraction.Player;

public class TestVictory {

		
	private Grid board;
	private Location locTest;
	private Player player1;
	private Player player2;
	private AMode mode;
	private IPathFinder finder;
	private AStarHeuristic heuristic;
	private Game game;
		
	@Before
	public void setUp() throws Exception {
		
		this.locTest = new Location(0,0);
		this.mode = new Mode1Vs1(0,2);
		this.heuristic = new AStarHeuristic();
		this.player1 = mode.getPlayer()[0];
		this.player2 = mode.getPlayer()[1];
		this.board = mode.getBoard();
		this.finder = new AStarPathFinder(board, 500, heuristic);
		this.game = new Game(mode);
		ARules.setBoard(board);
			
	}
	
	@Test
	public void Victory() {
		player1.setLoc(new Location(8,0));
		assertTrue("Vicoire ", game.win(player1));
		
	}
		

}
