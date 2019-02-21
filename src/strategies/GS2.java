package strategies;

import java.util.ArrayList;
import java.util.LinkedList;

import generic.Node;
import saveWesteros.Cell;
import saveWesteros.westerosState;

public class GS2 extends Strategy {

	LinkedList<Node> queue = new LinkedList<>();

	public GS2() {
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
						if ((heuristicFunction2(node)) < (heuristicFunction2(element)))
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
		westerosState parentState = null;
		Cell[][] grid = state.grid;
		int x = state.x;
		int y = state.y;
		int nearestDistanceToGoal = -1;

		if (node.parent != null) {
			parentState = (westerosState) node.parent.state;
		}

		if (grid[x][y].type == "DragonStone") {
			if (state.dragonGlassCount > parentState.dragonGlassCount)
				return 0;
//			System.out.println(x + " " + y);
//			System.out.println(((State) this.getParentNode().getState()).getX() + " "
//					+ ((State) this.getParentNode().getState()).getY());
		}

		if (parentState != null && state.whiteWalkersCount < parentState.whiteWalkersCount) {
			return 0;
		}

		if (state.dragonGlassCount == 0) {
//			System.out.println(this);
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[x][y].type == "DragonStone") {
						nearestDistanceToGoal = Math.abs(i - x) + Math.abs(j - y);
					}
				}
			}
//			System.out.println(nearestDistanceToGoal);
		} else {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[x][y].type == "WhiteWalker") {
						if (nearestDistanceToGoal == -1) {
							nearestDistanceToGoal = Math.abs(i - x) + Math.abs(j - y);
//							System.out.print(Integer.toString(nearestDistanceToGoal));
						} else {
							int distance = Math.abs(i - x) + Math.abs(j - y);
//							System.out.print(Integer.toString(distance));
							if (distance < nearestDistanceToGoal) {
//								System.out.println(
//										Integer.toString(distance) + " " + Integer.toString(nearestDistanceToGoal));
								nearestDistanceToGoal = distance;
							}
						}
					}
				}
			}
//			System.out.println(Integer.toString(nearestDistanceToGoal));
		}
//		System.out.println(x + " " + y);
//		System.out.println(((State) this.getParentNode().getState()).getX() + " "
//				+ ((State) this.getParentNode().getState()).getY());
		return nearestDistanceToGoal;

	}
}
