//package a3;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Queue;
//import java.util.Set;
//
//public class Graph implements A3Graph {
//
//	ArrayList<Vertex> vertices = new ArrayList<>();
//	
//	ArrayList<Integer> dfsList = new ArrayList<>();
//	ArrayList<Integer> bfsList = new ArrayList<>();
//
//	@Override
//	public void addNode(int nodeItem) {
//		vertices.add(new Vertex(nodeItem));
//	}
//
//	@Override
//	public void addEdge(int srcNodeItem, int tgtNodeItem) {
//		Vertex src = null, tgt = null;
//
//		// find srcNodeItem
//		for(int i = 0; i <= vertices.size(); i++) {
//			if(vertices.get(i).data == srcNodeItem)
//				src = vertices.get(i);
//			else if(vertices.get(i).data == tgtNodeItem)
//				tgt = vertices.get(i);
//
//			if(src != null && tgt != null) // found both Vertices
//				break;
//		}
//
//		src.adj.add(tgt);
//	}
//
//	@Override
//	public boolean hasNode(int nodeItem) {
//		for(int i = 0; i <= vertices.size(); i++) {
//			if(vertices.get(i).data == nodeItem)
//				return true;
//		}
//		
//		return false;
//	}
//
//	@Override
//	public boolean hasEdge(int srcNodeItem, int tgtNodeItem) {
//		Vertex src = null, tgt = null;
//
//		for(int i = 0; i <= vertices.size(); i++) {
//			if(vertices.get(i).data == srcNodeItem)
//				src = vertices.get(i);
//			if(vertices.get(i).data == tgtNodeItem)
//				tgt = vertices.get(i);
//
//			if(src != null && tgt != null) // found both Vertices
//				break;
//		}
//
//
//		return src.adj.contains(tgt);
//	}
//
//	@Override
//	public void printAllNodes() {
//		for(Iterator<Vertex> i = vertices.iterator(); i.hasNext();)
//			System.out.println(i.next().data);
//	}
//
//	@Override
//	public void printAllEdges() {
//		for(Iterator<Vertex> i = vertices.iterator(); i.hasNext();) {
//			Vertex v = i.next();
//			System.out.print(v.data + ": ");
//			for(Vertex edge : v.adj)
//				System.out.print(edge.data + " ");
//			System.out.println();
//		}
//	}
//	
//	public void resetVertices() {
//		for(Vertex v : vertices)
//			v.visited = false;
//	}
//	
//	public void dfs(Vertex v) {
//		v.visited = false;
//		dfsList.add(v.data);
//		for(Vertex w : v.adj) {
//			if(!w.visited)
//				dfs(w);
//		}
//	}
//
//	@Override
//	public List<Integer> visitDFS() {
//		return dfsList;
//	}
//	
//	public void bfs(Vertex v) {
//		Vertex u;
//		resetVertices();
//		
//		Queue<Vertex> q = new LinkedList<Vertex>();
//		bfsList.add(v.data);
//		v.visited = true;
//		q.add(v);
//		
//		while(!q.isEmpty()) {
//			u = q.remove();
//			for(Vertex w : u.adj) {
//				if(!w.visited) {
//					bfsList.add(w.data);
//					w.visited = true;
//					q.add(w);
//				}
//			}
//		}
//	}
//
//	@Override
//	public List<Integer> visitBFS() {
//		return bfsList;
//	}
//
//	@Override
//	public boolean hasSelfLoops() {
//		Set<Integer> set = new HashSet<Integer>();
//		for(Vertex v : vertices) {
//			set.add(v.data);
//			for(int i = 0; i <= v.adj.size(); i++) {
//				if(!set.add(v.adj.get(i).data))
//					return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public boolean isConnected() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean hasTwoCycles() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean hasCycles() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Map<Integer, List<Integer>> feedbackEdges() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
//
////class Vertex {
////
////	public LinkedList<Vertex> adj; // Adjacency list to hold edges
////	
////	public boolean visited;
////	
////	public int data;
////
////	public Vertex(int nodeItem) {
////		data = nodeItem;
////		adj = new LinkedList<Vertex>();
////		visited = false;
////	}
////
////}