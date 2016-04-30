package com.umons.view;

/*import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeGUI {
	
	MyButton mode1J;
	MyButton mode2J;
	MyButton reloadButton;

	public ModeGUI() {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		setLayout(gb);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		startButton = new MyButton("START", new Color(211, 84, 0));
		startButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				((QuoridorGUI)parentFrame).switchToPanel(QuoridorGUI.BOARDGUI);
			}
		});
		gb.setConstraints(startButton, gbc);
		this.add(startButton);
		
		gbc.gridy = 1;
		
		reloadButton = new MyButton("RELOAD", new Color(211, 84, 0));
		reloadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("charge la partie précedemment sauvé");
			}
		});
		gb.setConstraints(reloadButton, gbc);
		this.add(reloadButton);
		
		gbc.gridy = 2;
		
		quitButton = new MyButton("QUIT", new Color(211, 84, 0));
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
}*/
