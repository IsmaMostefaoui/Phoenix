package com.umons.model;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.umons.view.BoardGUI;
import com.umons.view.MyMouseListener;
import com.umons.view.QuoridorGUI;

public class Mode1Vs1 implements Mode{

	@Override
	public void init(Game game) {
		//AJOUTER A BOARDGUI UN PARAMTERE MODE POURT DESSINER LES PREVIEW (SELON QU'ON SOIT EN 1VSAI, NE PAS DESSINER LES PREVIEW DE L'IA)
		//AJOUTER AUSSI A MML (CONTROLLER) POUR QUAND ON AUGEMENTE LE TOUR, L'IA NE SOIT PAS OBLIGER DE PHYSIQUEMENT CLICKER
		Grid board = new Grid();		
		Player[] players = {new Player(board, Player.POS1, 1), new Player(board, Player.POS2, 2)};
		ARules.setBoard(board);
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR", true);
		JPanel panel = new BoardGUI(game, players[0], players[1]);
		panel.setFocusable(true);
		MyMouseListener l = new MyMouseListener(players[0], players[1], panel, game);
		panel.addMouseListener(l);
		panel.addMouseMotionListener(l);
		frame.setContentPane(panel);
		
	}

	@Override
	public int getNumberOfPlayer() {
		return 2;
	}

}
