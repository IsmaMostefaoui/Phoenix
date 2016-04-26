package com.umons.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;	

public class QuoridorGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	static final Dimension screenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	static final int height = (int) screenDimension.getHeight();
	static final int width = (int) screenDimension.getWidth();
	
	public static final int HEIGHT = (5*height)/6;
	static final int WIDTH = 2*width/3;
	
	private static CardLayout cl;
	private static JPanel content;
	String[] listContent = {"BoardGUI", "MenuGUI", "RuleGUI"};
	int indice = 0;
	
	public QuoridorGUI(String title, JPanel...panel) {
		super();
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		cl = new CardLayout();
		content = new JPanel();
		
	    //On définit le layout
	    content.setLayout(cl);
	    //On ajoute les cartes à la pile avec un nom pour les retrouver
	    
	    for (int i = 0; i < panel.length; i++) {
		    content.add(panel[i], listContent[i]);
	    }
	    this.setContentPane(content);
		
		setVisible(true);
	}
	
	public static void nextPanel(String nextPanel){
		System.out.println("je suis dans nextPanel et je devrais afficher le panel !!!!!!!!!!!");
		cl.show(content, nextPanel);
	}
}
