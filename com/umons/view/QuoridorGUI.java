package com.umons.view;

import java.awt.Dimension;
import javax.swing.JFrame;	

public class QuoridorGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	static final Dimension screenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	static final int height = (int) screenDimension.getHeight();
	static final int width = (int) screenDimension.getWidth();
	
	public static final int HEIGHT = (5*height)/6;
	static final int WIDTH = 2*width/3;
	
	public QuoridorGUI(String title) {
		super();
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
}
