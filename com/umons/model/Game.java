package com.umons.model;

import java.util.Random;

import com.umons.controller.Controller;
import com.umons.model.playerAbstraction.Player;

public class Game {

	private int numberPlayer;
	private int tour;
	private AMode mode;
	
	/**
	 * Constructeur de game. Initialise aléatoirement un tour selon le mode
	 * @param mode instance d'une sous classe de AMode
	 */
	public Game(AMode mode) {
		this.mode = mode;
		this.numberPlayer = mode.getNumberOfPlayer();
		Random r = new Random();
		tour = r.nextInt(numberPlayer);
		System.out.println("controller " + mode.getController());
	}
	
	/**
	 * Passe au joueur suivant
	 */
	public void nextPlayer() {
		tour = ((tour+1)%numberPlayer);
		
	}
	
	/**
	 * etourne le tour actuel de la game
	 * @return un int tour
	 */
	public int getTour() {
		return tour;
	}
	
	/**
	 * Réinitialise le tour en le réinitialisant aléatoirement
	 */
	public void resetTour() {
		Random r = new Random();
		tour = r.nextInt(numberPlayer);
	}
	
	/**
	 * Setter pour initialiser le nombre de tour
	 */
	public void setTour(int t) {
		this.tour = t;
	}

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
	}
	
	/**
	 * Retourne le mode utilisé dans game.
	 * @return le mode de type AMode
	 */
	public AMode getMode() {
		return mode;
	}

}
