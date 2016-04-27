package com.umons.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;	
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class QuoridorGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	static final Dimension screenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	static final int height = (int) screenDimension.getHeight();
	static final int width = (int) screenDimension.getWidth();
	
	public static final int HEIGHT = (5*height)/6;
	static final int WIDTH = 2*width/3;
	
	public static JPanel content = new JPanel();
	public static CardLayout cl = new CardLayout();
	
	public static int BOARDGUI = 0;
	public static int MENUGUI = 1;
	public static int RULESGUI = 2;
	
	private ArrayList<JPanel> nextPanes = new ArrayList<JPanel>();
	
	public QuoridorGUI(String title, JPanel...panels) {
		super();
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		for (int i = 0; i < panels.length; i++) {
			nextPanes.add(panels[i]);
		}
		
		setVisible(true);
	}
	
	public void switchToPanel(int panelName) {
		getContentPane().removeAll();
		System.out.println(nextPanes.get(panelName));
		setContentPane(nextPanes.get(panelName));
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void setPane(JPanel panelToAdd) {
		nextPanes.add(panelToAdd);
	}
}
