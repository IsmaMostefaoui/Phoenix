package com.umons.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class VictoryPanel extends JPanel{
	
	JLabel label;
	
	public VictoryPanel() {
		label = new JLabel();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 830, 860);
		label.setText("YOU WIN");
		label.setLocation(430, 415);
		label.setEnabled(true);
		
	}

}
