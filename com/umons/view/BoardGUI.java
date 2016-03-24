package com.umons.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.util.ArrayList;
import java.util.List;

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
	public static final int lSquare = 60;
	public static final int lWall = 30;
	public static final int lPawn = 55;
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
		g2d.setFont(new Font("Comic Sans Ms", Font.BOLD, 11));
		g2d.setColor(new Color(170, 57, 43));
		g2d.fillRect(0, 0, 835, 835);
		
		drawSquares(g2d, new Color(236, 240, 241));
		
		drawPawn(g2d, locPawn1, new Color(200, 250, 50), player1.getNbreOfWall());
		drawPawn(g2d, locPawn2, new Color(100, 50, 250), player2.getNbreOfWall());
		if (game.getTour() == 0) {
			drawPreview(g2d, player1);
		//rajouter des elif quand on sera sûr d'avoir un mode 4 joueur
		}else {
			drawPreview(g2d, player2);	
		}
		
		drawTour(g2d);
		
		drawWallHorizontal(g2d, new Color(100, 0, 0));
		drawWallVertical(g2d, new Color(200, 0, 0));
	}
	
	public void drawPawn(Graphics2D g2d, Location locPawn, Color c, int numberOfWall) {
		g2d.setColor(c);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(locPawn.coordToPixel().getLocX(), locPawn.coordToPixel().getLocY(), lPawn, lPawn);
		System.out.println("numbofwall: " + numberOfWall);
		if (numberOfWall != 10) {
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font ("Comic Sans Ms", Font.BOLD, 15));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.drawString(Integer.toString(numberOfWall), locPawn.coordToPixel().getLocX() + 23, locPawn.coordToPixel().getLocY() + 33);
		}
	}
	
	public void drawWallHorizontal(Graphics2D g2d, Color c) {
		g2d.setColor(c);
		for (int i = 0; i < locWallHorizontal.size(); i++) {
			Location loc = locWallHorizontal.get(i);
			g2d.fillRect(loc.coordToPixel().getLocX(), loc.coordToPixel().getLocY()+5, 2*lSquare+lWall, lWall-10);
		}
	}
	
	public void drawWallVertical(Graphics2D g2d, Color c) {
		g2d.setColor(c);
		for (int i = 0; i < locWallVertical.size(); i++) {
			Location loc = locWallVertical.get(i);
			g2d.fillRect(loc.coordToPixel().getLocX()+5, loc.coordToPixel().getLocY(), lWall-10, 2*lSquare+lWall);
		}
	}
	
	public void drawSquares(Graphics2D g2d, Color c) {
		int SPACE = 0;
		int HEIGHT = 0;
		for (int i =0; i < SQUARE_NUMBER; i++) {
			for (int j = 0; j < SQUARE_NUMBER; j++) {
				g2d.setColor(new Color(100, 100, 100));
				g2d.fillRect(START_X+SPACE, START_Y+HEIGHT, lSquare, lSquare);
				g2d.setColor(Color.WHITE);
				g2d.fillRect(START_X+SPACE, START_Y+HEIGHT, lSquare-5, lSquare-5);
				SPACE += lSquare + lWall;
			}
			SPACE =0;
			HEIGHT += lSquare + lWall;
		}
	}
	
	public void drawPreview(Graphics2D g2d, Player player) {
		Location motionCoord = MyMouseListener.getMotionCoord();
		System.out.println("\nmotioncoord: " + motionCoord);
		if (motionCoord != null && motionCoord.isSquare()){ 
			List<Location> list = ARules.rSquareAvailable(player);
			if (list.contains(motionCoord)) {
				g2d.setColor(new Color(46, 204, 113, 170));
				g2d.fillRect(motionCoord.coordToPixel().getLocX(), motionCoord.coordToPixel().getLocY(), lSquare-5, lSquare-5);
			}else {
				g2d.setColor(new Color(204, 0, 0, 120));
				g2d.fillRect(motionCoord.coordToPixel().getLocX(), motionCoord.coordToPixel().getLocY(), lSquare-5, lSquare-5);
			}
		}else if (motionCoord != null && motionCoord.isWallHorizontal() && ARules.rPutWall(motionCoord)) {
			g2d.setColor(new Color(200, 0, 0, 100));
			g2d.fillRect(motionCoord.coordToPixel().getLocX(), motionCoord.coordToPixel().getLocY()+5, 2*lSquare+lWall, lWall-10);
		}else if (motionCoord != null && motionCoord.isWallVertical() && ARules.rPutWall(motionCoord)) {
			g2d.setColor(new Color(100, 0, 0, 100));
			g2d.fillRect(motionCoord.coordToPixel().getLocX()+5, motionCoord.coordToPixel().getLocY(), lWall-10, 2*lSquare+lWall);
		}
	}
	
	public void drawTour(Graphics2D g2d) {
		g2d.setFont(new Font("Comic Sans Ms", Font.BOLD, 11));
		if (game.getTour() == 0) {
			g2d.setColor(new Color(100, 250, 50));
			g2d.drawString("JOUEUR 1", 750, 20);
		}else if (game.getTour() == 1){
			g2d.setColor(new Color(100, 50, 250));
			g2d.drawString("JOUEUR 2", 750, 20);
		}
	}
	
	public void drawVictory(Graphics g2d) {
	}
}
