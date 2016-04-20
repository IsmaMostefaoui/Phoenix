package com.umons.model;

import javax.swing.JPanel;
import com.umons.view.BoardGUI;
import com.umons.controller.Controller;
import com.umons.controller.MyMouseListener;
import com.umons.view.QuoridorGUI;

public class Mode1Vs1 implements IMode{

	private Player[] players;
	private Grid board;
	private AStarHeuristic heuristic;
	private IPathFinder finder;
	private int nbreHumans;
	
	/**
	 * Constructeur du Mode1Vs1. Initialise la grille, l'heuristique pour le pathfinding (et donc init. le pathfinding aussi).
	 * Initialise aussi un tableau de joueur correspondant au joueurs (humains ou non) pour le mode.
	 * @param nbreHumans le nombre de joueur humain dans la partie (le reste sera mis en IA automatiquement selon l'IA choisie)
	 */
	public Mode1Vs1(int nbreHumans) {
		board = new Grid();
		players = new Player[2];
		this.nbreHumans = nbreHumans;
		players[0] = new Player(board, Player.POS1, 1, this);
		if (nbreHumans == 2){
			//la aussi je suppose que l ia sera toujours numero 2 or c est pas vrai voir commentaire dans mouseclicked dans mml
			players[1] = new Player(board, Player.POS2, 2, this);
		}else if (nbreHumans == 1){
			//ATTENTION besoin de definir une interface pour ne pas spécifier forcement quelle type d ia utiliser dans le constructeur
			players[1] = new RandomIA(board, Player.POS2, 2, this);
		}
		heuristic = new AStarHeuristic();
		finder = new AStarPathFinder(board, 500, heuristic);
	}
	
	@Override
	public void init(Game game) {
		//AJOUTER A BOARDGUI UN PARAMTERE MODE POURT DESSINER LES PREVIEW (SELON QU'ON SOIT EN 1VSAI, NE PAS DESSINER LES PREVIEW DE L'IA)
		//AJOUTER AUSSI A MML (CONTROLLER) POUR QUAND ON AUGEMENTE LE TOUR, L'IA NE SOIT PAS OBLIGER DE PHYSIQUEMENT CLICKER	
		ARules.setBoard(board);
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR", true);
		JPanel panel = new BoardGUI(game);
		panel.setFocusable(true);
		Controller controller = new Controller(this, panel, game, finder);
		MyMouseListener l = new MyMouseListener(controller);
		panel.addMouseListener(l);
		panel.addMouseMotionListener(l);
		frame.setContentPane(panel);
		
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
				if (players[j].getOrder()==1 || players[j].getOrder()==2) {
					if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()) == null)) {
						check[j] = false;
					}else {
						System.out.println("joueur: " + j);
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
		long timeEnd = System.currentTimeMillis();
		System.out.println("\n\n\n--------------TIME: " + ((timeEnd - timeStart)) + "----------------");
		return check[0] && check [1];
	}
}
