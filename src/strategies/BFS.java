package strategies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import generic.Node;

public class BFS extends Strategy {

	Queue<Node> queue = new LinkedList<>();

	@Override
	public boolean isEmpty() { return queue.isEmpty(); }

	@Override
	public void add(ArrayList<Node> nodes) {
		
		for(Node node: nodes)
		{
			
			if(visited.contains(node.state.elementsToString()) == false) {
				visited.add(node.state.elementsToString());
				queue.add(node);
			}
		}
	}

	@Override
	public Node removeFront() { return queue.remove(); }

	
}
