package com.umons.console;

import javax.swing.JPanel;

import com.umons.controller.Controller;
import com.umons.controller.MyMouseListener;
import com.umons.model.AMode;
import com.umons.model.ARules;
import com.umons.model.Game;
import com.umons.model.Mode1Vs1;
import com.umons.view.BoardGUI;
import com.umons.view.QuoridorGUI;

public class MainStat {

	public static void main(String[] args) {
		int gameNumber = Integer.parseInt(args[0]);
		
		if (args.length <= 2){
			System.err.println("Commande inconnue ou pas assez d'argument");
			System.out.println("Veuillez essayer -h pour plusdin'formations sur l'utilisation de mode Statistique");
			return;
		}else if (args.length == 2){
			AMode mode;
			switch(args[1]){
				case "Facile":
					mode = new Mode1Vs1(AMode.EASY, 0);
					initConsole("2", mode);
					break;
				case "Standard":
					mode = new Mode1Vs1(AMode.MEDIUM, 0);
					initConsole("2", mode);
					break;
				case "Difficile":
					mode = new Mode1Vs1(AMode.DIFFICULT, 0);
					initConsole("2", mode);
					break;
			}
			
		}
	}
	
	public static void initConsole(String text, AMode mode) {
		System.out.println("Mode " + text + " joueurs lancé !");
		Game game = new Game(mode);
		mode.init(game);
		while ()
	}
}
