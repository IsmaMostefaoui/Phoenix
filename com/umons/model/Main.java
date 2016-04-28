package com.umons.model;

import com.umons.view.MenuGUI;
import com.umons.view.QuoridorGUI;

public class Main {
	
	public static void main(String[] args){
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR");
		MenuGUI menu = new MenuGUI(frame);
		frame.setContentPane(menu);
		frame.setPane(menu, QuoridorGUI.MENUGUI);
		frame.setVisible(true);
		
	}
}
