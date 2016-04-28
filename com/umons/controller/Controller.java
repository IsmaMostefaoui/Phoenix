package com.umons.controller;

import javax.swing.JPanel;

import com.umons.model.*;

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
		Location temp = MyMouseListener.getClickCoord().pixelToCoord();
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
			RandomIA IA = (RandomIA) players[0];
			IA.play(game, finder, players[1]);
			game.nextPlayer();
			panel.repaint();			
		}
		if (game.getTour() == 1 && !players[1].isHumanPLayer()) {
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
			RandomIA IA = (RandomIA) players[3];
			IA.play(game, finder, players[2]);
			game.nextPlayer();
			panel.repaint();
		}
	}
	
	public void updatePanel(){
		panel.repaint();
	}
	
}
