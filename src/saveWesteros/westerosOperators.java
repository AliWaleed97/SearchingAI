package saveWesteros;

import java.util.*;
import generic.Node;
import generic.Operator;
import generic.State;

public class westerosOperators extends Operator {

	int changeInX;
	int changeInY;

	public westerosOperators(int changeInX, int changeInY, String name, int cost) {
		super(name, cost);
		this.changeInX = changeInX;
		this.changeInY = changeInY;
	}

	@Override
	public Node applyOperator(Node node) {

		westerosState state = (westerosState) node.state;
		Cell[][] grid = state.grid;
		boolean killedWW = false;
		int originalX = state.x;
		int originalY = state.y;
		int newX = originalX + changeInX;
		int newY = originalY + changeInY;
		int dragonGlass = state.dragonGlassCount;
		ArrayList<String> neighbours = new ArrayList<>();
		ArrayList<String> wwPositionsKilled = (ArrayList<String>) state.WWPositionsKilled.clone();


		if (!WithinBoundaries(newX, newY))
			return null;

		String cellType = grid[newX][newY].type;

		// 3. can't go into an obstacle
		if (cellType == "Obstacle")
			return null;
		if (cellType == "WhiteWalker")
			return null;

		if (state.task == "Refill") {
			if (cellType == "DragonStone") {
				dragonGlass = 3;
				State newState = new westerosState(newX, newY, grid, dragonGlass, wwPositionsKilled);
				wwPositionsKilled.add("Collected DragonGlass " + (newX) + "  " + newY );

				Node newNode = new Node(newState, node, this);
				return newNode;
			}
		}

		int n = grid.length;
		int m = grid[0].length;
		Cell[][] newGrid = new Cell[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				newGrid[i][j] = new Cell(state.grid[i][j].type);
			}

		newGrid[originalX][originalY].type = "Free";
		if(cellType == "DragonStone")
			newGrid[newX][newY].type = "DragonStone";
		else
			newGrid[newX][newY].type = "Jon";
		
		if (this.name == "Kill") {
			if (dragonGlass > 0) {
				if (state.whiteWalkersNeighbours()) {
					neighbours = (ArrayList<String>) state.whiteWalkersNeighboursPositions().clone();
					dragonGlass--;

					if (neighbours != null) {
						if (neighbours.contains("North")) {
							newGrid[originalX - 1][originalY].type = "Free";
							wwPositionsKilled.add("Kill North " + (originalX - 1) + "  " + originalY + " DragonGlass:"
									+ dragonGlass  );
						}
						if (neighbours.contains("South")) {
							newGrid[originalX + 1][originalY].type = "Free";
							wwPositionsKilled.add("Kill South " + (originalX + 1) + " " + originalY + " DragonGlass:"
									+ dragonGlass );
						}
						if (neighbours.contains("West")) {
							newGrid[originalX][originalY - 1].type = "Free";
							wwPositionsKilled.add("Kill West " + (originalX) + " " + (originalY - 1) + " DragonGlass:"
									+ dragonGlass );

						}
						if (neighbours.contains("East")) {
							newGrid[originalX][originalY + 1].type = "Free";
							wwPositionsKilled.add("Kill East " + (originalX) + " " + (originalY + 1) + " DragonGlass:"
									+ dragonGlass );

						}
						State newState = new westerosState(newX, newY, newGrid, dragonGlass, wwPositionsKilled);
						Node newNode = new Node(newState, node, this);
						return newNode;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		State newState = new westerosState(newX, newY, newGrid, dragonGlass, wwPositionsKilled);
		Node newNode = new Node(newState, node, this);
		return newNode;
	}

	public boolean WithinBoundaries(int x, int y) {
		if (x <= 3 && y <= 3 && x >= 0 && y >= 0) {
			return true;
		}
		return false;
	}

}