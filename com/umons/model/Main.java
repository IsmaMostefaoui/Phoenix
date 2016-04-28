package com.umons.model;

public class Main {
	
	public static void main(String[] args){
		IMode mode = new Mode1Vs1(1);
		Game game = new Game(mode);
		game.init();
		
	}
}
