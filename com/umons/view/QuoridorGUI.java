package com.umons.view;

import javax.swing.JFrame;	

public class QuoridorGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	final int HEIGHT = 860;
	final int WIDTH = 830;
	
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
