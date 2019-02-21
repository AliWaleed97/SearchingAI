package generic;

import java.util.ArrayList;

import saveWesteros.westerosProblem;

public abstract class Problem {

	public ArrayList < Operator > operators;

	public abstract ArrayList < Operator > getOperators();

	public abstract State getInitialState();

	public abstract boolean testGoal(State state);

	public abstract String GridtoString();
}