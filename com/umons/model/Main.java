package com.umons.model;

import com.umons.view.QuoridorGUI;
import com.umons.view.WelcomGUI;

public class Main {	
	public static void main(String[] args){
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR");
		frame.setPane(new WelcomGUI(), QuoridorGUI.RULESGUI);
		frame.switchToPanel(QuoridorGUI.MENUGUI);
		frame.setVisible(true);
	}
}
