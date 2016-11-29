/**
 * class for help recording the result for table printing
 * @author Yicong Yang
 *
 */
public class TableItem {
	/**
	 * the letter
	 */
	char key;
	/**
	 * the frequency of the letter
	 */
	int freq;
	/**
	 * the encoded binary representation
	 */
	String Enc;
	/**
	 * constructor for the object
	 * @param theKey character input
	 * @param thefreq frequency input
	 * @param theEnc encodeing input
	 */
	public TableItem(char theKey, int thefreq, String theEnc){
		key = theKey;
		freq = thefreq;
		Enc = theEnc;
	}
	/**
	 * method to return the key
	 * @return character in the object
	 */
	public char getKey() {
		return key;
	}
	/**
	 * method to return the encoded string
	 * @return binary encode in the object
	 */
	public String getEnc(){
		return Enc;
	}
	/**
	 * method to return the freq int
	 * @return frequency of the character appeared
	 */
	public int getfreq() {
		return freq;
	}
	public String toString() {
		return ("Char: " + key + " Freq: " + freq + " Enc: " + Enc + "\n");
	}
}
