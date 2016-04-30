package com.umons.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import com.umons.model.AMode;
import com.umons.model.Mode1Vs1;
import com.umons.model.Mode2Vs2;

public class ChoiceDifficultyGUI extends JPanel{

	int mode;
	int difficult;
	
	JRadioButton easyIa;
	JRadioButton mediumIA;
	JRadioButton difficultIA;
	
	JLabel instruction;
	
	JButton button;
	
	final JFrame parentFrame;
	final QuoridorGUI quoridorFrame;
	
	public ChoiceDifficultyGUI(int mode, final JFrame parentFrame, final QuoridorGUI quoridorFrame) {
		
		this.parentFrame = parentFrame;
		this.quoridorFrame = quoridorFrame;
		
		this.mode = mode;
		this.difficult = AMode.EASY;
		
		instruction = new JLabel("Veuillez choisir une difficulté pour l'Ordinateur: \n");
		this.add(instruction);
		
		easyIa = new JRadioButton("Facile");
		easyIa.setSelected(true);
		easyIa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				difficult = AMode.EASY;
			}
		});
		
		mediumIA = new JRadioButton("Standard");
		mediumIA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				difficult = AMode.MEDIUM;
			}
		});
		
		difficultIA = new JRadioButton("Difficile");
		difficultIA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				difficult = AMode.DIFFICULT;
			}
		});
		
		ButtonGroup group = new ButtonGroup();
	    group.add(easyIa);
	    group.add(mediumIA);
	    group.add(difficultIA);
	    
	    this.add(easyIa);
	    this.add(mediumIA);
	    this.add(difficultIA);
	    
		button = new JButton("Let's Go !");
		this.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initIA(difficult);
			}
		});
	}
	
	public void initIA(int difficult){
		AMode mode;
		System.out.println("mode: " + ChoiceDifficultyGUI.this.mode);
		if (ChoiceDifficultyGUI.this.mode == 1 ){
			mode = new Mode1Vs1(difficult, 1);
		}else {
			mode = new Mode2Vs2(difficult, 1);
		}
		parentFrame.dispose();
		quoridorFrame.initGame("[J VS IA]", mode);
	}
}
