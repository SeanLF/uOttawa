package emailClient;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3183 modeling language!*/


import java.io.IOException;
import java.util.*;

import client.AbstractClient;
import emailCommon.*;

// line 15 "model.ump"
public class Client extends AbstractClient
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Client Attributes
  private String name;
  private String loginID;
  
  	/**
	 * A list of commands
	 */
	private static String[] cmdN = {
			"#login\n"
			, "#send\n"
			, "#logoff\n"
			, "#show\n"
			, "#set:\n"	      	  
			, "#removeLast:\n"
			, "#removeOld:\n"
			, "#removeAll:\n"
			, "#getUsers:\n"};

	/**
	 * a list of descriptions
	 */
	private static String[] cmd = {"Login to the server. \n"
			, "Send message. \n"	
			, "Logoff, without quitting the client. \n"
			, "Show the host name and port number. \n"
			, "Set host name and port number. \n"
			, "Remove the last email in inbox. \n"
			, "Remove the oldest email in inbox. \n"
			, "Delete all emails in inbox. \n"
			, "Display connected users."};

  //Client Associations
  private ClientConsole clientConsole;
  protected Mailbox inbox;
  protected Mailbox outbox;
  

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /**
   * Client constructor
   * @param host
   * @param port
   * @param aClientConsole
   * @param aLoginID
   * @param aName
   */
  public Client(String host, String port, ClientConsole aClientConsole, String aLoginID, String aName)
  {
    super(host, new Integer(port));
    name = aName;
    loginID = aLoginID;
    clientConsole = aClientConsole;
    try {
		openConnection();
		sendToServer("#login @"+loginID+" @"+name);
	} catch (IOException e) {
		clientConsole.showMessage("Server might be down. \nTry again later.");
		e.printStackTrace();
	}
    inbox = new Mailbox();
    outbox = new Mailbox();
    handleMessageFromUI("#getMail");
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
   * setter
   * @param aName
   * @return
   */
  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  /**
   * setter
   * @param aLoginID
   * @return
   */
  public boolean setLoginID(String aLoginID)
  {
    boolean wasSet = false;
    loginID = aLoginID;
    wasSet = true;
    return wasSet;
  }

  /**
   * getter
   * @return
   */
  public String getName()
  {
    return name;
  }

  /**
   * getter
   * @return
   */
  public String getLoginID()
  {
    return loginID;
  }

  /**
   * getter
   * @return
   */
  public ClientConsole getClientConsole()
  {
    return clientConsole;
  }

  /**
   * Handle a message from the server
   * 1. if email, add to Mailbox inbox & add to the list of "mails" in clientConsole
   * 2. if string, show a message
   */
    @Override
	protected void handleMessageFromServer(Object msg) {
    	if(msg instanceof Email){
    		clientConsole.play();
    		Email mssg = (Email) msg;
    		inbox.addEmail(mssg);
    		clientConsole.getList1().add(mssg.getTitle(),0);
    	}else{
    		clientConsole.showMessage((String)msg);
    	}
	
	}	
	
    /**
	 * This method handles all data coming from the UI            
	 * if message is not empty:
	 * 	if message is a command, call command(String)
	 * 	else send to server 
	 * @param message The message from the UI.    
	 */
	public void handleMessageFromUI(String message)  {
		// detect if message is a command
		if((message!=null)  && !message.equals("")) {
			if (message.charAt(0) == ('#'))
			command(message);
			else{
				try {
					sendToServer(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (!isConnected()){
					this.connectionException(null);
				}
			}
		}
	}
	
	/**
	 * send an Email to server
	 * @param fields
	 */
	private void send(String[] fields){
		if (fields==null)
			return;
		try {
			Email em = new Email(fields[0], fields[1], fields[2], new Date(), this.getName());
			outbox.addEmail(em);
			clientConsole.getList2().add(em.getTitle(),0);
			sendToServer(em);
		} catch (IOException e) {
			e.printStackTrace();
			clientConsole.showMessage("Cannot send message.");
		}
	}

	/**
	 * command is called when the client detects a "#" from the UI
	 * @param message
	 */
	private void command(String message)  {
		String[] field;
		if(message.startsWith("#send")){
			field = clientConsole.constructMessage();
			send(field);
		} else if(message.startsWith("#logoff")){
			try {
				closeConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			name=null;
			loginID=null;
		} else if(message.startsWith("#login")){
			field = clientConsole.constructLogin();
			loginID=field[0];
			name=field[1];
			try{
			openConnection();
			sendToServer(message+"@"+loginID+" @"+name);
			}catch(IOException e){
				e.printStackTrace();
			}
		} else if(message.startsWith("#help")){
			new HelpFrame(cmdN,cmd);
		} else if(message.startsWith("#show")){
			clientConsole.showMessage( "Host name: "+getHost()+"\nPort number: "+getPort());
		} else if(message.startsWith("#set")){
			clientConsole.construct2Input( "Host name: ","Port number: ", getHost(), new Integer(getPort()).toString());
		} else if(message.startsWith("#removeOldest")){
			inbox.removeLast();
			clientConsole.getList1().remove(clientConsole.getList1().getItemCount());
		} else if(message.startsWith("#remove")){
			inbox.removeFirst();
			clientConsole.getList1().remove(0);
		} else if(message.startsWith("#getMail")){
			try {
				sendToServer("#getUndelivered");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(message.startsWith("#removeAll")){
			inbox.removeAll();
			while(0<clientConsole.getList1().getItemCount()){
				clientConsole.getList1().remove(0);
			}
		} else if(message.startsWith("#getUsers")){
			try {
				sendToServer(message);
			} catch (IOException e) {
				clientConsole.showMessage("Could not get users.");
				e.printStackTrace();
			}
		} else if(message.startsWith("#reply")){
			if(clientConsole.tabbedPane.getSelectedIndex()==0){
				  int index = clientConsole.getList1().getSelectedIndex();
				  Email toReplyTo = inbox.getEmail(index); 
				  field = clientConsole.constructMessage(toReplyTo.getTitle(), toReplyTo.getAuthor());
				  send(field);
			  }
		} else if(message.startsWith("#deleteSelected")){
			if(clientConsole.tabbedPane.getSelectedIndex()==0){
				  int index = clientConsole.getList1().getSelectedIndex();
				  inbox.removeAt(index);
				  clientConsole.getList1().remove(index);
			  } else {
				  int index = clientConsole.getList2().getSelectedIndex();
				  outbox.removeAt(index);
				  clientConsole.getList2().remove(index);
			  }
		} else clientConsole.showMessage(message +" is not a command. Try checking your spelling.");
		
			
	}
	
	/**
	 * notifies the client that the connection has been closed
	 */
	@Override
	protected void connectionClosed() {
		inbox.removeAll();
		outbox.removeAll();
		clientConsole.clearInterface();
		clientConsole.showMessage("Connection closed.");
	}
	
	/**
	 * notifies client & resets client
	 */
	@Override
	protected void connectionEstablished() {
		clientConsole.showMessage("Connected to server successfully.");
	}
	
	/**
	 * notifies client & resets client
	 * @param exception
	 */
	@Override
	protected void connectionException(Exception exception) {
		clientConsole.clearInterface();
		clientConsole.unXpected();
		inbox.removeAll();
		outbox.removeAll();
		clientConsole.showMessage("Connection to server lost. Try logging back in later.");
	}
	
}

// end of class