/**
 * Tcss 342
 * @author Yicong Yang
 */
public class Dict {
	/**
	 * the field that stores the key
	 */
	private String myKey;
	/**
	 * the field that stores the original string
	 */
	private String myValue;
	/**
	 * constructor accepting the original string and generate the key
	 */
	public Dict(String myValue) {
		this.myValue = myValue;
		char [] achar = myValue.toCharArray();
		for (int i = 0; i < myValue.length(); i ++) {//bubble sorting to build the key
			for (int j = myValue.length() - 1; j > i; j --) {
				if (achar[j] < achar[j -1]) {
					char t = achar[j];
					achar[j] = achar[j-1];
					achar[j - 1] = t;
				}
			}
		}
		myKey = String.valueOf(achar);
	}
	/**
	 * method to return the key of the string
	 * @return the key of the string
	 */
	public String getMyKey() {
		return myKey;
	}
	/**
	 * method to return the the string
	 * @return the string
	 */
	public String getMyValue() {
		return myValue;
	}
	/**
	 * {@inheritDoc} compare the strings to see if they are equal even under the same key
	 * 
	 * @return true if the strings are the same.
	 */
	@Override
	public boolean equals(Object a) {
		return ((Dict)a).getMyValue().equals(myValue);
	}
	/**
	 * {@inheritDoc} build in O(1) to scan 10 characters
	 * 
	 * @return the hashcode for my key
	 */
	@Override
	public int hashCode() {
		int re = 1;
//		for(int i = 1; i < myKey.length() ; i = i * 2) {//O(logn)case
//			re = myKey.charAt(i) * (myKey.length() - i) * 6421   + re * i * 6421;
//			re = myKey.charAt(myKey.length() - i) * (myKey.length() - i) * 6421 + re * i * 6421;
//		}
		for(int i = 0; i < 4; i++) {//O(5) = O(1) case which has similar collision level as log(n);
			re = (int) (myKey.charAt((i) % myKey.length()) * Math.pow(10, i)   + re); 
		}
//		int choice = 0;
//		for(int i = 0; i < 5; i++) {//O(6) = O(1) case which has similar collision level as log(n);
//			re = (int) (myKey.charAt(choice % myKey.length()) * Math.pow(5, i)   + re);
//			choice = myKey.charAt(choice % myKey.length());
//		}
		return Math.abs(re);
		
	}
	/**
	 * to string method for testing
	 */
	public String toString() {
		return "[Key " + myKey + " Value " + myValue + "]";
	}
	

}
