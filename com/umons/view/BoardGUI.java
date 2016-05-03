package com.umons.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.umons.controller.Controller;
import com.umons.controller.MyMouseListener;
import com.umons.model.*;

/**
 * Surcharge de JPanel. Classe représentant le plateau du Quoridor (dessin)
 * @author isma
 *
 */
public class BoardGUI extends JPanel{

	
	private Font customFont;
	private Game game;
	MyButton backButton;
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	
	InfoGUI infoPanel;
	
	//constante representant la taille des carre, des murs et des pions
	public static int SQUARE_NUMBER = 9;
	
	public static final int lSquare = QuoridorGUI.width/28;
	public static final Color[] colorPawn = {new Color(243, 156, 18), new Color(100, 50, 250), new Color(220, 50, 250),new Color(46, 204, 113)};
	public static final Color colorPreviewWall = new Color(52, 73, 94, 120);
	public static final Color colorWall = new Color(52, 73, 94);
	
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
		
		this.player1 = game.getMode().getPlayer()[0]; 
		this.player2 = game.getMode().getPlayer()[1];
		
		if (game.getMode().getNumberOfPlayer() == 4) {
			this.player3 = game.getMode().getPlayer()[2];
			this.player4 = game.getMode().getPlayer()[3];
		}
		this.game = game;
		customFont = new Font("comic sans ms", 10, 11);
		infoPanel = new InfoGUI();
		this.setLayout(new BorderLayout());
		this.add(infoPanel, BorderLayout.EAST);
		infoPanel.setPreferredSize(new Dimension(lInfo,QuoridorGUI.HEIGHT));
		
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
		
		drawSquares(g2d, new Color(236, 240, 241));
		System.out.println("REPAINT IN BOARD");
		
		drawTour(g2d);
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
	
		drawWallHorizontal(g2d, colorWall);
		drawWallVertical(g2d, colorWall);
		
		/*
		 * if (game.win(player1)){
			winScreen("Joueur Jaune");
		}else if (game.win(player2)) {
			winScreen("Joueur Bleu");
		}else if (player3 != null && game.win(player3)){
			winScreen("Joueur Violet");
		}else if (player4 != null && game.win(player4)) {
			winScreen("Joueur Vert");
		}*/
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
		Location locPix = Controller.coordToPixel(locPawn);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(locPix.getLocX(), locPix.getLocY(), lPawn-2, lPawn-2);
		g2d.setColor(Color.WHITE);
		g2d.drawOval(locPix.getLocX(), locPix.getLocY(), lPawn-1, lPawn-1);
		if (numberOfWall != 10) {
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.drawString(Integer.toString(numberOfWall), locPix.getLocX() + 2*lPawn/5, locPix.getLocY() + 3*lPawn/5);
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
			Location locPix = Controller.coordToPixel(loc);
			g2d.fillRect(locPix.getLocX(), locPix.getLocY() + lSpaceWall/2, 2*lSquare+lWall, lWall - lSpaceWall);
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
			Location locPix = Controller.coordToPixel(loc);
			g2d.fillRect(locPix.getLocX()+lSpaceWall/2, locPix.getLocY(), lWall - (lSpaceWall), 2*lSquare+lWall);
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
		if (motionCoord != null) {
			Location motionCoordPix = Controller.coordToPixel(motionCoord);
			if (motionCoord != null && motionCoord.isSquare()){ 
				List<Location> list = ARules.rSquareAvailable(player);
				if (list.contains(motionCoord)) {
					g2d.setColor(new Color(46, 204, 113, 170));
					g2d.fillRect(motionCoordPix.getLocX(), motionCoordPix.getLocY(), lSquare-5, lSquare-5);
				}else {
					g2d.setColor(new Color(204, 0, 0, 120));
					g2d.fillRect(motionCoordPix.getLocX(), motionCoordPix.getLocY(), lSquare-5, lSquare-5);
				}
			}else if (canPreviewHor(motionCoord, player)) {
				g2d.setColor(c);
				g2d.fillRect(motionCoordPix.getLocX(), motionCoordPix.getLocY()+(lSpaceWall)/2, 2*lSquare+lWall, lWall - (lSpaceWall));
			}else if (canPreviewVer(motionCoord, player)) {
				g2d.setColor(c);
				g2d.fillRect(motionCoordPix.getLocX()+(lSpaceWall)/2, motionCoordPix.getLocY(), lWall - (lSpaceWall), 2*lSquare+lWall);
			}
		}
	}
	
	/**
	 * Vérifie si on est autorise a afficher la preview d un mur horizontal
	 * @param motionCoord les coordonnes de l endroit de la previsualtion
	 * @param player le joueur courant
	 * @return True si la preview peut se faire normalement
	 */
	public boolean canPreviewHor(Location motionCoord, Player player) {
		if (MyMouseListener.prevCoord != null && motionCoord.equals(MyMouseListener.prevCoord)) {
			return motionCoord != null && motionCoord.isWallHorizontal() && ARules.rPutWall(motionCoord) && ARules.rSlotFull(motionCoord)
					&& player.getNbreOfWall() > 0;
		}else {
			//TODO mettre getter et setter
			MyMouseListener.prevCoord = motionCoord;
			return motionCoord != null && motionCoord.isWallHorizontal() && ARules.rPutWall(motionCoord) && ARules.rSlotFull(motionCoord)
					&& player.getNbreOfWall() > 0 && game.getMode().testFinder(player, motionCoord, game.getMode().getFinder());
		}
	}
	
	/**
	 * Vérifie si on est autorise a afficher la preview d un mur vertical
	 * @param motionCoord les coordonnes de l endroit de la previsualtion
	 * @param player le joueur courant
	 * @return True si la preview peut se faire normalement
	 */
	public boolean canPreviewVer(Location motionCoord, Player player) {
		//TODO opti comme pour horizontal sinon previexw meme si bloque joueur
		if (MyMouseListener.prevCoord != null && motionCoord.equals(MyMouseListener.prevCoord)) {
			return motionCoord != null && motionCoord.isWallVertical() && ARules.rPutWall(motionCoord) && ARules.rSlotFull(motionCoord)
					&& player.getNbreOfWall() > 0;
		}else {
			//TODO mettre getter et setter
			MyMouseListener.prevCoord = motionCoord;
			return motionCoord != null && motionCoord.isWallVertical() && ARules.rPutWall(motionCoord) && ARules.rSlotFull(motionCoord)
					&& player.getNbreOfWall() > 0 && game.getMode().testFinder(player, motionCoord, game.getMode().getFinder());
		}
	}
	
	/**
	 * Temporaire, dessine le "tour" courant en haut a droite
	 * @param g2d l outil pour dessiner
	 */
	public void drawTour(Graphics2D g2d) {
		if (game.getTour() == 0) {
			g2d.setColor(new Color(243, 156, 18, 175));
			Location player = Controller.coordToPixel(player1.getLoc());
			g2d.fillRect(player.getLocX(), player.getLocY(), lSquare-5, lSquare-5);
		}else if (game.getTour() == 1){
			g2d.setColor(new Color(100, 50, 250, 175));
			Location player = Controller.coordToPixel(player2.getLoc());
			g2d.fillRect(player.getLocX(), player.getLocY(), lSquare-5, lSquare-5);
		}else if (game.getTour() == 2) {
			g2d.setColor(new Color(220, 50, 250, 175));
			Location player = Controller.coordToPixel(player3.getLoc());
			g2d.fillRect(player.getLocX(), player.getLocY(), lSquare-5, lSquare-5);
		}else if (game.getTour() == 3) {
			g2d.setColor(new Color(46, 204, 113, 175));
			Location player = Controller.coordToPixel(player4.getLoc());
			g2d.fillRect(player.getLocX(), player.getLocY(), lSquare-5, lSquare-5);
		}
	}
	
	/**
	 * Réinitialise le BoardGUI (joueurs au postion initiale, supression des murs restants, tour réinitialisé )
	 */
	public void reset(){
		locPawn1 = Player.POS1;
		locPawn2 = Player.POS2;
		locPawn3 = Player.POS3;
		locPawn4 = Player.POS4;
		
		locWallHorizontal.clear();
		locWallVertical.clear();
		
		game.resetTour();
		
	}
	
	/**
	 * Crée une board avec des positions de joueurs et de mur d'une ancienne partie.
	 * @return un BoardGUI repréentant la board chargé !
	 */
	public BoardGUI reload(Player[] players, ArrayList<Location>locWallHorizontalParam, ArrayList<Location>locWallVerticalParam, int tour) {
		this.player1 = players[0];
		this.player2 = players[1];
		if (players.length > 2) {
			this.player3 = players[2];
			this.player4 = players[3];
		}
		
		locPawn1 = player1.getLoc();
		locPawn2 = player2.getLoc();
		if (players.length > 2) {
			System.out.println("players.length: " + players.length);
			locPawn3 = player3.getLoc();
			locPawn4 = player4.getLoc();
		}
		
		locWallHorizontal = locWallHorizontalParam;
		locWallVertical = locWallVerticalParam;
		
		Game.setTour(tour);
		
		return this;
	}
	
	/**
	 * Classe représentant le panel de droite lors de l'affichage de la board.
	 * Sert à accueillir le bouton save, back et le label d'affichage des infos de jeu (//TODO)
	 * @author isma
	 *
	 */
	private class InfoGUI extends JPanel{

		private static final long serialVersionUID = 1L;
		
		JLabel labelPlayerTour;
		
		MyButton backButton = new MyButton("BACK", new Color(127, 140, 141));
		MyButton saveButton = new MyButton("SAVE", new Color(127, 140, 141));
		//MyButton passButton = new MyButton("PASS", new Color(127, 140, 141));
		
		/**
		 * Initalise le panel d'Info. Place deux boutons save et back.
		 * @param parentFrame la frame sur laquelle est posé le panel
		 */
		public InfoGUI() {
			
			this.setLayout(new BorderLayout());
			
			backButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame parentFrame = (JFrame) InfoGUI.this.getParent().getParent().getParent().getParent();
					((QuoridorGUI) parentFrame).switchToPanel(QuoridorGUI.MENUGUI);
				}
			});
			this.add(backButton, BorderLayout.SOUTH);
			
			saveButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try{
						File f = new File("./save");
						f.mkdir();
						FileOutputStream fos = new FileOutputStream("./save/save.sv");
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						ObjectOutputStream oos = new ObjectOutputStream(bos);
						oos.writeObject(game.getMode());
						oos.writeObject(locWallHorizontal);
						oos.writeObject(locWallVertical);
						//TODO save le mode AMode pour générer un mode dans le reload qui correspond au mode précedent
						oos.writeInt(game.getTour());
						System.out.println("Sauvegarde réussi !");
						oos.close();
						bos.close();
						fos.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
			this.add(saveButton, BorderLayout.NORTH);
			
			//TODO
			/*
			passButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//TODO
					System.out.println("Passe le tour");
				}
			});
			this.add(passButton, BorderLayout.NORTH);*/
			
			labelPlayerTour = new JLabel();
			this.add(labelPlayerTour, BorderLayout.WEST);
			this.setBackground(new Color(127, 140, 141));
		}
	}
}
