package com.umons.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;

public class MyButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	String text;
	Color color;
	
	public MyButton(String text, Color color) {
		super(text);
		this.text = text;
		this.color = color;
		this.setPreferredSize(new Dimension(250, 50));
	}
	
	public void paintComponent(Graphics g) {
	    Graphics2D g2d = (Graphics2D)g;
	    int ascent = g2d.getFontMetrics().getAscent();
	    int width = g2d.getFontMetrics().stringWidth(this.text);
	    g2d.setColor(color);
	    g2d.fillRect(0, 0, 250, 50);
	    g2d.drawString("bonjour", 50, 50);
	    g2d.drawString(this.text, this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) + 5);
	}
	
	
}
