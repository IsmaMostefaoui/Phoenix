package com.umons.model;

import java.util.*;
import com.umons.view.BoardGUI;

public class RandomIA extends Player {
	
	public RandomIA(Grid board, Location loc, int orderNumber, IMode mode) {
		super(board, loc, orderNumber, mode);
		this.human = false;
	}
	
	public RandomIA(Grid board, Location loc, int nbreOfWall, int orderNumber, IMode mode) {
		super(board, loc, nbreOfWall, orderNumber, mode);
		this.human = false;
	}
	
	public void Play() {
		Random rand = new Random();
		boolean choise = rand.nextBoolean();
		if (choise == true) {
			
		}
		else {
			
		}
		
	}
	
	
	

}
