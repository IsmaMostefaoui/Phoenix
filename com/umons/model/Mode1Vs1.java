package com.umons.model;

public class Mode1Vs1 extends AMode {
	
	private static final long serialVersionUID = -7116175129659919662L;
	
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
		players[0] = new Player(board, Player.POS1, 1, this);
		if (nbreHumans == 2){
			players[1] = new Player(board, Player.POS2, 2, this);
		}else if (nbreHumans == 1){
			players[1] = setPlayerTo(IA);
		}
		heuristic = new AStarHeuristic();
		finder = new AStarPathFinder(board, 500, heuristic);
	}
	
	public Mode1Vs1(String modeConsole, int IA1, int IA2) {
		
		board = new Grid();
		players = new Player[2];
		players[0] = setPlayerTo(IA1);
		players[1] = setPlayerTo(IA2);
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
				if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()) == null)) {
					check[j] = false;
				}else {
					check[j] = true;
					break;
				}
			}
		}
		long timeEnd = System.currentTimeMillis();
		//System.out.println("\n\n\n--------------TIME: " + ((timeEnd - timeStart)) + "----------------");
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
		players[0] = new Player(board, Player.POS1, 1, this);
		if (nbreHumans == 2){
			players[1] = new Player(board, Player.POS2, 2, this);
		}else if (nbreHumans == 1){
			players[1] = setPlayerTo(IA);
		}
	}

}
