package textEditor.core.structures;

/**
 * Implements a Node that stores an element and a reference to previous node and a following one
 *
 * @param <E>
 */
public class Node <E>{
	private E element;
	private Node<E> next;
	private Node<E> previous;
	
	
	
	public Node(E element) {
		this.element = element;
	}
	
	public Node() {
	}
	
	
	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public Node<E> getNext() {
		return next;
	}
	public void setNext(Node<E> next) {
		this.next = next;
	}
	public Node<E> getPrevious() {
		return previous;
	}
	public void setPrevious(Node<E> previous) {
		this.previous = previous;
	}
	
	

}
