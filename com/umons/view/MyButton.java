package com.umons.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class MyButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	String text;
	Color color;
	
	public MyButton(String text, Color color) {
		super(text);
		this.text = text;
		this.color = color;
		this.setPreferredSize(new Dimension(250, 60));
	}
	
	public void paintComponent(Graphics g) {
	    Graphics2D g2d = (Graphics2D)g;
	    int ascent = g2d.getFontMetrics().getAscent();
	    int width = g2d.getFontMetrics().stringWidth(this.text);
	    g2d.setColor(color);
	    g2d.fillRect(0, 0, 500, 60);
	    g2d.setColor(Color.white);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.drawString(this.text, (int)(this.getSize().getWidth()-width)/2, ascent);
	}
	
	
}
