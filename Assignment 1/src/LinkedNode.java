
public class LinkedNode<T extends Comparable<T>> {
	private T element;
	private LinkedNode<T> next;
	
	public LinkedNode(){
		element = null;
		next = null;
	}
	
	public LinkedNode(T theA){
		element = theA;
		next = null;
	}
	public T getElement() {
		return element;
	}
	public void setElement(T data) {
		this.element = data;
	}
	public LinkedNode<T> getNext() {
		return next;
	}
	public void setNext(LinkedNode<T> next) {
		this.next = next;
	}

}
