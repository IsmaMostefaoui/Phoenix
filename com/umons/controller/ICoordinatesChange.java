package com.umons.controller;

import com.umons.model.board.Location;

public interface ICoordinatesChange {


	/**
	 * Converti des des coordonnées pixel (correspondant à un pixel du panel)
	 * en coordonnées tableau (correspondant à un élement du plateau de jeu)
	 * @param loc les coordonnées à convertir
	 * @return un Location représentant les coordonnées tableau
	 */
	Location pixelToCoord(Location loc);
	

	/**
	 * Converti des coordonnées tableau (correspondant à un élement du plateau de jeu)
	 * en coordonnées pixel (correspondant à un pixel du panel)
	 * @param loc les coordonnées à convertir
	 * @return un Location représentant les coordonnées pixel
	 */
	Location coordToPixel(Location loc);
}
