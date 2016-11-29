import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sorting {
	
	private static int mergecompare = 0;
	private static int mergesort = 0;
	
	public static void main(String[] args) {
		File file = new File("./random1000.txt");
		List<Integer> test = new ArrayList<>();
		Scanner filein;
		try {
			filein = new Scanner(file);
			while(filein.hasNext()){
				test.add(new Integer(filein.nextInt()));
			}
			int [] fortest = new int[test.size()];
			for(int i = 0; i < test.size(); i++){
				fortest[i] = test.get(i);
			}
			//bubbleSort(fortest.clone());
			//shell(fortest.clone());
			//newBubbleSort(fortest.clone());
	       // selectionSort(fortest.clone());
	        //mergeSort(fortest.clone());
	        //quickSort(fortest.clone());
			filein.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] list1 = {28, 17, 5, 2, 3, 4, 30, 6, 31, 38,
				39, 23, 1, 26, 29, 21, 7, 10, 9 , 13, 
				22, 33, 11, 17, 12, 15, 16, 19, 20, 25,
				24, 27, 8, 40, 42, 41, 45, 43, 44,
				46, 32, 49, 47, 48, 50, 34, 35, 36, 37};
		shell(list1.clone());
		
	}

	public static void bubbleSort(int[] l) {
		int numComp = 0;
		int numSwap = 0;

		System.out.println("bubbleSort()\nBefore sort: " + Arrays.toString(l));

		for (int j = 0; j < l.length - 1; j++) {
			for (int i = 0; i < l.length - 1; i++) {
				
				//comparing, so increment counter
				numComp++;
				if (l[i] > l[i + 1]) {

					//swapping, so increment counter
					numSwap++;
					final int temp = l[i];
					l[i] = l[i + 1];
					l[i + 1] = temp;

				}
			}
		}
		
		System.out.println("After sort:  " + Arrays.toString(l));
		checkSorted(l);
		System.out.println("Comparisons: " + numComp + " Swaps: " + numSwap);
	}
	
	public static void shell(int[] l) {
		System.out.println("bubbleSort()\nBefore sort: " + Arrays.toString(l));
		int numComp = 0;
		int numSwap = 0;
		int increment = l.length / 2;
		while (increment > 0) {
			System.out.println("\nIncrement=  " + increment);
			for (int i = increment; i < l.length; i++) {
				System.out.println("i=  " + i);
				numComp++;
				int j = i;
				System.out.println("j=  " + (j - increment));
				int temp = l[i];
				while (j >= increment && l[j - increment] > temp) {
					numSwap ++;
					l[j] = l[j - increment];

					j = j - increment;
				}
				l[j] = temp;
			}
			if (increment == 2) {
				increment = 1;
			} else {
				increment *= (5.0 / 11);
				System.out.println("increment=  " + increment);
			}
		}
		System.out.println("After sort:  " + Arrays.toString(l));
		checkSorted(l);
		System.out.println("Comparisons: " + numComp + " Swaps: " + numSwap);
	}
	
	public static void newBubbleSort(int[] l) {
		int numComp = 0;
		int numSwap = 0;

		System.out.println("\nnewBubbleSort()\nBefore sort: " + Arrays.toString(l));

		// complete method here (remember to count swaps and comparisons)
		for (int j = 0; j < l.length - 1; j++) {
			for (int i = 0; i < l.length - 1; i++) {
				
				//comparing, so increment counter
				numComp++;
				if (l[i] > l[i + 1]) {

					//swapping, so increment counter
					numSwap++;
					final int temp = l[i];
					l[i] = l[i + 1];
					l[i + 1] = temp;

				}
			}
			boolean isSorted = true;
			for(int i = 0; i < l.length - 1; i++) {
				if(l[i] > l[i + 1]) {
					isSorted = false;
				}
			}
			if(isSorted){
				break;
			}	
		}
		System.out.println("After sort:  " + Arrays.toString(l));
		checkSorted(l);
		System.out.println("Comparisons: " + numComp + " Swaps: " + numSwap);
	}
    
    public static void selectionSort(int[] l) {
		int numComp = 0;
		int numSwap = 0;

		System.out.println("\nSelection Sort()\nBefore sort: " + Arrays.toString(l));

		// complete method here (remember to count swaps and comparisons)
		
		for(int i = 0; i < l.length; i++) {
			int min = l[i];
			int minIndex = i;
			for(int j = i+1; j < l.length; j++){
				numComp++;
				if(l[j] < min){
					minIndex = j;
					min = l[j];
				}
			}
			if(minIndex != i){
				numSwap ++;
				int temp = l[i];
				l[i] = l[minIndex];
				l[minIndex] = temp;
			}
		}
		System.out.println("After sort:  " + Arrays.toString(l));
		checkSorted(l);
		System.out.println("Comparisons: " + numComp + " Swaps: " + numSwap);
	}
    
    public static void mergeSort(int[] l) {

		System.out.println("\nmergeSort()\nBefore sort: " + Arrays.toString(l));

		l = merge(l);
		
		System.out.println("After sort:  " + Arrays.toString(l));
		checkSorted(l);
		System.out.println("Comparisons: " + mergecompare + " Swaps: " + mergesort);
	}
    public static int[] merge(int[] l){
    	int mid = l.length/2;
    	int[] left = new int[mid];
    	int[] right = new int[l.length - mid];
    	if(l.length > 1){
    		for(int i=0; i< mid; i++){
    			left[i] = l[i];
    		}
    		left = merge(left);
    		for(int i= 0; i< l.length - mid; i++){
    			right[i] = l[mid + i];
    		}
    		right = merge(right);
    		int checkindex = 0;
    		int leftindex =0;
    		int rightindex = 0;
    		while(checkindex < l.length){
    			mergecompare ++;
    			mergesort ++;
    			if(leftindex == left.length){
    				l[checkindex] = right[rightindex];
    				checkindex ++;
    				rightindex ++;
    			} else if(rightindex == right.length){
    				l[checkindex] = left[leftindex];
    				checkindex ++;
    				leftindex ++;
    			} else {
    				if (left[leftindex] <= right[rightindex]){
    					l[checkindex] = left[leftindex];
        				checkindex ++;
        				leftindex ++;
    				} else {
    					l[checkindex] = right[rightindex];
        				checkindex ++;
        				rightindex ++;
    				}
    			}
    		}
    		

    	}
    	return l;
    }
    
    public static void quickSort(int[] l) {
		int numComp = 0;
		int numSwap = 0;

		System.out.println("\nquickSort()\nBefore sort: " + Arrays.toString(l));

		// complete method here (remember to count swaps and comparisons)
		
		System.out.println("After sort:  " + Arrays.toString(l));
		checkSorted(l);
		System.out.println("Comparisons: " + numComp + " Swaps: " + numSwap);
	}
    
    private int[] quicksort(int[] i, int Start, int End){
    	int mid = (Start - End)/2;


    	
    	return i;
    }
    private int forleft(int[] i, int Start, int mid){
    	int re = mid;
    	while(re == mid){
    		if(i[Start] < i[mid]){
    			Start ++;
    		} else {
    			re = mid;
    		}
    	}
    	return re;
    }
    
	private static void checkSorted(int[] l) {
		boolean isSorted = true;
		for(int i = 0; i < l.length - 1; i++) {
			if(l[i] > l[i + 1]) {
				isSorted = false;
			}
		}
		
		if (isSorted) {
			System.out.println("Result: Success! (Sorted)");
		} else {
			System.out.println("Result: Failed! (Not sorted)");
		}
	}
}
