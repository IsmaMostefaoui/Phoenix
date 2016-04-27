package com.umons.model;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestJunit {
	
	@Test
	public void rSquareAvailableTest() {
		Grid board = new Grid();
		Location locTest = new Location(0,0);
		for (int x = 0; x <=  9; x++) {
			for (int y = 0; y <=  9; y++) {
				locTest.setLocX(x); locTest.setLocY(y);
				board.setItemInGrid(locTest, true);
				Location tabWall = {locTest.wallDown(), locTest.wallRight(), locTest.wallLeft(), locTest.wallUp()};
			}
			
			
		}
		
	}

}
