package textEditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import textEditor.core.EwlListAdaptor;
import textEditor.core.EwlStructureAdaptor;
import textEditor.core.SpellChecker;
import textEditor.core.TextParser;
import textEditor.core.structures.LeakyStack;
import textEditor.core.textListeners.TextDocumentListener;
import textEditor.core.textListeners.TextEditEvent;
import textEditor.core.textListeners.TextEventListener;
import textEditor.util.PropertiesLoader;

/**
 * @author Hussein Al Osman
 * @version 1.0
 * This is the main Graphical User Interface (GUI) class that defines the principal GUI components
 * and makes visible the main functions of the system 
 */
@SuppressWarnings("serial")
public class TextEditorGui extends JFrame implements TextEventListener{

	/**
	 * Swing global variables
	 */
	private JTextArea textArea;
	private JFileChooser fileChooser;
	private Document document;

	/**
	 * Non swing global variables
	 */
	private TextParser textParser;
	private SpellChecker spellChecker;
	private LeakyStack<TextEditEvent> stack;
	private TextDocumentListener textDocumentListener;
	
	private EwlStructureAdaptor ewlStructure;
	
	/**
	 * Text editor lone constructor 
	 * 
	 */
	public TextEditorGui(){
		/**
		 * TODO Change this default stack by providing your own implementation
		 */
		stack = new LeakyStack<TextEditEvent>();
		
		ewlStructure = new EwlListAdaptor();
		
		// Load the spellcheck EWL into memory
		try {
			ewlStructure.loadEwl();
		} catch (IOException e2) {
			// Display an error message and exit if EWL file cannot be loaded
			JOptionPane.showMessageDialog(TextEditorGui.this, "Cannot load the EWL file",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			
			// Exit program since EWL file could not be loaded
			exit(-1);
		}
		
		// Initialize the GUI components and create their listeners
		initGui();

	}
	/**
	 * Creates the GUI components and their corresponding action listeners
	 */
	private void initGui(){
		// Set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			// If this cannot be achieved, then do nothing, no harm is done
		}

		/**
		 * GUI components are created in this section
		 */
		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem openMenu = new JMenuItem("Open...");
		JMenuItem exitMenu = new JMenuItem("Exit");
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem spellCheckMenu = new JMenuItem("Spell check...");
		JMenuItem undoMenu = new JMenuItem("Undo");
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenu = new JMenuItem("About");
		
		fileChooser = new JFileChooser();

		menuBar.add(fileMenu);
		fileMenu.add(openMenu);
		fileMenu.add(exitMenu);
		
		menuBar.add(editMenu);
		editMenu.add(spellCheckMenu);
		editMenu.add(undoMenu);
		
		menuBar.add(helpMenu);
		helpMenu.add(aboutMenu);
		
		setJMenuBar(menuBar);
		
		// Create text area
		textArea = new JTextArea();
		textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
		textArea.getInputMap().put(KeyStroke.getKeyStroke("F7"), "SpellCheck");
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setContentPane(scrollPane);
		
		// Set some frame properties
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Text Editor");
		this.setSize(800,600);
		this.setResizable(true);
		this.setVisible(true);
		
		
		/**
		 * All listeners are defined in this section
		 */
		// Text area event listeners

		textArea.getActionMap().put("Undo", new AbstractAction("Undo") {
			public void actionPerformed(ActionEvent evt) {
					performUndo();
			}
		});

		textArea.getActionMap().put("SpellCheck", new AbstractAction("SpellCheck") {
			public void actionPerformed(ActionEvent evt) {
					performSpellCheck();
			}
		});
		
		textDocumentListener = new TextDocumentListener();
		document = textArea.getDocument();
		document.addDocumentListener(textDocumentListener);
		textDocumentListener.addEventListener(this);
		
		
		
		// Open menu action listener
		openMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				performOpenFile();
			}
		});
		
		// Exit menu action listener
		exitMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				exit(0);
			}
		});
		
		// SpellCheck menu action listener
		spellCheckMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				performSpellCheck();
			}
		});
		
		// Undo menu action listener
		undoMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				performUndo();
			}
		});
		
		// About menu action listener
		aboutMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});		
	}
	
	/**
	 * Launches the file chooser. If a file is chosen,
	 * the file's text is read and set into the text area of the editor
	 */
	private void performOpenFile(){
		int returnVal = fileChooser.showOpenDialog(TextEditorGui.this);
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
	            try {
	            	String text = readTextFile(file);
	            	textArea.setText(text);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(TextEditorGui.this, "Cannot read text file",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
	      }		
	}
	
	/**
	 * Launches the spellcheck operation by retrieving the
	 * text from the textArea, parsing is and running the spellChecking
	 * operation
	 */
	private void performSpellCheck(){
		textParser = new TextParser(textArea.getText());
		String[] textWords = textParser.parseText();
		
		spellChecker = new SpellChecker(textWords,ewlStructure);
		
		SpellCheckerGui spellCheckerGui = 
			new SpellCheckerGui(spellChecker.getIncorrectWords());
		spellCheckerGui.setVisible(true);		
	}
	
	/**
	 * Pops a previously stored text edit event form the
	 * stack and reverses its effect. For instance, if the event is 
	 * and text INSERT_EVENT, the added string is removed. If it is
	 * a REMOVE_EVENT, then the removed string is added back
	 */
	private void performUndo(){
		if (!stack.isEmpty()){
			TextEditEvent editEvent = stack.pop();
			try {
				// It is important to turn off the document listener before
				// programatically changing the content of the document so
				// that such an event would not be mistaken to be a user action
				textDocumentListener.setEnabled(false);
				
				if (editEvent.getEventType() == TextEditEvent.INSERT_EVENT){
					// This was an insert event, therefore to undo it we should remove
					document.remove(editEvent.getEditOffset(), editEvent.getEditLength());
				}
				else {
					// This is a remove event, thereofre to undo it we should insert
					document.insertString(editEvent.getEditOffset(), editEvent.getEditStr(),null);
				}
				
				// Turn the listener back on now that we have finished manipulating
				// the document so that we can again catch the user actions
				textDocumentListener.setEnabled(true);
				
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
	}
			
	/**
	 * Shows information about the program
	 */
	private void showAbout(){
		try {
			File aboutFile = PropertiesLoader.getAboutFile();
			String aboutStr = readTextFile(aboutFile);
			
			JOptionPane.showMessageDialog(TextEditorGui.this, aboutStr,
					"About", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	
	/**
	 * This method is an event handler. Whenever a user event is caught,
	 * it is pushed into the stack so that it can be later reversed when
	 * an undo command is called.
	 */
	@Override
	public void newTextEvent(TextEditEvent e) {
		stack.push(e);
	}
	
	/**
	 * Reads a file from the hard disk and retunrs its textual content
	 * @param file to be read
	 * @return the textual content of the file
	 * @throws IOException if the file cannot be found or read
	 */
	private String readTextFile(File file) throws IOException{
		StringBuffer textBuffer = new StringBuffer();
		
		FileReader fstream = new FileReader(file);
		BufferedReader br = new BufferedReader(fstream);
		
		String line=null;
		while ((line=br.readLine()) != null){
			textBuffer.append(line+"\n");
		}
		
		return textBuffer.toString();
		
	}

	/**
	 * Exits the program
	 * @param ret exit return value
	 */
	private void exit(int ret){
		System.exit(ret);
	}
	
	/**
	 * Main method to launch the application
	 * @param args
	 */
	public static void main (String [] args){
		new TextEditorGui();
	}
	
}
