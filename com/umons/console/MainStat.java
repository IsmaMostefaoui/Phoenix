package com.umons.console;

import com.umons.model.AMode;
import com.umons.model.Game;
import com.umons.model.Mode1Vs1;
import com.umons.model.Mode2Vs2;

public class MainStat {

	public static void main(String[] args) {
		int gameNumber = Integer.parseInt(args[0]);
		if (args.length <= 2){
			System.err.println("Commande inconnue ou pas assez d'argument");
			System.out.println("Veuillez essayer -h pour plusdin'formations sur l'utilisation de mode Statistique");
			return;
		}else if (args.length == 3){
			int IA1 = convertToIA(args[1]);
			int IA2 = convertToIA(args[2]);
			AMode mode = new Mode1Vs1("console", IA1, IA2);
			float[] tabOfWin = playConsole("2", mode, gameNumber);
			System.out.println("Sur un nombre d'essai de " + gameNumber + " le pourcentage de victoire de chaque IA est: " );
			System.out.println("IA 1 " + args[1] + ": " + tabOfWin[0] + " %");
			System.out.println("IA 2 " + args[2] + ": " + tabOfWin[1] + " %");
		}else if (args.length == 5) {
			int IA1 = convertToIA(args[1]);
			int IA2 = convertToIA(args[2]);
			int IA3 = convertToIA(args[3]);
			int IA4 = convertToIA(args[4]);
			AMode mode = new Mode2Vs2("console", IA1, IA2, IA3, IA4);
			float[] tabOfWin = playConsole("4", mode, gameNumber);
			System.out.println("Sur un nombre d'essai de " + gameNumber + " le pourcentage de victoire de chaque IA est: " );
			System.out.println("IA 1 " + args[1] + ": " + tabOfWin[0] + " %");
			System.out.println("IA 2 " + args[2] + ": " + tabOfWin[1] + " %");
			System.out.println("IA 3 " + args[1] + ": " + tabOfWin[0] + " %");
			System.out.println("IA 4 " + args[2] + ": " + tabOfWin[1] + " %");
		}
	}
	
	public static int convertToIA(String IA){
		switch(IA) {
		case "Facile":
			return AMode.EASY;
		case "Standard":
			return AMode.MEDIUM;
		case "Difficile":
			return AMode.DIFFICULT;
		}
		return -1;
	}
	
	public static float[] playConsole(String text, AMode mode, int gameNumber) {
		float[] percentOfWin = {0, 0, 0, 0};
		int defautGameNumber = gameNumber;
		while (gameNumber > 0){
			Game game = new Game(mode);
			int playerWin = mode.play(game);
			switch(playerWin){
			case 1:
				percentOfWin[0]++;
				break;
			case 2:
				percentOfWin[1]++;
				break;
			case 3:
				percentOfWin[2]++;
				break;
			case 4:
				percentOfWin[3]++;
				break;
			}gameNumber--;
		}
		for (int i =0; i < percentOfWin.length; i++) {
			percentOfWin[i] = (percentOfWin[i]/defautGameNumber)*100;
		}return percentOfWin;
		
	}
}
