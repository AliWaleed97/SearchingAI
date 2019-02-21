package generic;

public class Node {

	public State state;
	public Node parent;
	public Operator operator;
	public int pathCost;
	public int depth;

	public Node(State state, Node parent, Operator operator) {
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		if (parent != null) {
			this.depth = parent.depth + 1;
			this.pathCost = parent.pathCost + operator.cost;
		}

	}

	public void setPathCost(int newCost) {
		pathCost = newCost;
	}
}