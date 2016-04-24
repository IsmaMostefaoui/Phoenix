package com.umons.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import com.umons.controller.MyMouseListener;
import com.umons.model.*;

/**
 * Surcharge de JPanel. Classe représentant le plateau du Quoridor (dessin)
 * @author isma
 *
 */
public class BoardGUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private Font customFont;
	private Game game;
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	//constante representant la taille des carre, des murs et des pions
	public static int SQUARE_NUMBER = 9;
	public static final int lSquare = 60;
	public static final int lWall = 30;
	public static final int lPawn = 55;
	//constante representant les x et y a partir d'ou on commence a dessiner le carre
	public static final int START_X = 25;
	public static final int START_Y = 25;
	//position des joueurs et des murs horizontaux et verticaux en temps reel
	public static Location locPawn1 = Player.POS1;
	public static Location locPawn2 = Player.POS2;
	public static Location locPawn3 = Player.POS3;
	public static Location locPawn4 = Player.POS4;
	public static ArrayList<Location>locWallHorizontal = new ArrayList<Location>();
	public static ArrayList<Location>locWallVertical = new ArrayList<Location>();
	
	/**
	 * Constructeur du JPanel personnalise
	 * @param game une instance de game utilise principalement pour recuperer le tour courant
	 * @param player1 une instance du joueur 1
	 * @param player2 une instance du joueur 2
	 */
	public BoardGUI(Game game) {
		
		this.player1 = game.getMode().getPlayer()[0]; this.player2 = game.getMode().getPlayer()[1];
		if (game.getMode().getNumberOfPlayer() == 4) {
			this.player3 = game.getMode().getPlayer()[2];
			this.player4 = game.getMode().getPlayer()[3];
		}
		this.game = game;
		customFont = new Font("comic sans ms", 10, 11);
		/*try {
            //create the font to use. Specify the size!
			InputStream myStream = new BufferedInputStream(new FileInputStream("D:\\Mes documents\\worksplace\\Phoenix\\src\\com\\umons\\misc\\FunSized.ttf"));
            customFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(FontFormatException e)
        {
            e.printStackTrace();
        }
        */
	}
	
	/**
	 * Surcharge de paintComponent() pour dessiner le plateau de jeu (les cases, les fentes, les pions, les previews etc...)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(customFont);
		g2d.setColor(new Color(174, 50, 20));
		g2d.fillRect(0, 0, 865, 835);
		
		drawSquares(g2d, new Color(236, 240, 241));
		
		drawPawn(g2d, locPawn1, new Color(200, 250, 50), player1.getNbreOfWall());
		drawPawn(g2d, locPawn2, new Color(100, 50, 250), player2.getNbreOfWall());
		if (game.getMode().getNumberOfPlayer() == 4) {
			drawPawn(g2d, locPawn3, new Color(220, 50, 250), player3.getNbreOfWall());
			drawPawn(g2d, locPawn4, new Color(225, 220, 50), player4.getNbreOfWall());
		}
		switch (game.getTour()) {
		case 0:
			drawPreview(g2d, new Color(122, 200, 200, 120), player1);
			break;
		case 1:
			drawPreview(g2d, new Color(122, 200, 200, 120), player2);
			break;
		case 3:
			drawPreview(g2d, new Color(122, 200, 200, 120), player3);
			break;
		case 4:
			drawPreview(g2d, new Color(122, 200, 200, 120), player4);
			break;
		}
		
		drawTour(g2d);
		
		drawWallHorizontal(g2d, new Color(122, 200, 200));
		drawWallVertical(g2d, new Color(122, 200, 200));
	}
	
	/**
	 * Dessine un pion sur la case donne en parametre avec un nombre de mur et une couleur predefinie.
	 * @param g2d l outil pour dessiner le pion
	 * @param locPawn l objet Location representant la case sur la quelle doit etre dessiner le pion (coordonnee tableau/PAS pixel)
	 * @param c un objet Color representant la couleur du pion
	 * @param numberOfWall le nombre de mur restant (selon les joueurs)
	 */
	public void drawPawn(Graphics2D g2d, Location locPawn, Color c, int numberOfWall) {
		g2d.setColor(c);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(locPawn.coordToPixel().getLocX(), locPawn.coordToPixel().getLocY(), lPawn, lPawn);
		if (numberOfWall != 10) {
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.drawString(Integer.toString(numberOfWall), locPawn.coordToPixel().getLocX() + 23, locPawn.coordToPixel().getLocY() + 33);
		}
	}
	
	/**
	 * Dessine un mur horizontal avec une couleur c
	 * @param g2d l outil pour dessiner le mur
	 * @param c un objet Color representant la couleur du mur
	 */
	public void drawWallHorizontal(Graphics2D g2d, Color c) {
		g2d.setColor(c);
		for (int i = 0; i < locWallHorizontal.size(); i++) {
			Location loc = locWallHorizontal.get(i);
			g2d.fillRect(loc.coordToPixel().getLocX(), loc.coordToPixel().getLocY()+5, 2*lSquare+lWall, lWall-10);
		}
	}


	
	/**
	 * Dessine un mur vertical avec une couleur c
	 * @param g2d l outil pour dessiner le mur
	 * @param c un objet Color representant la couleur du mur
	 */
	public void drawWallVertical(Graphics2D g2d, Color c) {
		g2d.setColor(c);
		for (int i = 0; i < locWallVertical.size(); i++) {
			Location loc = locWallVertical.get(i);
			g2d.fillRect(loc.coordToPixel().getLocX()+5, loc.coordToPixel().getLocY(), lWall-10, 2*lSquare+lWall);
		}
	}
	
	/**
	 * Dessine toutes les cases du plateau avec une couleur predefinie
	 * @param g2d l outil pour dessiner
	 * @param c un objet Color representant la couleur des cases
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
	
	/**
	 * Dessine les preview lorsque la souris passe sur une cases ou une fentes
	 * @param g2d l outil pour dessiner
	 * @param c un objet color representant la couleur de la preview (pour les murs)
	 * @param player une instance du joueur courant pour savoir si les case a "previewer" correspondent a des deplacements correcte ou pas
	 */
	public void drawPreview(Graphics2D g2d, Color c, Player player) {
		Location motionCoord = MyMouseListener.getMotionCoord();
		if (motionCoord != null && motionCoord.isSquare()){ 
			List<Location> list = ARules.rSquareAvailable(player);
			if (list.contains(motionCoord)) {
				g2d.setColor(new Color(46, 204, 113, 170));
				g2d.fillRect(motionCoord.coordToPixel().getLocX(), motionCoord.coordToPixel().getLocY(), lSquare-5, lSquare-5);
			}else {
				g2d.setColor(new Color(204, 0, 0, 120));
				g2d.fillRect(motionCoord.coordToPixel().getLocX(), motionCoord.coordToPixel().getLocY(), lSquare-5, lSquare-5);
			}
		}else if (canPreviewHor(motionCoord, player)) {
			g2d.setColor(c);
			g2d.fillRect(motionCoord.coordToPixel().getLocX(), motionCoord.coordToPixel().getLocY()+5, 2*lSquare+lWall, lWall-10);
		}else if (canPreviewVer(motionCoord, player)) {
			g2d.setColor(c);
			g2d.fillRect(motionCoord.coordToPixel().getLocX()+5, motionCoord.coordToPixel().getLocY(), lWall-10, 2*lSquare+lWall);
		}
	}
	
	/**
	 * Vérifie si on est autorise a afficher la preview d un mur horizontal
	 * @param motionCoord les coordonnes de l endroit de la previsualtion
	 * @param player le joueur courant
	 * @return True si la preview peut se faire normalement
	 */
	public boolean canPreviewHor(Location motionCoord, Player player) {
		return motionCoord != null && motionCoord.isWallHorizontal() && ARules.rPutWall(motionCoord) && ARules.rSlotFull(motionCoord)
				&& player.getNbreOfWall() > 0;
	}
	
	/**
	 * Vérifie si on est autorise a afficher la preview d un mur vertical
	 * @param motionCoord les coordonnes de l endroit de la previsualtion
	 * @param player le joueur courant
	 * @return True si la preview peut se faire normalement
	 */
	public boolean canPreviewVer(Location motionCoord, Player player) {
		return motionCoord != null && motionCoord.isWallVertical() && ARules.rPutWall(motionCoord) && ARules.rSlotFull(motionCoord)
				&& player.getNbreOfWall() > 0;
	}
	
	/**
	 * Temporaire, dessine le "tour" courant en haut a droite
	 * @param g2d l outil pour dessiner
	 */
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
	
	/**
	 * Pas encore fonctionnel
	 * @param g2d
	 */
	public void drawVictory(Graphics g2d) {
		JPanel victoryPanel = new VictoryPanel();
	}
}
