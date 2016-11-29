/**
 * Linked list class
 * @author Yicong Yang
 *
 * @param <T> generic type
 */
public class LinkedList<T extends Comparable<T>> {
private LinkedNode<T> head;
	/**
	 * create with no nodes
	 */
	public LinkedList(){
		head = null;
	}
	/**
	 * method to add number to the list
	 * @param input the reading from the file
	 */
	public void addNumber(T input){
		LinkedNode<T> temp = new LinkedNode<T>(input);
		if(head == null){
			head = temp;
		} else {
			LinkedNode<T> temp2 = head;
			while(temp2.getNext() != null){
				temp2 = temp2.getNext();
			}
			temp2.setNext(temp);
		}
	}
	/**
	 * method to print the list
	 */
	public void printList(){
		if(head == null) {
			System.out.println("null");
		} else {
			LinkedNode<T> temp = head;
			while(temp != null){
				System.out.print(temp.getElement() + " ");
				temp = temp.getNext();
			}
		}
	}
	/**
	 * to print the result of bubblesort on the console
	 */
	public void resultBubbleSort(){
		printList(Sorting.BubbleSort(copying()));
	}
	/**
	 * to print the result of shell sort on both console and output file
	 * @param filename the input file name for output to use
	 */
	public void resultShellSort(String filename){
		printList(Sorting.ShellSort(copying(),filename));
	}
	/**
	 * method to print the list within the class
	 * @param theHead node for printing
	 */
	private void printList(LinkedNode<T> theHead){
		if(theHead == null) {
			System.out.println("null");
		} else {
			LinkedNode<T> temp = theHead;
			while(temp != null){
				System.out.print(temp.getElement() + " ");
				temp = temp.getNext();
			}
		}
	}
	/**
	 * to deep copy a list thus it will keep its original status when sorting.
	 * @return the copied node
	 */
	private LinkedNode<T> copying(){
		LinkedNode<T> temp = new LinkedNode<T>(head.getElement());
		LinkedNode<T> temp4 = temp;
		LinkedNode<T> temp2 = head;
		while(temp2.getNext() != null){
			LinkedNode<T> temp3 = new LinkedNode<T>(temp2.getNext().getElement());
			temp.setNext(temp3);
			temp = temp.getNext();
			temp2 = temp2.getNext();
		}
		return temp4;
	}

	
}
