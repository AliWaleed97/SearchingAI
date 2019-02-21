package saveWesteros;

import java.util.*;
import generic.*;
import strategies.*;


public class westerosSearch {



	public static Strategy getController(String s)
	{
		if(s.equals("BF"))			//Breadth-First Search
			return new BFS();
		if(s.equals("DF"))			//Depth-First Search
			return new DFS();
		if(s.equals("ID"))			//Iterative-Deepening Search
			return new IDS();
		 if(s.equals("UC"))			//Uniform-Cost Search
		 	return new UCS();
		 if(s.equals("GS1"))		//Greedy Search With Heuristic Number 1
			 return new GS1();
		 if(s.equals("GS2"))		//Greedy Search With Heuristic Number 2 
			 return new GS2();
		 if(s.equals("AS1"))		//A*Search With Heuristic Number 1 
			 return new AS1();
		 if(s.equals("AS2"))		//A*Search With Heuristic Number 2 
			 return new AS2();
		return null;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        int n = 4; 
        int m = 4;
		westerosProblem problem =  new westerosProblem(n, m);
		System.out.println("Start of the Round \n");
		System.out.println(problem.getInitialState());
		while(true)
		{
			System.out.println("Enter Strategy");
			String strategy = sc.next();
			if(strategy.equals("Stop"))
				break;
			Strategy controller = getController(strategy);
			GeneralSearch.search(problem, controller, true);
		}
		sc.close();
		
	}

}
