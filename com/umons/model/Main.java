package com.umons.model;

public class Main {
	
	public static void main(String[] args){
		Mode mode = new Mode1VsRIA();
		Game game = new Game(mode);
		game.init();
		
	}
}
