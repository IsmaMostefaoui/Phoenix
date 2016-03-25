package com.umons.model;

public class Main {
	
	public static void main(String[] args){
		Mode mode = new Mode1Vs1();
		Game game = new Game(mode);
		game.init();
		
	}
}
