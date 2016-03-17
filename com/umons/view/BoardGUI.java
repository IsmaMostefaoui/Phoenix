package com.umons.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import com.umons.model.*;

public class BoardGUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	public static int SQUARE_NUMBER = 9;
	Game game;
	Player player1;
	Player player2;
	//constante representant la taille des carre, des murs et des pions
	public static final int lSquare = 50;
	public static final int lWall = 25;
	public static final int lPawn = 49;
	//constante representant les x et y a partir d'ou on commence a dessiner le carre
	public static final int START_X = 25;
	public static final int START_Y = 25;
	//position des joueurs et des murs horizontaux et verticaux
	public static Location locPawn1 = Player.POS1;
	public static Location locPawn2 = Player.POS2;
	public static ArrayList<Location>locWallHorizontal = new ArrayList<Location>();
	public static ArrayList<Location>locWallVertical = new ArrayList<Location>();
	
	public BoardGUI(Game game, Player player1, Player player2) {
		this.player1 = player1; this.player2 = player2;
		this.game = game;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		int SPACE = 0;
		int HEIGHT = 0;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 835, 835);
		g2d.setColor(Color.WHITE);
		for (int i =0; i < SQUARE_NUMBER; i++) {
			for (int j = 0; j < SQUARE_NUMBER; j++) {
				g2d.fillRect(START_X+SPACE, START_Y+HEIGHT, lSquare, lSquare);
				SPACE += lSquare + lWall;
			}
			SPACE =0;
			HEIGHT += lSquare + lWall;
		}
		if (game.getTour() == 0) {
			g2d.setColor(Color.ORANGE);
			g2d.drawString("JOUEUR 1", 735, 60);
		}else if (game.getTour() == 1){
			g2d.setColor(Color.GREEN);
			g2d.drawString("JOUEUR 2", 735, 60);
		}
		g2d.setColor(Color.ORANGE);
		g2d.fillOval(locPawn1.coordToPixel().getLocX(), locPawn1.coordToPixel().getLocY(), lPawn, lPawn);
		g2d.setColor(Color.GREEN);
		g2d.fillOval(locPawn2.coordToPixel().getLocX(), locPawn2.coordToPixel().getLocY(), lPawn, lPawn);
		g2d.setColor(Color.RED);
		for (int i = 0; i < locWallHorizontal.size(); i++) {
			Location loc = locWallHorizontal.get(i);
			g2d.fillRect(loc.coordToPixel().getLocX(), loc.coordToPixel().getLocY()+5, 2*lSquare+lWall, lWall-10);
		}
		for (int i = 0; i < locWallVertical.size(); i++) {
			Location loc = locWallVertical.get(i);
			g2d.fillRect(loc.coordToPixel().getLocX()+5, loc.coordToPixel().getLocY(), lWall-10, 2*lSquare+lWall);
		}
	}
}
