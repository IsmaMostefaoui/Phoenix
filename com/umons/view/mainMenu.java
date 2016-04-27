package com.umons.view;
import java.awt.Color;

import javax.swing.*;

public class mainMenu {
	public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		QuoridorGUI frame = new QuoridorGUI("QUORIDOR", panel);
		JPanel menu = new MenuGUI(frame);
		frame.setContentPane(menu);
		frame.setVisible(true);
	}
}
