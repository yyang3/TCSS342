
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * HuffmanTree class
 * @author RickYang
 * Class to build the tree from the freq table.
 */
public class HuffmanTree {
	/**
	 * the map recorded the freq
	 */
	private Map<Character, Integer> mytable;
	/**
	 * the head node.
	 */
	private HuffmanTreeNode myhead;
	/**
	 * the priority queue for getting the right node for the tree
	 */
	private Queue<HuffmanTreeNode> collect;
	/**
	 * the constructor of the tree
	 * @param thetable the map recorded the frequency table
	 */
	public HuffmanTree( Map<Character, Integer> thetable) {
		mytable = thetable;
		Comparator<HuffmanTreeNode> fornode = new Comparator<HuffmanTreeNode>(){
			@Override
			public int compare(HuffmanTreeNode arg0, HuffmanTreeNode arg1) {
				int temp = 0;
				if(arg0.getFreq() > arg1.getFreq()){
					temp = 1;
				} else if (arg0.getFreq() < arg1.getFreq()){
					temp = -1;
				}
				return temp;
			}			
		};
		collect = new PriorityQueue<>(3, fornode);
		load();	
	}
	/**
	 *  method to return the head node
	 * @return the head node
	 */
	public HuffmanTreeNode theHead() {
		return myhead;
	}
	/**
	 * helping method for building the tree.
	 */
	private void load(){
		for (Entry<Character, Integer> entry : mytable.entrySet()) {//building the leaf
		    char key = entry.getKey();
		    int value = entry.getValue();
		    collect.add(new HuffmanTreeNode(value, key));
		}
		while(collect.size() > 1) {//poll 2 leaf from the queue to build a node and put it back
			//the queue is heap sorted based on the frequency.
			HuffmanTreeNode temp1 = collect.poll();
			HuffmanTreeNode temp2 = collect.poll();
			collect.add(new HuffmanTreeNode(temp1, temp2));	
		}
		myhead = collect.peek();
	}
	

}
