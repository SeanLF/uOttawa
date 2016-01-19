// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @author Sean Floyd (6778524)
 * @author Isaac Shannon (6709038)
 * @version October 2013
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
	/**
	 * A list of commands and their uses
	 */
	private static String listOfCommands = "List of commands: \n"
			+ "#start:                  Start listening to clients connecting. \n"
			+ "#stop:                   Stop listening for clients to connect. \n"
			+ "#close:                  Close the server. \n"
			+ "#gethost:                Get the host name. \n"
			+ "#sethost <hostName>:     Set host to hostName. \n"
			+ "#getport:                Get port name. \n"
			+ "#setport <portNum>:      Set port to portNum. \n"
			+ "#quit:                   Immediately quit server. \n \n"
			+ "For changes to take effect, after changing the host name and/or port number, "
			+ "please close and start the server again. \n";
  
  //Instance variables
  
  /**
   * A reference to our console.
   */
  private ServerConsole console; 
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   * @param console The server console
   */
  public EchoServer(int port, ServerConsole console) 
  {
    super(port);
    this.console = console;
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client){
	  
	 //check if the message is a valid login request
	  if(checkLogin(client,msg)){ 
		  
		  // If user is already logged in, tell them
		  if(client.getInfo("id")!=null){
			  try {
				client.sendToClient("You can't login twice");
			} catch (IOException e) {} // do nothing
		  }
		  
		  else {
		  //set the login id
		  client.setInfo("id", returnId(msg));
		  console.display(stringId(client) + " has logged in from " + client + "."); 
		  try {
			client.sendToClient("Welcome to SimpleChat, " + stringId(client) + "!");
		} catch (IOException e) {} // do nothing
		  }
	  	}
	  
	  // check if client has an ID, disconnect if not
	  else if(stringId(client).equals("null"))
		  try{
			  client.sendToClient("Please login first, you will now be disconnected.");
			  client.close();
		  }
	  	  catch(Exception ex) { }
	  else{
		  // send message normally
		  console.display(stringId(client) + "> " + msg);
		  this.sendToAllClients(stringId(client)+ "> " +msg);
	  }  	
  }
  
  /**
   * This method is to handle any message that the user at the server console types.
   * @param message The message typed in by our server console user.
   */
  public void handleMessageFromServerUI(String message){
	  
	//modified for question E50 b) and E51 - 6709038
      
      if(message!=null && message.charAt(0) == ('#')){
      	if( message.equals("#quit")){ // Emergency shutdown
      		try {
				close();
			} catch (IOException e) {}
      		System.exit(0);
      	}
      	else if(message.equals("#stop")){ // stop listening for incoming connections
      		stopListening();
      		sendToAllClients("Warning: Server stopped listening.");
      	}
      	else if(message.equals("#close")){ // close connections and server
      		try {
				close();
		    } catch (IOException e) {}
      		
      	}
      	else if(message.equals("#start")){ // start listening
      		if(!isListening())
				try {
					listen();
				} catch (IOException e) {}
      	}
      	else if(message.equals("#getport")) // return the port we're using
      		System.out.println("Port: "+getPort());       
      	else if(message.contains("#setport")){ // set a new (?) port
      		try{
      			String portNum = message.substring(9,message.length());
      			setPort(Integer.parseInt(portNum));
      			System.out.println("Port set to: "+getPort());
      		}catch(NumberFormatException e){
      			System.out.println("Not a port!");
      		}
      	} else if(message.equals("#help")){
      		console.display(listOfCommands);
      	}
      	else System.out.println(message+" is not a command"); // otherwise, #... was not a cmd
      } 
      else{ // if no # was detected, then it is an ordinary message to be sent
      	message=("SERVER MSG> "+message);
        console.display(message);
      	this.sendToAllClients(message);
	  }
  }
  
  
 /**added for E51 6709038
  * this method checks to see if the first 6 characters are #login and if the string is at least 8 characters  
  */
  public boolean checkLogin(ConnectionToClient client,Object msg){
	  String message = msg.toString();
	  String firstSix;
	  boolean login = false;

	// get the first 6 characters of the string
	  if (message.length() > 7){
		  firstSix = message.substring(0,6);
		  
		// check if the six characters are #login
		  if(firstSix.equals("#login")){ 
			  login = true;  
		  }
	  }	  
	  return login; 
  }
  
  
  /**added for E51 6709038
   * returns the client's id in string form
   */
  public String stringId(ConnectionToClient client){
	  String id;
	  // retrieve the id
	  Object objectId = client.getInfo("id");
	  // convert the id to a string
	  if(objectId != null)
		  id = objectId.toString();
	  else
		  id = "null";
	  return id; 
  }
  
  /**added for E51 6709038
   * this method separates the id name from a login message and returns it 
   */
  public String returnId(Object msg){
	  
	  String message = msg.toString();
	  String id; 
	  int len = message.length();
	// separate the client ID from the rest of the message
	  id = message.substring(7,(len));
	  return id; 
  }
  
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    console.display
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    console.display
      ("Server has stopped listening for connections.");
  }
  
  /**
   * changed for E49 - 6778524
   * This method overrides the one in the superclass. 
   * @param client
   */
  protected void clientConnected(ConnectionToClient client) {
	  console.display("Client connected!");
  }
  
  /**
   * changed for E49 - 6778524
   * This method overrides the one in the superclass. 
   * @param client
   */
  synchronized protected void clientDisconnected(ConnectionToClient client) {
	  disconnectionNotify(client);
  }
  
  /**
   * changed for E49 - 6778524
   * This method overrides the one in the superclass. 
   * @param client
   * @param exception
   */
  synchronized protected void clientException(
		    ConnectionToClient client, Throwable exception) {
	  disconnectionNotify(client);
  }
  
  /**
   * Displays on the console the name of the user who disconnected.
   * @param client
   */
  private void disconnectionNotify(ConnectionToClient client){
	  console.display(stringId(client) + " disconnected.");
	  sendToAllClients(stringId(client) + " disconnected.");
  }
  
  
}
//End of EchoServer class
