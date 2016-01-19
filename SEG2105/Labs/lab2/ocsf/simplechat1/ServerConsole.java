// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

import ocsf.server.AbstractServer;
import client.*;
import common.*;

/**
 * This class constructs the UI for a server client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ClientConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 * @author Sean Floyd (6778524)
 * @author Isaac Shannon (6709038)
 * @version October 2013
 */

//Created for E50 B) - 6709038
public class ServerConsole implements ChatIF 
{

	EchoServer server;
	
	//Constructors ****************************************************

	public ServerConsole() {}

	/**
	   * This method waits for input from the console.  Once it is 
	   * received, it sends it to the server's's message handler.
	   */
	  public void accept() 
	  {
	    try
	    {
	    	// set up reader
	      BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
	      while (true) // read from console and send to Server for analysis of input
	      {
	        server.handleMessageFromServerUI( fromConsole.readLine());	        
	      }
	    } 
	    catch (Exception ex) // in the event that an error occurs, we want to catch it / happens when in=null
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }
	  
	  /**
	   * This method overrides the method in the ChatIF interface.  It
	   * displays a message onto the screen.
	   *
	   * @param message The string to be displayed.
	   */
	  public void display(String message) 
	  {
	    System.out.println("> " + message);
	  }
	  
	  /**
	   * make server listen and console accept
	   * @param portNumber
	   * @param console
	   * @throws IOException
	   */
	  private void listenAccept(int portN, ServerConsole con) throws IOException{
		  server = new EchoServer(portN, con);
	    	server.listen(); //Start listening for connections		      
	        con.accept(); // start listening for server console input
	  }
	  
	  /**
	   * This method is responsible for the creation of the server instance & UI
	   *
	   * @param args[0] The loginID 
	   * @param args[2] The port number to listen on.  Defaults to 5555 
	   *          if no argument is entered.
	   * @param args[1] The host to listen to. Defaults to localhost. 
	   * 
	   * This main method was initially in EchoServer but was moved here.
	   * 
	   */
	  public static void main(String[] args) 
	  {
		  int port = 0; //Port to listen on
		  ServerConsole console;
		  try{  
			  port = Integer.parseInt(args[0]); //Get port from command line
		  }
		  catch(Throwable t){ 
			  port = EchoServer.DEFAULT_PORT; //Set port to 5555
		  }	
		  System.out.println("Type #help for a list of commands. \n");
		  console = new ServerConsole();	    
		  try { 	
			  console.listenAccept(port, console);
		  } 
		  catch (Exception ex){ 
			  System.out.println("ERROR - Could not listen for clients!");
		  }
	  }
	  
	  
	  
	}
	//End of ConsoleChat class