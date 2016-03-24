package com.umons.model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.umons.view.BoardGUI;
import com.umons.view.MyMouseListener;
import com.umons.view.QuoridorGUI;

public class Main {
	
	public static void main(String[] args){
		Player joueur1 = new Player(Player.POS1, 1);
		Player joueur2 = new Player(Player.POS2, 2);
		Grid board = new Grid(joueur1, joueur2);
		ARules.setBoard(board);
		
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
		MyMouseListener l = new MyMouseListener(board, joueur1, joueur2, panel, game);
		panel.addMouseListener(l);
		panel.addMouseMotionListener(l);
		frame.setContentPane(panel);
		
	}
}
