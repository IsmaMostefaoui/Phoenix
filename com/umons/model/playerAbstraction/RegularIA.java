package com.umons.model.playerAbstraction;

import java.util.ArrayList;

import com.umons.model.AMode;
import com.umons.model.ARules;
import com.umons.model.Game;
import com.umons.model.board.Grid;
import com.umons.model.board.Location;
import com.umons.model.pathFinding.IPathFinder;
import com.umons.model.pathFinding.Path;
import com.umons.view.BoardGUI;

public class RegularIA extends Player implements IRobot{

	private static final long serialVersionUID = 5053087481566937460L;

	public RegularIA(Grid grid, Location loc, int NbreOfWall ,int OrderNumber, AMode mode) {
		super(grid, loc, NbreOfWall, OrderNumber, mode);
		this.human = false;
	}
	
	public RegularIA(Grid grid, Location loc, int OrderNumber, AMode mode) {
		super(grid, loc, OrderNumber, mode);
		this.human = false;	
	}
	
	
	public ArrayList<Path> testFinderMove(Player[] players,IPathFinder finder) {
		ArrayList<Path> path = new ArrayList<Path>();
		for (int j = 0; j < players.length; j++) {
			path.add(shortestPath(players[j], finder)); //une fois qu'on trouve le chemin le plus court on l'ajoute à la liste des chemins
		}
		return path;
	}
	
	/**
	 * Retourne le chemin le plus court entre plusieurs position
	 * @param player l'instance du joueur courant
	 * @param finder l'algorithme de pathfinding choisi
	 * @return le chemin le plus court vers la destination d'arrivé du joueur
	 */
	public Path shortestPath(Player player, IPathFinder finder) {
		Location playerLoc = player.getLoc();
		int coordFinish = player.getCoordFinish();
		Path minPath = finder.findPath(playerLoc.getLocX(), playerLoc.getLocY(), 0, coordFinish);
		if (player.getOrder()==1 || player.getOrder()==2) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(playerLoc.getLocX(), playerLoc.getLocY(), 2*i, coordFinish);
				if ((minPath != null && currentPath != null && currentPath.getLength() < minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					minPath = currentPath;
				}else if (minPath == null && currentPath != null) {
					minPath = currentPath;
				}
			}
		}else if (player.getOrder() == 3 || player.getOrder() == 4) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(playerLoc.getLocX(), playerLoc.getLocY(), coordFinish, 2*i);
				if ((minPath != null && currentPath != null && currentPath.getLength() < minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					minPath = currentPath;
				}
			}
		}
		return minPath;
	}
	
	
	/**
	 * Retourne le chemin le plus court entre plusieurs position  en simulant la pose d'un mur
	 * @param locWall la position du mur à poser
	 * @param player l'instance du joueur courant
	 * @param finder l'algorithme de pathfinding choisi
	 * @return le chemin le plus court vers la destination d'arrivé du joueur
	 */
	public Path shortestPath(Location locWall, Player player, IPathFinder finder) {
		Location playerLoc = player.getLoc();
		int coordFinish = player.getCoordFinish();
		//peut être null, donc à gérer
		Path minPath = finder.findPath(locWall, player.getLoc().getLocX(), player.getLoc().getLocY(), 0, player.getCoordFinish());
		if (player.getOrder()==1 || player.getOrder()==2) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(locWall, playerLoc.getLocX(), playerLoc.getLocY(), 2*i, coordFinish);
				if ((minPath != null && currentPath != null && currentPath.getLength() <= minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					minPath = currentPath;
				}else if (minPath == null && currentPath != null) {
					minPath = currentPath;
				}
			}
		}else if (player.getOrder() == 3 || player.getOrder() == 4) {
			for (int i = 0; i > ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(locWall, playerLoc.getLocX(), playerLoc.getLocY(), coordFinish, 2*i);
				if ((currentPath != null && currentPath != null && currentPath.getLength() <= minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					minPath = currentPath;
				}
			}
		}
		return minPath;
	}
	
	/**
	 * Ne sert plus trop à rien je crois
	 * Retourne le chemin le plus court entre plusieurs position
	 * @param player l'instance du joueur courant
	 * @param finder l'algorithme de pathfinding choisi
	 * @return le chemin le plus court vers la destination d'arrivé du joueur
	 */
	public Path longestPath(Player player, IPathFinder finder) {
		Location playerLoc = player.getLoc();
		int coordFinish = player.getCoordFinish();
		Path maxPath = finder.findPath(player.getLoc().getLocX(), player.getLoc().getLocY(), 0, player.getCoordFinish());
		if (player.getOrder()==1 || player.getOrder()==2) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(playerLoc.getLocX(), playerLoc.getLocY(), 2*i, coordFinish);
				if ((currentPath.getLength() > maxPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					maxPath = currentPath;
				}
			}
		}else if (player.getOrder() == 3 || player.getOrder() == 4) {
			for (int i = 0; i > ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(playerLoc.getLocX(), playerLoc.getLocY(), coordFinish, 2*i);
				if ((currentPath.getLength() > maxPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					maxPath = currentPath;
				}
			}
		}
		return maxPath;
	}
	
	public void play(Game game, IPathFinder finder, Player opponent){
		System.out.println("j'utilise une ia compliqué pour moi qui suit: " + this.getOrder());
		Player[] players = {this, opponent};
		ArrayList<Path> path = testFinderMove(players, finder);
		if (path.get(0) != null && path.get(1) != null && (path.get(0).getLength() <= path.get(1).getLength())) {
			this.move(path);	
		}else {
			Location nextWall = chooseWall(players, finder);
			if (super.putWall(nextWall, finder)) {
				System.out.println("Joueur " + this.getOrder() + " pose un mur à : " + nextWall);
				if (nextWall.isWallHorizontal()){
					BoardGUI.locWallHorizontal.add(nextWall);
				}else {
					BoardGUI.locWallVertical.add(nextWall);
				}
			}else if (path.get(0) != null && path.get(1) != null) {
				move(path);
			}
			
			/*
			if (nextWall != null && this.getNbreOfWall() != 0) {
				super.putWall(nextWall, finder);
					if (nextWall != null && this.getNbreOfWall() >= 0 && nextWall.isWallHorizontal()){
					BoardGUI.locWallHorizontal.add(nextWall);
				}else if(this.getNbreOfWall() != 0 ) {
					BoardGUI.locWallVertical.add(nextWall);
				}
			}else {
				this.move(path);
			}*/
		}				
	}

	
	public Location chooseWall(Player[] players, IPathFinder finder) {
		ArrayList<Location> allWall = ARules.rWallAvailable();
		ArrayList<Path> tab = testFinderMove(players, finder);
		Path fixPath = tab.get(1); //le chemin fixe du joueur, celui de départ, sans la pose de mur de l'IA
		Path thisPlayerFixTab = tab.get(0);
		Path maxPath = fixPath; //le plus lng chemin est celui de départ à la base
		Location nextWall = null;
		for (Location wallAvailable : allWall) {
			//TODO
			//  i ou i est le numéro du joueur
			//si on peut poser un mur à ces coordonnées
			if (ARules.rPutWall(wallAvailable) && ARules.rSlotFull(wallAvailable) && mode.testFinder(players[0], wallAvailable, finder)){ 
				//on calcule le chemin le plus court en simulant la position d'un mur
				Path shortWallPath = shortestPath(wallAvailable, players[1], finder);
				//on caclule notre chemin le plus court aussi
				Path thisPlayerShortWallPath = shortestPath(wallAvailable, players[0], finder);
				//shortWallPath peut etre null
				//si son chemin le plus court est plus grand que son chemin de départ (et son chemin maximum par la suite) et que
				//notre chemin n'est pas devnu plus long avec la pose du mur
				if (shortWallPath != null && maxPath != null && thisPlayerShortWallPath != null && thisPlayerFixTab != null) {
					if (shortWallPath.getLength() > maxPath.getLength() && thisPlayerShortWallPath.getLength() <= thisPlayerFixTab.getLength()) {
						maxPath = shortWallPath;
						//on prend ce mur
						nextWall = wallAvailable;
					}
				}
			}
			
		}
		//ici on regarde si le chemin le plus long n'est pas le même que celui de départ
		//si oui, ben on refait la même chose est élargissant le champs i.e. en enleavant la condition qui dit7
		//que notre chemin ne doit pas être plus long (au cas ou)
		
		if ((maxPath != null && fixPath != null && (maxPath.getLength() == fixPath.getLength())) || nextWall == null) {
			for (Location wallAvailable : allWall) {
				if (ARules.rPutWall(wallAvailable) && ARules.rSlotFull(wallAvailable) && mode.testFinder(players[0], wallAvailable, finder)){
					Path shortWallPath = shortestPath(wallAvailable, players[1], finder);
					if (shortWallPath != null && maxPath != null) {
						if (shortWallPath.getLength() > maxPath.getLength()) {
							maxPath = shortWallPath;
							nextWall = wallAvailable;
						}
					}
				}
				
			}
		}
		return nextWall;
	}
			
	public void move(ArrayList<Path> path) {
		Location nextLocation = path.get(0).getStep(1);
		board.setItemInGrid(this.getLoc(), false);
		board.setItemInGrid(nextLocation, true);
		this.setLoc(nextLocation);
		int tour = this.getOrder();
		System.out.println("Joueur " + this.getOrder() + "  bouge à  " + nextLocation);
		switch(tour) {
		case 1 :
			BoardGUI.locPawn1 = nextLocation;
			break;
		case 2 :
			BoardGUI.locPawn2 = nextLocation;
			break;
		case 3 : 
			BoardGUI.locPawn3 = nextLocation;
			break;
		case 4 :
			BoardGUI.locPawn4 = nextLocation;
			break;
		}
		
	}
	
	@Override
	public boolean putWall(Location[] tabWall, IPathFinder finder){
		return true;
	}
	
	@Override
	public void move() {
		
	}
	
	
					
				
}
