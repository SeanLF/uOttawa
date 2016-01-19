package textEditor.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import textEditor.util.PropertiesLoader;

/**
 * Abstract class defining the main methods that should be implemented by any class
 * adapting a structure to expose mainly its insert (add) and find (search) operation
 */
public abstract class EwlStructureAdaptor {

	/**
	 * Loads the words of the ewl into the structure. Note that the
	 * addWord operation is abstract and therefore should be implemented
	 * by child classes
	 * @throws IOException
	 */
	public void loadEwl() throws IOException{
		File ewlFile = PropertiesLoader.getEwlFile();
		FileReader fstream = new FileReader(ewlFile);
		BufferedReader br = new BufferedReader(fstream);
			
		String word=null;
		while ((word=br.readLine()) != null){
			this.addWord(word);
		}
	
		
	}
	/**
	 * Add word into the structure
	 * @param word to be added into the structure
	 */
	protected abstract void addWord(String word);
	
	/**
	 * Look for word in the structure
	 * @param word to searched for in the structure
	 * @return true if the word exists and false otherwise
	 */
	public abstract boolean wordExists(String word);
	
	/**
	 * Returns the size of the structure
	 * @return the size of the structure
	 */
	public abstract int getSize();
	
	/**
	 * Test whether the structure is empty
	 * @return true if the structure is empty and false otherwise
	 */
	public abstract boolean isEmpty();
}
