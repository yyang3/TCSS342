/**
 * Decoder class
 * @author Yicong Yang
 *
 */
public class Decoder {
	/**
	 * Static method for decoder.
	 * @param Input the encoded string in '1's and '0's
	 * @param theTree the huffman tree for decoding.
	 * @return the result string after decoding
	 */
	public static String decode(String Input, HuffmanTree theTree) {
		StringBuffer temp = new StringBuffer();
		HuffmanTreeNode scan = theTree.theHead();
		for(int i = 0; i < Input.length(); i ++){
			if(Input.charAt(i) == '1'){
				scan = scan.getLeft();
			} else {
				scan = scan.getRight();
			}
			if(scan.getLetter() != 0){
				temp.append(scan.getLetter());
				scan = theTree.theHead();
			} 
		}
		String re = temp.toString();
		return re;
	}

}
