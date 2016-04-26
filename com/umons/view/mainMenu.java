package com.umons.view;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class mainMenu {
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		MenuGUI menu = new MenuGUI();
		QuoridorGUI frame = new QuoridorGUI("QUORIDOR", panel, menu);
		frame.getContentPane().add(menu);
		frame.setVisible(true);
	}
}
