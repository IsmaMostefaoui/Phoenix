package com.umons.view;
import java.awt.Color;

import javax.swing.*;

public class mainMenu {
	public static void main(String[] args) {
		QuoridorGUI frame = new QuoridorGUI("QUORIDOR");
		JPanel menu = new MenuGUI();
		menu.setBackground(Color.black);
		frame.setContentPane(menu);
		frame.setVisible(true);
	}
}
