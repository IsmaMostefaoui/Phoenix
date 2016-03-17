package com.umons.model;

public class Game {

	private int numberPlayer;
	private int tour;
	
	
	public Game(int numberPlayer) {
		tour = 1;
		this.numberPlayer = numberPlayer;
	}
	
	public void nextPlayer() {
		tour = ((tour+1)%numberPlayer);
	}
	
}
