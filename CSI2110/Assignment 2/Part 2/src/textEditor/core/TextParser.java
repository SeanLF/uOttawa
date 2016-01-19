package textEditor.core;

/**
 * This class is responsible for parsing alphabetic words from a text string
 *
 */
public class TextParser {

	private String text;
	
	public TextParser(String text){
		this.text = text;
	}

	/**
	 * Parse the words in the text using a simple regular expression
	 * @return the list of alphabetic words parsed from the text string
	 */
	public String[] parseText(){
		String[] textWords = text.trim().split("[^a-zA-Z]+");
		return textWords;
	}
}
