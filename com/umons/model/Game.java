package com.umons.model;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {

	private int numberPlayer;
	private static int tour = 0;
	private Grid board;
	private Mode mode;
	
	public Game(Mode mode) {
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
	 * @param player une instance le Player
	 * @return True si le joueur à gagner,sinon false
	 */
	public boolean win(Player player) {
		System.out.println("entre dans win");
		if (player.getOrder() == 1 || player.getOrder() == 2){
			return player.getCoordFinish() == player.getLoc().getLocY();
		}else {
			return player.getCoordFinish() == player.getLoc().getLocX();
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
		mode.init(this);
	}
	
	public void stop(JPanel off) {
		off.setEnabled(false);
		off.setVisible(false);
	}
	
	public Mode getMode() {
		return mode;
	}

}