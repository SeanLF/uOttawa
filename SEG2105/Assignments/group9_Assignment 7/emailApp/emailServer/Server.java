package emailServer;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3183 modeling language!*/

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Date;
import emailCommon.*;
import server.AbstractServer;
import server.ConnectionToClient;

// line 1 "model.ump"
public class Server extends AbstractServer
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Server Associations
  private ServerConsole serverConsole;
  private Mailbox undelivered;
  	/**
	 * A list of all the possible commands 
	 */
	private static String[] cmdN = {
			"#start:\n"
			, "#stop:\n"                   
			, "#close:\n"                  
			, "#clear:\n"                  
			, "#getport:\n"                
			, "#setport:\n"			        
			, "#quit:\n"                   
			, "#getIP:\n"                   
			, "#send:\n"                    
			, "#getUsers:\n"};
	
	/**
	 * A description of all the commands
	 */
	private static String cmd[] ={ 
			"Start listening to clients connecting. \n"
			, "Stop listening for clients to connect. \n"
		    , "Close the server. \n"
			, "Clear the text area. \n"
		    , "Get port name. \n"
			, "Set port to portNum. \n"
			, "Immediately quit server. \n"
			, "Get server IP \n"
		    , "Send message through UI \n"
			, "Displays the connected users."};

  //------------------------
  // CONSTRUCTOR
  //------------------------

	/**
	 * The actual server constructor
	 * @param aPort the port to be used
	 * @param aHost the host to use
	 * @param aServerConsole
	 */
  public Server(int aPort, ServerConsole aServerConsole)
  {
    super(aPort);
    if (aServerConsole == null || aServerConsole.getServer() != null)
    {
      throw new RuntimeException("Unable to create Server due to ServerConsole");
    }
    serverConsole = aServerConsole;
    undelivered = new Mailbox();
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
   * simple getter for server Console
   * @return
   */
  public ServerConsole getServerConsole()
  {
    return serverConsole;
  }

  /**
   * handles messages from Clients
   * 1st message is always a login request
   * after that, if the message is a string: if command, handle appropriately, otherwise append to textArea
   * if the message is an email, toString() it and append it to the console textArea
   */
  @Override
  protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
	if(msg instanceof String){
		String mssg = msg.toString();
		if(mssg.startsWith("#login") && mssg.length()>10){ // param: first is login, second is name
			try {
				client.setName( mssg.substring(mssg.lastIndexOf("@")+1));
				client.setInfo("login",mssg.substring(mssg.indexOf("@")+1, mssg.lastIndexOf("@")-1));
				client.sendToClient("Welcome to your inbox, "+client.getName());	
				serverConsole.display(client.getName()+" logged in as "+client.getInfo("login"));
			} catch (IOException e) {
				serverConsole.display("Welcome message failed to send. Client might have disconnected.");
			}
		} else if(mssg.startsWith("#getUsers")){
			try {
				client.sendToClient(getUsers());
			} catch (IOException e) {
				serverConsole.display("Message failed to send. Client might have disconnected.");
				e.printStackTrace();
			}
		} else if(mssg.startsWith("#getUndelivered")) {
			serverConsole.display(client.getName()+" wants to see if they have undelivered mails.");
			checkIfClientHasMail(client);
		}else{
			serverConsole.display(client.getName()+"> "+mssg);
		}
	} else if (msg instanceof Email) {
		try {
			if(!findAndSend((Email)msg, ((Email) msg).getRecip())){
				if(!((Email) msg).getRecip().equals("Server")){
					client.sendToClient("Client not connected. \nMessage will be sent when reconnections occurs.");
					undelivered.addEmail((Email) msg);
				}
		}
		} catch (IOException e) {
			serverConsole.display("Message failed to send. Client might have disconnected.");
			e.printStackTrace();
		}
		serverConsole.display(msg.toString());
		
	}
	
}
  
  /**
   * Handles all of the Server commands typed by the server user
   * @param message
   * @throws IOException
   */
  private void command(String message) throws IOException{
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
    		serverConsole.showMessage("Port: "+getPort());       
    	else if(message.contains("#setport")){ // set a new port
    		try{
    			setPort(Integer.parseInt(serverConsole.getInput("Port: ")));
    			serverConsole.display("Port set to: "+getPort());
    		}catch(NumberFormatException e){
    			serverConsole.showMessage("Not a port!");
    		}
    	} else if(message.equals("#help")){
    		new HelpFrame(cmdN,cmd);
    	}else if(message.equals("#getIP")){
    		serverConsole.display("IP: "+InetAddress.getLocalHost());
    	}else if(message.equals("#clear")){
    		serverConsole.textArea.setText("");
    	}else if(message.equals("#send")){
    		
    		/*
    		 * this will prompt user to create message, search the connected clients for the recipient, and send it to them
    		 * or add it to hash map through client.setInfo(i, mail) where i is the number of messages
    		 */
    		
    		String[] field = serverConsole.constructMessage(); // field: title, recipient, content
    		if(field==null)return;
    		Email newMail = new Email(field[0], field[1], field[2], new Date(), "Server");
    		if(!findAndSend(newMail, field[1])){
    			serverConsole.display("Mail will be sent when client reconnects.");
    			undelivered.addEmail(newMail);
    		}
    	}else if(message.equals("#getIP")){
    		serverConsole.showMessage("Server IP is "+Inet4Address.getLocalHost().getHostAddress());
    	}else if(message.equals("#getUndelivered")){
    		serverConsole.display("Undelivered:");
    		for(int i =0; i<undelivered.numberOfEmails();i++)
    			serverConsole.display(undelivered.getEmail(i).toString() +"\n");
    	}else if(message.equals("#getUsers")){
    		serverConsole.display(getUsers());
    	}else serverConsole.display(message+" is not a command. Try checking your spelling."); // otherwise, #... was not a cmd
  }

  /**
   * This method is to handle any command that the user at the server console types.
   * @param message The message typed in by our server console user.
   * @throws IOException 
   */
  public void handleMessageFromUI(String message) throws IOException {
	  if(message!=null && !message.equals(null) && !message.equals("") && message.charAt(0) == ('#')){
	      	command(message);
	      } 
	      else{ // if no # was detected, then it is an ordinary message to be sent
	      	message=("SERVER MSG> "+message);
	      	serverConsole.display(message);
	      	this.sendToAllClients(message);
		  }

  }

  /**
   * We use this method when a client wants to send an email to another client.
   * Search through the thread list, which are ClientConnections, and check the name or login
   * if the recipient the user typed is found, send the mail to that user.
   * if the user is not logged in, return false
   * @param mail
   * @param recipient
   * @return whether we found and sent the mail
   * @throws IOException
   */
  protected boolean findAndSend(Email mail, String recipient) throws IOException{
	  Thread[] clientThreadList = getClientConnections();
		for(int i=0; i<clientThreadList.length; i++){
			ConnectionToClient bar = (ConnectionToClient) clientThreadList[i];
			if (bar.getInfo("login").equals(recipient) || bar.getName().equals(recipient)){
				bar.sendToClient(mail);
				return true;
			}
		}
		return false;
  }
  
  
/**
 * This method overrides the one in the superclass.  
 * Called when the server starts listening for connections.
 * displays a message to the user
 */
  protected void serverStarted()
{
  serverConsole.display
    ("Server listening for connections on port " + getPort());
}

/**
 * This method overrides the one in the superclass.  Called
 * when the server stops listening for connections.
 */
  protected void serverStopped()
{
	serverConsole.display
    ("Server has stopped listening for connections.");
}

/**
 * This method overrides the one in the superclass. 
 * @param client
 */
  protected void clientConnected(ConnectionToClient client) {
	serverConsole.display("Client connected!");
}

/**
 * This method overrides the one in the superclass. 
 * @param client
 */
  synchronized protected void clientDisconnected(ConnectionToClient client) {
	  disconnectionNotify(client);
}

/**
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
	serverConsole.display((client.getInfo("login")) + " disconnected.");
}
  
  /**
   * called when either the user or server wants to fetch the list of connected users
   * @return a list of connected users
   */
  private String getUsers(){
	  Thread[] clientThreadList = getClientConnections();
	String clients ="Users are: \n";
	for(int i=0; i<clientThreadList.length; i++){
		ConnectionToClient bar = (ConnectionToClient) clientThreadList[i];
		clients = clients +new Integer(i+1)+". Username: "+ bar.getInfo("login") + ", Name: "+  bar.getName() + "\n";
	}
	if(clients.equals("Users are: \n"))
		return "No clients connected at this time";
	else
		return clients;
  }
  
  /**
   * When client logs in, we check if there are any messages for him
   */
  protected void checkIfClientHasMail(ConnectionToClient client){
	  try{
		  int i=undelivered.numberOfEmails()-1;
		  while(i>=0){
			  Email atIndex = undelivered.getEmail(i);
			  if(atIndex.getRecip().equals(client.getName())||atIndex.getRecip().equals (client.getInfo("login"))){
				try {
					client.sendToClient(atIndex);
				} catch (Exception e) {
					e.printStackTrace();
				}
				serverConsole.display("One less undelivered message!");
				undelivered.removeTo(client.getName(), (String) client.getInfo("login"));
				i=undelivered.numberOfEmails()-1;
			  }else i--;
		  }
		  
	  }catch(Exception e){e.printStackTrace();}
  }
  
}