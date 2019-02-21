package generic;

import java.util.ArrayList;

import saveWesteros.westerosProblem;
import saveWesteros.westerosState;
import strategies.Strategy;

public class GeneralSearch {

	public static void search(Problem problem, Strategy strategy, boolean visualize) {

		State initState = problem.getInitialState();

		strategy.makeQueue(new Node(initState, null, null));
		int expandedNodes = 0;
		while (!strategy.isEmpty()) {
			Node node = strategy.removeFront();
			expandedNodes++;
			if (problem.testGoal(node.state)) {
				System.out.println("The Agent Won");
				if (visualize) {
					printOperators(node);
					System.out.println();
					System.out.println("Number of Expanded nodes = " + expandedNodes);
					System.out.println("The Path length = " + node.depth);
					System.out.println("The Path Cost = " + node.pathCost);
					System.out.println((((westerosState) node.state).WWPositionsKilled.toString()));
				}
				return;
			}
			ArrayList<Node> expanded = expand(node, problem.operators);
			strategy.add(expanded);
		}
		System.out.println("The Agent Lost");
	}

	public static ArrayList<Node> expand(Node node, ArrayList<Operator> operators) {
		ArrayList<Node> expandedNodes = new ArrayList<Node>();
		for (int i = 0; i < operators.size(); i++) {
			Node nxtNode = operators.get(i).applyOperator(node);

			if (nxtNode != null) {
				expandedNodes.add(nxtNode);
			}
		}
		return expandedNodes;
	}

	public static void printOperators(Node node) {
		if (node.parent != null) {
			printOperators(node.parent);
			System.out.println("Operator: " + node.operator.name
					+ "\t\t Position:("+((westerosState) node.state).x 
					+", "+(((westerosState) node.state).y) +")");
			System.out.println(node.state.toString());
		}
	}
}