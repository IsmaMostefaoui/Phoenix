package com.umons.model;
import java.util.*;

import com.umons.view.BoardGUI;

public class RandomIA extends Player {
	//Rapport de Bug : Le pathfinding ne fonctionne pas sur les joueurs 3 et 4 en mode 2vs2, du coups GROSSE ERREUR EN ROUGE DANS LA CONSOLE 
	//disant que "n must be positive" (un truc comme ça), c'est dans la methode randomLoc()
	// car je demande un rand.nextInt() sur la liste des squareAvailables. Or celle-ci est vide 
	//à un moment car pathfinding de fct pas sur les joueurs3 et 4 :p Je vais même rajouter quelque chose, il y a un bug quand l'IA pose un 
	//mur, parfois elle essaye de le poser en dehors de la grille ce qui fait une belle erreur (je ne comprends même pa comment c'est pos
	

	private List<Location> previousLoc;
	
	public RandomIA(Grid grid, Location loc, int NbreOfWall ,int OrderNumber, IMode mode) {
		super(grid, loc, NbreOfWall, OrderNumber, mode);
		this.human = false;
		previousLoc = new ArrayList<Location>();
		previousLoc.add(this.getLoc());
		
		
	}
	
	public RandomIA(Grid grid, Location loc, int OrderNumber, IMode mode) {
		super(grid, loc, OrderNumber, mode);
		this.human = false;
		previousLoc = new ArrayList<Location>();
		previousLoc.add(this.getLoc());
		
		
	}
	
	/**
	 * Choisis aleatoirement entre se deplacer et poser un mur, si impossible de placer un mur -> se deplace
	 * @param grid palteau de jeu
	 * @param opponent Instance Player du joueur adverse
	 */
	public void play(Game game, IPathFinder finder, Player opponent) {
		Random rand = new Random();
		boolean choice = rand.nextBoolean();
		if (choice == true) {
			this.move();
		}
		if (choice == false) {
			if (!this.chooseWall(opponent, finder)) { // si ne peut poser aucun mur, faire un deplacement
				this.move();

			}
		}
	}
	
	
	
	/**
	 * L'IA choissit la position la plus proche de la ligne d'arrivée
	 * @param grid Grille de jeu
	 */

	public void move() {
		List<Location> list = ARules.rSquareAvailable(this);
		Location nextLocation;
		switch (this.getOrder()) {
		case 1 :
			Location smallestLoc = smallest(list, "y");
			if (smallestLoc.equals(previousLoc.get(previousLoc.size() -1)) ||  smallestLoc.equals(this.getLoc()))  {
				System.out.println("dansle if random"); 
				nextLocation = randomLoc(list, previousLoc.get(previousLoc.size() -1));	
			} else {
				nextLocation = smallestLoc;
			}
			board.setItemInGrid(this.getLoc(), false);
			previousLoc.add(this.getLoc());
			loc = nextLocation;
			board.setItemInGrid(nextLocation, true);
			BoardGUI.locPawn1 = nextLocation;
			break;
		case 2 :
			Location biggestLoc = biggest(list, "y");
			if (biggestLoc.equals(previousLoc.get(previousLoc.size() -1)) || biggestLoc.equals(this.getLoc()))  {
				nextLocation = randomLoc(list,previousLoc.get(previousLoc.size() -1));	
			} else {
				nextLocation = biggestLoc;
			}		
			board.setItemInGrid(this.getLoc(), false);
			previousLoc.add(this.getLoc());
			loc = nextLocation;
			board.setItemInGrid(nextLocation, true);
			BoardGUI.locPawn2 = nextLocation;	
			break;
		case 3 : 
			Location biggestLoc3 = biggest(list, "x");
			if (biggestLoc3.equals(previousLoc.get(previousLoc.size() -1)) || biggestLoc3.equals(this.getLoc()))  {
				nextLocation = randomLoc(list,previousLoc.get(previousLoc.size() -1));	
			} else {
				nextLocation = biggestLoc3;
			}
			board.setItemInGrid(this.getLoc(), false);
			previousLoc.add(this.getLoc());
			loc = nextLocation;
			board.setItemInGrid(nextLocation, true);
			BoardGUI.locPawn3 = nextLocation;
			break;
		case 4 :
			Location smallestLoc4 = smallest(list, "x");
			if (smallestLoc4.equals(previousLoc.get(previousLoc.size() -1)) || smallestLoc4.equals(this.getLoc()))  {
				nextLocation = randomLoc(list,previousLoc.get(previousLoc.size() -1));	
			} else {
				nextLocation = smallestLoc4;
			}
			board.setItemInGrid(this.getLoc(), false);
			previousLoc.add(this.getLoc());
			loc = nextLocation;
			board.setItemInGrid(nextLocation, true);
			BoardGUI.locPawn4 = nextLocation;
			break;
		}
			
	
	}
	
	
	/**
	 * Pose un mur autour du Pion adverse si possible. 
	 * @param opponent Instance Player du joueur adverse
	 * @param finder Pathfinding
	 * @return true si il peut poser un mur, sinon false
	 */
	public boolean chooseWall(Player opponents, IPathFinder finder) {
		int ordre = opponents.getOrder();
		switch (ordre) {
		case 1 : 
			Location[] tabWall1 = {opponents.getLoc().wallUp(), opponents.getLoc().wallLeft(), opponents.getLoc().wallRight(), opponents.getLoc().wallDown() };
			boolean put1 = putWall(tabWall1,finder);
			return put1;
		case 2 :
			Location[] tabWall2 = {opponents.getLoc().wallDown(), opponents.getLoc().wallLeft(), opponents.getLoc().wallRight(), opponents.getLoc().wallUp() };
			boolean put2 = putWall(tabWall2,finder);
			return put2;
		case 3:
			Location[] tabWall3 = {opponents.getLoc().wallRight(),opponents.getLoc().wallUp(), opponents.getLoc().wallDown(), opponents.getLoc().wallLeft() };
			boolean put3 = putWall(tabWall3,finder);
			return put3;
		case 4:
			Location[] tabWall4 = {opponents.getLoc().wallLeft(),opponents.getLoc().wallUp(), opponents.getLoc().wallDown(), opponents.getLoc().wallRight() };
			boolean put4 = putWall(tabWall4,finder);
			return put4;
		}
		return false;
	}
				
	/**
	 * Verifie si la pose d'un mur autour d'un adversaire est possible, et le pose
	 * @param tabWall Locations des murs autours du joueur cible
	 * @param finder PathFinding
	 * @return true si peut être posé, sinon false.
	 */
	public boolean putWall(Location[] tabWall, IPathFinder finder) {
		int i = 0;
		while (i < 4) {	
			if (super.putWall(tabWall[i], finder)) {		
				if (tabWall[i].isWallHorizontal()){
					BoardGUI.locWallHorizontal.add(tabWall[i]);
				}else {
						BoardGUI.locWallVertical.add(tabWall[i]);
				}
				return true;	
			}
		i++;
		}
	return false;
	}
	
	
	/**
	 * Retourne la position la plus petite selon x ou y
	 * @param list liste des squareAvailables
	 * @param coord "x" ou "y"
	 * @return la loc la plus petite si elle existe, sinon la pos actuelle
	 */
	public Location smallest(List<Location> list, String coord) {
		if (coord.equals("x") || coord.equals("X")) {
			Location smallest = this.getLoc();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getLocX() < smallest.getLocX()) {
					smallest = list.get(i);
				}
			}
			return smallest;
			
		}else if (coord.equals("y") || coord.equals("Y")) {
			Location smallest = this.getLoc();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getLocY() < smallest.getLocY()) {
					smallest = list.get(i);
				}
			}
			return smallest;			
		}
		return this.getLoc();	
	}
	
	/**
	 * Retourne la position la plus grande selon x ou y
	 * @param list liste des squareAvailables
	 * @param coord "x" ou "y"
	 * @return la loc la plus grande si elle existe, sinon la pos actuelle
	 */
	public Location biggest(List<Location> list, String coord) {
		if (coord.equals("x") || coord.equals("X")) {
			Location biggest = this.getLoc();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getLocX() > biggest.getLocX()) {
					biggest = list.get(i);
				}
			}
			return biggest;
			
		}else if (coord.equals("y") || coord.equals("Y")) {
			Location biggest = this.getLoc();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getLocY() > biggest.getLocY()) {
					biggest = list.get(i);
				}
			}
			return biggest;			
		}
		return this.getLoc();	
	}
	
	/**
	 * Retourne une position aléatoire dans la mesure du possible
	 * @param list La liste des case potentielle
	 * @param previousLoc la position de la case précedente
	 * @return la position aléatoirement choisie
	 */
	public Location randomLoc(List<Location> list, Location previousLoc) {
		if (list.contains(previousLoc) && list.size() != 1) {
			list.remove(previousLoc);
		}
		Random rand = new Random();
		int index = rand.nextInt(list.size());
		return list.get(index);
	}
		
	
}

