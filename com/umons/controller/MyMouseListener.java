package com.umons.controller;

import com.umons.view.*;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import com.umons.model.Game;
import com.umons.model.IPathFinder;
import com.umons.model.Location;
import com.umons.model.Player;
import com.umons.model.RandomIA;
import com.umons.model.Audio;

public class MyMouseListener implements MouseInputListener{

	private int x1, y1, xpressed, ypressed, xreleased, yreleased;
	private static Location clickCoord;
	private static Location motionCoord;
	private Game game;
	private final JPanel panel;
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private IPathFinder finder;
	//ajout d'un objet audio pour plus tard
	Audio aud;
	
	
	//a changer, pas tres bon de mettre ça ici, je crois (je parledes joueur et meme du game et meme du board)
	public MyMouseListener(Player player1, Player player2, JPanel panel, Game game, IPathFinder finder) {
		this.panel = panel;
		this.game = game;
		this.player1 = player1; this.player2 = player2;
		this.finder = finder;
		//aud = new Audio("D:\\Mes documents\\worksplace\\Phoenix\\src\\com\\umons\\misc\\8461.wav");
		
	}
	
	public MyMouseListener(Player player1, Player player2,Player player3, Player player4, JPanel panel, Game game, IPathFinder finder) {
		this.panel = panel;
		this.game = game;
		this.player1 = player1; this.player2 = player2; this.player3 = player3; this.player4 = player4;
		this.finder = finder;
		//aud = new Audio("D:\\Mes documents\\worksplace\\Phoenix\\src\\com\\umons\\misc\\8461.wav");
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX(); y1 = e.getY();
		clickCoord = new Location(x1, y1);
		Location temp = clickCoord.pixelToCoord();
		if (game.getTour() == 0) {
			//la je suppose que l ia sera toujours le deuxieme troisieme ou quatrieme joueur ce qui est totalement faux
			//#simulation entre deux ou quatre IA (stat mode)
			player1.play(game, temp, finder);
		}else if (game.getTour() == 1){
			if (player2.isHumanPLayer()) {
				player2.play(game, temp, finder);
			}else {
				//on sait alors que c est un robot donc on cast pour acceder a la methode move de l IA
				RandomIA IA = (RandomIA) player2;
				IA.move(game, finder, player1);
				game.nextPlayer();
			}
		}else if (game.getTour() == 2) {
			player3.play(game, temp, finder);
		}else if (game.getTour() == 3){
			player4.play(game, temp, finder);
		}panel.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		xpressed = e.getX(); ypressed = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Quand on click et puis qu'on lache, si la case presse est la meme que la case
		//"depress�" (celle ou on lache le click), alors on fait quand m�me avancer le pion.
		
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
	
	public void play(Player player, Location loc, int tour) {
		
	}

}
