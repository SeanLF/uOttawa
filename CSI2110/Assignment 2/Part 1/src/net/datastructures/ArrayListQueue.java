package net.datastructures;

import java.util.ArrayList;

public class ArrayListQueue<E> implements Queue<E>{
	
	// instance variables
	protected int front;
	protected int back;
	protected ArrayList<E> list;

	public ArrayListQueue (){
		front = 0;
		back=-1;
		list = new ArrayList<E>();
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	@Override
	public E front() throws EmptyQueueException {
		if(isEmpty()) {
		     throw new EmptyQueueException("Empty!");
		     }
		 else
		    return list.get(front);
	}

	@Override
	public void enqueue(E element) {
		back++;
		list.add(element);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		E r;
		if(isEmpty())
			throw new EmptyQueueException("Empty!");
		 else{
			 r = front();
			 list.remove(r);
			 back--;   
		     }		
		return r;
	}

}
