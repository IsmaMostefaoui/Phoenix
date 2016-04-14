package com.umons.view;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.umons.model.Game;
import com.umons.model.Grid;
import com.umons.model.IPathFinder;
import com.umons.model.Location;
import com.umons.model.Player;
import com.umons.model.Audio;

public class MyMouseListener implements MouseInputListener{

	private int x1, y1, xpressed, ypressed, xreleased, yreleased;
	private static Location clickCoord;
	private static Location motionCoord;
	private Game game;
	private final JPanel panel;
	private Player joueur1;
	private Player joueur2;
	private IPathFinder finder;
	//ajout d'un objet audio pour plus tard
	Audio aud;
	
	
	//a changer, pas tres bon de mettre Ã§a ici, je crois (je parledes joueur et meme du game et meme du board)
	public MyMouseListener(Player joueur1, Player joueur2, JPanel panel, Game game, IPathFinder finder) {
		this.panel = panel;
		this.game = game;
		this.joueur1 = joueur1; this.joueur2 = joueur2;
		this.finder = finder;
		//aud = new Audio("D:\\Mes documents\\worksplace\\Phoenix\\src\\com\\umons\\misc\\8461.wav");
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX(); y1 = e.getY();
		clickCoord = new Location(x1, y1);
		Location temp = clickCoord.pixelToCoord();
		if (game.getTour() == 0) {
			//le deplacement du joueur se fait ici, avec la condition
			//si move retourne vrai, alors le pion se deplace
			//sinon, alors move retourne faux et ne fait rien
			if (temp.isSquare() && joueur1.move(temp)) {
				BoardGUI.locPawn1 = temp;
				game.nextPlayer();
				if (game.win(joueur1)) {
					System.out.println("JOUEUR1 A GAGNE");
					game.stop(panel);
				}
				panel.repaint();
			}else if (temp.isWallHorizontal() && joueur1.putWall(temp, finder)){
				System.out.println("locwall rempli");
				BoardGUI.locWallHorizontal.add(temp);
				game.nextPlayer();
				panel.repaint();
				//aud.run();
			}else if (temp.isWallVertical() && joueur1.putWall(temp, finder)){
				System.out.println("locwall rempli");
				BoardGUI.locWallVertical.add(temp);
				game.nextPlayer();
				panel.repaint();
				//aud.run();
			}
		}else if (game.getTour() == 1){
			if (temp.isSquare() && joueur2.move(temp)) {
				BoardGUI.locPawn2 = temp;
				game.nextPlayer();
				if (game.win(joueur2)) {
					System.out.println("JOUEUR2 A GAGNE");
					game.stop(panel);
				}
				panel.repaint();
			}else if (temp.isWallHorizontal() && joueur2.putWall(temp, finder)){
				System.out.println("locwall rempli j2");
				BoardGUI.locWallHorizontal.add(temp);
				game.nextPlayer();
				panel.repaint();
				//aud.run();
			}else if (temp.isWallVertical() && joueur2.putWall(temp, finder)){
				System.out.println("locwall rempli j2");
				BoardGUI.locWallVertical.add(temp);
				game.nextPlayer();
				panel.repaint();
				//aud.run();
			}
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
