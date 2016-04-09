package com.umons.model;

import javax.swing.JPanel;

import com.umons.view.BoardGUI;
import com.umons.view.MyMouseListener;
import com.umons.view.QuoridorGUI;

public class Main {
	
	public static void main(String[] args){
		Player joueur1 = new Player(Player.POS1, 1);
		RandomIA joueur2 = new RandomIA(Player.POS2, 2);
		Grid board = new Grid(joueur1, joueur2);
		Rules rules = new Rules(board);
		System.out.println("***DEBUT DU TEST***");
		boolean posOk = false;
		boolean wallOk = false;
		int x = 0;
		int y = 0;
		board.afficheGrid(joueur1, joueur2);
		Game game = new Game(board, 2);
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR", true);
		JPanel panel = new BoardGUI(game, joueur1, joueur2);
		panel.setFocusable(true);
		panel.addMouseListener(new MyMouseListener(board, joueur1, joueur2, panel, game));
		frame.setContentPane(panel);
	}
}
