/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/


import java.util.*;

// line 14 "Sorted.ump"
// line 78 "Sorted.ump"
public class Course
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private String code;
  private Comparator<Registration> registrationsPriority;

  //Course Associations
  private Academy academy;
  private List<Registration> registrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(String aCode, Academy aAcademy)
  {
    code = aCode;
    registrationsPriority = 
      new Comparator<Registration>(){
        @Override
        public int compare(Registration arg0, Registration arg1)
        {
          return ((String)arg0.getName()).compareTo(
                 ((String)arg1.getName()));
        }
      };
    boolean didAddAcademy = setAcademy(aAcademy);
    if (!didAddAcademy)
    {
      throw new RuntimeException("Unable to create course due to academy");
    }
    registrations = new ArrayList<Registration>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    code = aCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRegistrationsPriority(Comparator<Registration> aRegistrationsPriority)
  {
    boolean wasSet = false;
    registrationsPriority = aRegistrationsPriority;
    wasSet = true;
    return wasSet;
  }

  public String getCode()
  {
    return code;
  }

  public Comparator<Registration> getRegistrationsPriority()
  {
    return registrationsPriority;
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
      existingAcademy.removeCourse(this);
    }
    academy.addCourse(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfRegistrations()
  {
    return 0;
  }

  public Registration addRegistration(Student aStudent)
  {
    return new Registration(aStudent, this);
  }

  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) { return false; }
    Course existingCourse = aRegistration.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aRegistration.setCourse(this);
    }
    else
    {
      registrations.add(aRegistration);
    }
    wasAdded = true;
    if(wasAdded)
        Collections.sort(registrations, registrationsPriority);
    
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistration, as it must always have a course
    if (!this.equals(aRegistration.getCourse()))
    {
      registrations.remove(aRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  
  public void delete()
  {
    Academy placeholderAcademy = academy;
    this.academy = null;
    placeholderAcademy.removeCourse(this);
    for(int i=registrations.size(); i > 0; i--)
    {
      Registration aRegistration = registrations.get(i - 1);
      aRegistration.delete();
    }
  }

  @umplesourcefile(line={79},file={"Sorted.ump"},javaline={191},length={7})
   public String toString(){
    String result ="Course=" + code + "\n";
    for (Registration r: getRegistrations()) {
      result +="  Has: " + r + "\n";
    }    
    return result;
  }

}