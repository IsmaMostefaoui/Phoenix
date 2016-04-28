package com.umons.model;

public class Mode2Vs2 extends AMode{
	
	/**
	 * Constructeur du Mode1Vs1. Initialise la grille, l'heuristique pour le pathfinding (et donc init. le pathfinding aussi).
	 * Initialise aussi un tableau de joueur correspondant au joueurs (humains ou non) pour le mode.
	 * @param nbreHumans le nombre de joueur humain dans la partie (le reste sera mis en IA automatiquement selon l'IA choisie)
	 */
	public Mode2Vs2(int nbreHumans) {
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
			//ATTENTION besoin de definir une interface pour ne pas spécifier forcement quelle type d ia utiliser dans le constructeur
			players[1] = new MediumIA(board, Player.POS2, 2, this);
			players[2] = new MediumIA(board, Player.POS3, 3, this);
			players[3] = new MediumIA(board, Player.POS4, 4, this);
		}
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
		long timeStart = System.currentTimeMillis();
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
		}long timeEnd = System.currentTimeMillis();
		System.out.println("\n\n\n--------------TIME: " + ((timeEnd - timeStart)) + "----------------");
		return check[0] && check [1] && check[2] && check[3];
	}
}
