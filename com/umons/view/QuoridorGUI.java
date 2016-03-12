package com.umons.view;

import javax.swing.JFrame;	

public class QuoridorGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int HEIGHT = 900;
	final int WIDTH = 900;
	
	public QuoridorGUI(String title, boolean resizable) {
		super();
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(resizable);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
}
