package a3;

import java.util.Iterator;
import java.util.List;

public class TestMain {

	public static void main(String[] args) {
		
		MyGraph g = new MyGraph();
		
		g.addNode(1);
		g.addNode(999);
		g.addNode(12);
		g.addNode(13);
		g.addNode(2);
		
		g.addEdge(1, 999);
		g.addEdge(12, 999);
		g.addEdge(999, 2);
		
		g.addEdge(999, 999);
		
		// introduce cycle
		g.addEdge(999, 1);
		
		if(g.hasNode(999))
			System.out.println("Node 999 exists");
		
		if(g.hasEdge(1, 999))
			System.out.println("Edge exists between 1 -- 999");
		
		System.out.println("\nprintAllNodes:");
		g.printAllNodes();
		System.out.println();
		
		System.out.println("\nprintAllEdges:");
		g.printAllEdges();
		
		System.out.println("\nvisitDFS:");
		List<Integer> dfs = g.visitDFS(1);
		for(Integer i : dfs)
			System.out.println(i);
		
		System.out.println("\nvisitBFS:");
		List<Integer> bfs = g.visitBFS(1);
		for(Integer i : bfs)
			System.out.println(i);
		
		
		System.out.println();
		
		if(g.hasSelfLoops())
			System.out.println("SELF LOOP");
		else
			System.out.println("NO SELF LOOP");
		
		System.out.println();
		
		if(g.isConnected())
			System.out.println("Connected!");
		else
			System.out.println("Not Connected!");
		
		if(g.hasCycles()) 
			System.out.println("\nCYCLE FOUND");
		else
			System.out.println("\nNO CYCLE FOUND");

	}

}
