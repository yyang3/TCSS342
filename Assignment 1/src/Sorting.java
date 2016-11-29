/**
 * Linked list class
 * Tcss 342
 * @author Yicong Yang
 *
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



public class Sorting {
	private static int numComp = 0;
	private static int numSwap = 0;
	private static int totalpass = 0;
	private static List<String[]> outPut;
	private static String file; 
	private static long start, end;
	
	
	
	/**
	 * Bubble sort method.
	 * @param first the unsorted node
	 * @return the sorted node
	 */
	public static <T extends Comparable<T>> LinkedNode<T> BubbleSort(LinkedNode<T> first) {
		LinkedNode<T> Headertracker = first;
		
		while(Headertracker.getNext() != null){
			LinkedNode<T> SortChecker = first;
			numComp++;
			if(SortChecker.getElement().
					compareTo(SortChecker.getNext().getElement())>0){//Swap on the header to start
				numSwap++;
				LinkedNode<T> temp3 = SortChecker.getNext();
				SortChecker.setNext(temp3.getNext());
				temp3.setNext(SortChecker);
				SortChecker = temp3;
				first = SortChecker;
				Headertracker = SortChecker;
			}
			while(SortChecker.getNext().getNext() != null){
				numComp++;
				if (SortChecker.getNext().getElement().
						compareTo(SortChecker.getNext().getNext().getElement())>0) {
					numSwap++;
					LinkedNode<T> temp3 = SortChecker.getNext().getNext().getNext();
					SortChecker.getNext().getNext().setNext(SortChecker.getNext());
					SortChecker.setNext(SortChecker.getNext().getNext());
					SortChecker.getNext().getNext().setNext(temp3);
				} 
				SortChecker = SortChecker.getNext();
			}
			Headertracker = Headertracker.getNext();
		}
		LinkedNode<T> SortedCheck = first;
		checkSorted(SortedCheck);
		return first;
	}
	/**
	 * Shell sort method.
	 * @param first the unsorted node
	 * @param fileName file address for output to print on the header
	 * @return the sorted node
	 */
	
	public static <T extends Comparable<T>> LinkedNode<T> ShellSort(LinkedNode<T> first, String fileName){
		file = fileName;
		numComp = 0;
		numSwap = 0;
		int SwapwithK = 0;
		int CompwithK = 0;
		int pass = 1;
		String[] oneTime = new String[4];
		String[] header = new String[]{"K","pass","cmp","exch" };
		outPut = new ArrayList<String[]>();
		outPut.add(header);
		start = System.currentTimeMillis();
		LinkedNode<T> CountTotal = first;
		int totalNum = 0;
		int Kindex = 0;
		while (CountTotal != null){//counting total
			CountTotal = CountTotal.getNext();
			totalNum ++;
		}
		while (totalNum > (Math.pow(3,Kindex)-1)/2){//(3^k ¨C 1)/2 finding K
			Kindex ++;
		}
		Kindex--;
		int k = (int) ((Math.pow(3,Kindex)-1)/2);
		while(k > 1){
			oneTime[0] = String.valueOf(k);
			
			LinkedNode<T> StartingPoint = first;
			LinkedNode<T> AfterOneBeforeK = first;
			for(int x = 1; x < k; x++){//getting to the kth node
				AfterOneBeforeK = AfterOneBeforeK.getNext();
			}
			CompwithK++;
			if(StartingPoint.getElement().compareTo(AfterOneBeforeK.getNext().
					getElement())>0){//Special case on the head
				SwapwithK++;
				LinkedNode<T> temp = AfterOneBeforeK.getNext().getNext();
				AfterOneBeforeK.getNext().setNext(StartingPoint.getNext());
				StartingPoint.setNext(temp);
				temp = AfterOneBeforeK.getNext();
				AfterOneBeforeK.setNext(StartingPoint);
				StartingPoint = temp;
				first = StartingPoint; 
			} 
			AfterOneBeforeK = AfterOneBeforeK.getNext();
			for(int i = 1; i < totalNum - k; i++){//normal case
				CompwithK++;
				if(AfterOneBeforeK.getNext()!= null){
					if(StartingPoint.getNext().getElement().
							compareTo(AfterOneBeforeK.getNext().getElement())>0){
						SwapwithK++;
						LinkedNode<T> temp = AfterOneBeforeK.getNext().getNext();
						AfterOneBeforeK.getNext().setNext(StartingPoint.getNext().getNext());
						StartingPoint.getNext().setNext(temp);
						temp = StartingPoint.getNext();
						StartingPoint.setNext(AfterOneBeforeK.getNext());
						AfterOneBeforeK.setNext(temp);
					}
					StartingPoint = StartingPoint.getNext();
					AfterOneBeforeK = AfterOneBeforeK.getNext();
				}
			}
			boolean isSorted = checkSorted2(first, k);
			if(isSorted){
				Kindex--;
				k = (int) ((Math.pow(3,Kindex)-1)/2);
				oneTime[1] = String.valueOf(pass);
				oneTime[2] = String.valueOf(CompwithK);
				oneTime[3] = String.valueOf(SwapwithK);
				outPut.add(oneTime.clone());
				totalpass = totalpass + pass;
				numComp = numComp + CompwithK;
				numSwap = numSwap + SwapwithK;
				pass = 1;
				CompwithK = 0;
				SwapwithK = 0;
			} else{
				pass ++;
			}	
		}
		while(k == 1){
			oneTime[0] = String.valueOf(k);
			LinkedNode<T> StartingPoint = first;
			CompwithK++;
			if(StartingPoint.getElement().compareTo(StartingPoint.getNext().
					getElement())>0){//Swap on the header to start
				SwapwithK++;
				LinkedNode<T> temp3 = StartingPoint.getNext();
				StartingPoint.setNext(temp3.getNext());
				temp3.setNext(StartingPoint);
				StartingPoint = temp3;
				first = StartingPoint;
			}
			while(StartingPoint.getNext().getNext() != null){
				CompwithK++;
				if (StartingPoint.getNext().getElement().
						compareTo(StartingPoint.getNext().getNext().getElement())>0) {
					//swapping, so increment counter
					SwapwithK++;
					LinkedNode<T> temp3 = StartingPoint.getNext().getNext().getNext();
					StartingPoint.getNext().getNext().setNext(StartingPoint.getNext());
					StartingPoint.setNext(StartingPoint.getNext().getNext());
					StartingPoint.getNext().getNext().setNext(temp3);
				} 
				StartingPoint = StartingPoint.getNext();
			}
			boolean isSorted = checkSorted2(first, k);
			if(isSorted){
				Kindex--;
				k = (int) ((Math.pow(3,Kindex)-1)/2);
				oneTime[1] = String.valueOf(pass);
				oneTime[2] = String.valueOf(CompwithK);
				oneTime[3] = String.valueOf(SwapwithK);
				outPut.add(oneTime.clone());
				numComp = numComp + CompwithK;
				numSwap = numSwap + SwapwithK;
				totalpass = totalpass + pass;
				pass = 1;
				CompwithK = 0;
				SwapwithK = 0;
			} else{
				pass ++;
			}

		}
		oneTime[0] = "total";
		oneTime[1] = String.valueOf(totalpass);
		oneTime[2] = String.valueOf(numComp);
		oneTime[3] = String.valueOf(numSwap);
		outPut.add(oneTime.clone());
		LinkedNode<T> temp3 = first;
		checkSorted(temp3);
		printout();
		end = System.currentTimeMillis();
		return first;
	}

	/**
	 * Check if the list is sorted.
	 * @param first the node for check
	 * @param K the distance between two points for comparison
	 * @return boolean whether it is sorted
	 */
	
	private static <T extends Comparable<T>> boolean checkSorted2(LinkedNode<T> first, int K) {
		boolean isSorted = true;
		LinkedNode<T> last =first;
		for (int i = 0; i < K; i++){
			last = last.getNext();
		}
		while (last != null && isSorted) { 
			if(first.getElement().compareTo(last.getElement())>0) {
				isSorted = false;
			}
			first = first.getNext();
			last = last.getNext();
		}
		return isSorted;
	}
	/**
	 * Check if it is sorted
	 * @param first the node to check
	 */
	private static <T extends Comparable<T>> void checkSorted(LinkedNode<T> first) {
		boolean isSorted = true;
		while (first.getNext() != null) { 
			if(first.getElement().compareTo(first.getNext().getElement())>0) {
				System.out.println("Error at: " + first.getElement() + " + " + first.getNext().getElement()
						);
				isSorted = false;
			}
			first = first.getNext();
		}
		
		if (isSorted) {
			System.out.println("\nResult: Success! (Sorted), num of "
					+ "compare: " + numComp + ", num of swap: " + numSwap);
		} else {
			System.out.println("Result: Failed! (Not sorted)");
		}
	}
	/**
	 * method to form the output.txt.
	 */
	private static void printout(){
		String filename = "Output.txt";
		try {
			PrintWriter outputstream = new PrintWriter(filename);
			outputstream.println("a.out < " + file);
			for(int i = 0; i < outPut.size(); i++){
				if(i == 1 || i == outPut.size()-1){
					for(int k = 0; k < 50; k++){
						outputstream.print("-");
					}
					outputstream.print("\n");
				}
				
				for(int j=0; j < outPut.get(i).length; j++){
					outputstream.printf("%10s", outPut.get(i)[j]);
				}
				outputstream.println();
			}
			outputstream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
