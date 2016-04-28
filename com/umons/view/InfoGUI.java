package com.umons.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InfoGUI extends JPanel{

	MyButton backButton = new MyButton("BACK", new Color(127, 140, 141));
	
	public InfoGUI(final JFrame parentFrame) {
		this.setLayout(new BorderLayout());
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((QuoridorGUI)parentFrame).switchToPanel(QuoridorGUI.MENUGUI);
			}
		});
		this.add(backButton, BorderLayout.SOUTH);
		this.setBackground(new Color(127, 140, 141));
	}
}
