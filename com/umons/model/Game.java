package com.umons.model;

public class Game {

	private int numberPlayer;
	private int tour = 0;
	private Grid board;
	
	public Game(Grid board, int numberPlayer) {
		this.numberPlayer = numberPlayer;
		this.board = board;
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

	public boolean play(Player player, Location loc) {
		if (loc.isSquare()) {
			return player.move(board, loc);
		}else if (loc.isWallHorizontal() || loc.isWallVertical()) {
			return player.putWall(board, loc);
		}return false;

	}
	/**
	 * Verifie si le Joueur à gagner la partie (A atteint l'extremité opposé de la grille)
	 * @param player une instance le Player
	 * @return True si le joueur à gagner,sinon false
	 */
	public boolean win(Player player) {
		if (player.getOrder() == 1) {
			return player.getLoc()== Player.POS2;
		}else if (player.getOrder() == 2) {
			return player.getLoc() == Player.POS1;
		}else if (player.getOrder() == 3) {
			return player.getLoc() == Player.POS4;
		}else if (player.getOrder() == 4) {
			return player.getLoc() == Player.POS3;
		}return false;
	}

}
