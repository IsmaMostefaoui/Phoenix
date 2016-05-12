package com.umons.model;

import java.io.Serializable;

/**
 * La description d'une implménetation qui peut trouver un chemnin sunr un grille à partir de deux position
 * 
 * @author Kevin Glass
 */
public interface IPathFinder extends Serializable{

	/**
	 * Trouve un chemin en partant de la position (sx, sy) jusqu'à la position
	 * (tx, ty) en évitant les obstacles et en essayant d'optimiser le cout de
	 * deplacement fourni par la grille
	 * 
	 * @param coordWall la position du mur pour lequel le pathfinding vérifie si il est bloquant ou pas
	 * @param sx La coordonnées x de la position de départ
	 * @param sy La coordonnées y de la position de départ
	 * @param tx La coordonnées x de la position d'arrivé
	 * @param ty La coordonnées y de la position d'arrivé
	 * @return le chemin partant de la postion de départ jusque la postion d'arrivé, ou null si pas de chemin
	 */
	public Path findPath(Location coordWall, int sx, int sy, int tx, int ty);
	
	/**
	 * 
	 * @param sx La coordonnées x de la position de départ
	 * @param sy La coordonnées y de la position de départ
	 * @param tx La coordonnées x de la position d'arrivé
	 * @param ty La coordonnées y de la position d'arrivé
	 * @return le chemin partant de la postion de départ jusque la postion d'arrivé, ou null si pas de chemin
	 */
	public Path findPath(int sx, int sy, int tx, int ty);
}
