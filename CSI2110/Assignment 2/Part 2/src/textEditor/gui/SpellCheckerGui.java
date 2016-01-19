package textEditor.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * Class define the GUI responsible for displaying the incorrecly spelled words
 *
 */
public class SpellCheckerGui extends JFrame{
	public SpellCheckerGui(LinkedList<String> invalidWords){
		// Set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			// Do nothing
		}
		
		
		// Set some frame properties
		this.setTitle("Spell Checker");
		this.setSize(400,300);
		this.setResizable(false);
		
		
		
		
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setContentPane(scrollPane);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Incorrectly spelled words:\n");
		for (String line:invalidWords){
			buffer.append(line+"\n");
		}
		
		textArea.setText(buffer.toString());
		
		setVisible(true);
	}
}
