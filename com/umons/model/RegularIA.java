package com.umons.model;

import java.util.List;

public class RegularIA {
	
	public void move(Player[] players,IPathFinder finder) {
	List<Location> way;
	long timeStart = System.currentTimeMillis();
	for (int j = 0; j < players.length; j++) {
		for (int i = 0; i < ((Grid.getLen()/2)+1); i++) {
			if (players[j].getOrder()==1 || players[j].getOrder()==2) {
				if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), 2*i, players[j].getCoordFinish()) == null)) {
					check[j] = false;
				}else {
					check[j] = true;
					break;
				}
			}else if (players[j].getOrder()==3 || players[j].getOrder()==4){
				if ((finder.findPath(coordWall, players[j].getLoc().getLocX(), players[j].getLoc().getLocY(), players[j].getCoordFinish(), 2*i) == null)) {
					check[j] = false;
				}else {
					check[j] = true;
					break;
				}
			}
		}
	}
	long timeEnd = System.currentTimeMillis();
	System.out.println("\n\n\n--------------TIME: " + ((timeEnd - timeStart)) + "----------------");
	return check[0] && check [1];
}
}
