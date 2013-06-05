/* From the visionary mind that brought you
 * Assignment 1 & Assignment 2, comes now the nail biting
 * final conclusion to the 1DV016 Assignment saga.
 */
package a3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MyGraph implements A3Graph {
	
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	
	ArrayList<Integer> dfsList = new ArrayList<Integer>();
	ArrayList<Integer> bfsList = new ArrayList<Integer>();
	
	boolean cycle = false;

	
	Vertex src, tgt;

	@Override
	public void addNode(int nodeItem) {
		vertices.add(new Vertex(nodeItem));
	}
	
	public Vertex findNode(int nodeItem) {
		Vertex s = null;
		for(Vertex v : vertices) {
			if(v.nodeItem == nodeItem)
				s = v; 
		}
		return s;
	}
	
	public void findNodes(int srcNodeItem, int tgtNodeItem) {
		src = tgt = null;
		
		for(Vertex v : vertices) {
			if(src != null && tgt != null)
				break;
			else if(v.nodeItem == srcNodeItem)
				src = v;
			else if(v.nodeItem == tgtNodeItem)
				tgt = v;
		}
	}
	
	public void clearVisited() {
		for(Vertex v : vertices)
			v.visited = false;
	}

	@Override
	public void addEdge(int srcNodeItem, int tgtNodeItem) {
		if(srcNodeItem == tgtNodeItem) {
			Vertex s = findNode(srcNodeItem);
			s.edges.add(s);
			s.selfLoop = true;
		}
		else {
			findNodes(srcNodeItem, tgtNodeItem);
			src.edges.add(tgt);
		}
	}

	@Override
	public boolean hasNode(int nodeItem) {
		for(Vertex v : vertices) {
			if(v.nodeItem == nodeItem)
				return true;
		}
		return false;
	}

	@Override
	public boolean hasEdge(int srcNodeItem, int tgtNodeItem) {
		findNodes(srcNodeItem, tgtNodeItem);
		return src.edges.contains(tgt);
	}

	@Override
	public void printAllNodes() {
		for(Vertex v : vertices) 
			System.out.println(v.nodeItem);
	}

	@Override
	public void printAllEdges() {
		for(Vertex v : vertices) {
			System.out.print(v.nodeItem + ": ");
			for(Vertex e : v.edges)
				System.out.print(e.nodeItem + " ");
			System.out.println();
		}
	}

	@Override
	public List<Integer> visitDFS(int startNodeItem) {
		dfs(findNode(startNodeItem));
		return dfsList;
	}
	
	public void dfs(Vertex s) {
		s.visited = true;
		dfsList.add(s.nodeItem);
		for(Vertex v : s.edges) {
			if(!v.visited)
				dfs(v);
			else
				cycle = true;
		}
	}
	
	@Override
	public List<Integer> visitBFS(int startNodeItem) {
		clearVisited();
		
		Vertex u;
		
		Queue<Vertex> q = new LinkedList<Vertex>();
		Vertex s = findNode(startNodeItem);
		bfsList.add(s.nodeItem);
		s.visited = true;
		q.add(s);
		
		while(!q.isEmpty()) {
			u = q.remove();
			for(Vertex v : u.edges) {
				if(!v.visited) {
					bfsList.add(v.nodeItem);
					v.visited = true;
					q.add(v);
				}
			}
		}
		return bfsList;
	}

	@Override
	public boolean hasSelfLoops() {
		for(Vertex v : vertices) {
			if(v.selfLoop)
				return true;
		}
//		for(Vertex v : vertices) {
//			int tmp = v.nodeItem;
//			for(Vertex e : v.edges) {
//				if(e.nodeItem == tmp)
//					return true;
//			}
//		}
		return false;
	}

	@Override
	public boolean isConnected() {
		for(Vertex v : vertices) {
			if(v.edges.size() == 0)
				return false;
		}
		return true;
	}

	// is there a path A-B and B-A
	@Override
	public boolean hasTwoCycles() {
		return false;
	}

	@Override
	public boolean hasCycles() {
		cycle = false;
		clearVisited();
		for(Vertex v : vertices) {
			dfs(v);
		}
		return cycle;
	}

	@Override
	public Map<Integer, List<Integer>> feedbackEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Vertex {
	
	public ArrayList<Vertex> edges;
	public boolean visited, selfLoop;
	public int nodeItem;
	
	public Vertex(int nodeItem) {
		this.nodeItem = nodeItem;
		edges = new ArrayList<Vertex>();
		visited = selfLoop = false;
	}
}
