package com.umons.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.umons.model.AMode;
import com.umons.model.Game;
import com.umons.model.Mode1Vs1;
import com.umons.model.board.Location;
import com.umons.model.playerAbstraction.Player;


public class MenuGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	
	Image image;
	MyButton startButton;
	MyButton quitButton;
	MyButton reloadButton;
	MyButton rulesButton;
	
	
	/**
	 * Constructeur du Panel MenuGUI. Charge une image.
	 * @param parentFrame
	 */
	public MenuGUI(final QuoridorGUI parentFrame) {
		System.out.println("dans le constructeur");
		try {
			float timeFirst = System.currentTimeMillis();
			image = ImageIO.read(new File("./misc/wallpaper.jpg"));
			float timeEnd = System.currentTimeMillis();
			System.out.println("----------TIME: " + (timeEnd - timeFirst) + "--------------");
			System.out.println("Image Chargé");
		}catch (IOException ie) {
			System.err.println("Erreur d'image :");
			ie.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());
		
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		setLayout(gb);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		startButton = new MyButton("START", new Color(248, 140, 6));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Mode1Vs1 mode = new Mode1Vs1(AMode.MEDIUM, 1);
				Game game = new Game(mode);
				mode.init(parentFrame, game);
				parentFrame.setPane(mode.getPane(), QuoridorGUI.BOARDGUI);
				parentFrame.switchToPanel(QuoridorGUI.BOARDGUI);
				mode.makeRobotPlay();
			}
		});
		gb.setConstraints(startButton, gbc);
		this.add(startButton);
		
		gbc.gridy = 1;
		
		reloadButton = new MyButton("RELOAD", new Color(248, 100, 5));
		reloadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				load(parentFrame);
			}
		});
		gb.setConstraints(reloadButton, gbc);
		this.add(reloadButton);
		
		gbc.gridy = 2;
		
		rulesButton = new MyButton("RULES", new Color(248, 60, 5));
		rulesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Desktop d = Desktop.getDesktop();
				try {
					d.open(new File("./misc/regle_du_jeu.pdf"));
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}
		});
		gb.setConstraints(rulesButton, gbc);
		this.add(rulesButton);
		
		gbc.gridy = 3;
		quitButton = new MyButton("QUIT", new Color(248, 20, 4));
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("quitte le jeux");
				System.exit(0);
			}
		});
		gb.setConstraints(quitButton, gbc);
		this.add(quitButton);
	}
	
	/**
	 * Dessin l'image de fond pour le menu
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.drawImage(image, -450, -75, this);
	}
	
	/**
	 * Charge une partie à partir d'une ancienne sauvegarde
	 * @param parentFrame
	 */
	public void load(QuoridorGUI parentFrame){
		try{
			FileInputStream fis = new FileInputStream("./save/save.sv");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			AMode mode = (AMode)ois.readObject();
			
			Player[] players = (Player[])mode.getPlayer();
			
			@SuppressWarnings("unchecked")
			ArrayList<Location> locWallHorizontal = (ArrayList<Location>)ois.readObject();
			@SuppressWarnings("unchecked")
			ArrayList<Location> locWallVertical = (ArrayList<Location>)ois.readObject();
			
			int tour = ois.readInt();
			Game game = new Game(mode);
			mode.init(parentFrame, game);
			
			BoardGUI board = ((BoardGUI) mode.getPane()).reload(players, locWallHorizontal, locWallVertical, tour);
			
			parentFrame.setPane(board, QuoridorGUI.BOARDGUI);
			parentFrame.switchToPanel(QuoridorGUI.BOARDGUI);
		}catch(IOException ie) {	
			JLabel warningLabel = new JLabel("Vous n'avez pas de précedente partie sauvegardé");
			warningLabel.setForeground(Color.white);
			MenuGUI.this.add(warningLabel);
			try {
				Thread.sleep(100);
			}catch (Exception e2) {
				System.err.println("Erreur dans le thread de warning: ");
				e2.printStackTrace();
			}
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
