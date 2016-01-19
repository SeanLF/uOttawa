/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/


import java.util.*;

// line 9 "Sorted.ump"
// line 68 "Sorted.ump"
public class Student
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Attributes
  private int id;
  private String name;

  //Student Associations
  private Academy academy;
  private List<Registration> registrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student(int aId, String aName, Academy aAcademy)
  {
    id = aId;
    name = aName;
    boolean didAddAcademy = setAcademy(aAcademy);
    if (!didAddAcademy)
    {
      throw new RuntimeException("Unable to create registrant due to academy");
    }
    registrations = new ArrayList<Registration>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public Academy getAcademy()
  {
    return academy;
  }

  public Registration getRegistration(int index)
  {
    Registration aRegistration = registrations.get(index);
    return aRegistration;
  }

  public List<Registration> getRegistrations()
  {
    List<Registration> newRegistrations = Collections.unmodifiableList(registrations);
    return newRegistrations;
  }

  public int numberOfRegistrations()
  {
    int number = registrations.size();
    return number;
  }

  public boolean hasRegistrations()
  {
    boolean has = registrations.size() > 0;
    return has;
  }

  public int indexOfRegistration(Registration aRegistration)
  {
    int index = registrations.indexOf(aRegistration);
    return index;
  }

  public boolean setAcademy(Academy aAcademy)
  {
    boolean wasSet = false;
    if (aAcademy == null)
    {
      return wasSet;
    }

    Academy existingAcademy = academy;
    academy = aAcademy;
    if (existingAcademy != null && !existingAcademy.equals(aAcademy))
    {
      existingAcademy.removeRegistrant(this);
    }
    academy.addRegistrant(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfRegistrations()
  {
    return 0;
  }

  public Registration addRegistration(Course aCourse)
  {
    return new Registration(this, aCourse);
  }

  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) { return false; }
    Student existingStudent = aRegistration.getStudent();
    boolean isNewStudent = existingStudent != null && !this.equals(existingStudent);
    if (isNewStudent)
    {
      aRegistration.setStudent(this);
    }
    else
    {
      registrations.add(aRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistration, as it must always have a student
    if (!this.equals(aRegistration.getStudent()))
    {
      registrations.remove(aRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRegistrationAt(Registration aRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addRegistration(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrationAt(Registration aRegistration, int index)
  {
    boolean wasAdded = false;
    if(registrations.contains(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegistrationAt(aRegistration, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Academy placeholderAcademy = academy;
    this.academy = null;
    placeholderAcademy.removeRegistrant(this);
    for(int i=registrations.size(); i > 0; i--)
    {
      Registration aRegistration = registrations.get(i - 1);
      aRegistration.delete();
    }
  }

  @umplesourcefile(line={69},file={"Sorted.ump"},javaline={211},length={7})
   public String toString(){
    String result="Student=" + id + "[" + name + "\n";
    for (Registration r: getRegistrations()) {
      result +="  In: " + r + "\n";
    }
    return result;
  }

}