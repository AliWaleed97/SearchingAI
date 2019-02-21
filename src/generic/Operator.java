package generic;

public abstract class Operator {


	public String name;
	public int cost;


	public Operator(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	public abstract Node applyOperator(Node node);

}