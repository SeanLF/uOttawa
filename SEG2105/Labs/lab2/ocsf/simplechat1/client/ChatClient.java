// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import ocsf.server.ConnectionToClient;
import common.*;

import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Sean Floyd (6778524)
 * @author Isaac Shannon (6709038)
 * @version October 2013
 */
public class ChatClient extends AbstractClient
{
  
	// Class variables
	/**
	 * A list of commands and their uses
	 */
	private static String listOfCommands = "List of commands: \n"
			+ "#login <loginID>:      Login to the server. \n"
			+ "#logoff:               Logoff, without quitting the client. \n"
			+ "#gethost:              Get the host name. \n"
			+ "#sethost <hostName>:   Set host to hostName. \n"
			+ "#getport:              Get port name. \n"
			+ "#setport <portNum>:    Set port to portNum. \n"
			+ "#quit:                 Immediately quit client. \n \n"
			+ "For changes to take effect, after changing the host name and/or port number, "
			+ "please logoff and login to the server again. \n";
	
	//Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  
  /**
   * login ID
   */
  String loginID;

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
  }
  
  /**
   * changed for E49 - 6778524
   * override from AbstractClient
   * @param Exception e
   */
  protected void connectionException(Exception e){
	  disconnectionNotify("Connection severed. \n"
	  		+ "Try logging in when server is back up.");
  }
  
  /**
   * notifies client of disconnection
   * @param client
   */
  private void disconnectionNotify(String notifyMessage){
	  clientUI.display(notifyMessage);
  }
  
  /**
   * changed for E49 - 6778524
   * override from AbstractClient
   */
  protected void connectionClosed() {
	  disconnectionNotify("Disconnected.");
	}

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message) throws IOException
  {
	  /*
	   * changes made for E50 - 6778524
	   * if message is not null and starts with # its a command
	   * 	try to act appropriately
	   * otherwise send the message to the server
	   */
	
    if((message!=null) && (message.charAt(0) == ('#'))){
    	if(message.contains("#quit"))
    		quit();
    	else if(message.equals("#logoff"))
    		closeConnection();
    	else if(message.contains("#login")){
    			if(!this.isConnected())
    				openConnection();
    			sendToServer(message);
    	}
    	else if(message.equals("#gethost"))
    		clientUI.display("Host: "+getHost());
    	else if(message.equals("#getport"))
    		clientUI.display("Port: "+getPort());
    	else if(message.contains("#setport")){
    		try{
    			String portNum = message.substring(9,message.length());
    			setPort(Integer.parseInt(portNum));
    			clientUI.display("Port set to: "+getPort());
    		}catch(NumberFormatException e){
    			clientUI.display("Not a port!");
    		}
    	}
    	else if(message.contains("#sethost")){
    		if(message.length()>9){
    			setHost(message.substring(9,message.length()));
    			clientUI.display("Host set to: "+getHost());
    		}
    	} else if(message.equals("#help")){
    		clientUI.display(listOfCommands);
    	}    	
    	else clientUI.display(message+" is not a command.");
    	
    }else{
    
	  try
    {
      sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server. Check connection!");
    }
   
    /*
     * changed for E49 when server disconnects
     */
    if (!isConnected()){
    	this.connectionException(null);
    }
    }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
    	if(isConnected())
    		closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
