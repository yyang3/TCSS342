/**
 * Tcss 342
 * @author Yicong Yang
 */

import java.io.*;
import java.util.Scanner;

/**
 * main class for testing
 *
 */
public class Main {
	
	/**
	 * the linked list used for test.
	 */
	private static LinkedList<Integer> t;
	/**
	 * the address of the test file.
	 */
	private static String file;
	/**
	 * main method
	 * @param args from JVM
	 */
	public static <T> void main(String[] args) {
		// TODO Auto-generated method stub
		file = "random100.txt";
		t = readValue(file);
		displayList();
		t.resultBubbleSort();
		t.resultShellSort(file);
	}
	/**
	 * Method to read value from the file.
	 * @param File file address
	 * @return the linked list
	 */
	private static LinkedList<Integer> readValue(String File){
		LinkedList<Integer> test = new LinkedList<Integer>();
		File file = new File(File);
		Scanner filein;
		try {
			filein = new Scanner(file);
			while(filein.hasNext()){
				test.addNumber(new Integer(filein.nextInt()));
			}
			filein.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		return test;	
	}
	/**
	 * display the unsorted list.
	 */
	private static void displayList(){
		t.printList();
	}

}
