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
		
		if(g.hasNode(999))
			System.out.println("Node 999 exists");
		
		if(g.hasEdge(1, 999))
			System.out.println("Edge exists between 1 -- 999");
		
		g.printAllNodes();
		System.out.println();
		
		g.printAllEdges();
		
		System.out.println("visitDFS:");
		List<Integer> dfs = g.visitDFS(g.vertices.get(0));
		for(Integer i : dfs)
			System.out.println(i);
		
		System.out.println("visitBFS:");
		List<Integer> bfs = g.visitBFS(g.vertices.get(0));
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

	}

}
