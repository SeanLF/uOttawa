package emailCommon;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3183 modeling language!*/

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

// line 8 "model.ump"
public class Email implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Email Attributes
  private String title;
  private String content;
  private Date date;
  private String recip;
  private String from;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /**
   * An email has a set of variables
   * @param aTitle
   * @param recipient
   * @param aContent
   * @param aDate
   * @param author
   */
  public Email(String aTitle, String recipient, String aContent, Date aDate, String author)
  {
    setTitle(aTitle);
    setContent(aContent);
    setDate(aDate);
    setRecip(recipient);
    setAuthor(author);
  }

  //------------------------
  // All of the getters and setters + a toString()
  //------------------------

  /**
   * setting the title
   * @param aTitle
   * @return
   */
  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  /**
   * set content
   * @param aContent
   * @return
   */
  public boolean setContent(String aContent)
  {
    boolean wasSet = false;
    content = aContent;
    wasSet = true;
    return wasSet;
  }

  /**
   * set date
   * @param aDate
   * @return
   */
  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }
  
  /**
   * set recipient
   * @param recip
   */
  public void setRecip(String recip) {
		this.recip = recip;
	}
  
  /**
   * set author
   * @param author
   */
  public void setAuthor(String author){
	  this.from = author;
  }

  /**
   * getter
   * @return
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * getter
   * @return
   */
  public String getContent()
  {
    return content;
  }

  /**
   * getter
   * @return
   */
  public Date getDate()
  {
    return date;
  }
  
  /**
   * getter
   * @return string of date
   */
  public String getDat(){
	  return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
  }

  /**
   * getter
   * @return
   */
  public String getFrom(){
	  return from.toString();
  }

  /**
   * getter
   * @return
   */
  	public String getRecip() {
	return recip;
}

  	/**
  	 * getter
  	 * @return
  	 */
	public String getAuthor(){
		return from;
	}

	/**
	 * to string implementation of an email
	 */
	public String toString()
	{
		return ("From: "+from 
				+ "\nTo: "+recip
				+ "\nDate sent: " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date)
				+ "\nSubject:"+title
				+ "\n\n"+content+"\n");
	}
}