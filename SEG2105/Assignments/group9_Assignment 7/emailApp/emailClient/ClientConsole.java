package emailClient;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import emailCommon.*;



/**
 * icons @copy iconfinder.com
 * @author Sean
 * @author Isaac
 */
// line 35 "model.ump"
public class ClientConsole extends clientGUI
{
	/**
	 * won't work unless im here
	 */
	private static final long serialVersionUID = 1L;

@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}


  //ClientConsole Associations
  private Client client;
  protected Clip clip;
  private AudioInputStream stream;
  
  /**
   * Constructor
   */
  public ClientConsole(){
		super("Email client");
		new JOptionPane();
		String[] answers = constructInitLogin();
		client = new Client(answers[0], answers[1], this, answers[2], answers[3]);	
		try{
		    
		    AudioFormat format;
		    DataLine.Info info;
		    stream = AudioSystem.getAudioInputStream((getClass().getResource("/yoda.wav")));
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		}catch(Exception e){
			e.printStackTrace();
		}
  }
  
  /**
   * getter
   * @return
   */
  public Client getClient()
  {
    return client;
  }
  
  /**
   * Overrides hook method from clientUI:
   * handles message typed in console - replaces accept()
 * @throws IOException 
   */
  public void checkInput() throws IOException {
	  client.handleMessageFromUI(input.getText());			 
	  input.setText(null);
	}
  
  /**
   * Overrides hook method from clientUI:
   * create a new email
   */
  @Override
  public void compose() throws IOException{
	  client.handleMessageFromUI("#send");
  }
  
  /**
   * use mailviewer to display the email selected
   */
  @Override
  public void displayInbox(int c){
	  Email e = getClient().inbox.getEmail(c);
	  mailview.fetchField(0).setText(e.getAuthor()	);
	  mailview.fetchField(1).setText(e.getRecip()	);
	  mailview.fetchField(3).setText(e.getTitle()	);
	  mailview.fetchField(2).setText(e.getDat()	);
	  mailview.fetchArea(4).setText(e.getContent()	);	   
	}
  
  /**
   * Overrides hook in clientGUI
   * use mailViewer to display the email selected
   */
  @Override
  public void displayOutbox(int c){
	  Email e = getClient().outbox.getEmail(c);
	  mailview.fetchField(0).setText(e.getAuthor()	);
	  mailview.fetchField(1).setText(e.getRecip()	);
	  mailview.fetchField(3).setText(e.getTitle()	);
	  mailview.fetchField(2).setText(e.getDat()	);
	  mailview.fetchArea(4).setText(e.getContent()	);		  
	}
  
  /**
   * Overrides the hook method in ClientGUI
   * self explanatory
   */
  @Override
  public void openHelp(){
	  client.handleMessageFromUI("#help");
  }
  
  /**
   * called when client tries to login or logout -> button is clicked
   */
  @Override
  public void alterConnection(){
	  if(client.isConnected()){
		  logOnOff.setText("Login");
		  logOnOff.setIcon(new ImageIcon(getClass().getResource("/login.png")));
		  client.handleMessageFromUI("#logoff");
	  } else {
		  logOnOff.setText("Disconnect");
		  logOnOff.setIcon(new ImageIcon(getClass().getResource("/logout.png")));
		  client.handleMessageFromUI("#login");
		  client.handleMessageFromUI("#getMail");
	  }
  }
  
  /**
   * Gets the users connected to the server
   */
  @Override
  public void getContacts(){
	  client.handleMessageFromUI("#getUsers");
  }
  
  /**
   * Reply to the selected message
   */
  @Override
  public void reply(){
	  client.handleMessageFromUI("#reply");
  }  
  
  /**
   * Delete the selected message
   */
  @Override
  public void delete(){
	  client.handleMessageFromUI("#deleteSelected");
  }
  
  /**
   * Rare case: unexpected disconnection: we need to change the connection button.
   */
  public void unXpected(){
	  logOnOff.setText("Login");
	  logOnOff.setIcon(new ImageIcon(getClass().getResource("/login.png")));
  }
  
  /**
   * When a disconnection occurs, clear the user interface
   */
  protected void clearInterface(){
	  getList1().removeAll();
	  getList2().removeAll();
	  mailview.fetchField(0).setText("");
	  mailview.fetchField(1).setText("");
	  mailview.fetchField(3).setText("");
	  mailview.fetchField(2).setText("");
	  mailview.fetchArea(4).setText("");
  }
  
  /**
   * plays clip
   */
  public void play(){
	  // play the clip
	  clip.start(); 
	  // clip ends, next time it plays, start from beginning of clip
	  clip.setFramePosition(0);
  }
  
  /**
   * get mail
   */
  @Override
  public void getMail(){
	  client.handleMessageFromUI("#getMail");
  }
  
  /**
   * Run me!!!!!
   * @param args
   */
  public static void main (String[] args){
	  new ClientConsole();
  }

}