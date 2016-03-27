package com.umons.view;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
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
	public static final int lPawn = 54;
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
		g2d.setColor(new Color(170, 57, 43));
		g2d.fillRect(0, 0, 835, 835);
		
		drawSquares(g2d, new Color(236, 240, 241));
		
		drawPawn(g2d, locPawn1, new Color(200, 250, 50), player1.getNbreOfWall());
		drawPawn(g2d, locPawn2, new Color(100, 50, 250), player2.getNbreOfWall());
		
		drawWallHorizontal(g2d, new Color(100, 0, 0));
		drawWallVertical(g2d, new Color(200, 0, 0));
		if (game.getTour() == 0) {
			System.out.println("in");
			g2d.setColor(new Color(100, 250, 50));
			g2d.drawString("JOUEUR 1", 750, 20);
		}else if (game.getTour() == 1){
			g2d.setColor(new Color(100, 50, 250));
			g2d.drawString("JOUEUR 2", 750, 20);
		}
	}
	
	/**
	 * Dessine un Cercle dans la grille representant le Pion d'un joueur
	 * @param g2d Objet graphics
	 * @param locPawn Objet Location du joueur 
	 * @param c Couleur du Pion
	 * @param numberOfWall Nombre de mur restant au joueur
	 */
	public void drawPawn(Graphics2D g2d, Location locPawn, Color c, int numberOfWall) {
		/*
		// Create a translucent intermediate image in which we can perform
        // the soft clipping
        GraphicsConfiguration gc = g2d.getDeviceConfiguration();
        BufferedImage img = gc.createCompatibleImage(getWidth(), getHeight(), Transparency.TRANSLUCENT);
        Graphics2D g2 = img.createGraphics();

        // Clear the image so all pixels have zero alpha
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Render our clip shape into the image.  Note that we enable
        // antialiasing to achieve the soft clipping effect.  Try
        // commenting out the line that enables antialiasing, and
        // you will see that you end up with the usual hard clipping.
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        */
		g2d.setColor(c);
		g2d.fillOval(locPawn.coordToPixel().getLocX(), locPawn.coordToPixel().getLocY(), lPawn, lPawn);
		System.out.println("numbofwall: " + numberOfWall);
		if (numberOfWall != 10) {
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font ("Comic Sans Ms", Font.BOLD, 15));
			g2d.drawString(Integer.toString(numberOfWall), locPawn.coordToPixel().getLocX() + 23, locPawn.coordToPixel().getLocY() + 33);
		}
		/*
		g2.setComposite(AlphaComposite.SrcAtop);
        g2.setColor(Color.WHITE);
        int LINE_GAP = 1;
		int gap = LINE_GAP;
        AffineTransform at = g2.getTransform();

        g2.setTransform(AffineTransform.getRotateInstance(Math.toRadians(45),lPawn / 2, lPawn / 2));

        for (int index = 0; index < 10; index++) {
            int LINE_THICKNESS = 1;
			int x1 = index*gap-(LINE_THICKNESS/2);
            int y1 = 0;
            int x2 = index*gap+(LINE_THICKNESS/2);
            int y2 = lPawn;
            int width = x2 - x1;
            int height = y2 - y1;

            g2.fillRect(x1, y1, width, height);
        }

        g2.setTransform(at);
        g2.dispose();

        // Copy our intermediate image to the screen
        ((Graphics)g2d).drawImage(img, 0, 0, null);
        */
	}
	
	
	/**
	 * Dessine un mur horizontal dans la grille
	 * @param g2d Objet Graphics
	 * @param c Couleur mur
	 */
	public void drawWallHorizontal(Graphics2D g2d, Color c) {
		g2d.setColor(c);
		for (int i = 0; i < locWallHorizontal.size(); i++) {
			Location loc = locWallHorizontal.get(i);
			g2d.fillRect(loc.coordToPixel().getLocX(), loc.coordToPixel().getLocY()+5, 2*lSquare+lWall, lWall-10);
		}
	}
	/**
	 * Dessine un mur vertcal dans la grille
	 * @param g2d Objet Graphics
	 * @param c Couleur mur
	 */
	public void drawWallVertical(Graphics2D g2d, Color c) {
		g2d.setColor(c);
		for (int i = 0; i < locWallVertical.size(); i++) {
			Location loc = locWallVertical.get(i);
			g2d.fillRect(loc.coordToPixel().getLocX()+5, loc.coordToPixel().getLocY(), lWall-10, 2*lSquare+lWall);
		}
	}
	
	/**
	 * Dessine les cases dans la grille
	 * @param g2d Objet Graphics2D
	 * @param c Le couleur de la case
	 */
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
	
	public void drawPreviewSquare() {
		
	}
	
	public void drawPreviewWall() {
		
	}
	
	public void drawVictory(Graphics g2d) {
	}
}
