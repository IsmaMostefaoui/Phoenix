package com.umons.model;

public interface Mode{
	
	/**
	 * Initalise une game
	 * @param game un objet game ou sont definis certaines fonctions pratique concernant le deroulement d une partie
	 */
	void init(Game game);
	
	/**
	 * @return le nombre de joueur dans la game
	 */
	int getNumberOfPlayer();
	
	/**
	 * 
	 * @return les instance de player dans l ordre de leur numero
	 */
	Player[] getPlayer();
	
	/**
	 * A changer de place(quelque part ou on a acces a tous les player)
	 * Test si il y a un chemin
	 * @param player l instance du joueur qui pose le mur
	 * @param finder
	 * @param loc la position du mur qui risque de bloquer un joueur
	 * @return vrai si il y a un chemin, faux sinon
	 */
	boolean testFinder(Player player, Location coordWall, IPathFinder finder);
}
