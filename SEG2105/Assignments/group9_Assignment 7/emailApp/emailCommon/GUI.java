package emailCommon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3183 modeling language!*/



/**
 * 
 * @author Sean
 * @author Isaac
 */
// line 36 "model.ump"
@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  	public  JTextArea textArea;
	public  JTextField input;
	public  JScrollPane scroll;
	public  JMenuBar menuBar;
	public  JButton compose;
	public  JMenu menu, back, font;
	public  JMenuItem[] bac, fon;
	public  Color[] c ={ Color.RED , Color.BLACK, Color.GRAY, Color.GREEN, Color.WHITE, Color.MAGENTA, Color.BLUE, Color.LIGHT_GRAY}; 
  //------------------------
  // CONSTRUCTOR
  //------------------------
  
	/**
	 * Creates a GUI with a specific name
	 * @param nameOfFrame
	 */
	public GUI(String nameOfFrame){
	  super("Email "+ nameOfFrame);
	  construct();
  }

	/**
	 * Overrides actionPerformed from ActionListener
	 * checks if the source of the event is a request to change a font or background color.
	 */
	@Override
  	public void actionPerformed(ActionEvent e) {
  		if(e.getSource()==compose){
  			try {
  				compose();
  			} catch (IOException e1) {
  				e1.printStackTrace();
  			}
  		}else if(e.getSource()==fon[0]){
  			changeFont(0);
  		} else if(e.getSource()==fon[1]){
  			changeFont(1);
  		} else if(e.getSource()==fon[2]){
  			changeFont(2);
  		} else if(e.getSource()==fon[3]){
  			changeFont(3);
  		} else if(e.getSource()==fon[4]){
  			changeFont(4);
  		} else if(e.getSource()==fon[5]){
  			changeFont(5);
  		} else if(e.getSource()==fon[6]){
  			changeFont(6);
  		} else if(e.getSource()==bac[0]){
  			changeBackground(0);
  		} else if(e.getSource()==bac[1]){
  			changeBackground(1);
  		} else if(e.getSource()==bac[2]){
  			changeBackground(2);
  		} else if(e.getSource()==bac[3]){
  			changeBackground(3);
  		} else if(e.getSource()==bac[4]){
  			changeBackground(4);
  		} else if(e.getSource()==bac[5]){
  			changeBackground(5);
  		} else if(e.getSource()==bac[6]){
  			changeBackground(6);
  		}
  	}
  
  /**
   * hook
   */
  	public void compose() throws IOException{	}

  	/**
  	 * change text area background depending on the menu item clicked
  	 * @param i
  	 */
	public void changeBackground(int i){
		if(i==0)textArea.setBackground(c[0]);
		if(i==1)textArea.setBackground(c[1]);
		if(i==2)textArea.setBackground(c[2]);
		if(i==3)textArea.setBackground(c[3]);
		if(i==4)textArea.setBackground(c[4]);
		if(i==5)textArea.setBackground(c[5]);
		if(i==6)textArea.setBackground(c[6]);
		return;
	}
	
	/** 
	 * change text area font color depending on the menu item clicked
	 * @param i
	 */
  	public void changeFont(int i){
  		if(i==0)textArea.setForeground(c[0]);
		if(i==1)textArea.setForeground(c[1]);
		if(i==2)textArea.setForeground(c[2]);
		if(i==3)textArea.setForeground(c[3]);
		if(i==4)textArea.setForeground(c[4]);
		if(i==5)textArea.setForeground(c[5]);
		if(i==6)textArea.setForeground(c[6]);
		return;
	}

  	/**
  	 * Display a message.
  	 * Message can be a string or an Email
  	 * @param message
  	 */
  	public void display(Object message){
		if(message instanceof String)
			textArea.append(message+"\n\n");
		if(message instanceof Email)
			showMessage((String)message);
	}
	
  	/**
  	 * Construct the GUI
  	 */
  	private void construct(){
  		// set default close operation and give it a specific background color
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(c[4]);
		
		// instantiate all of the instance variables
		textArea = new JTextArea(20,20);
		input = new JTextField(10);
		compose = new JButton("Compose", new ImageIcon(getClass().getResource("/compose.png")));
		
		// set the text zones editable states 
		textArea.setEditable(false);
		input.setEditable(true);
		
		// permit our input bar to receive text from the user
		input.addKeyListener(
				new KeyListener(){
					public void keyPressed(KeyEvent e12){
						if(e12.getKeyChar() == KeyEvent.VK_ENTER)
							try {
								checkInput();
							} catch (IOException e) {
								e.printStackTrace();
							}
					}
					@Override
					public void keyTyped(KeyEvent e) {}
					@Override
					public void keyReleased(KeyEvent e) {}
				});
		
		// add to the JFrame our compose button, our text zones
		add(compose, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		add(input, BorderLayout.SOUTH);
		
		// make things look nice (wrapping text, scrolling...)
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		this.add(scroll);
		
		// edit frame properties
		setResizable(true); 
		setVisible(true);
		textArea.setBackground(c[7]);
		textArea.setForeground(c[6]);
		input.setBackground(c[7]);
		input.setForeground(c[6]);
		
		// all of the cool menu bar stuff
		menuBar = new JMenuBar();
		menu = new JMenu("Appearance");
		back = new JMenu("Background colours");
		font = new JMenu("Font colours");
		menu.add(font);
		menu.add(back);
		menuBar.add(menu);
		bac = new JMenuItem[7];
		fon = new JMenuItem[7];
		bac[0] = new JMenuItem("Red");
		bac[1] = new JMenuItem("Black");
		bac[2] = new JMenuItem("Gray");
		bac[3] = new JMenuItem("Green"); 
		bac[4] = new JMenuItem("White");
		bac[5] = new JMenuItem("Magenta");
		bac[6] = new JMenuItem("Blue");
		fon[0] = new JMenuItem("Red");
		fon[1] = new JMenuItem("Black");
		fon[2] = new JMenuItem("Gray");
		fon[3] = new JMenuItem("Green"); 
		fon[4] = new JMenuItem("White");
		fon[5] = new JMenuItem("Magenta");
		fon[6] = new JMenuItem("Blue");
		for(int i=0; i<c.length-1; i++){
			back.add(bac[i]);
			font.add(fon[i]);
			bac[i].addActionListener(this);
			fon[i].addActionListener(this);
		}
		compose.addActionListener(this);
		setJMenuBar(menuBar);
		setSize(500,400);
	}
	
  	/**
  	 * duplicated from clientGUI
  	 * @param inputOne name of first prompted input
  	 * @param inputTwo name of second prompted input
  	 * @param DEFAULT_CONT_ONE default value for prompted input
  	 * @param DEFAULT_CONT_TWO default value for prompted input
  	 * @return prompted output
  	 */
  	public String[] construct2Input(String inputOne, String inputTwo, String DEFAULT_CONT_ONE, String DEFAULT_CONT_TWO){
  		// 2 Strings
  		String[] output = new String[2];
  		JTextField host = new JTextField(DEFAULT_CONT_ONE);
        JTextField port = new JTextField(DEFAULT_CONT_TWO);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel(inputOne));
        panel.add(host);
        panel.add(new JLabel(inputTwo));
        panel.add(port);
        JOptionPane.showConfirmDialog(this, panel, "Set host & port",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (host.getText().equals("") || host.getText().equals(null))
        	output[0] = DEFAULT_CONT_ONE;
        else output[0] = host.getText();
        if (port.getText().equals("") || port.getText().equals(null))
        	output[1] = DEFAULT_CONT_TWO;
        else output[1] = port.getText();	
        return output;
  	}
  	
  	/**
  	 * duplicated from clientGUI
  	 */
	public String[] constructInitLogin(){
        JTextField host = new JTextField("localhost");
        JTextField port = new JTextField("5555");
        JTextField login = new JTextField("special");
        JTextField name = new JTextField("Anonymous");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Host name:"));
        panel.add(host);
        panel.add(new JLabel("Port number:"));
        panel.add(port);
        panel.add(new JLabel("Login ID:"));
        panel.add(login);
        panel.add(new JLabel("Name:"));
        panel.add(name);
        JOptionPane.showConfirmDialog(this, panel, "Login",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
            String[] s = new String[4];
            if (host.getText().equals("") || host.getText().equals(null))
            	s[0] = "localhost";
            else s[0] = host.getText();
            if (port.getText().equals("") || port.getText().equals(null))
            	s[1] = "5555";
            else s[1] = port.getText();	
            if (login.getText().equals("") || login.getText().equals(null))
            	s[2] = "special";
            else s[2] = login.getText();
            if (name.getText().equals("") || name.getText().equals(null))
            	s[3] = "Anonymous";
            else s[3] = name.getText();	
            return s;        
	}
	
	/**
  	 * duplicated from clientGUI
  	 */
	public String[] constructLogin(){
        JTextField login = new JTextField("Special");
        JTextField name = new JTextField("Anonymous");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        String[] s = new String[2];
        panel.add(new JLabel("Login ID:"));
        panel.add(login);
        panel.add(new JLabel("Name:"));
        panel.add(name);
        JOptionPane.showConfirmDialog(this, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (login.getText().equals("") || login.getText().equals(null))
        	s[0] = "special";
        else s[0] = login.getText();
        if (name.getText().equals("") || name.getText().equals(null))
        	s[1] = "Anonymous";
        else s[1] = name.getText();	
        return s;        
	}
	
	/**
  	 * duplicated from clientGUI
  	 */
	public String[] constructMessage(){
		JTextField title = new JTextField();
        JTextField to = new JTextField();
        JTextField content = new JTextField();
        content.setSize(5, 5);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(title);
        panel.add(new JLabel("To:"));
        panel.add(to);
        panel.add(new JLabel("Message:"));
        panel.add(content);
        if(JOptionPane.showConfirmDialog(this, panel, "New Message",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)==JOptionPane.OK_OPTION){
            	String[] field = new String[3];
            	field[0]=title.getText();
            	field[1]=to.getText();
            	field[2]=content.getText();
            	return field;
            }else return null;
	}
	
	/**
  	 * duplicated from clientGUI
  	 */
	public String[] constructMessage(String title, String to){
		JTextField tittle = new JTextField("RE: title");
        JTextField tto = new JTextField(to);
        JTextField content = new JTextField();
        content.setSize(5, 5);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(tittle);
        panel.add(new JLabel("To:"));
        panel.add(tto);
        panel.add(new JLabel("Message:"));
        panel.add(content);
        if(JOptionPane.showConfirmDialog(this, panel, "New Message",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)==JOptionPane.OK_OPTION){
        	String[] field = new String[3];
        	field[0]=tittle.getText();
        	field[1]=tto.getText();
        	field[2]=content.getText();
        	return field;
        }else return null;
	}
	
	/**
  	 * duplicated from clientGUI
  	 */
	public  int notification(String message){
		new JOptionPane();
		return JOptionPane.showConfirmDialog(this, message, "New eMail", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
  	 * duplicated from clientGUI
  	 */
	public void showMessage(String message){
		new JOptionPane();
		JOptionPane.showMessageDialog(this,message);
	}
	
	/**
  	 * duplicated from clientGUI
  	 */
	public String getInput(String message){
		new JOptionPane();
		return (JOptionPane.showInputDialog(this,message));
		
	}

	/**
  	 * duplicated from clientGUI
  	 */
	public void checkInput() throws UnknownHostException, IOException{}
	
}