package strategies;

import java.util.ArrayList;
import java.util.LinkedList;

import generic.Node;
import saveWesteros.westerosState;

public class GS1 extends Strategy {

	LinkedList<Node> queue = new LinkedList<>();;

	public GS1() {
		// TODO Auto-generated constructor stub
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
						if ((heuristicFunction1(node)) < (heuristicFunction1(element)))
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

	public int heuristicFunction1(Node node) {
		int wwAlive = ((westerosState) node.state).whiteWalkersCount;
		int heuristic = (int) Math.ceil(wwAlive / 3);
		heuristic = heuristic * 20;

		return heuristic;

	}
}
