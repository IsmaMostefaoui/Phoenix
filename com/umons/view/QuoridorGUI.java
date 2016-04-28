package com.umons.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class QuoridorGUI extends JFrame{
	
	boolean printMenuBar = false;
	
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
	public static int VICTORYGUI = 3;
	
	private ArrayList<JPanel> nextPanes = new ArrayList<JPanel>();
	
	public QuoridorGUI(String title) {
		super();
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public void switchToPanel(int panelName) {
		getContentPane().removeAll();
		System.out.println(nextPanes.get(panelName));
		setContentPane(nextPanes.get(panelName));
		nextPanes.get(panelName).repaint();
		if (panelName == BOARDGUI) {
			printMenuBar = true;
			menuBar();
		}else {
			printMenuBar = false;
		}
		SwingUtilities.updateComponentTreeUI(this);
		nextPanes.get(panelName).repaint();
		
	}
	
	public void setPane(JPanel panelToAdd) {
		nextPanes.add(panelToAdd);
		System.out.println(nextPanes.size());
	}
	
	public void menuBar() {
		JMenuBar menuBar = new JMenuBar();
		if (printMenuBar) {
			this.setJMenuBar(menuBar);

			JMenu menuPartie = new JMenu("Partie");
			menuBar.add(menuPartie);

			JMenuItem partie = new JMenuItem("Choix Mode");
			JMenuItem start = new JMenuItem("Exit");
			JMenuItem stop = new JMenuItem("Undo (Ctrl+Z");

			menuPartie.add(partie); 
			menuPartie.add(start); 
			menuPartie.add(stop);

			JMenu menuCouleur = new JMenu("Couleur");
			menuBar.add(menuCouleur);

			JMenu snake = new JMenu("Snake");
			JMenu wall = new JMenu("Wall");

			menuCouleur.add(snake); menuCouleur.add(wall);

			JMenuItem noirS = new JMenuItem("Noir (banane au miel, KFC, et toutes les epices)");
			JMenuItem arabeS = new JMenuItem("Couleur Arabe(thé, couscous, tajiin,  etc...)");

			JMenuItem noirW = new JMenuItem("Noir (banane au miel, KFC, et toutes les epices)");
			JMenuItem arabeW = new JMenuItem("Couleur Arabe(thé, couscous, tajiin,  etc...)");

			snake.add(noirS); snake.add(arabeS);
			wall.add(noirW); wall.add(arabeW);
		}else {
			getContentPane().remove(menuBar);
		}
	}
}
