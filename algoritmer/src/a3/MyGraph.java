/* From the visionary mind that brought you
 * Assignment 1 & Assignment 2, comes now the nail biting
 * final conclusion to the 1DV016 Assignment saga.
 * 
 * ma222ce presents:
 * 		ASSIGNMENT 3
 */
package a3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.plaf.ListUI;

public class MyGraph implements A3Graph {
	
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	
	ArrayList<Integer> dfsList = new ArrayList<Integer>();
	ArrayList<Integer> bfsList = new ArrayList<Integer>();
	
	boolean cycle = false, selfloop = false;
	int cnt = 0;

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
	
	public void clear() {
		for(Vertex v : vertices)
			v.visited = false;
		cnt = 0;
	}

	@Override
	public void addEdge(int srcNodeItem, int tgtNodeItem) {
		if(srcNodeItem == tgtNodeItem) {
			Vertex s = findNode(srcNodeItem);
			s.edges.add(s);
			selfloop = true;
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
		clear();
		
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
		return selfloop;
	}
	
	public void connectivity(Vertex s) {
		s.visited = true;
		cnt++;
		System.out.println("Node: " + s.nodeItem + " cnt: " + cnt);
		for(Vertex v : s.edges) {
			if(!v.visited)
				connectivity(v);
		}
	}

	@Override
	public boolean isConnected() {
		clear();
		connectivity(vertices.get(0));
		return cnt == vertices.size();
	}

	@Override
	public boolean hasTwoCycles() {
		for(Vertex a : vertices) {
			for(Vertex b : a.edges) {
				if(b.edges.contains(a))
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasCycles() {
		cycle = false;
		clear();
		for(Vertex v : vertices) {
			dfs(v);
		}
		return cycle;
	}

	@Override
	public Map<Integer, List<Integer>> feedbackEdges() {
		List<Integer> sources = new ArrayList<Integer>();
		List<Integer> sinks = new ArrayList<Integer>();
		
		if(hasCycles() && hasTwoCycles() && !isConnected())
			return null;
		
		for(Vertex v : vertices) {
			for(Vertex e : v.edges) {
				sinks.add(0, e.nodeItem); // prepend
				v.edges.remove(e);
			}
			sources.add(v.nodeItem); // append
			vertices.remove(v);
		}
		
		List<Integer> list = new ArrayList<Integer>(sources);
		list.addAll(sinks);
		
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		
		return null;
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
