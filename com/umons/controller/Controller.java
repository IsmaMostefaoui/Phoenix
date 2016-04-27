package com.umons.controller;

import javax.swing.JPanel;

import com.umons.model.*;

public class Controller {
	
	private IMode mode;
	private IPathFinder finder;
	private Game game;
	private JPanel panel;
	
	public Controller(IMode mode, JPanel panel, Game game, IPathFinder finder){
		this.panel = panel;
		this.game = game;
		this.finder = finder;
		this.mode = mode;
	}
	
	public void makePlayerPlay(Player player1, Player player2, Player player3, Player player4) {
		Location temp = MyMouseListener.getClickCoord().pixelToCoord();
		if (game.getTour() == 0) {
			player1.play(game, temp, finder);
			makeRobotPlay(player1, player2, player3, player4);
		}else if (game.getTour() == 1){
			player2.play(game, temp, finder);
			makeRobotPlay(player1, player2, player3, player4);
		}else if (game.getTour() == 2) {
			player3.play(game, temp, finder);
			makeRobotPlay(player1, player2, player3, player4);
		}else if (game.getTour() == 3){
			player4.play(game, temp, finder);
			makeRobotPlay(player1, player2, player3, player4);
		}panel.repaint();
	}
	
	public void makeRobotPlay(Player player1, Player player2, Player player3, Player player4) {
		// ca je le met pour directement check si les joueur suivant est un robot. Ainsi je profite du fait que le joueur reel ait clicke
		// pour faire joueur ceux qui ne sont pas reels.
		if (game.getTour() == 0 && !player1.isHumanPLayer()) {
			System.out.println("je suis rentré dans player1 est un robot ???");
			MediumIA IA = (MediumIA) player1;
			IA.play(game, finder, player2);
			game.nextPlayer();
		}
		if (game.getTour() == 1 && !player2.isHumanPLayer()) {
			//on sait alors que c est un robot donc on cast pour acceder a la methode move de l IA
			//parce que le move de MediumIA n est pas la surcharge du move de player (il aurait fallu qu ils aient la meme signature)
			//donc, si on cast pas, il va chercher si player a un move avec cette signature, ce qui est faux, donc bug compil
			MediumIA IA = (MediumIA) player2;
			IA.play(game, finder, player1);
			game.nextPlayer();
		}
		if (game.getTour() == 2 && !player3.isHumanPLayer()) {
			MediumIA IA = (MediumIA) player3;
			IA.play(game, finder, player4);
			game.nextPlayer();
		}
		if (game.getTour() == 3 && !player4.isHumanPLayer()) {
			MediumIA IA = (MediumIA) player4;
			IA.play(game, finder, player3);
			game.nextPlayer();
		}
	}
	
	public void updatePanel(){
		panel.repaint();
	}
	
}
