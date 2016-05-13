package com.umons.test;


import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;


import com.umons.model.AMode;
import com.umons.model.ARules;
import com.umons.model.Mode1Vs1;
import com.umons.model.board.Grid;
import com.umons.model.board.Location;
import com.umons.model.pathFinding.AStarHeuristic;
import com.umons.model.pathFinding.AStarPathFinder;
import com.umons.model.pathFinding.IPathFinder;
import com.umons.model.playerAbstraction.Player;



public class TestMove {
	
	private Grid board;
	private Location locTest;
	private Player player;
	private AMode mode;
	private IPathFinder finder;
	private AStarHeuristic heuristic;
	
	@Before
	public void setUp() throws Exception {
		
		this.locTest = new Location(0,0);
		this.mode = new Mode1Vs1(0,2);
		this.heuristic = new AStarHeuristic();
		this.player = mode.getPlayer()[0];
		this.board = mode.getBoard();
		this.finder = new AStarPathFinder(board, 500, heuristic);
		ARules.setBoard(board);
		
	}
	@Test
	public void move() {
		for (int y = 0; y < board.getLen(); y+=2) {
			for (int x = 0; x < board.getLen(); x+=2) {
				locTest.setLocX(x); locTest.setLocY(y);
				ArrayList<Location> squares = ARules.rSquareAvailable(locTest);
				for (int i = 0; i < squares.size(); i++) {
					int diffX = Math.abs(squares.get(i).getLocX() - locTest.getLocX());
					int diffY = Math.abs(squares.get(i).getLocY() - locTest.getLocY());
					assertTrue("Location  " + locTest + ", fausse : y != 2 ou y!= 0",diffY == 0 || diffY == 2);
					assertTrue("Location  " + locTest + ", fausse : x != 2 ou x!= 0",diffX == 2 || diffX == 0);
				}	
			}	
		}
	}
	
	@Test
	public void faceToFace() {
		locTest.setLocX(0); locTest.setLocY(0);
		board.setItemInGrid(locTest, true);
		board.setItemInGrid(new Location(2,0), true);
		ArrayList<Location> loc = ARules.rSquareAvailable(locTest);
		assertTrue("Peut se deplacer en (4,0)", loc.contains(new Location(4,0)));
		board.resetGrid();
		
	}
	
	@Test
	public void diagUpRight() {
		locTest.setLocX(0); locTest.setLocY(16);
		board.setItemInGrid(locTest, true);
		board.setItemInGrid(new Location(0,14), true);
		player.setLoc(locTest);
		player.putWall(new Location(0,13), finder);
		ArrayList<Location> loc = ARules.rSquareAvailable(locTest);
		assertTrue("Peut se deplacer en (2,14)", loc.contains(new Location(2,14)));
		mode.reset();
	}
	
	@Test
	public void diagUpLeft() {
		locTest.setLocX(8); locTest.setLocY(16);
		board.setItemInGrid(locTest, true);
		board.setItemInGrid(new Location(8,14), true);
		player.setLoc(locTest);
		player.putWall(new Location(8,13), finder);
		ArrayList<Location> loc = ARules.rSquareAvailable(locTest);
		assertTrue("Peut se deplacer en (6,14)", loc.contains(new Location(6,14)));
		mode.reset();
	}
	
	@Test
	public void diagDownLeft() {
		locTest.setLocX(8); locTest.setLocY(0);
		board.setItemInGrid(locTest, true);
		board.setItemInGrid(new Location(8,2), true);
		player.setLoc(locTest);
		player.putWall(new Location(8,3), finder);
		ArrayList<Location> loc = ARules.rSquareAvailable(locTest);
		assertTrue("Peut se deplacer en (6,2)", loc.contains(new Location(6,2)));
		mode.reset();
	}
	
	@Test
	public void diagDownRight() {
		locTest.setLocX(8); locTest.setLocY(0);
		board.setItemInGrid(locTest, true);
		board.setItemInGrid(new Location(8,2), true);
		player.setLoc(locTest);
		player.putWall(new Location(8,3), finder);
		ArrayList<Location> loc = ARules.rSquareAvailable(locTest);
		assertTrue("Peut se deplacer en (10,2)", loc.contains(new Location(10,2)));
		mode.reset();
	}
	
	
}
