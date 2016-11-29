/**
 * Tcss 342
 * @author Yicong Yang
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class HashDict {
	/**
	 * loading factor for rehashing
	 */
	private static final double LF = 0.8;
	/**
	 * file input.
	 */
	private static final String FILE = "words.txt";
	/**
	 * my own table
	 */
	private List<Dict>[] myTable;
	/**
	 * size of the table
	 */
	private int size;
	/**
	 * number of words load in
	 */
	private int load;
	/**
	 * testing count for total collision in every size before rehashing.
	 */
	private int coll;
	/**
	 * total collision
	 */
	private int totalcoll;
	/**
	 * starting time of running my hash table to load in words
	 */
	private long startmy;
	/**
	 * Starting time of running the original hash table function
	 */
	private long startori;
	/**
	 * ending time of my hashtable loading 
	 */
	private long endmy;
	/**
	 * ending time of original hashtable
	 */
	private long endori;
	/**
	 * testing for original hashmap
	 */
	private HashMap<String, Dict> oriMap;
	/**
	 * constructor with no input
	 */
	public HashDict() {
		size = 50000;
		load = 0;
		coll = 0;
		totalcoll = 0;
		myTable = creatingNewTable(size);
		oriMap = new HashMap<>();
		readinOri();
		readin();
		
	}
	
	//******************************original hash table function**********************************

	/**
	 * method to load words into the original hashtable function
	 */
	private void readinOri() {
		startori = System.currentTimeMillis();
		File in = new File(FILE);
		try {
			Scanner temp = new Scanner(in);
			while (temp.hasNext()) {
				String a = temp.nextLine().toLowerCase();
				Dict tempD = new Dict(a);
				oriMap.put(tempD.getMyKey(), tempD);				
			}
			temp.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endori = System.currentTimeMillis();
		System.out.println("Final Time Result for original hashtable: " + (endori - startori) + " ms");
	}

	//********************************my Hashtable function*********************************************
	/**
	 * method to read in my hashtable function
	 */
	private void readin() {
		startmy = System.currentTimeMillis();
		File in = new File(FILE);
		try {
			Scanner temp = new Scanner(in);
			while (temp.hasNext()) {
				String a = temp.nextLine().toLowerCase();
				Dict tempD = new Dict(a);
				if (((double)load)/size > LF) {
					//System.out.printf("Total collision for size %d: %d load: %d\n",size ,coll ,load);
					rehashing();
					coll = 0;
				}
				if (myTable[tempD.hashCode()%size].size() > 0) {
					coll ++;
					totalcoll ++;
				}
				myTable[tempD.hashCode()%size].add(tempD);
				load ++;
			}
			temp.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endmy = System.currentTimeMillis();
		System.out.println("Final time result for my hash table: " + (endmy - startmy) + " ms");
		System.out.println("Total collision (Encluding all collision in all size and collision in rehashing): "
				+ "" + totalcoll + " load: " + load + " size: " + size);
	}
	/**
	 * help method to create my hash table
	 * @param theSize the size of the table
	 * @return the hashtable structure.
	 */
	private List<Dict>[] creatingNewTable(int theSize) {
		List<Dict>[] temp = (List<Dict>[]) new List[theSize];
		for (int i = 0; i < theSize; i ++) {
			temp[i] = new LinkedList<Dict>();
		}
		return temp;
	}
	/**
	 * help method to rehash the table for larger size
	 */
	private void rehashing() {
		coll = 0;
		size = findNextPrime(2 * size);
		List<Dict>[] temp = creatingNewTable(size);
		for (int i = 0; i < myTable.length; i ++) {
			for (Dict t : myTable[i]) {
				Dict t2 = new Dict(t.getMyValue());
				if (temp[t2.hashCode()%size].size() > 0) {
					coll ++;
					totalcoll ++;
				}
				temp[t.hashCode()%size].add(t2);
			}
		}
		//System.out.printf("\nAfter rehashing Total collision for size %d: %d load: %d\n",size ,coll ,load);
		myTable = temp;
	}
	/**
	 * help method to find the prime number for the size
	 * @param a the original size
	 * @return the new size
	 */
	private int findNextPrime (int a) {
		boolean isPrime = false;
	    for (int i = a; i < 2 * a && !isPrime; i ++ ) {
	    	a = a + 1;
	    	boolean ip = true;
	    	// go with your regular prime checking routine
	    	for (int j = 2; j < a && ip; j++) {
	            if (a % j == 0) {
	                ip = ip && false;
	            } else {
	            	ip = ip && true;
	            }
	        }
	    	isPrime = ip;
	    }
	    return a;
	}
	
//********************************************function testing******************************************
	
	/**
	 * method to test inputing a key and find all the values in the key.
	 * @param get the key string
	 */
	private void get(String get) {
		Dict find = new Dict(get);
		if (myTable[find.hashCode()%size].size() > 0) {
			System.out.print(myTable[find.hashCode()%size].get(0).getMyKey() + " ");
			System.out.print(myTable[find.hashCode()%size].size() + " ");
			for (int i = 0; i < myTable[find.hashCode()%size].size(); i ++) {
				System.out.print(myTable[find.hashCode()%size].get(i).getMyValue() + " ");
			}
			System.out.println();
		} else {
			System.out.println("Key not found!");
		}
		
	}
//	/**
//	 * find a specific string in the table
//	 * @param a the string to find
//	 */
//	private void find(String a) {
//		Dict find = new Dict(a);
//		if(myTable[find.hashCode()%size].contains(find)) {
//			System.out.println("find " + a + " at Key: " 
//					+ myTable[find.hashCode()%size].get(0).getMyKey());
//		} else {
//			System.out.println("Word " + a + " not found!");
//		}
//	}
	/**
	 * method to print out the hash table
	 */
	public void output() {
		String filename = "Output.txt";
		String fin = "test.txt";
		ArrayList<String> theList = new ArrayList<>();
		File in = new File(fin);
		try {
			Scanner temp = new Scanner(in);
			while (temp.hasNext()) {
				String a = temp.nextLine().toLowerCase();
				theList.add(a);
			}
			temp.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PrintWriter outputstream = new PrintWriter(filename);
			for (int j = 0; j < theList.size(); j ++) {
				Dict find = new Dict(theList.get(j));
				outputstream.printf("%s %d", theList.get(j), myTable[find.hashCode()%size].size());
				for (int i = 0; i < myTable[find.hashCode()%size].size(); i ++) {
					outputstream.printf(" %s",myTable[find.hashCode()%size].get(i).getMyValue());
				}
				outputstream.println();
//				if (myTable[find.hashCode()%size].size() > 0) {
//					
//				} else {
//					System.out.println("Key " + theList.get(j) + " not found!");
//				}
			}
//			for(int i = 0; i < myTable.length; i++){
//				Dict[] a = myTable[i].toArray(new Dict[myTable[i].size()]);
//				if (a.length > 0) {
//					outputstream.printf("%s %d ", a[0].getMyKey(), a.length);
//				}
//				for(int j = 0; j < myTable[i].size(); j++){
//					outputstream.printf("%s, ", a[j].getMyValue());
//				}
//				outputstream.println();
//			}
			outputstream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
