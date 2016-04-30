package com.umons.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import com.umons.model.AMode;
import com.umons.model.Game;
import com.umons.model.Main;
import com.umons.model.Mode1Vs1;
import com.umons.model.Mode2Vs2;


public class QuoridorGUI extends JFrame{
	
	boolean printMenuBar = false;
	
	private static final long serialVersionUID = 1L;
	
	static final Dimension screenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	static final int height = (int) screenDimension.getHeight();
	static final int width = (int) screenDimension.getWidth();
	
	public static final int HEIGHT = (8*height)/9;
	static final int WIDTH = 2*width/3;
	
	public static JPanel content = new JPanel();
	public static CardLayout cl = new CardLayout();
	
	public static int BOARDGUI = 0;
	public static int MENUGUI = 1;
	public static int RULESGUI = 2;
	public static int VICTORYGUI = 3;
	
	private JPanel[] nextPanes = new JPanel[5];
	
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
		if (panelName == BOARDGUI) { 
			printMenuBar = true;
		}else{
			printMenuBar = false;
		}
		menuBar();
		if (panelName == MENUGUI){
			//TODO horrible mais sinon chargement trop long
			this.setContentPane(new MenuGUI(this, Main.image));
			if (nextPanes[BOARDGUI] != null){
				((BoardGUI)nextPanes[BOARDGUI]).reset();
			}
		}else {
			this.setContentPane(nextPanes[panelName]);
		}SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	public void setPane(JPanel panelToAdd, int index) {
		nextPanes[index] = panelToAdd;
	}
	
	public void menuBar() {
		JMenuBar menuBar = new JMenuBar();
		if (printMenuBar) {
			this.setJMenuBar(menuBar);

			JMenu menuPartie = new JMenu("Partie");
			menuBar.add(menuPartie);

			JMenu nouvellePartie = new JMenu("Nouvelle Partie");
			menuPartie.add(nouvellePartie);
			
			
			JMenu mode1 = new JMenu("Mode 2 Joueurs");
			nouvellePartie.add(mode1);
			JMenuItem mode1vs1 = new JMenuItem("Joueur contre Joueur");
			mode1.add(mode1vs1);
			mode1vs1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AMode mode = new Mode1Vs1(AMode.EASY, 2);
					initGame("[J VS J]", mode);
				}
			});
			
			JMenuItem mode1vsIa = new JMenuItem("Joueur contre Ordinateur");
			mode1.add(mode1vsIa);
			mode1vsIa.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame difficultFrame = new JFrame();
					difficultFrame.setSize(QuoridorGUI.WIDTH/4, QuoridorGUI.HEIGHT/9);
					ChoiceDifficultyGUI choice = new ChoiceDifficultyGUI(1, difficultFrame, QuoridorGUI.this);
					difficultFrame.add(choice);
					difficultFrame.setLocationRelativeTo(null);
					difficultFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					difficultFrame.setResizable(false);
					difficultFrame.setVisible(true);
				}
			});
			
			JMenu mode2 = new JMenu("Mode 4 Joueurs");
			nouvellePartie.add(mode2);
			JMenuItem mode2vs2 = new JMenuItem("Joueurs contre Joueurs [2 vs 2]");
			mode2.add(mode2vs2);
			mode2vs2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AMode mode = new Mode2Vs2(AMode.EASY, 4);
					initGame("[2J VS 2J]", mode);
				}
			});
			JMenuItem mode2vsIa = new JMenuItem("Joueur contre Ordinateurs [1 vs 3]");
			mode2.add(mode2vsIa);
			mode2vsIa.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame difficultFrame = new JFrame();
					difficultFrame.setSize(QuoridorGUI.WIDTH/4, QuoridorGUI.HEIGHT/9);
					ChoiceDifficultyGUI choice = new ChoiceDifficultyGUI(2, difficultFrame, QuoridorGUI.this);
					difficultFrame.add(choice);
					difficultFrame.setLocationRelativeTo(null);
					difficultFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					difficultFrame.setResizable(false);
					difficultFrame.setVisible(true);
				}
			});
			
			JMenuItem quit = new JMenuItem("Quitter");
			menuPartie.add(quit); 
			quit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			JMenu option = new JMenu("Options");
			menuBar.add(option);
			
			JMenuItem undo = new JMenuItem("Undo (Ctrl+Z)");
			option.add(undo);

		}else {
			getContentPane().remove(menuBar);
		}
	}
	
	public void initGame(String text, AMode mode){
		Game game = new Game(mode);
		mode.init(this, game);
		((BoardGUI) mode.getPane()).reset();
		QuoridorGUI.this.setTitle("THE QUORIDOR " + text);
		QuoridorGUI.this.setPane(mode.getPane(), QuoridorGUI.BOARDGUI);
		QuoridorGUI.this.switchToPanel(QuoridorGUI.BOARDGUI);
	}
}
