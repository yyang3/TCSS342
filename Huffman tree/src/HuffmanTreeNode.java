/**
 * HuffmanTreeNode class
 * @author RickYang
 * class to build each nodes
 */
public class HuffmanTreeNode {
	/**
	 * frequency of the character
	 */
	private int freq;
	/**
	 * left node of the current node
	 */
	private HuffmanTreeNode left;
	/**
	 * right node of the current node
	 */
	private HuffmanTreeNode right;
	/**
	 * the character in the node
	 */
	private char letter;
	/**
	 * helper data for checking how the tree is built, not really needed
	 */
	private String cumulative;
	
	/**
	 * constructor to build the internal nodes
	 * @param a left node of the internal node
	 * @param b right node of the internal node
	 */
	public HuffmanTreeNode(HuffmanTreeNode a, HuffmanTreeNode b) {
		freq = a.freq + b.freq;
		letter = 0;
		left = a;
		right = b;
		cumulative = a.cumulative + " + " + b.cumulative;
	}
	/**
	 * constructor to build the leaves
	 * @param theFreq the freq of the character appears
	 * @param theletter the character
	 */
	public HuffmanTreeNode(int theFreq, char theletter) {
		freq = theFreq;
		letter = theletter;
		left = null;
		right = null;
		cumulative = Character.toString(theletter);
	}
	/**
	 * getting method for the frequency
	 * @return the freq of the character
	 */
	public int getFreq() {
		return freq;
	}
	/**
	 * method to get the left node.
	 * @return left node
	 */
	public HuffmanTreeNode getLeft() {
		return left;
	}
	/**
	 * method to get the right node
	 * @return right node
	 */
	public HuffmanTreeNode getRight() {
		return right;
	}
	/**
	 * method to get the character in the node
	 * @return the character in the node
	 */
	public char getLetter() {
		return letter;
	}
	public String toString(){
		String re = "Cumulative: " + cumulative + " freq: " +freq;
		return re;
	}

}
