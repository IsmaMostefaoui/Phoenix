package com.umons.model.pathFinding;

import java.io.Serializable;

import com.umons.model.board.Location;

/**
 * @author Inspired by Kevin Glass's code[1]
 * [1] http://www.cokeandcode.com/main/tutorials/path-finding/
 */
public class AStarHeuristic implements Serializable{
	
	private static final long serialVersionUID = -1796292856014720847L;

	/**
	 * Calcul le poid d'une case selon sa distance par rapport à la cible finale
	 * @param current current la Location de la case actuelle
	 * @param target target la Location de la case finale
	 * @return le poid de la case selon le trajet
	 */
	public float getCost(Location current, Location target) {
		float dx = target.getLocX() - current.getLocX();
		float dy = target.getLocY() - current.getLocY();
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}
}
