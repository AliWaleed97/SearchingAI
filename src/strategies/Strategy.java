package strategies;

import java.util.ArrayList;
import java.util.TreeMap;

import generic.Node;
import generic.State;

public abstract class Strategy {

	public ArrayList<String> visited;
	Node rootNode;

	public abstract boolean isEmpty();

	public abstract void add(ArrayList<Node> nodes);

	public void makeQueue(Node node) {
		rootNode = node;
		visited = new ArrayList<String>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(rootNode);
		add(nodes);
	}

	public abstract Node removeFront();
}
