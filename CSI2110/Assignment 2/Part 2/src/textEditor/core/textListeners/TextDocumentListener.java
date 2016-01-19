package textEditor.core.textListeners;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentEvent.ElementChange;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


/**
 * Class defining a DocumentListener in order to catch changes to the text editor Document. 
 * This class generally listens to any change on the Document. Now, if the enabled flag
 * is set, then a user TextEditEvent is generated and sent to all TextEventListeners.
 *
 */
public class TextDocumentListener implements DocumentListener{
	// List of events listeners that are listening to text edit events
	private LinkedList<TextEventListener> eventListeners;
	// String that keeps track of the textual content of the editor
	private String textTracker = "";
	// Boolean specifying whether the TextDocumentListener is active or not
	private AtomicBoolean enabled;
	
	/**
	 * Constructor
	 */
	public TextDocumentListener(){
		eventListeners = new LinkedList<TextEventListener>();
		enabled = new AtomicBoolean(true);
	}
	
	/**
	 * Add a TextEventListener to the list of listeners
	 * @param listener that wishes to register for TextEditEvents
	 */
	public void addEventListener(TextEventListener listener){
		eventListeners.add(listener);
	}

	/**
	 * Method invoked whenever an insert action is detected on the document 
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {		
		try {
			// retrieve the document in question
			Document doc = e.getDocument();
			if (enabled.get()){
				//The insert operation should result in a TextEditEvent only if the
				// TextDocumentLilstenr is enabled. It is sometimes disabled temporarily
				// in order to programatically change the document without generating
				// unwanted events
				
				// Get the string that has been added in this document insert
				String addedString = doc.getText(e.getOffset(), e.getLength());
				
				// Create a TextEditEvent
				TextEditEvent editEvent = new TextEditEvent(TextEditEvent.INSERT_EVENT, 
						addedString, e.getOffset(), e.getLength());
				
				// Send the TextEditEvent to all listeners
				sendTextEditEvent(editEvent);
			}
			// This is the string that keeps track of the document's text. While this is
			// not needed for INSERT_EVENTs, it is invaluable for REMOVE_EVENTs
			textTracker = doc.getText(0, doc.getLength());
		
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * Method invoked whenever a remove action is detected on the document 
	 */	
	@Override
	public void removeUpdate(DocumentEvent e) {	
		try {
			Document doc = e.getDocument();
			if (enabled.get()){
				//The insert operation should result in a TextEditEvent only if the
				// TextDocumentLilstenr is enabled. It is sometimes disabled temporarily
				// in order to programatically change the document without generating
				// unwanted events
				
				// To retrieve the removedString, we make use of the textTracker since by
				// the time the removeUpdate method is called, the document has already been
				// modified and there is no other way to retrieve the removedString.
				String removedString = textTracker.substring(e.getOffset(),e.getOffset()+e.getLength());

				// Create a TextEditEvent
				TextEditEvent editEvent = new TextEditEvent(TextEditEvent.REMOVE_EVENT, 
						removedString, e.getOffset(), e.getLength());

				// Send the TextEditEvent to all listeners
				sendTextEditEvent(editEvent);	
			}
			
			// Make sure the textTracker is up to date all the time
			textTracker = doc.getText(0, doc.getLength());
			
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
			
	}


	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	
	/**
	 * Sets the enabled flag on the TextDocumentListener
	 * @param isEnabled
	 */
	public void setEnabled(boolean isEnabled){
		enabled.set(isEnabled);
	}
	
	/**
	 * Sends a TextEditEvent to all TextEventListeners
	 * @param editEvent to be sent to all TextEventListeners
	 */
	private void sendTextEditEvent(TextEditEvent editEvent){
		for (TextEventListener eventListener: eventListeners){
			eventListener.newTextEvent(editEvent);
		}		
	}

}
