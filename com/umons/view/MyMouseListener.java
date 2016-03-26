package com.umons.view;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.umons.model.Game;
import com.umons.model.Grid;
import com.umons.model.Location;
import com.umons.model.Main;
import com.umons.model.Player;

public class MyMouseListener implements MouseInputListener{

	private int x1, y1, xpressed, ypressed, xreleased, yreleased;
	final JPanel panel;
	public static Location clickCoord;
	public static Location motionCoord;
	Game game;
	Player joueur1;
	Player joueur2;
	Grid board;
	
	
	//a changer, pas tres bon de mettre Ã§a ici, je crois (je parledes joueur et meme du game et meme du board)
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
		Location temp = clickCoord.pixelToCoord();
		if (game.getTour() == 0) {
			if (temp.isSquare() && joueur1.move(board, temp)) {
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
			}board.afficheGrid(joueur1, joueur2);
		}else if (game.getTour() == 1){
			if (temp.isSquare() && joueur2.move(board, temp)) {
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
			}board.afficheGrid(joueur1, joueur2);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		xpressed = e.getX(); ypressed = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Quand on click et puis qu'on lache, si la case presse est la meme que la case
		//"depressé" (celle ou on lache le click), alors on fait quand même avancer le pion.
		
		xreleased = e.getX(); yreleased = e.getY();
		Location pressed = new Location(xpressed, ypressed);
		Location released = new Location(xreleased, yreleased);
		if (pressed.pixelToCoord().equals(released.pixelToCoord())) {
			mouseClicked(e);
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
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		motionCoord = new Location(e.getX(), e.getY()).pixelToCoord();
		panel.repaint();
	}
	
	public static Location getMotionCoord() {
		return motionCoord;
	}

}
