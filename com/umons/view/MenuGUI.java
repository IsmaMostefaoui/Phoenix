package com.umons.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	
	Image image;
	MyButton startButton;
	MyButton quitButton;
	
	public MenuGUI() {
		System.out.println("dans le constructeur");
		try {
			image = ImageIO.read(new File("./misc/wallpaper.jpg"));
		}catch (IOException ie) {
			System.err.println("Erreur d'image :");
			ie.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.gridheight = 0;
		gbc.gridwidth = 0;
		
		startButton = new MyButton("START");
		this.add(startButton, gbc);
		quitButton = new MyButton("QUIT");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.drawImage(image, -750, -75, this);
	}

}
