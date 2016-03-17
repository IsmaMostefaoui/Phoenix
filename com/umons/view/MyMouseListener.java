package com.umons.view;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import com.umons.model.Location;
import com.umons.model.Main;
import com.umons.model.Player;

public class MyMouseListener implements MouseListener{

	private int x1, y1;
	final JPanel panel;
	public static Location temp;
	public MyMouseListener(JPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX(); y1 = e.getY();
		temp = new Location(x1, y1);
		if (Main.tour == 1) {
			while(MyMouseListener.temp == null){
				System.out.println("dans la boucle du main");
			}
			System.out.println("sorti du xhile dans main");
			if (MyMouseListener.temp.pixelToCoord().lSquare()) {
				System.out.println("in temp");
				Main.locPawn1 = MyMouseListener.temp.pixelToCoord();
				Main.tour++;
			}else {
				if (MyMouseListener.temp.pixelToCoord().isWallHorizontal()){
					Main.locWallHorizontal.add(MyMouseListener.temp.pixelToCoord());
					Main.tour++;
				}else {
					Main.locWallVertical.add(MyMouseListener.temp.pixelToCoord());
					Main.tour++;
				}
			}panel.repaint();
		}else if (tour == 2){
			if (MyMouseListener.temp.pixelToCoord().lSquare()) {
				Main.locPawn2 = MyMouseListener.temp.pixelToCoord();
				Main.tour--;
			}else {
				if (MyMouseListener.temp.pixelToCoord().isWallHorizontal()){
					Main.locWallHorizontal.add(MyMouseListener.temp.pixelToCoord());
					Main.tour--;
				}else {
					Main.locWallVertical.add(MyMouseListener.temp.pixelToCoord());
					Main.tour--;
				}
			}panel.repaint();
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

}
