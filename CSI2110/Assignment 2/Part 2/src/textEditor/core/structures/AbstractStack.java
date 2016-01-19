package textEditor.core.structures;

import java.util.EmptyStackException;

import textEditor.util.PropertiesLoader;

/**
 * Abstract class defining the operations that must be implemented by child classes
 * @param <E>
 * 
 * TODO Extends this abstract class
 */
public abstract class AbstractStack <E>{

	// Number of allowable undo operations
	protected int maxUndos; 
	
	public AbstractStack(){
		// Get the number of allowable undo operations from the properties file
		maxUndos = PropertiesLoader.getMaxUndos();
	}
	
	public abstract void push(E e);
	
	public abstract E top() throws EmptyStackException;
	
	public abstract E pop() throws EmptyStackException;
	
	public abstract int size();
	
	public abstract boolean isEmpty();
}
