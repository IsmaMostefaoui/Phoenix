package com.umons.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.umons.model.*;
import com.umons.view.BoardGUI;
import com.umons.view.QuoridorGUI;

public class Controller {
	
	
	//temp
	int i = 0;
	
	private AMode mode;
	private IPathFinder finder;
	private Game game;
	private JPanel panel;
	private Player[] players;
	
	/**
	 * Constructeur du controller pour le mode graphique
	 * @param mode Instance du mode de jeu (1vs1, 2vs2)
	 * @param panel Un JPanel représentant le panel de la grille
	 * @param game une instance de game régissant certaines règles
	 * @param finder l'instance de l'algorithme choisi pour le pathfinding
	 */
	public Controller(AMode mode, JPanel panel, Game game, IPathFinder finder){
		this.panel = panel;
		this.game = game;
		this.finder = finder;
		this.mode = mode;
		players = mode.getPlayer();
	}
	
	/**
	 * Constructeur du controller pour le mode console
	 * @param mode
	 * @param game
	 * @param finder
	 */
	public Controller(AMode mode, Game game, IPathFinder finder){
		this.game = game;
		this.finder = finder;
		this.mode = mode;
		players = mode.getPlayer();
	}
	
	public void makePlayerPlay(){
		Location temp = Controller.pixelToCoord(MyMouseListener.getClickCoord());
		try {
			if (game.getTour() == 0 && players[0].isHumanPlayer()) {
				players[0].play(game, temp, finder);
				if (game.win(players[0])){
					winScreen("Joueur Jaune");
				}else {
					makeRobotPlay();
				}
			}
			else if (game.getTour() == 1 && players[1].isHumanPlayer()) {
				players[1].play(game, temp, finder);
				if (game.win(players[1])){
					winScreen("Joueur Bleu");
				}else {
					makeRobotPlay();
				}
			}
			else if (game.getTour() == 2 && players[2].isHumanPlayer()) {
				players[2].play(game, temp, finder);
				if (game.win(players[2])){
					winScreen("Joueur Violet");
				}else {
					makeRobotPlay();
				}
			}
			else if (game.getTour() == 3 && players[3].isHumanPlayer()) {
				players[3].play(game, temp, finder);
				if (game.win(players[3])){
					winScreen("Joueur Vert");
				}else {
					makeRobotPlay();
				}
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
		if (game.getTour() == 0 && !players[0].isHumanPlayer()) {
			IRobot IA = (IRobot) players[0];
			IA.play(game, finder, players[1]);
			game.nextPlayer();
			panel.repaint();
			if (game.win((Player) (IA))) {
				winScreen("Robot Jaune");
			}
		}
		if (game.getTour() == 1 && !players[1].isHumanPlayer()) {
			//on sait alors que c est un robot donc on cast pour acceder a la methode move de l IA
			//parce que le move de MediumIA n est pas la surcharge du move de player (il aurait fallu qu ils aient la meme signature)
			//donc, si on cast pas, il va chercher si player a un move avec cette signature, ce qui est faux, donc bug compil
			IRobot IA = (IRobot) players[1];
			IA.play(game, finder, players[0]);
			game.nextPlayer();
			panel.repaint();
			if (game.win((Player) (IA))) {
				winScreen("Robot Bleu");
			}
		}

		if (game.getTour() == 2 && !players[2].isHumanPlayer()) {
			IRobot IA = (IRobot) players[2];
			IA.play(game, finder, players[3]);
			game.nextPlayer();
			panel.repaint();
			if (game.win((Player) (IA))) {
				winScreen("Robot Violet");
			}
		}

		if (game.getTour() == 3 && !players[3].isHumanPlayer()) {
			IRobot IA = (IRobot) players[3];
			IA.play(game, finder, players[2]);
			game.nextPlayer();
			panel.repaint();
			if (game.win((Player) (IA))) {
				winScreen("Robot Vert");
			}
		}
		
		//TODO
		if (mode.getAllPlayerRobot() && i != 50) {
			i++;
			makeRobotPlay();
		}
	}
	
	public void updatePanel(){
		panel.repaint();
	}
	 /**
	  * Fait apparaître la fenetre informant le joueur du gagnat de la partie !
	  */
	public  void winScreen(String player) {
		final QuoridorGUI parentFrame = (QuoridorGUI) ((BoardGUI)panel).getParent().getParent().getParent();
		final JDialog win = new JDialog(parentFrame, true);
		win.setTitle("!! Félicitations !!");
		win.setLayout(new FlowLayout());
		
		JLabel winner = new JLabel("Bravo " + player + ", vous avez gagné !");
		win.add(winner);
		
		JButton playAgain = new JButton("Recommencer");
		win.add(playAgain);
		playAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode.reset();
				parentFrame.initGame("", mode);
				win.dispose();
				
				/*
				Game game = new Game(mode);
				mode.init(parentFrame, game);
				parentFrame.setPane(mode.getPane(), QuoridorGUI.BOARDGUI);
				parentFrame.switchToPanel(QuoridorGUI.BOARDGUI);
				win.dispose();
				mode.getPane().repaint();
				*/
			}
		});
		
		JButton backToMenu = new JButton("Revenir au menu");
		win.add(backToMenu);
		backToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentFrame.switchToPanel(QuoridorGUI.MENUGUI);	
				win.dispose();
			}
		});
		
		JButton quit = new JButton("Quitter");
		win.add(quit);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		win.setSize(4*QuoridorGUI.WIDTH/10, QuoridorGUI.HEIGHT/9);
		win.setLocationRelativeTo(null);
		win.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		win.setVisible(true);
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
