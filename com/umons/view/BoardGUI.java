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

import javax.swing.JFrame;
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
	
	public static final int lSquare = QuoridorGUI.width/28;
	public static final Color[] colorPawn = {new Color(243, 156, 18), new Color(100, 50, 250), new Color(220, 50, 250),new Color(46, 204, 113)};
	public static final Color colorPreviewWall= new Color(52, 73, 94, 120);
	public static final Color colorWAll = new Color(52, 73, 94);
	
	public static final int lSpaceEdge = 5;
	
	public static final int lWall = QuoridorGUI.width/60;
	public static final int lPawn = lSquare - lSpaceEdge;
	//constante representant les x et y a partir d'ou on commence a dessiner le carre
	public static final int START_X = 5*lWall/6;
	public static final int START_Y = 2*lWall/3;
	
	public static final int lBack = 9*lSquare + 8*lWall + START_X + START_Y;
	public static final int lInfo = QuoridorGUI.WIDTH - lBack;
	
	public final int lSpaceWall = 2*lWall/5;
	
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
		
		g2d.setColor(new Color(170, 57, 43));
		g2d.fillRect(0, 0, lBack, QuoridorGUI.HEIGHT);
		g2d.setColor(new Color(170, 57, 70));
		g2d.fillRect(lBack, 0, lInfo, QuoridorGUI.HEIGHT);

		
		drawSquares(g2d, new Color(236, 240, 241));
		
		drawPawn(g2d, locPawn1, colorPawn[0], player1.getNbreOfWall());
		drawPawn(g2d, locPawn2, colorPawn[1], player2.getNbreOfWall());
		if (game.getMode().getNumberOfPlayer() == 4) {
			drawPawn(g2d, locPawn3, colorPawn[2], player3.getNbreOfWall());
			drawPawn(g2d, locPawn4, colorPawn[3], player4.getNbreOfWall());
		}
		switch (game.getTour()) {
		case 0:
			drawPreview(g2d, colorPreviewWall, player1);
			break;
		case 1:
			drawPreview(g2d, colorPreviewWall, player2);
			break;
		case 2:
			drawPreview(g2d, colorPreviewWall, player3);
			break;
		case 3:
			drawPreview(g2d, colorPreviewWall, player4);
			break;
		}
		
		drawTour(g2d);
		
		drawWallHorizontal(g2d, colorWAll);
		drawWallVertical(g2d, colorWAll);
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
			g2d.drawString(Integer.toString(numberOfWall), locPawn.coordToPixel().getLocX() + 2*lPawn/5, locPawn.coordToPixel().getLocY() + 3*lPawn/5);
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
			g2d.fillRect(loc.coordToPixel().getLocX(), loc.coordToPixel().getLocY() + lSpaceWall/2, 2*lSquare+lWall, lWall - lSpaceWall);
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
			g2d.fillRect(loc.coordToPixel().getLocX()+lSpaceWall/2, loc.coordToPixel().getLocY(), lWall - (lSpaceWall), 2*lSquare+lWall);
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
				g2d.fillRect(START_X+SPACE, START_Y+HEIGHT, lSquare-lSpaceEdge, lSquare-lSpaceEdge);
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
		System.out.println("motioncoord dans player: " + motionCoord);
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
			g2d.fillRect(motionCoord.coordToPixel().getLocX(), motionCoord.coordToPixel().getLocY()+(lSpaceWall)/2, 2*lSquare+lWall, lWall - (lSpaceWall));
		}else if (canPreviewVer(motionCoord, player)) {
			g2d.setColor(c);
			g2d.fillRect(motionCoord.coordToPixel().getLocX()+(lSpaceWall)/2, motionCoord.coordToPixel().getLocY(), lWall - (lSpaceWall), 2*lSquare+lWall);
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
				&& player.getNbreOfWall() > 0 && game.getMode().testFinder(player, motionCoord, game.getMode().getFinder());
	}
	
	/**
	 * Vérifie si on est autorise a afficher la preview d un mur vertical
	 * @param motionCoord les coordonnes de l endroit de la previsualtion
	 * @param player le joueur courant
	 * @return True si la preview peut se faire normalement
	 */
	public boolean canPreviewVer(Location motionCoord, Player player) {
		return motionCoord != null && motionCoord.isWallVertical() && ARules.rPutWall(motionCoord) && ARules.rSlotFull(motionCoord)
				&& player.getNbreOfWall() > 0 && game.getMode().testFinder(player, motionCoord, game.getMode().getFinder());
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
