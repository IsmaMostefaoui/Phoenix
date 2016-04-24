package com.umons.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MenuGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	
	Image image;
	
	public MenuGUI() {
		try {
			image = ImageIO.read(new File("./misc/wallpaper.jpg"));
		}catch (IOException ie) {
			System.err.println("Erreur d'image :");
			ie.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.fillRect(0, 0, 100, 100);
	}

}
