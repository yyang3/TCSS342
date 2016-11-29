import java.io.UnsupportedEncodingException;


/**
 * Tcss 342
 * @author Yicong Yang
 * Tester class  used for creating and testing the function
 * of the huffman tree. 
 */
public class Tester {
	/**
	 * main method
	 * @param args the args from JVM
	 */
	public static void main(String args[]) {
		String test = "My Test works totally fine";// THE TESTING STRING!!!
		System.out.println("Testing String: " + test);
		int size = 0;
		try {
			size = test.getBytes("utf8").length * Byte.SIZE;//SIZE OF THE STRING IN BIT			
		} catch (UnsupportedEncodingException e) {
			System.out.println("Not proper encoding...");
		}
		HuffmanFrequencyTable test2 = new HuffmanFrequencyTable(test);//BUILD HUFFMAN TABLE
		HuffmanTree test3 = new HuffmanTree(test2.getTable());// BUILD HUFFMAN TREE
		Encoder tst4 = new Encoder(test3, test2.getTable());// ENCODING
		String test5 = tst4.result(test);//ENCODING RESULT
		tst4.print();//PRINT FREQUENCY TABLE WITH CODE FOR EACH CHARACTER
		int sizeb = test5.length();//SIZE IN BIT OF ENCODED STRING
		System.out.println("Encoded bit stream:");
		System.out.println(test5);
		System.out.println("Total number of bits without Huffman coding: " + size);
		System.out.println("Total number of bits with Huffman coding: " + sizeb);
		System.out.println("Decoded String: " + Decoder.decode(test5, test3));
		
	}

}
