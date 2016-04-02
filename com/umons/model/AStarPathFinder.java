package com.umons.model;

import java.util.ArrayList;
import java.util.Collections;

public class AStarPathFinder implements IPathFinder{

	
	//BUUUUGGGG
	private Node[][] nodes;
	
	public AStarPathFinder() {
		nodes = new Node[Grid.getLen()][Grid.getLen()];
		for (int i = 0 ; i < Grid.getLen(); i++) {
			for (int j = 0; j < Grid.getLen(); j++) {
				nodes[i][j] = new Node(i, j);
			}
		}
	}
	
	@Override
	public Path findPath(int sx, int sy, int tx, int ty) {
		ArrayList<Node> closeList = new ArrayList<Node>();
		NodeSortedList openList = new NodeSortedList(nodes[sx][sy]);
	}
	
	/**
	 * Representation d une liste qui se trie apres chaque ajout de noeud
	 * 
	 * @author Inspired by Kevin Glass's code
	 *
	 */
	private class NodeSortedList extends ArrayList{
		
		private ArrayList<Node> list = new ArrayList<Node>();
		
		public NodeSortedList(Node firstNode) {
			list.add(0, firstNode);
		}
		
		public Node getFirst() {
			return list.get(0);
		}
		
		public void add(Node node) {
			list.add(node);
			Collections.sort(list);
		}
		
		public void remove(Node node) {
			list.remove(node);
		}
		
		public boolean contains(Object obj) {
			Node node = (Node) obj;
			return list.contains(node);
		}
	}
	
	/**
	 * Representation d un noeud (selon la theorie des graphes)
	 * @author Inspired by Kevin Glass's code
	 *
	 */
	public class Node implements Comparable{
		
		private int x;
		private int y;
		private int cost;
		private Node parent;
		private float heuristic;
		//temporaire
		private int depth;
		
		/**
		 * Crée un nouveau noeud
		 * 
		 * @param x la coordonnées en x du noeud
		 * @param y la coordonnées en y du noeud
		 */
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		/**
		 * Défini le "parent" de ce noeud
		 * 
		 * @param parent le noeud parent, celui qui nous amene a ce noeud
		 * @return The depth we have no reached in searching
		 */
		public int setParent(Node parent) {
			//temporaire
			depth = parent.depth + 1;
			this.parent = parent;
			
			return depth;
		}
		
		/**
		 * @see Comparable#compareTo(Object)
		 */
		public int compareTo(Object other) {
			Node o = (Node) other;
			
			float f = heuristic + cost;
			float of = o.heuristic + o.cost;
			
			if (f < of) {
				return -1;
			} else if (f > of) {
				return 1;
			} else {
				return 0;
			}
		}

	}
	
}