import java.util.HashMap;
import java.util.Map;
/**
 * HuffmanFrequencyTable class
 * @author Yicong Yang
 * class for creating the frequency table.
 */
public class HuffmanFrequencyTable {
	/**
	 * the map for recording the key and the frequency.
	 */
	private Map<Character, Integer> HuffColl; 
	/**
	 * constructor of the freq table
	 * @param theInput the input string
	 */
	public HuffmanFrequencyTable(String theInput){
		HuffColl = new HashMap<Character, Integer>();
		checkandadd(theInput);
	}
	/**
	 * add each character into the freq table
	 * @param theInput
	 */
	private final void checkandadd(String theInput){
		if(theInput.length() < 2) {
			System.out.println("Length of input is less than 2.");
		} else {
			for(int i = 0; i < theInput.length(); i++) {
				char temp = theInput.charAt(i);
				if(HuffColl.containsKey(theInput.charAt(i))){
					HuffColl.put(temp, HuffColl.get(temp) + 1);
				} else {
					HuffColl.put(temp, 1);
				}
			}
		}	
	}
	/**
	 * return the map object recording the table.
	 * @return the map represents the table.
	 */
	public Map<Character, Integer> getTable(){
		return HuffColl;
	}
}
