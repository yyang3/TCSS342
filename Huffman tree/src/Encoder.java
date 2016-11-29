import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Encoder class
 * @author Yicong Yang
 * Used for encoding the input string.
 */
public class Encoder {
	/**
	 * the head node of the tree for encoding.
	 */
	private HuffmanTreeNode myTree;
	/**
	 * the list to record the result.
	 */
	private List<TableItem> result;
	/**
	 * constructor for encoder
	 * @param theTree the Huffman Tree
	 * @param theInput the Frequency table for create the table output.
	 */
	public Encoder(HuffmanTree theTree, Map<Character, Integer> theInput){
		myTree = theTree.theHead();
		result = new ArrayList<>();
		encode(theInput);
	}
	
	/**
	 * Helper method for encoder to call for encoding.
	 * @param theInput the map records all the characters appeared.
	 */
	private final void encode(Map<Character, Integer> theInput) {
		for (Entry<Character, Integer> entry : 
			theInput.entrySet()) {//creating Eob object for recording the results
		    char key = entry.getKey();
		    int value = entry.getValue();
		    String temp = traverse(key, myTree);// method for encoding a letter
		    result.add(new TableItem(key, value, temp));// add the encoded result to the result arraylist.
		}
	}
	/**
	 * helper method for encoding a letter
	 * @param e the character for encoding
	 * @param a the starting node of the tree.
	 * @return
	 */
	private String traverse(char e, HuffmanTreeNode a) {
		String L = traversedetail(e, a.getLeft(), String.valueOf(1));
		String R = traversedetail(e, a.getRight(), String.valueOf(0));
		if(L == null) {
			return R;
		} else {
			return L;
		}
	}
	/**
	 * helper method for encoding a letter after the first node.
	 * @param e the character for encoding
	 * @param a the node currently at
	 * @param encode the string result.
	 * @return
	 */
	private String traversedetail(char e, HuffmanTreeNode a, String encode){
		if(a == null){
			return null;
		} else if(a.getLetter() == e) {
			return encode;
		} else {
			String L = traversedetail(e, a.getLeft(), encode + String.valueOf(1));
			String R = traversedetail(e, a.getRight(), encode + String.valueOf(0));
			if(L == null) {
				return R;
			} else {
				return L;
			}
		}
	}
	/**
	 * method to encode the whole string comparing to the result arrayList.
	 * @param Input the string to be encoded
	 * @return the binary number of the encoded string.
	 */
	public String result(String Input){
		String re = null;
		Map<Character, String> temp = new HashMap<>();
		for(int i = 0; i < result.size(); i++) {
			temp.put(result.get(i).getKey(), result.get(i).getEnc());
		}
		re = temp.get(Input.charAt(0));
		for(int i = 1; i < Input.length(); i++) {
			re = re + temp.get(Input.charAt(i));
		}
		return re;
	}
	/**
	 * method to printout the frequency table with encoded result.
	 */
	public void print() {
		for(int i = 0; i < 50; i ++) {
			System.out.print("=");
		}
		System.out.println("\nChar \t\tfrequency \t\tcode");
		for(int i = 0; i < 50; i ++) {
			System.out.print("-");
		}
		for(int i = 0; i < result.size(); i ++) {
			System.out.printf("\n%c\t\t%d\t\t\t%s", result.get(i).getKey(), 
					result.get(i).getfreq(), result.get(i).getEnc());
		}
		System.out.println();
		for(int i = 0; i < 50; i ++) {
			System.out.print("=");
		}
		System.out.println();
	}
	

}

