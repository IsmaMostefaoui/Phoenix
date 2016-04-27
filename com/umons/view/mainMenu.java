package com.umons.view;
import java.awt.Color;

import javax.swing.*;

public class mainMenu {
	public static void main(String[] args) {
		QuoridorGUI frame = new QuoridorGUI("QUORIDOR");
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		JPanel menu = new MenuGUI();
		frame.setContentPane(menu);
		frame.setVisible(true);
	}
}
