package com.umons.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import com.umons.model.*;

public class BoardGUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private int SQUARE_NUMBER = 9;
	Player player1;
	Player player2;
	
	static Location loc;
	
	public BoardGUI(Player player1, Player player2) {
		this.player1 = player1; this.player2 = player2;
		loc = new Location (player1.getLoc().coordToPixel().getLocX(), player1.getLoc().coordToPixel().getLocY());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		int SPACE = 0;
		int HEIGHT = 0;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 835, 835);
		g2d.setColor(Color.WHITE);
		for (int i =0; i < SQUARE_NUMBER; i++) {
			for (int j = 0; j < 9; j++) {
				g2d.fillRect(30+SPACE, 30+HEIGHT, 60, 60);
				SPACE += 90;
			}
			SPACE =0;
			HEIGHT += 90;
		}
		g2d.setColor(Color.BLUE);
		g2d.fillOval(loc.getLocX(), loc.getLocY(),50, 50);
	}
	
}
