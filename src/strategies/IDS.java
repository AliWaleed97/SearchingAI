package strategies;

import java.util.ArrayList;
import java.util.Stack;

import generic.Node;

public class IDS extends Strategy {

	 static int MaximumDepth = 100000000;
	 Stack<Node> stack = new Stack<>();
	 int currentDepth = 0;

	@Override
	public boolean isEmpty() 
	{ 
		if(!stack.isEmpty())
			return false;
		if(stack.isEmpty() && currentDepth == MaximumDepth)
			return true;
		currentDepth++;
		makeQueue(rootNode);
		return false;
	}
	@Override
	public void add(ArrayList<Node> nodes) {
		for(Node node: nodes)
		{
			if(node.depth <= currentDepth && visited.contains(node.state.elementsToString()) == false) {
				visited.add(node.state.elementsToString());
				stack.push(node);
			}
		}
	}

	@Override
	public Node removeFront() { return stack.pop(); }	
}
