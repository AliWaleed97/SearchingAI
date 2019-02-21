package saveWesteros;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import generic.Operator;
import generic.Problem;
import generic.State;

public class westerosProblem extends Problem {

    public int n;
    public int m;
    public Cell[][] grid;
    String[] types = {
        "Free",
        "WhiteWalker",
        "Obstacle"
    };
    public westerosProblem(int N, int M) {

        this.n = N;
        this.m = M;

        this.grid = new Cell[this.n][this.m];
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = new Cell(this.types[(int)(Math.random() * 3)]);
            }
        }
        while (true) {
            int x = (int)(Math.random() * 4);
            int y = (int)(Math.random() * 4);
            if (x != 3 && y != 3) {
                this.grid[x][y].type = "DragonStone";
                break;
            }
        }
        this.grid[3][3].type = "Jon";
        operators = new ArrayList <> ();
        operators.add(new westerosOperators(-1, 0, "North", 1));
        operators.add(new westerosOperators(0, -1, "West", 1));
        operators.add(new westerosOperators(1, 0, "South", 1));
        operators.add(new westerosOperators(0, -1, "East", 1));
        operators.add(new westerosOperators(0, 0, "Kill", 20));
    }

    @Override
    public ArrayList < Operator > getOperators() {
        return operators;
    }
    @Override
    public State getInitialState() {

        return new westerosState(n - 1, m - 1, grid,3 );
    }

    @Override
    public boolean testGoal(State state) {

        westerosState testState = (westerosState)state;
        if(testState.whiteWalkersCount==0){
            return true;
        }
        return false;
    }
    public String GridtoString() {
        String count = "";
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
            	if(this.grid[i][j].type == "Free")
            		count += this.grid[i][j].type+"\t\t";
            	else
            		count += this.grid[i][j].type+"\t";
            }
            count += "\n";
        }
        return count;
    }

}