package emailCommon;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3183 modeling language!*/

import java.util.*;

// line 24 "model.ump"
public class Mailbox
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //Mailbox Associations
  private List<Email> emails;

  /**
   * Constructor
   */
  public Mailbox()
  {
    emails = new ArrayList<Email>(30);
  }

  /**
   * get mail at position index
   * @param index
   * @return
   */
  public Email getEmail(int index)
  {
    Email aEmail = emails.get(index);
    return aEmail;
  }

  /**
   * get all mail
   * @return
   */
  public List<Email> getEmails()
  {
    List<Email> newEmails = Collections.unmodifiableList(emails);
    return newEmails;
  }

  /**
   * @return number of emails
   */
  public int numberOfEmails()
  {
    int number = emails.size();
    return number;
  }

  /**
   * @return true if has emails
   */
  public boolean hasEmails()
  {
    boolean has = emails.size() > 0;
    return has;
  }

  /**
   * get index of specific email
   * @param aEmail
   * @return
   */
  public int indexOfEmail(Email aEmail)
  {
    int index = emails.indexOf(aEmail);
    return index;
  }

  /**
   * add email
   * @param email
   */
  public void addEmail(Email email){
	  emails.add(0,email);
  }

  /**
   * remove first email
   */
  public void removeFirst(){
	  emails.remove(0);
  }
  
  /**
   * remove last email
   */
  public void removeLast(){
	  emails.remove(emails.size()-1);
  }
  
  /**
   * remove all mails
   */
  public void removeAll(){
	  while(hasEmails())
		  emails.removeAll(getEmails());
  }
  
  /**
   * remove mail at index
   * @param index
   */
  public void removeAt(int index){
	  emails.remove(index);
  }
  
  /**
   * remove email that is to a specific person
   * @param name
   */
  public void removeTo(String name, String login){
	  for(int i=emails.size()-1;i>=0;i--){
		  if(emails.get(i).getRecip().equals(name) || emails.get(i).getRecip().equals(login)){
			  emails.remove(i);
			  return;
		  }
	  }
  }

}