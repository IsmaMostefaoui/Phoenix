package com.umons.model;

public class Mode2Vs2 extends AMode{

	private static final long serialVersionUID = 2775811177978274876L;
	
	int IA;
	int nbreHumans;
	int IA1;
	int IA2;
	int IA3;
	int IA4;
	
	/**
	 * Constructeur du Mode1Vs1. Initialise la grille, l'heuristique pour le pathfinding (et donc init. le pathfinding aussi).
	 * Initialise aussi un tableau de joueur correspondant au joueurs (humains ou non) pour le mode.
	 * @param nbreHumans le nombre de joueur humain dans la partie (le reste sera mis en IA automatiquement selon l'IA choisie)
	 */
	public Mode2Vs2(int IA, int nbreHumans) {
		
		this.IA = IA;
		board = new Grid();
		players = new Player[4];
		this.nbreHumans = nbreHumans;
		players[0] = new Player(board, Player.POS1, 1, this);
		if (nbreHumans == 4){
			//la aussi je suppose que l ia sera toujours numero 2 or c est pas vrai voir commentaire dans mouseclicked dans mml
			players[1] = new Player(board, Player.POS2, 2, this);
			players[2] = new Player(board, Player.POS3, 3, this);
			players[3] = new Player(board, Player.POS4, 4, this);
		}else if (nbreHumans == 1){
			players[1] = setPlayerTo(IA, Player.POS2, 2);
			players[2] = setPlayerTo(IA, Player.POS3, 3);
			players[3] = setPlayerTo(IA, Player.POS4, 4);
		}
		heuristic = new AStarHeuristic();
		finder = new AStarPathFinder(board, 500, heuristic);
	}
	
	public Mode2Vs2(String modeConsole, int IA1, int IA2, int IA3, int IA4) {
		board = new Grid();
		this.IA1 = IA1;
		this.IA2 = IA2;
		this.IA3 = IA3;
		this.IA4 = IA4;
		players = new Player[4];
		players[0] = setPlayerTo(IA1, Player.POS1, 1);
		players[1] = setPlayerTo(IA2, Player.POS2, 2);
		players[2] = setPlayerTo(IA3, Player.POS3, 3);
		players[3] = setPlayerTo(IA4, Player.POS4, 4);
		heuristic = new AStarHeuristic();
		finder = new AStarPathFinder(board, 500, heuristic);
	}
	
	@Override
	public int getNumberOfPlayer() {
		return 4;
	}

	@Override
	public Player[] getPlayer() {
		return players;
	}

	@Override
	public boolean testFinder(Player player, Location coordWall, IPathFinder finder){
		boolean[] check = {true, true, true, true};
		for (int j = 0; j < players.length; j++) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				if (players[j].getOrder()==1 || players[j].getOrder()==2) {
					if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()) == null)) {
						check[j] = false;
					}else {
						check[j] = true;
						break;
					}
				}else if (players[j].getOrder()==3 || players[j].getOrder()==4){
					if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), players[j].getCoordFinish(), 2*i) == null)) {
						check[j] = false;
					}else {
						check[j] = true;
						break;
					}
				}
			}
		}
		return check[0] && check [1] && check[2] && check[3];
	}

	@Override
	public boolean getAllPlayerRobot() {
		return (!players[0].isHumanPlayer() && !players[1].isHumanPlayer() && !players[2].isHumanPlayer() && !players[3].isHumanPlayer());
	}
	
	@Override
	public void reset() {
		board = new Grid();
		players = new Player[4];
		if (nbreHumans == 4){
			players[0] = new Player(board, Player.POS1, 1, this);
			//la aussi je suppose que l ia sera toujours numero 2 or c est pas vrai voir commentaire dans mouseclicked dans mml
			players[1] = new Player(board, Player.POS2, 2, this);
			players[2] = new Player(board, Player.POS3, 3, this);
			players[3] = new Player(board, Player.POS4, 4, this);
		}else if (nbreHumans == 1){
			players[0] = new Player(board, Player.POS1, 1, this);
			players[1] = setPlayerTo(IA, Player.POS2, 2);
			players[2] = setPlayerTo(IA, Player.POS3, 3);
			players[3] = setPlayerTo(IA, Player.POS4, 4);
		}
		heuristic = new AStarHeuristic();
		finder = new AStarPathFinder(board, 500, heuristic);
	}
	
	@Override
	public void resetConsole(){
		board = new Grid();
		players = new Player[4];
		players[0] = setPlayerTo(IA1, Player.POS1, 1);
		players[1] = setPlayerTo(IA2, Player.POS2, 2);
		players[2] = setPlayerTo(IA3, Player.POS3, 3);
		players[3] = setPlayerTo(IA4, Player.POS4, 4);
		heuristic = new AStarHeuristic();
		finder = new AStarPathFinder(board, 500, heuristic);
	}
}
