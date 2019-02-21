package strategies;

import java.util.ArrayList;
import java.util.Stack;

import generic.Node;


public class DFS extends Strategy {

	Stack<Node> stack = new Stack<>();

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void add(ArrayList<Node> nodes) {
		for (Node node : nodes) {
			if(visited.contains(node.state.elementsToString()) == false) {
				visited.add(node.state.elementsToString());
				stack.push(node);
			}
		}
	}

	@Override
	public Node removeFront() {
		return stack.pop();
	}
}