import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;


public class SAP {
	private Map<Integer, ArrayList<Integer>> myMap;
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Map<Integer, ArrayList<Integer>> a) {
		boolean temp = true;
		for(Integer b : a.keySet()) {
			temp = temp && (a.get(b).size() != 1);
		}
		if (temp) {
			throw new java.lang.IllegalArgumentException("Not a rooted DAG");
		} else {
			myMap = a;
		}
	}

	// length of ancestral path between v and w; -1 if no such path
	public int length(int v, int w) { 
		int re = ancestor(v, w);
		if (re == -1) {
			return -1;
		} else {
			return l(v,re) + l(w, re);
		}
	}
	//find the distance from the vertice to its ancestor.
	private int l (int v, int w) {
		Queue<Integer> List = new LinkedBlockingQueue<>();
		Queue<Integer> Len = new LinkedBlockingQueue<>();
		int length = queP(0, v, w, List, Len);
		return length;
	}
	/**
	 * method to use bfs to find the shortest path between v & w
	 * @param u distance count
	 * @param v vertice from 
	 * @param w vertice to 
	 * @param h queue to do bfs for vertices
	 * @param o queue to record the distance
	 * @return shortest distance
	 */
	private final int queP(int u, int v, int w,  
			Queue<Integer> h, Queue<Integer> o) {
		int re = -1;
		u = u + 1;
		ArrayList<Integer> temp = myMap.get(v);
		if (temp != null) {
			for(int i = 0; i < temp.size(); i ++) {
				if (temp.get(i) != v) {
					h.add(temp.get(i));
					o.add(u);
				}
			}
			while (!h.isEmpty() && re == -1) {
				int lent = o.poll();
				int comp = h.poll();
				if (comp != w) {
					re = queP(lent, comp, w, h, o);
				} else {
					re = lent;
				}
			}
		}
		return re;

	}

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w) {
		Stack<Integer> checkv = new Stack<>();
		Stack<Integer> checkw = new Stack<>();
		ArrayList<Integer> vDepthFirst = new ArrayList<>();
		ArrayList<Integer> wDepthFirst = new ArrayList<>();
		stackP(v, checkv, vDepthFirst);
		stackP(w, checkw, wDepthFirst);
		ArrayList <Integer> ancestors = new ArrayList<>();
		int re = -1;
		for (int i = 0; i < vDepthFirst.size(); i ++) {
			for (int j = 0; j < wDepthFirst.size(); j ++) {
				if (vDepthFirst.get(i).equals(wDepthFirst.get(j)) 
						&& !ancestors.contains(vDepthFirst.get(i))) {
					ancestors.add(vDepthFirst.get(i));
				}
			}
		}
		int min = 10000;
		for (int i = 0; i < ancestors.size(); i ++) {
			int temp = l(v, ancestors.get(i)) + l(w, ancestors.get(i));
			if(min > temp) {
				min = temp;
				re = ancestors.get(i);
			}
		}
		if (min == 10000) {
			re = -1;
		}		
		return re;
	}
	/**
	 * use dfs to list all the ancestors of index v
	 * @param v index
	 * @param w stack to use
	 * @param u list for recording
	 * @return the list for recording
	 */
	private ArrayList<Integer> stackP(int v, Stack<Integer> w, ArrayList<Integer> u) {
		ArrayList<Integer> vList = myMap.get(v);
		if (vList != null) {
			for(int i = 0; i < vList.size(); i ++) {
				if (vList.get(i) != v) {
					w.push(vList.get(i));
				}
			}
			while (!w.isEmpty()) {
				int t = w.pop();
				u.add(t);
				if (myMap.get(t).size() > 1) {
					stackP(t, w, u);
				} 
			}
		}
		return u;

	}

}
