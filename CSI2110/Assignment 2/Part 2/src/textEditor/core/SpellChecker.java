package textEditor.core;

import java.util.LinkedList;

/**
 * Class responsible for performing the spellCheck operation
 */
public class SpellChecker {
	
	private String [] textWords; // this is the array of words we want to check in the EWL file
	private EwlStructureAdaptor ewlStructure; // this is an adaptor that allows us to perform
													// some operations on the structure that holds the 
													// EWL
	
	public SpellChecker(String [] textWords, EwlStructureAdaptor ds){
		this.textWords = textWords;
		this.ewlStructure = ds;
	}
	
	/**
	 * Checks if every word in the text matches exactly one that is found in the EWL
	 * @return the list of words not found in the EWL
	 */
	public LinkedList <String> getIncorrectWords(){
		long startTimeStamp = System.currentTimeMillis();
		
		LinkedList <String> incorrectWords = new LinkedList <String>();
		for (String word: textWords){
			// Check if the word exists in the EWL
			if (word.length()> 0 && !ewlStructure.wordExists(word)){
				// Check first if the words has already been added to the incorrect words list
				if (incorrectWords.indexOf(word) < 0){ 
					incorrectWords.add(word);
				}
			}

		}
		
		long endTimeStamp = System.currentTimeMillis();
		long diff = endTimeStamp - startTimeStamp;
		System.out.println("Time it took to finish spellcheck operation: "+diff+" ms");
		
		return incorrectWords;
	}
}
