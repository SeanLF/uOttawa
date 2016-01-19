package emailServer;
import java.io.IOException;
import javax.swing.JOptionPane;
import emailCommon.GUI;

// line 34 "model.ump"
public class ServerConsole extends GUI
{

	private static final long serialVersionUID = 1L;

@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServerConsole Associations
  private Server server;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /**
   * Constructs the Server UI
   */
  	public ServerConsole(){
  		// create a window by the name of Server
  		super("Server");
  		// pop up a JOptionPane requesting a port number. If the result is empty, use default value. If cancel out, quit server. 
  		String i = getInput("Please enter a port. \n\nIf none is entered, the default value of 5555 will be used. ");
  		try{
  			if(i.isEmpty())
  				server = new Server(5555, this);
  			else
  				server = new Server(Integer.parseInt(i), this);
  		}catch(NumberFormatException e){
  			showMessage("Not a number! Port set to 5555.");
  			server = new Server(5555, this);
  		} catch(Exception e){
  			System.exit(0);
  		}
  	}
  //------------------------
  // INTERFACE
  //------------------------

  public Server getServer()
  {
    return server;
  }

  public static void main (String[] args){
	  ServerConsole myNewConsole = new ServerConsole();
	  try {
		myNewConsole.getServer().listen();
		
	} catch (IOException e) {
		new JOptionPane();
		JOptionPane.showMessageDialog(null,"Error: cannot create server or listen. \nCheck if no other servers are running!");
	}
  }

  /**
   * Overrides hook method from GUI:
   * handles message typed in console - replaces accept()
   * @throws IOException 
   */
  @Override
	public void checkInput() throws IOException {
	  server.handleMessageFromUI(input.getText());			 
	  input.setText(null);
	}
  
  /**
   * Overrides hook method from GUI
   * Used when compose button is pressed
   * @throws IOException 
   */
  @Override
  public void compose() throws IOException{
	  server.handleMessageFromUI("#send");
  }
  
  
  	
}