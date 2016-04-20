package com.umons.controller;

import javax.swing.JPanel;

import com.umons.model.*;

public class Controller {
	
	private IMode mode;
	private IPathFinder finder;
	private Game game;
	private JPanel panel;
	private Player[] players;
	
	public Controller(IMode mode, JPanel panel, Game game, IPathFinder finder){
		this.panel = panel;
		this.game = game;
		this.finder = finder;
		this.mode = mode;
		players = mode.getPlayer();
	}
	
	public void makePlayerPlay() {
		Location temp = MyMouseListener.getClickCoord().pixelToCoord();
		if (game.getTour() == 0) {
			players[0].play(game, temp, finder);
			makeRobotPlay();
		}else if (game.getTour() == 1){
			players[1].play(game, temp, finder);
			makeRobotPlay();
		}else if (game.getTour() == 2) {
			players[2].play(game, temp, finder);
			makeRobotPlay();
		}else if (game.getTour() == 3){
			players[3].play(game, temp, finder);
			makeRobotPlay();
		}panel.repaint();
	}
	
	public void makeRobotPlay() {
		// ca je le met pour directement check si les joueur suivant est un robot. Ainsi je profite du fait que le joueur reel ait clicke
		// pour faire joueur ceux qui ne sont pas reels.
		if (game.getTour() == 0 && !players[0].isHumanPLayer()) {
			System.out.println("je suis rentré dans player1 est un robot ???");
			RandomIA IA = (RandomIA) players[0];
			IA.move(game, finder, players[1]);
			game.nextPlayer();
		}
		if (game.getTour() == 1 && !players[1].isHumanPLayer()) {
			//on sait alors que c est un robot donc on cast pour acceder a la methode move de l IA
			//parce que le move de RandomIA n est pas la surcharge du move de player (il aurait fallu qu ils aient la meme signature)
			//donc, si on cast pas, il va chercher si player a un move avec cette signature, ce qui est faux, donc bug compil
			RandomIA IA = (RandomIA) players[1];
			IA.move(game, finder, players[0]);
			game.nextPlayer();
		}
		if (game.getTour() == 2 && !players[2].isHumanPLayer()) {
			RandomIA IA = (RandomIA) players[2];
			IA.move(game, finder, players[3]);
			game.nextPlayer();
		}
		if (game.getTour() == 3 && !players[3].isHumanPLayer()) {
			RandomIA IA = (RandomIA) players[3];
			IA.move(game, finder, players[2]);
			game.nextPlayer();
		}
	}
	
	public void updatePanel(){
		panel.repaint();
	}
	
}
