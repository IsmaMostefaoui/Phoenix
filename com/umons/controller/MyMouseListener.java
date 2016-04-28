package com.umons.controller;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import com.umons.model.Location;

public class MyMouseListener implements MouseInputListener{

	private int x1, y1, xpressed, ypressed, xreleased, yreleased;
	private static Location clickCoord;
	private static Location motionCoord;
	private Controller controller;
	
	
	//a changer, pas tres bon de mettre ça ici, je crois (je parledes joueur et meme du game et meme du board)
	public MyMouseListener(Controller controller) {
		this.controller = controller;
		//aud = new Audio("D:\\Mes documents\\worksplace\\Phoenix\\src\\com\\umons\\misc\\8461.wav");
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//tout se passe dans le press/release
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
		if (Controller.pixelToCoord(pressed).equals(Controller.pixelToCoord(released))) {
			clickCoord = released;
			controller.makePlayerPlay();
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
		motionCoord = Controller.pixelToCoord(new Location(e.getX(), e.getY()));
		controller.updatePanel();
	}
	
	public static Location getMotionCoord() {
		return motionCoord;
	}
	
	public static Location getClickCoord() {
		return clickCoord;
	}
	


}
