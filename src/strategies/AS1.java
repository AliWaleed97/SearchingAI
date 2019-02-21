package strategies;

import java.util.ArrayList;
import java.util.LinkedList;

import generic.Node;
import saveWesteros.Cell;
import saveWesteros.westerosState;

public class AS1 extends Strategy {

	LinkedList<Node> queue = new LinkedList<>();

	public AS1() {
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
						if ((node.pathCost + heuristicFunction2(node)) < (element.pathCost
								+ heuristicFunction2(element)))
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

	public int heuristicFunction2(Node node) {
		westerosState state = (westerosState) node.state;
		westerosState parent = null;
		Cell[][] grid = state.grid;
		int x = state.x;
		int y = state.y;
		int minimumDistance = -1;

		if (node.parent != null) {
			parent = (westerosState) node.parent.state;
		}

		if (grid[x][y].type == "DragonStone") {
			if (state.dragonGlassCount > parent.dragonGlassCount)
				return 0;
		}

		if (parent != null) {
			if (state.whiteWalkersCount < parent.whiteWalkersCount) {
				return 0;
			}
		}

		if (state.dragonGlassCount > 0) {
			for (int i = 0; i < grid.length; i++)
				for (int j = 0; j < grid[i].length; j++)
					if (grid[x][y].type == "WhiteWalker")
						if (minimumDistance == -1)
							minimumDistance = Math.abs(i - x) + Math.abs(j - y);
						else {
							int distance = Math.abs(i - x) + Math.abs(j - y);
							if (distance < minimumDistance)
								minimumDistance = distance;
						}

		} else {
			for (int i = 0; i < grid.length; i++)
				for (int j = 0; j < grid[i].length; j++)
					if (grid[x][y].type == "DragonStone")
						minimumDistance = Math.abs(i - x) + Math.abs(j - y);
		}
		return minimumDistance;

	}
}
