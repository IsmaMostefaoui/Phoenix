package com.umons.model;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import com.umons.view.BoardGUI;
import com.umons.view.MyMouseListener;
import com.umons.view.QuoridorGUI;

public class Main {
	
	
	
	public static void main(String[] args){
		Player joueur1 = new Player(Player.POS1);
		Player joueur2 = new Player(Player.POS2);
		Grid board = new Grid(joueur1, joueur2);
		Rules rules = new Rules(board);
		System.out.println("***DEBUT DU TEST***");
		boolean posOk = false;
		boolean wallOk = false;
		int x = 0;
		int y = 0;
		board.afficheGrid(joueur1, joueur2);
		Game game = new Game(board, 2);
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR", true);
		JPanel panel = new BoardGUI(game, joueur1, joueur2);
		panel.setFocusable(true);
		panel.addMouseListener(new MyMouseListener(board, joueur1, joueur2, panel, game));
		frame.setContentPane(panel);
		//LA PIRE BOUCLE DE TOUS LES TEMPS, ELLE TOURNE SANS CESSER... CE QUI A TENDANCE A FAIRE LAGUER LE PC, ESSAYE DE LANCER MAIN TU VA VOIR
		/*
		do{
			if (tour == 1) {
				System.out.println("Joueur1, Pion ou Mur (1 ou 2) >>> ");
				choix = sc.nextInt();
				sc.nextLine();
				if (choix == 1) {
					do {
						System.out.print("Joueur 1, à vous de jouer. Ou voulez-vous aller ? >> ");
						direction = sc.nextLine();
						System.out.println();
						posOk = joueur1.move(board, joueur1.getLoc().stringToCoord(direction));
					}while (!posOk);
					board.afficheGrid(joueur1, joueur2);
					System.out.println();
					posOk = false;
					tour++;
				}else if (choix == 2) {
					do {
						do {
							System.out.print("Position en X (colonne) >> ");
							x = Integer.parseInt(sc.nextLine());
						}while (x < 0 || x > board.getLen());
						do {
							System.out.print("Position en Y (ligne) >> ");
							y = Integer.parseInt(sc.nextLine());
						}while (y < 0 || x > board.getLen());
						System.out.println();
						wallOk = joueur1.putWall(board,new Location(x, y));
						tour++;
					}while (!wallOk);
				}
			}
			else if (tour == 2){
				System.out.println("Joueur2, Pion ou Mur (1 ou 2) >>> ");
				choix = sc.nextInt();
				sc.nextLine();
				if (choix == 1) {
					do {
						System.out.print("Joueur 2, à vous de jouer. Ou voulez-vous aller ? >> ");
						direction = sc.nextLine();
						System.out.println();
						posOk = joueur2.move(board, joueur2.getLoc().stringToCoord(direction));
					}while (!posOk);
					board.afficheGrid(joueur1, joueur2);
					System.out.println();
					posOk = false;
					tour--;
				}else if (choix == 2) {
					do {
						do {
							System.out.print("Position en X (colonne) >> ");
							x = Integer.parseInt(sc.nextLine());
						}while (x < 0 || x > board.getLen());
						do {
							System.out.print("Position en Y (ligne) >> ");
							y = Integer.parseInt(sc.nextLine());
						}while (y < 0 || x > board.getLen());
						System.out.println();
						wallOk = joueur2.putWall(board,new Location(x, y));
						tour++;
					}while (!wallOk);
				}
			}
			
		}while(!stop);*/
	}

}
