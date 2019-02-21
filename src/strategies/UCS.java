package strategies;

import java.util.ArrayList;
import java.util.LinkedList;

import generic.Node;


public class UCS extends Strategy {

	LinkedList<Node> queue = new LinkedList<>();


	public UCS() {
	}


	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public void add(ArrayList<Node> nodes) {
		for (Node node : nodes) {
			if (visited.contains(node.state.elementsToString()) == false) {
				visited.add(node.state.elementsToString());
				if (queue != null) {
					int i = 0;
					for (Node element : queue) {
						if (node.pathCost < element.pathCost)
							break;
						i++;
					}
					queue.add(i, node);
				} else {
					queue.add(node);
				}
			}

		}
	}

	@Override
	public Node removeFront() {
		return queue.remove();
	}
}
