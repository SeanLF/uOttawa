package emailCommon;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * The user interface of the client
 * icons @copy iconfinder.com
 * @author Sean Floyd
 * @author Isaac Shannon
 */
public class clientGUI extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	
	public JTabbedPane tabbedPane = new JTabbedPane();
	public MailViewer mailview = new MailViewer();
	JComponent panel1 = makeListPanel();
	JComponent panel2 = makeListPanel();
	JPanel buttonPanel = new JPanel((LayoutManager)new FlowLayout(FlowLayout.LEFT));
	JButton getMail = new JButton("Fetch mail", new ImageIcon(getClass().getResource("/refresh.png"))); 
	JButton compose = new JButton("Compose", new ImageIcon(getClass().getResource("/compose.png"))); 
	JButton reply = new JButton("Reply", new ImageIcon(getClass().getResource("/reply.png"))); 
	JButton delete = new JButton("Delete", new ImageIcon(getClass().getResource("/trash.png"))); 
	protected JButton logOnOff = new JButton("Logout", new ImageIcon(getClass().getResource("/logout.png")));
	JButton contacts = new JButton("Connected Contacts", new ImageIcon(getClass().getResource("/contacts.png")));
	JButton help = new JButton("Help", new ImageIcon(getClass().getResource("/help.png")));
	JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, mailview);
	protected JPanel inputPanel = new JPanel((LayoutManager)new FlowLayout(FlowLayout.LEFT));
	protected JLabel inputLabel = new JLabel("Command:");
	protected JTextField input = new JTextField(58);
	
	protected clientGUI(String s){
		//window name
		super(s);
		//default close operation
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//tab pane for viewing our mail
		tabbedPane.addTab("Inbox", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		tabbedPane.addTab("Outbox", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		addMouseLis(getList1());
		addMouseLis(getList2());
		
		// input area for commands
		input.addKeyListener(
				new KeyListener(){
					public void keyPressed(KeyEvent e12){
						if(e12.getKeyChar() == KeyEvent.VK_ENTER)
							try {
								checkInput();
							} catch (IOException e) {
								// 
								e.printStackTrace();
							}
						
					}

					@Override
					public void keyTyped(KeyEvent e) {}

					@Override
					public void keyReleased(KeyEvent e) {}
				});
		
		// adding our components to the window frame
		add(split);
		add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.add(getMail);
		buttonPanel.add(compose);
		buttonPanel.add(reply);
		buttonPanel.add(delete);
		buttonPanel.add(contacts);
		buttonPanel.add(logOnOff);
		buttonPanel.add(help);
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		add(inputPanel, BorderLayout.SOUTH);
		
		//adding actionListeners
		addActionListener();
		
		//make everything nice size-wise
		split.setEnabled(false); // disable the resizing of the split pane
		setResizable(false); // prevent the user from resizing the window 
		pack();
		setSize(800, 605);
		
		
		//show the formed window
		setVisible(true);
		
	}
	
	void addActionListener(){
		getMail.addActionListener(this);
		compose.addActionListener(this);
		reply.addActionListener(this);
		delete.addActionListener(this);
		logOnOff.addActionListener(this);
		contacts.addActionListener(this);
		help.addActionListener(this);
	}
	
	void addMouseLis(final List l){
		l.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		            // single click
		            if(l.equals(getList1()))displayInbox(l.getSelectedIndex());
		            else displayOutbox(l.getSelectedIndex());
		         }
		    }  
		});
	}


	/**
	 * Hook
	 * @throws IOException 
	 */
	public void checkInput() throws IOException {	}
	
	private JComponent makeListPanel () {
        JPanel panel = new JPanel(false);
        List filler = new List(150);
        panel.setLayout (new GridLayout(1,1));
        panel.add(filler);
        return panel;
}
	
	public List getList1(){
		Component[] c = panel1.getComponents();
		return (List) c[c.length-1];
	}
	public List getList2(){
		Component[] c = panel2.getComponents();
		return (List) c[c.length-1];
	}
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==compose){
	  		try {
				compose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	  	} else if( e.getSource()==logOnOff){
	  		alterConnection();
	  	} else if( e.getSource()==contacts){
	  		getContacts();
	  	} else if( e.getSource()==reply){
	  		reply();
	  	} else if( e.getSource()==delete){
	  		delete();
	  	} else if( e.getSource()==getMail){
	  		getMail();
	  	} else if(e.getSource()==help){
	  		openHelp();
	  	}
		
	}

	public void compose() throws IOException {}
	
	public String[] construct2Input(String inputOne, String inputTwo, String DEFAULT_CONT_ONE, String DEFAULT_CONT_TWO){
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
	 * to implement reply
	 * @param title
	 * @param to
	 * @return inputs
	 */
	public String[] constructMessage(String title, String to){
		JTextField tittle = new JTextField("RE: "+title);
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
	
	public void showMessage(String message){
		new JOptionPane();
		JOptionPane.showMessageDialog(this,message);
	}
	
	public String getInput(String message){
		new JOptionPane();
		return (JOptionPane.showInputDialog(this,message));
	}
	
	//hook
	public void displayInbox(int c){
	}
	
	protected void displayOutbox(int selectedIndex) {
	}
	
	/**
	 * Hook method implemented in ClientConsole: changes name of button on click & logs in or out client
	 */
	public void alterConnection(){}
	
	/**
	 * Hook method implemented in ClientConsole: opens help frame
	 */
	public void openHelp(){}
	
	/**
	 * Hook method implemented in ClientConsole: displays the list of connected users
	 */
	public void getContacts(){}
	
	/**
	 * Hook method
	 */
	public void reply(){}
	
	/**
	 * Hook method
	 */
	public void delete(){}
	
	/**
	 * Hook method
	 */
	public void getMail(){}
	
}
