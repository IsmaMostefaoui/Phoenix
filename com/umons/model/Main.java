package com.umons.model;

import com.umons.view.MenuGUI;
import com.umons.view.QuoridorGUI;

public class Main {
	
	public static void main(String[] args){
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR [J VS J]");
		MenuGUI menu = new MenuGUI(frame);
		frame.setPane(menu, QuoridorGUI.MENUGUI);
		frame.switchToPanel(QuoridorGUI.MENUGUI);
		frame.setVisible(true);
		
	}
}
