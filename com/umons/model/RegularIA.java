package com.umons.model;

import java.util.ArrayList;
import java.util.List;
import com.umons.view.BoardGUI;

public class RegularIA extends Player implements IRobot{
	
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
	System.out.println("path size: " + path.size());
	for (int i = 0; i < path.size(); i++) {
		path.get(i);
		System.out.println("i: " + i);
		for (int j = 0; j < path.get(i).getLength(); j++){
			System.out.println(path.get(i).getStep(j));
		}
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
		Path minPath = finder.findPath(player.getLoc().getLocX(), player.getLoc().getLocY(), 0, player.getCoordFinish());
		if (player.getOrder()==1 || player.getOrder()==2) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(playerLoc.getLocX(), playerLoc.getLocY(), 2*i, coordFinish);
				if ((currentPath.getLength() < minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					minPath = currentPath;
				}
			}
		}else if (player.getOrder() == 3 || player.getOrder() == 4) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(playerLoc.getLocX(), playerLoc.getLocY(), coordFinish, 2*i);
				if ((currentPath.getLength() < minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					minPath = currentPath;
				}
			}
		}
		return minPath;
	}
	
	
	/**
	 * Retourne le chemin le plus court entre plusieurs position
	 * @param player l'instance du joueur courant
	 * @param finder l'algorithme de pathfinding choisi
	 * @return le chemin le plus court vers la destination d'arrivé du joueur
	 */
	public Path shortestPath(Location locWall, Player player, IPathFinder finder) {
		Location playerLoc = player.getLoc();
		int coordFinish = player.getCoordFinish();
		Path minPath = finder.findPath(locWall, player.getLoc().getLocX(), player.getLoc().getLocY(), 0, player.getCoordFinish());
		if (player.getOrder()==1 || player.getOrder()==2) {
			for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(locWall, playerLoc.getLocX(), playerLoc.getLocY(), 2*i, coordFinish);
				if ((currentPath != null && currentPath.getLength() <= minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					minPath = currentPath;
				}
			}
		}else if (player.getOrder() == 3 || player.getOrder() == 4) {
			for (int i = 0; i > ((Grid.getLen()/2)+1); i++) {
				Path currentPath = finder.findPath(playerLoc.getLocX(), playerLoc.getLocY(), coordFinish, 2*i);
				if ((currentPath != null && currentPath.getLength() <= minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
					//on change
					minPath = currentPath;
				}
			}
		}
		return minPath;
	}
	
	/**
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
		Player[] players = {this, opponent};
		ArrayList<Path> path = testFinderMove(players, finder);
		
		if (path.get(0).getLength() <= path.get(1).getLength()) {
			System.out.println("path[0].getLength() = " + path.get(0).getLength() );
			System.out.println("path[1].getLength() = " + path.get(0).getLength() );
			Location nextLocation = path.get(0).getStep(1);
			System.out.println("NextLocation = " + nextLocation);
			board.setItemInGrid(this.getLoc(), false);
			board.setItemInGrid(nextLocation, true);
			this.setLoc(nextLocation);
			BoardGUI.locPawn2 = nextLocation;
			
		}else {
			Location nextWall = chooseWall(players, finder);
			System.out.println("nextWall: " + nextWall);
			if (nextWall.isWallHorizontal()){
				BoardGUI.locWallHorizontal.add(nextWall);
			}else {
					BoardGUI.locWallVertical.add(nextWall);
			}
		}				
	}

	
	public Location chooseWall(Player[] players, IPathFinder finder) {
		ArrayList<Location> allWall = ARules.rWallAvailable();
		ArrayList<Path> tab = testFinderMove(players, finder);
		Path fixPath = tab.get(1); //le chemin fixe du joueur, celui de départ, sans la pose de mur de l'IA
		Path thisPlayerFixTab = tab.get(0);
		Path maxPath = fixPath; // le plus long chemin à la base est celui de départ
		Location nextWall = null;
		for (Location wallAvailable : allWall) {
			//TODO
			//                           i ou i est le numéro du joueur
			Path shortWallPath = shortestPath(wallAvailable, players[1], finder);
			Path thisPlayerShortWallPath = shortestPath(wallAvailable, players[0], finder);
			System.out.println("regle de mur: " + ARules.rPutWall(wallAvailable) + " " + ARules.rSlotFull(wallAvailable));
			if (ARules.rPutWall(wallAvailable) && ARules.rSlotFull(wallAvailable) && mode.testFinder(players[0], wallAvailable, finder)){
				if (shortWallPath.getLength() >= maxPath.getLength() && thisPlayerShortWallPath.getLength() <= thisPlayerFixTab.getLength()) {
					maxPath = shortWallPath;
					System.out.println("chox nextWall: " + wallAvailable);
					nextWall = wallAvailable;
				}
			}
			
		}
		if (maxPath.getLength() == fixPath.getLength()) {
			for (Location wallAvailable : allWall) {
				Path shortWallPath = shortestPath(wallAvailable, players[1], finder);
				System.out.println("regle de mur: " + ARules.rPutWall(wallAvailable) + " " + ARules.rSlotFull(wallAvailable));
				if (ARules.rPutWall(wallAvailable) && ARules.rSlotFull(wallAvailable) && mode.testFinder(players[0], wallAvailable, finder)){
					if (shortWallPath.getLength() > maxPath.getLength()) {
						maxPath = shortWallPath;
						System.out.println("chox nextWall: " + wallAvailable);
						nextWall = wallAvailable;
					}
				}
				
			}
		}
		return nextWall;
	}
			
	@Override
	public boolean putWall(Location[] tabWall, IPathFinder finder){
		//TODO
		return true;
	}
	@Override
	public void move() {
		//TODO
	}
	
	
					
				
}
