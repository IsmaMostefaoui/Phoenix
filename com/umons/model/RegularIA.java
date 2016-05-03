package com.umons.model;

import java.util.ArrayList;
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
				System.out.println(": " + playerLoc.getLocX() + "" + playerLoc.getLocY() + "" + coordFinish);
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
				System.out.println("wich is null ? : " + minPath);
				if ((minPath != null && currentPath != null && currentPath.getLength() <= minPath.getLength())) { //si le chemin courant est plus petit que le minimum actuelle
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
			super.putWall(nextWall, finder);
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
		Path maxPath = fixPath; //le plus lng chemin est celui de départ à la base
		Location nextWall = null;
		for (Location wallAvailable : allWall) {
			//TODO
			//                           i ou i est le numéro du joueur
			System.out.println("regle de mur: " + ARules.rPutWall(wallAvailable) + " " + ARules.rSlotFull(wallAvailable));
			//si on peut poser un mur à ces coordonnées
			if (ARules.rPutWall(wallAvailable) && ARules.rSlotFull(wallAvailable) && mode.testFinder(players[0], wallAvailable, finder)){ 
				//on calcule le chemin le plus court en simulant la position d'un mur
				Path shortWallPath = shortestPath(wallAvailable, players[1], finder);
				//on caclule notre chemin le plus court aussi
				Path thisPlayerShortWallPath = shortestPath(wallAvailable, players[0], finder);
				//shortWallPath peut etre null
				//si son chemin le plus court est plus grand que son chemin de départ (et son chemin maximum par la suite) et que
				//notre chemin n'est pas devnu plus long avec la pose du mur
				if (shortWallPath != null && shortWallPath.getLength() >= maxPath.getLength() && thisPlayerShortWallPath.getLength() <= thisPlayerFixTab.getLength()) {
					maxPath = shortWallPath;
					System.out.println("chox nextWall: " + wallAvailable);
					//on prend ce mur
					nextWall = wallAvailable;
				}
			}
			
		}
		//ici on regarde si le chemin le plus long n'est pas le même que celui de départ
		//si oui, ben on refait la même chose est élargissant le champs i.e. en enleavant la condition qui dit7
		//que notre chemin ne doit pas être plus long (au cas ou)
		if (maxPath.getLength() == fixPath.getLength()) {
			for (Location wallAvailable : allWall) {
				System.out.println("regle de mur: " + ARules.rPutWall(wallAvailable) + " " + ARules.rSlotFull(wallAvailable));
				if (ARules.rPutWall(wallAvailable) && ARules.rSlotFull(wallAvailable) && mode.testFinder(players[0], wallAvailable, finder)){
					Path shortWallPath = shortestPath(wallAvailable, players[1], finder);
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
