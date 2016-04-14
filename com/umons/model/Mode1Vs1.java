package com.umons.model;

import javax.swing.JPanel;
import com.umons.view.BoardGUI;
import com.umons.controller.MyMouseListener;
import com.umons.view.QuoridorGUI;

public class Mode1Vs1 implements Mode{

	Player[] players;
	Grid board;
	AStarHeuristic heuristic;
	IPathFinder finder;
	
	public Mode1Vs1() {
		board = new Grid();
		players = new Player[2];
		players[0] = new Player(board, Player.POS1, 1, this);
		players[1] = new Player(board, Player.POS2, 2, this);
		heuristic = new AStarHeuristic();
		finder = new AStarPathFinder(board, 500, heuristic);
	}
	
	@Override
	public void init(Game game) {
		//AJOUTER A BOARDGUI UN PARAMTERE MODE POURT DESSINER LES PREVIEW (SELON QU'ON SOIT EN 1VSAI, NE PAS DESSINER LES PREVIEW DE L'IA)
		//AJOUTER AUSSI A MML (CONTROLLER) POUR QUAND ON AUGEMENTE LE TOUR, L'IA NE SOIT PAS OBLIGER DE PHYSIQUEMENT CLICKER	
		ARules.setBoard(board);
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR", true);
		JPanel panel = new BoardGUI(game, players[0], players[1]);
		panel.setFocusable(true);
		MyMouseListener l = new MyMouseListener(players[0], players[1], panel, game, finder);
		panel.addMouseListener(l);
		panel.addMouseMotionListener(l);
		frame.setContentPane(panel);
		
	}
	

	@Override
	public int getNumberOfPlayer() {
		return 2;
	}

	@Override
	public Player[] getPlayer() {
		return players;
	}

	@Override
	public boolean testFinder(Player player, Location coordWall, IPathFinder finder){
		boolean[] check = {true, true};
		for (int j = 0; j < players.length; j++) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				if (players[j].getOrder()==1 || players[j].getOrder()==2) {
					long timeStart = System.currentTimeMillis();
					if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()) == null)) {
						long timeEnd = System.currentTimeMillis();
						System.out.println("temps: " + ((timeEnd - timeStart)));
						check[j] = false;
					}else {
						System.out.println("joueur: " + j);
						check[j] = true;
						break;
					}
				}else if (players[j].getOrder()==3 || players[j].getOrder()==4){
					if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), players[j].getCoordFinish(), 2*i) == null)) {
						check[j] = false;
					}else {
						check[j] = true;
						break;
					}
				}
			}
		}return check[0] && check [1];
	}
}
