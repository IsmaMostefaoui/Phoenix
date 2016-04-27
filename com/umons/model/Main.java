package com.umons.model;

public class Main {
	
	public static void main(String[] args){
		IMode mode = new Mode2Vs2(1);
		Game game = new Game(mode);
		game.init();
		
	}
}
