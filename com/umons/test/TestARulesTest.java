package com.umons.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.umons.model.ARules;
import com.umons.model.Grid;
import com.umons.model.Location;
import com.umons.model.Player;

public class TestARulesTest {
	
	private Grid board;
	private Location locTest;
	
	@Before
	public void setUp() throws Exception {
		this.board = new Grid();
		this.locTest = new Location(0,0);
	}
	@Test
	public void rsquareAvailableTest() {
		for (int x = 0; x <=  board.getLen()-2; x=x +2) {
			for (int y = 0;y <=  board.getLen()-2; y=y+2) {
				locTest.setLocX(x); locTest.setLocY(y);
				List<Location> squares = ARules.rSquareAvailable(locTest);
				for (int i = 0; i < squares.size(); i++) {
					int diffX = Math.abs(squares.get(i).getLocX() - locTest.getLocX());
					int diffY = Math.abs(squares.get(i).getLocY() - locTest.getLocY());
					assertFalse("Location  " + locTest + ", fausse : y != 2 ou y!= 0",diffY != 2 || diffY != 0);
					assertFalse("Location  " + locTest + ", fausse : x != 2 ou x!= 0",diffX != 2 || diffX != 0);
				}
				
				
			}
			
			
		}
		
	}
	
	@Test
	public void rPutWall() {
		Grid board = new Grid();
		Location locTest = new Location (0,0);
		for (int x = 0; x <=  board.getLen(); x=x +2) {
			for (int y = 0; y <=  board.getLen(); y=y+2) {
				locTest.setLocX(x); locTest.setLocY(y);
				Location[] tabWall = {locTest.wallDown(), locTest.wallLeft(), locTest.wallRight(), locTest.wallUp()};
				for (int index = 0; index < tabWall.length; index++) {
					//TODO
					
					
				}
				
			}
		}
		
	}

}
