package com.umons.controller;

import javax.swing.JPanel;

import com.umons.model.*;
import com.umons.view.BoardGUI;
import com.umons.view.QuoridorGUI;

public class Controller {
	
	private AMode mode;
	private IPathFinder finder;
	private Game game;
	private JPanel panel;
	private Player[] players;
	
	public Controller(AMode mode, JPanel panel, Game game, IPathFinder finder){
		this.panel = panel;
		this.game = game;
		this.finder = finder;
		this.mode = mode;
		players = mode.getPlayer();
	}
	
	public void makePlayerPlay(){
		Location temp = Controller.pixelToCoord(MyMouseListener.getClickCoord());
		try {
			switch (game.getTour()){
			case 0:
				players[0].play(game, temp, finder);
				makeRobotPlay();
				panel.repaint();
				break;
			case 1:
				players[1].play(game, temp, finder);
				makeRobotPlay();
				break;
			case 2:
				players[2].play(game, temp, finder);
				makeRobotPlay();
				break;
			case 3:
				players[3].play(game, temp, finder);
				makeRobotPlay();
				break;
			}
		}catch (InterruptedException ie){
			System.err.println("Erreur dans le sleep");
			ie.printStackTrace();
		}finally {
			panel.repaint();
		}
		
	}
	
	public void makeRobotPlay() throws InterruptedException {
		// ca je le met pour directement check si les joueur suivant est un robot. Ainsi je profite du fait que le joueur reel ait clicke
		// pour faire joueur ceux qui ne sont pas reels.
		if (game.getTour() == 0 && !players[0].isHumanPLayer()) {
			System.out.println("je suis rentré dans player1 est un robot ???");
			MediumIA IA = (MediumIA) players[0];
			IA.play(game, finder, players[1]);
			game.nextPlayer();
			panel.repaint();			
		}
		if (game.getTour() == 1 && !players[1].isHumanPLayer()) {
			//on sait alors que c est un robot donc on cast pour acceder a la methode move de l IA
			//parce que le move de MediumIA n est pas la surcharge du move de player (il aurait fallu qu ils aient la meme signature)
			//donc, si on cast pas, il va chercher si player a un move avec cette signature, ce qui est faux, donc bug compil
			RandomIA IA = (RandomIA) players[1];
			IA.play(game, finder, players[0]);
			game.nextPlayer();
			panel.repaint();
		}

		if (game.getTour() == 2 && !players[2].isHumanPLayer()) {
			RandomIA IA = (RandomIA) players[2];
			IA.play(game, finder, players[3]);
			game.nextPlayer();
			panel.repaint();
		}

		if (game.getTour() == 3 && !players[3].isHumanPLayer()) {
			MediumIA IA = (MediumIA) players[3];
			IA.play(game, finder, players[2]);
			game.nextPlayer();
			panel.repaint();
		}
	}
	
	public void updatePanel(){
		panel.repaint();
	}
	
	public static Location pixelToCoord(Location loc) {
		int x = loc.getLocX();
		int y = loc.getLocY();
		int xtemp = -1;
		int ytemp = -1;
		if (x < BoardGUI.START_X || x > BoardGUI.lBack || y < BoardGUI.START_Y || y > QuoridorGUI.HEIGHT) {
			return new Location(-1, -1);
		}
		for (int i = 0; i < BoardGUI.SQUARE_NUMBER; i++) {
			//carre
			if (x >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.START_X && x <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + BoardGUI.START_X)) {
				xtemp = 2*i;
			//fente
			}else if (x >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.START_X+BoardGUI.lSquare && x <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + BoardGUI.START_X + BoardGUI.lWall)){
				xtemp = (2*i)+1;
			}
			if (y >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.START_Y && y <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare + BoardGUI.START_Y)) {
				ytemp = 2*i;
			}else if (y >= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + BoardGUI.START_Y+BoardGUI.lSquare && y <= (i)*(BoardGUI.lWall+BoardGUI.lSquare) + (BoardGUI.lSquare +  BoardGUI.START_Y + BoardGUI.lWall)){
				ytemp = (2*i)+1;
			}
		}return new Location(xtemp, ytemp);
	}

	/**
	 * Transforme un objet Location en des coordonées pixel
	 * @return un Objet Location correspondant au coordonnées pixel sur le panel pour la grille
	 */
	public static Location coordToPixel(Location loc) {
		int x = loc.getLocX();
		int y = loc.getLocY();
		int xtemp;
		int ytemp;
		if (x == 0) {
			xtemp = BoardGUI.START_X;
		}else if (x == 1){
			xtemp = BoardGUI.START_X + BoardGUI.lSquare;
		}else if (x % 2 == 0) {
			xtemp = BoardGUI.START_X + x/2*(BoardGUI.lSquare + BoardGUI.lWall);
		}else {
			xtemp = BoardGUI.START_X + BoardGUI.lSquare + (x/2)*(BoardGUI.lSquare + BoardGUI.lWall);
		}
		if (y == 0) {
			ytemp = BoardGUI.START_Y;
		}else if (y == 1) {
			ytemp = BoardGUI.START_Y + BoardGUI.lSquare;
		}else if (y % 2 == 0) {
			ytemp = BoardGUI.START_Y + y/2*(BoardGUI.lSquare + BoardGUI.lWall);
		}else {
			ytemp = BoardGUI.START_Y + BoardGUI.lSquare + (y/2)*(BoardGUI.lSquare + BoardGUI.lWall);
		}
		return new Location(xtemp, ytemp);
	}
	
}
