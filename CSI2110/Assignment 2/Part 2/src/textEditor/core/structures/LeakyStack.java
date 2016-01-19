package textEditor.core.structures;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class LeakyStack<E> extends AbstractStack<E> {
	
	LinkedList<E> s;

	@Override
	public void push(E e) {
		if(s.size()<maxUndos)
			s.addFirst(e);
		else{
			s.removeLast();
			s.addFirst(e);
		}	
	}

	@Override
	public E top() throws EmptyStackException {
		return s.peek();
	}

	@Override
	public E pop() throws EmptyStackException {
		return s.pop();
	}

	@Override
	public int size() {
		return s.size();
	}

	@Override
	public boolean isEmpty() {
		return s.size()==0;
	}
	
	public LeakyStack(){
		s = new LinkedList<E>();
	}

}
