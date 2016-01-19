package emailCommon;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MailViewer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] c = {"From: ", "To: ", "Date: ", "Subject: "};
	JScrollPane s;

	public JPanel[] panel = {
			new JPanel((LayoutManager)new FlowLayout(FlowLayout.LEFT)),
			new JPanel((LayoutManager)new FlowLayout(FlowLayout.LEFT)),
			new JPanel((LayoutManager)new FlowLayout(FlowLayout.LEFT)),
			new JPanel((LayoutManager)new FlowLayout(FlowLayout.LEFT)),
			new JPanel((LayoutManager)new FlowLayout(FlowLayout.LEFT))
			};
	
	MailViewer(){
		super(new GridLayout(2,1,7,2));
		JPanel infoVcont = new JPanel(new GridLayout(4,1,7,2));
		add(infoVcont);
		for(int i=0; i<panel.length-1; i++){
			infoVcont.add(panel[i]);
			JLabel l = new JLabel(c[i]);
			JTextField t = new JTextField(50);
			t.setEditable(false);
			panel[i].add(l);
			panel[i].add(t);
		}
		int i = panel.length-1;
		add(panel[i]);
		JTextArea t = new JTextArea(15,51);
		t.setEditable(false);
		t.setSize(panel[i].getSize());
		t.setLineWrap(true);
		t.setWrapStyleWord(true);
		s = new JScrollPane(t);
		panel[panel.length-1].add(s);
		panel[0].setBackground(Color.WHITE);panel[1].setBackground(Color.WHITE);
		panel[2].setBackground(Color.WHITE);panel[3].setBackground(Color.WHITE);panel[4].setBackground(Color.WHITE);
	}
	
	public JTextField fetchField(int i){
		return (JTextField) panel[i].getComponent(1);
	}
	
	public JTextArea fetchArea(int i){
		return (JTextArea) s.getViewport().getView();
	}
	
}
