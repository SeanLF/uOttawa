package emailCommon;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HelpFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * panels to better organize the space
	 */
	public JPanel[] panel = {
			new JPanel(new GridLayout(3,1,2,2)),
			new JPanel(new GridLayout(10,2,2,2)),
			new JPanel(new GridLayout(3,1,2,2))
			};

	/**
	 * Creates a new JFrame with 3 panels: 
	 * 1st panel contains information
	 * 2nd panel contains a list of command names and descriptions
	 * 3rd panel contains general information
	 * @param cmdN
	 * @param cmd
	 */
	public HelpFrame(String[] cmdN, String[] cmd){
		super("Help"); // window name
		
		panel[0].add(new JLabel("List of Commands"));

		// empty JLabels to be used as a new line
		panel[0].add(new JLabel()); 
		panel[0].add(new JLabel());
		
		// for each row, add the command name and Text Field
		for(int i=0; i<cmdN.length;i++){
			JTextField cN = new JTextField(cmd[i]);
			panel[1].add(new JLabel(cmdN[i]));
			panel[1].add(cN);
			cN.setEditable(false);
			
		}
		
		// new line & informative text
		panel[2].add(new JLabel());
		panel[2].add( new JLabel("For changes to take effect, after changing the host name and/or port number,"));
		panel[2].add( new JLabel("please logoff/stop and login/start the server again. "));
		
		// placing my panels in the proper order
		add(panel[0], BorderLayout.NORTH);
		add(panel[1], BorderLayout.CENTER);
		add(panel[2], BorderLayout.SOUTH);
		
		// default close operation
		setDefaultCloseOperation(getDefaultCloseOperation());
		
		//make everything nice and cozy then show the window.
		setResizable(false);
		pack();
		setVisible(true);
	}
	
}
