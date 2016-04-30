package com.umons.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.umons.view.MenuGUI;
import com.umons.view.QuoridorGUI;

public class Main {
	
	public static Image image;
	
	public static void main(String[] args){
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR [J VS J]");
		
		try {
			image = ImageIO.read(new File("./misc/wallpaper.jpg"));
			MenuGUI menu = new MenuGUI(frame, image);
			frame.setPane(menu, QuoridorGUI.MENUGUI);
			frame.switchToPanel(QuoridorGUI.MENUGUI);
			frame.setVisible(true);
		}catch (IOException ie) {
			System.err.println("Erreur d'image :");
			ie.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
