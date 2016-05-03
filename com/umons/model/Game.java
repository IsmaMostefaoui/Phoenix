package com.umons.model;

import javax.swing.JPanel;

import com.umons.view.QuoridorGUI;

public class Game {

	private int numberPlayer;
	private static int tour = 0;
	private AMode mode;
	
	public Game(AMode mode) {
		this.mode = mode;
		this.numberPlayer = mode.getNumberOfPlayer();
		
	}
	
	/**
	 * Passe au joueur suivant
	 */
	public void nextPlayer() {
		tour = ((tour+1)%numberPlayer);
		
	}
	/**
	 * Accesseur de l'Attribut tour
	 * @return un int tour
	 */
	public int getTour() {
		return tour;
	}
	
	/**
	 * Réinitialise le tour en le mettant à 0 (i.e. pour le premier joueurs)
	 */
	public void resetTour() {
		tour = 0;
	}
	
	/**
	 * Setter pour initialiser le nombre de tour
	 */
	public static void setTour(int t) {
		tour = t;
	}
	
	/*
	public boolean play(Player player, Location loc) {
		if (loc.isSquare()) {
			return player.move(loc);
		}else if (loc.isWallHorizontal() || loc.isWallVertical()) {
			return player.putWall(loc);
		}return false;
	}*/


	
	/**
	 * Verifie si le Joueur à gagner la partie (A atteint l'extremité opposé de la grille)
	 * @param string une instance le Player
	 * @return True si le joueur à gagner,sinon false
	 */
	public boolean win(Player string) {
		if (string.getOrder() == 1 || string.getOrder() == 2){
			return string.getCoordFinish() == string.getLoc().getLocY();
		}else {
			return string.getCoordFinish() == string.getLoc().getLocX();
		}
		/*
		if (player.getOrder() == 1) {
			System.out.println("entre dans getorder == 1 + print du return: " + player.getLoc().equals(Player.POS2));
			return player.getLoc().getLocY() == Player.POS2.getLocY();
		}else if (player.getOrder() == 2) {
			return player.getLoc().getLocY() == Player.POS1.getLocY();
		}else if (player.getOrder() == 3) {
			return player.getLoc().getLocX() == Player.POS4.getLocX();
		}else if (player.getOrder() == 4) {
			return player.getLoc().getLocX() == Player.POS3.getLocX();
		}return false;*/
	}
	
	public void init() {
		//TODO
	}
	
	public void stop(JPanel off) {
		off.setFocusable(false);
		off.setEnabled(false);
	}
	
	public AMode getMode() {
		return mode;
	}
	
	public QuoridorGUI getFrame() {
		//TODO
		return null;
	}

}
