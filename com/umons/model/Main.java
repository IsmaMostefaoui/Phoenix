package com.umons.model;

import com.umons.view.QuoridorGUI;

public class Main {	
	public static void main(String[] args){
		//float timeFirst = System.currentTimeMillis();
		QuoridorGUI frame = new QuoridorGUI("THE QUORIDOR [J VS IA]");
		
		frame.switchToPanel(QuoridorGUI.MENUGUI);
		//float timeEnd = System.currentTimeMillis();
		//System.out.println("----------TIME: " + (timeEnd - timeFirst) + "--------------");
		frame.setVisible(true);
	}
}
