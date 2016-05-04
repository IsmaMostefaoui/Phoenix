package com.umons.model;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.umons.controller.Controller;
import com.umons.controller.MyMouseListener;
import com.umons.view.BoardGUI;
import com.umons.view.QuoridorGUI;

public abstract class AMode implements Serializable{
	
	private static final long serialVersionUID = 7479369038416187671L;
	
	protected Player[] players;
	protected Grid board;
	protected transient JPanel boardPanel;
	protected transient JFrame frame;
	protected AStarHeuristic heuristic;
	protected IPathFinder finder;
	protected int nbreHumans;
	protected transient Controller controller;
	
	public static final int EASY = 0;
	public static final int MEDIUM = 1;
	public static final int DIFFICULT = 2;
	
	
	/**
	 * Initalise une game
	 * @param game un objet game ou sont definis certaines fonctions pratique concernant le deroulement d une partie
	 */
	public void init(QuoridorGUI frame, Game game){
		ARules.setBoard(board);
		JPanel board = new BoardGUI(game);
		
		this.boardPanel = board;
		this.frame = frame;
		
		board.setFocusable(true);
		controller = new Controller(this, board, game, finder);
		MyMouseListener l = new MyMouseListener(controller);
		board.addMouseListener(l);
		board.addMouseMotionListener(l);
		frame.setPane(board, QuoridorGUI.BOARDGUI);
		try {
			//TODO
			controller.makeRobotPlay();
			board.repaint();
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	/**
	 * Initalise une game pour le mode console
	 * @param game un objet game ou sont definis certaines fonctions pratique concernant le deroulement d une partie
	 * @return le numéro du joueur qui a gagné (1, 2, 3 ou 4)
	 */
	public int play(Game game) {
		ARules.setBoard(board);
		controller = new Controller(this, game, finder);
		if (this.getAllPlayerRobot()) {
			//try {
				return controller.makeRobotPlayTerminal();
			/*
			 }catch (InterruptedException ie) {
				System.err.println("Erreur de thread dans Mode: ");
				ie.printStackTrace();
			}*/
		}
		return -1;
	}
	
	/**
	 * Initialise un joueur de la partie comme étant une IA
	 * @param IA le degré de difficulté de l'IA
	 * @return Une instance de Player correspondant à une IA
	 */
	public Player setPlayerTo(int IA) {
		switch (IA){
		case AMode.EASY:
			return new RandomIA(board, Player.POS2, 2, this);
		case AMode.MEDIUM:
			return new MediumIA(board, Player.POS2, 2, this);
		case AMode.DIFFICULT:
			return new RegularIA(board, Player.POS2, 2, this);
		}return null;
	}
	
	/**
	 * @return le nombre de joueur dans la game
	 */
	public abstract int getNumberOfPlayer();
	
	/**
	 * 
	 * @return les instance de player dans l ordre de leur numero
	 */
	public abstract Player[] getPlayer();
	
	/**
	 * Test si il y a un chemin pour le joueur player apres qu'il ait mis un mur de coordonées coordWall selon l'algorithme de recherche "finder"
	 * @param player l instance du joueur qui pose le mur
	 * @param finder
	 * @param loc la position du mur qui risque de bloquer un joueur
	 * @return vrai si il y a un chemin, faux sinon
	 */
	public abstract boolean testFinder(Player player, Location coordWall, IPathFinder finder);
	
	/**
	 * Retourne l'instance du PathFInding utilisé dans le mode.
	 * @return une instance implémentant IPathFinder
	 */
	public IPathFinder getFinder() {
		return finder;
	}
	
	/**
	 * Retourne le panel BoardGUI qui est défini dans mode.init();
	 * @return un JPanel correspondant au GUI du plateau
	 */
	public JPanel getPane() {
		return boardPanel;
	}
	
	/**
	 * Réinitialise le mode de jeu selon le constructeur
	 */
	public abstract void reset();
	
	/**
	 * Vérifie si tous les joueurs sont des IA
	 * @return vrai si les joueurs sont des IA, faux sinon
	 */
	public abstract boolean getAllPlayerRobot();
}

