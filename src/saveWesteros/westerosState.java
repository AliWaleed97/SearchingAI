package saveWesteros;

import java.util.*;
import generic.State;

public class westerosState extends State {
    public int x;
    public int y;
    public Cell[][] grid;
    public int whiteWalkersCount;
    public int dragonGlassCount;
    public String task;
    public ArrayList<String> WWPositionsKilled;

    public westerosState(int x, int y, Cell[][] grid,int dragonGlassCount) {
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.dragonGlassCount = dragonGlassCount;
        this.WWPositionsKilled = new ArrayList<String>();
        for (Cell[] row: grid)
            for (Cell element: row)
                if (element.type == "WhiteWalker")
                    this.whiteWalkersCount++;
        if(this.dragonGlassCount<=0)
            this.task = "Refill";
        else
            this.task="Kill";
    }
    public westerosState(int x, int y, Cell[][] grid,int dragonGlassCount, ArrayList<String> WWPositionsKilled) {
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.dragonGlassCount = dragonGlassCount;
        this.WWPositionsKilled = WWPositionsKilled;
        for (Cell[] row: grid)
            for (Cell element: row)
                if (element.type == "WhiteWalker")
                    this.whiteWalkersCount++;
        if(this.dragonGlassCount<=0)
            this.task = "Refill";
        else
            this.task="Kill";
    }


    public Cell North() {

        return this.grid[this.x - 1][this.y];
    }

    public Cell South() {

        return this.grid[this.x + 1][this.y];
    }

    public Cell West() {

        return this.grid[this.x][this.y - 1];
    }

    public Cell East() {

        return this.grid[this.x][this.y + 1];
    }

    public ArrayList <String> whiteWalkersNeighboursPositions() {
        ArrayList <String> positions = new ArrayList <String> ();
        if (this.WithinBoundaries(this.x - 1, this.y)) {
            if (this.North().type == "WhiteWalker")
                positions.add("North");
        }
        if (this.WithinBoundaries(this.x + 1, this.y)) {
            if (this.South().type == "WhiteWalker")
                positions.add("South");
        }
        if (this.WithinBoundaries(this.x, this.y - 1)) {
            if (this.West().type == "WhiteWalker")
                positions.add("West");
        }
        if (this.WithinBoundaries(this.x, this.y + 1)) {
            if (this.East().type == "WhiteWalker")
                positions.add("East");
        }

        return positions;
    }
    public boolean whiteWalkersNeighbours() {
        if (this.WithinBoundaries(this.x - 1, this.y)) {
            if (this.North().type == "WhiteWalker")
                return true;
        }
        if (this.WithinBoundaries(this.x + 1, this.y)) {
            if (this.South().type == "WhiteWalker")
                return true;
        }
        if (this.WithinBoundaries(this.x, this.y - 1)) {
            if (this.West().type == "WhiteWalker")
                return true;
        }
        if (this.WithinBoundaries(this.x, this.y + 1)) {
            if (this.East().type == "WhiteWalker")
                return true;
        }
        return false;

    }

    public boolean WithinBoundaries(int x, int y) {

        if (x < grid.length && y < grid.length && x >= 0 && y >= 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        String count = "";
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
            	if(this.grid[i][j].type == "Free" || this.grid[i][j].type == "Jon")
            		count += this.grid[i][j].type+"\t\t";
            	else
            		count += this.grid[i][j].type+"\t";
            }
            count += "\n";
        }
        return count;
    }
    public String elementsToString() {
		return "{x: "+ this.x+",y: "+this.y+",dg: "+this.dragonGlassCount+",ww: "+this.whiteWalkersCount+this.toString();
    	
    }

}