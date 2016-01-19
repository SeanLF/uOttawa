package textEditor.core.structures;

/**
 * Node List implementation based on a doubly linked list
 * @author Bob
 */

public class NodeList<E>{

	private Node <E> head;		// head of the doubly linked list
	private Node <E> trailer;	// trailer of the doubly linked list
	
	private int size;			// size of the node list
	
	public NodeList (){
		// create the head
		head = new Node<E>();
		
		// create trailer
		trailer = new Node<E>();
		
		// Link them together
		head.setNext(trailer);
		trailer.setPrevious(head);
		
		// node list is empty at the begining
		size = 0;
	}
	
	/**
	 * Return the size of the node list
	 * @return size of the node list
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Checks if the node list is empty
	 * @return true if the node list is empty, false otherwise
	 */
	public boolean isEmpty(){
		return size==0;
	}
	
	/**
	 * Checks if the node passed as a parameter is the first one
	 * @param n: node to be checked
	 * @return true if n is the first node, false otherwise
	 */
	public boolean isFirst(Node<E> n){
		return  head.getNext().equals(n);
	}
	
	/**
	 * Returns the first node
	 * @return the first node
	 */
	public Node<E> first(){
		return  head.getNext();
	}
	
	/**
	 * Checks if the node passed as a parameter is the last one
	 * @param n: node to be checked
	 * @return true if n is the last node, false otherwise
	 */	
	public boolean isLast(Node<E> n){
		return  trailer.getPrevious().equals(n);
	}

	/**
	 * Returns the last node
	 * @return the last node
	 */
	public Node<E> last(){
		return  trailer.getPrevious();
	}
	
	/**
	 * Returns the node that comes after the one passed as a parameter
	 * @param n: any nodeList node
	 * @return the node after n
	 */
	public Node<E> next(Node<E> n){
		return  n.getNext();
	}
	
	/**
	 * Returns the node that comes before the one passed as a parameter
	 * @param n: any nodeList node
	 * @return the node before n
	 */
	public Node<E> previous(Node<E> n){
		return  n.getPrevious();
	}
	
	/**
	 * Add this element at the beginning of the node list
	 * @param e element to be inserted
	 * @return node inserted into nodeList
	 */
	public Node<E> addFirst(E e){
		Node<E> n = new Node<E>(e);
		
		Node<E> w = head.getNext();
		
		n.setNext(w);
		n.setPrevious(head);
		
		w.setPrevious(n);
		head.setNext(n);
		
		size++;
		
		return n;
	}
	
	/**
	 * Add this element at the end of the nodeList
	 * @param e element to be inserted
	 * @return node inserted into nodeList
	 */
	public Node<E> addLast(E e){
		Node<E> n = new Node<E>(e);
		
		Node<E> w = trailer.getPrevious();
		
		n.setNext(trailer);
		n.setPrevious(w);
		
		w.setNext(n);
		trailer.setPrevious(n);
		
		size++;
		
		return n;
	}
	
	/**
	 * Adds a node containing element e before node w
	 * @param w a node list node
	 * @param e an element to be inserted
	 * @return the node inserted
	 */
	public Node<E> addBefore(Node<E> w, E e){
		Node<E> n = new Node<E>(e);
		
		Node<E> r = w.getPrevious();
		
		n.setPrevious(r);
		n.setNext(w);
		
		r.setNext(n);
		w.setPrevious(n);
		
		size++;
	
		return n;
	}
	
	/**
	 * Adds a node containing element e after node w
	 * @param w a nodeList node
	 * @param e an element to be inserted
	 * @return the node inserted
	 */
	public Node<E> addAfter(Node<E> w, E e){
		Node<E> n = new Node<E>(e);
		
		Node<E> r = w.getNext();
		
		n.setNext(r);
		n.setPrevious(w);
		
		
		w.setNext(n);
		r.setPrevious(n);
	
		size++;
		
		return n;
	}
	
	/**
	 * Removes node from nodeList
	 * @param n node to be removed
	 * @return the removed node
	 */
	public Node<E> remove(Node<E> n){
		
		Node<E> w = n.getPrevious();
		Node<E> r = n.getNext();
		
		w.setNext(r);
		r.setPrevious(w);
		
		n.setNext(null);
		n.setPrevious(null);
	
		size --;
		
		return n;
	}
	
	/**
	 * Swaps the elements of two nodes
	 * @param n nodeList node
	 * @param w nodeList node
	 */
	public void swapElements(Node<E> n , Node<E> w){
		E temp = n.getElement();
		
		n.setElement(w.getElement());
		w.setElement(temp);
	}
	
	/**
	 * Sets the element of node n
	 * @param n nodeList node
	 * @param e element
	 */
	public void set(Node<E> n , E e){
		n.setElement(e);
	}
	
	/**
	 * Checks if element e is contained in the nodeList
	 * @param e element
	 * @return true if the element is found, false otherwise
	 */
	public boolean contains(E e) {
		Node<E> n = head.getNext();
		while (!n.equals(trailer)){
			
			if (n.getElement().equals(e)){
				return true;
			}
			
			n = n.getNext();
		}
		
		return false;
	}
	

}
