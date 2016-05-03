package com.umons.model;

import com.umons.controller.Controller;

public class Mode1Vs1 extends AMode {
	
	int IA;
	int nbreHumans;
	
	/**
	 * Constructeur du Mode1Vs1. Initialise la grille, l'heuristique pour le pathfinding (et donc init. le pathfinding aussi).
	 * Initialise aussi un tableau de joueur correspondant au joueurs (humains ou non) pour le mode.
	 * @param nbreHumans le nombre de joueur humain dans la partie (le reste sera mis en IA automatiquement selon l'IA choisie)
	 */
	public Mode1Vs1(int IA, int nbreHumans) {
		
		this.IA = IA;
		this.nbreHumans = nbreHumans;
		
		board = new Grid();
		players = new Player[2];
		this.nbreHumans = nbreHumans;
		if (nbreHumans == 2){
			//la aussi je suppose que l ia sera toujours numero 2 or c est pas vrai voir commentaire dans mouseclicked dans mml
			players[0] = new Player(board, Player.POS1, 1, this);
			players[1] = new Player(board, Player.POS2, 2, this);
		}else if (nbreHumans == 1){
			//ATTENTION besoin de definir une interface pour ne pas spécifier forcement quelle type d ia utiliser dans le constructeur
			players[0] = new Player(board, Player.POS1, 1, this);
			switch (IA){
			case AMode.EASY:
				players[1] = new RandomIA(board, Player.POS2, 2, this);
				break;
			case AMode.MEDIUM:
				players[1] = new MediumIA(board, Player.POS2, 2, this);
				break;
			case AMode.DIFFICULT:
				players[1] = new RegularIA(board, Player.POS2, 2, this);
				break;
			}
		}
		heuristic = new AStarHeuristic();
		finder = new AStarPathFinder(board, 500, heuristic);
	}

	@Override
	public int getNumberOfPlayer() {
		return 2;
	}

	@Override
	public Player[] getPlayer() {
		return players;
	}

	@Override
	public boolean testFinder(Player player, Location coordWall, IPathFinder finder){
		boolean[] check = {true, true};
		long timeStart = System.currentTimeMillis();
		for (int j = 0; j < players.length; j++) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				System.out.println("test null: " + players[j] + coordWall + " finder: " + finder);
				if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()) == null)) {
					check[j] = false;
				}else {
					check[j] = true;
					break;
				}
			}
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("\n\n\n--------------TIME: " + ((timeEnd - timeStart)) + "----------------");
		return check[0] && check [1];
	}

	@Override
	public boolean getAllPlayerRobot() {
		return (!players[0].isHumanPlayer() && !players[1].isHumanPlayer());
	}

	@Override
	public void reset() {
		board = new Grid();
		players = new Player[2];
		if (nbreHumans == 2){
			players[0] = new Player(board, Player.POS1, 1, this);
			players[1] = new Player(board, Player.POS2, 2, this);
		}else if (nbreHumans == 1){
			players[0] = new Player(board, Player.POS1, 1, this);
			switch (IA){
			case AMode.EASY:
				players[1] = new RandomIA(board, Player.POS2, 2, this);
				break;
			case AMode.MEDIUM:
				players[1] = new MediumIA(board, Player.POS2, 2, this);
				break;
			case AMode.DIFFICULT:
				players[1] = new RegularIA(board, Player.POS2, 2, this);
				break;
			}
		}
	}

}
