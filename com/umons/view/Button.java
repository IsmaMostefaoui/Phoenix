package com.umons.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class Button extends JButton{

	private static final long serialVersionUID = 1L;
	
	String text;
	
	public Button(String text) {
		super(text);
		this.text = text;
	}
	
	public void painComponent(Graphics g) {
	    Graphics2D g2d = (Graphics2D)g;
	    g2d.drawRect(0, 0, 100, 100);
	    g2d.setColor(Color.white);
	    g2d.drawString(this.text, this.getWidth() / 2 - (this.getWidth()/ 2 /4), (this.getHeight() / 2) + 5);
	}
	
	
}
