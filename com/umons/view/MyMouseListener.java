package com.umons.view;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.umons.model.Game;
import com.umons.model.Grid;
import com.umons.model.Location;
import com.umons.model.Main;
import com.umons.model.Player;

public class MyMouseListener implements MouseListener, MouseMotionListener{

	private int x1, y1;
	final JPanel panel;
	public static Location clickCoord;
	public static Location motionCoord;
	Game game;
	Player joueur1;
	Player joueur2;
	Grid board;
	
	
	//a changer, pas tres bon de mettre ça ici, je crois (je parledes joueur et meme du game et meme du board)
	public MyMouseListener(Grid board, Player joueur1, Player joueur2, JPanel panel, Game game) {
		this.panel = panel;
		this.game = game;
		this.joueur1 = joueur1; this.joueur2 = joueur2;
		this.board = board;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX(); y1 = e.getY();
		clickCoord = new Location(x1, y1);
		System.out.println("clickcoord: " + clickCoord);
		Location temp = clickCoord.pixelToCoord();
		System.out.println("coordonnées tableau du click: " + temp);
		if (game.getTour() == 0) {
			if (temp.lSquare() && joueur1.move(board, temp)) {
				BoardGUI.locPawn1 = temp;
				game.nextPlayer();
				if (game.win(joueur1)) {
					System.out.println("JOUEUR1 A GAGNE");
				}
				panel.repaint();
			}else if (temp.isWallHorizontal() && joueur1.putWall(board, temp)){
				BoardGUI.locWallHorizontal.add(temp);
				game.nextPlayer();
				panel.repaint();
			}else if (temp.isWallVertical() && joueur1.putWall(board, temp)){
				BoardGUI.locWallVertical.add(temp);
				game.nextPlayer();
				panel.repaint();
			}
		}else if (game.getTour() == 1){
			if (temp.lSquare() && joueur2.move(board, temp)) {
				BoardGUI.locPawn2 = temp;
				game.nextPlayer();
				if (game.win(joueur2)) {
					System.out.println("JOUEUR2 A GAGNE");
				}
				panel.repaint();
			}else if (temp.isWallHorizontal() && joueur2.putWall(board, temp)){
				BoardGUI.locWallHorizontal.add(temp);
				game.nextPlayer();
				panel.repaint();
			}else if (temp.isWallVertical() && joueur2.putWall(board, temp)){
				BoardGUI.locWallVertical.add(temp);
				game.nextPlayer();
				panel.repaint();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		motionCoord = new Location(e.getX(), e.getY()).pixelToCoord();
		panel.repaint();
	}

}
