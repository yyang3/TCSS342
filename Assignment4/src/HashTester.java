/**
 * 
 * 
 * my hash function only reads 6 characters from the string instead of n characters
 * thus it is better than O(log(n)). I provided 3 hash functions and 2 of them are 
 * in O(1) and one of them are in O(logn). In the assignment it says that it needs to
 * be better than O(log(n)) so I am now using one of the O(n) function. All three of my
 * hash function all work well when the load is small and the words are different from
 * each other. With heavy load it will gets closer to the oridinary hash function and 
 * the collision will increase dramatically. Also I am now starting from size 5 and counting
 * all the collision including rehashing. The collision also depends on size of the hash table
 * so it will affects the runtime of the hashtable function a lot.
 * 
 * 
 * Tcss 342
 * @author Yicong Yang
 */
public class HashTester {
	/**
	 * main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		HashDict a = new HashDict();
		a.output();//for whole hash table
	}

}
