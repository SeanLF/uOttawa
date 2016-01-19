/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/


import java.util.*;

/**
 * This example demonstrates two cases of sorted associations
 * The main program adds items out of order
 * But when they are printed the output will be sorted
 * Mixin with main program and toString method
 */
// line 4 "Sorted.ump"
// line 37 "Sorted.ump"
public class Academy
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Academy Attributes
  private Comparator<Student> registrantsPriority;

  //Academy Associations
  private List<Course> courses;
  private List<Student> registrants;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Academy()
  {
    registrantsPriority = 
      new Comparator<Student>(){
        @Override
        public int compare(Student arg0, Student arg1)
        {
          return ((Integer)arg0.getId()).compareTo(
                 ((Integer)arg1.getId()));
        }
      };
    courses = new ArrayList<Course>();
    registrants = new ArrayList<Student>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRegistrantsPriority(Comparator<Student> aRegistrantsPriority)
  {
    boolean wasSet = false;
    registrantsPriority = aRegistrantsPriority;
    wasSet = true;
    return wasSet;
  }

  public Comparator<Student> getRegistrantsPriority()
  {
    return registrantsPriority;
  }

  public Course getCourse(int index)
  {
    Course aCourse = courses.get(index);
    return aCourse;
  }

  public List<Course> getCourses()
  {
    List<Course> newCourses = Collections.unmodifiableList(courses);
    return newCourses;
  }

  public int numberOfCourses()
  {
    int number = courses.size();
    return number;
  }

  public boolean hasCourses()
  {
    boolean has = courses.size() > 0;
    return has;
  }

  public int indexOfCourse(Course aCourse)
  {
    int index = courses.indexOf(aCourse);
    return index;
  }

  public Student getRegistrant(int index)
  {
    Student aRegistrant = registrants.get(index);
    return aRegistrant;
  }

  public List<Student> getRegistrants()
  {
    List<Student> newRegistrants = Collections.unmodifiableList(registrants);
    return newRegistrants;
  }

  public int numberOfRegistrants()
  {
    int number = registrants.size();
    return number;
  }

  public boolean hasRegistrants()
  {
    boolean has = registrants.size() > 0;
    return has;
  }

  public int indexOfRegistrant(Student aRegistrant)
  {
    int index = registrants.indexOf(aRegistrant);
    return index;
  }

  public static int minimumNumberOfCourses()
  {
    return 0;
  }

  public Course addCourse(String aCode)
  {
    return new Course(aCode, this);
  }

  public boolean addCourse(Course aCourse)
  {
    boolean wasAdded = false;
    if (courses.contains(aCourse)) { return false; }
    Academy existingAcademy = aCourse.getAcademy();
    boolean isNewAcademy = existingAcademy != null && !this.equals(existingAcademy);
    if (isNewAcademy)
    {
      aCourse.setAcademy(this);
    }
    else
    {
      courses.add(aCourse);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCourse(Course aCourse)
  {
    boolean wasRemoved = false;
    //Unable to remove aCourse, as it must always have a academy
    if (!this.equals(aCourse.getAcademy()))
    {
      courses.remove(aCourse);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCourseAt(Course aCourse, int index)
  {  
    boolean wasAdded = false;
    if(addCourse(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseAt(Course aCourse, int index)
  {
    boolean wasAdded = false;
    if(courses.contains(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseAt(aCourse, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfRegistrants()
  {
    return 0;
  }

  public Student addRegistrant(int aId, String aName)
  {
    return new Student(aId, aName, this);
  }

  public boolean addRegistrant(Student aRegistrant)
  {
    boolean wasAdded = false;
    if (registrants.contains(aRegistrant)) { return false; }
    Academy existingAcademy = aRegistrant.getAcademy();
    boolean isNewAcademy = existingAcademy != null && !this.equals(existingAcademy);
    if (isNewAcademy)
    {
      aRegistrant.setAcademy(this);
    }
    else
    {
      registrants.add(aRegistrant);
    }
    wasAdded = true;
    if(wasAdded)
        Collections.sort(registrants, registrantsPriority);
    
    return wasAdded;
  }

  public boolean removeRegistrant(Student aRegistrant)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistrant, as it must always have a academy
    if (!this.equals(aRegistrant.getAcademy()))
    {
      registrants.remove(aRegistrant);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  
  public void delete()
  {
    for(int i=courses.size(); i > 0; i--)
    {
      Course aCourse = courses.get(i - 1);
      aCourse.delete();
    }
    for(int i=registrants.size(); i > 0; i--)
    {
      Student aRegistrant = registrants.get(i - 1);
      aRegistrant.delete();
    }
  }

  @umplesourcefile(line={54},file={"Sorted.ump"},javaline={258},length={12})
   public String toString(){
    String result="Students:\n";
    for (Student s: getRegistrants()) {
      result +=s + "\n";
    }
    result +="Courses:\n";
    for (Course c: getCourses()) {
      result +=c + "\n";
    }

    return result;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  //  @umplesourcefile(line={37},file={"Sorted.ump"},javaline={275},length={18})
  @umplesourcefile(line={38},file={"Sorted.ump"},javaline={276},length={19})
  public static void main(String [ ] args) 
  {
    Thread.currentThread().setUncaughtExceptionHandler(new UmpleExceptionHandler());
    Thread.setDefaultUncaughtExceptionHandler(new UmpleExceptionHandler());
    Academy ac = new Academy();
    Student j = ac.addRegistrant(12,"Jim");
    Student a = ac.addRegistrant(4,"Ali");
    Student m = ac.addRegistrant(8,"Mary");
    Student f = ac.addRegistrant(3,"Francois");
    Course c = ac.addCourse("CS191");
    Course c2 = ac.addCourse("AN234");    
    j.addRegistration(c);
    a.addRegistration(c);
    m.addRegistration(c);
    f.addRegistration(c);
    m.addRegistration(c2);
    f.addRegistration(c2);
    System.out.println(ac);
  }

  public static class UmpleExceptionHandler implements Thread.UncaughtExceptionHandler
  {
    public void uncaughtException(Thread t, Throwable e)
    {
      translate(e);
      if(e.getCause()!=null)
      {
        translate(e.getCause());
      }
      e.printStackTrace();
    }
    public void translate(Throwable e)
    {
      java.util.List<StackTraceElement> result = new java.util.ArrayList<StackTraceElement>();
      StackTraceElement[] elements = e.getStackTrace();
      try
      {
        for(StackTraceElement element:elements)
        {
          Class clazz = Class.forName(element.getClassName());
          String methodName = element.getMethodName();
          boolean methodFound = false;
          for(java.lang.reflect.Method meth:clazz.getDeclaredMethods())
          {
            if(meth.getName().equals(methodName))
            {
              for(java.lang.annotation.Annotation anno: meth.getAnnotations())
              {
                if(anno.annotationType().getSimpleName().equals("umplesourcefile"))
                {
                  int[] methodlength = (int[])anno.annotationType().getMethod("length", new Class[]{}).invoke(anno,new Object[]{});
                  int[] javaline = (int[])anno.annotationType().getMethod("javaline", new Class[]{}).invoke(anno,new Object[]{});
                  int[] line = (int[])anno.annotationType().getMethod("line", new Class[]{}).invoke(anno,new Object[]{});
                  String[] file = (String[])anno.annotationType().getMethod("file", new Class[]{}).invoke(anno,new Object[]{});
                  for(int i=0;i<file.length;i++)
                  {
                    int distanceFromStart = element.getLineNumber()-javaline[i]-(("main".equals(methodName))?2:0);
                    if(file[i] == "")
                    {
                      break;
                    }
                    else if(distanceFromStart>=0&&distanceFromStart<=methodlength[i])
                    {
                      result.add(new StackTraceElement(element.getClassName(),element.getMethodName(),file[i],line[i]+distanceFromStart));
                      methodFound = true;
                      break;
                    }
                  }
                }
              }
              if(methodFound)
              {
                break;
              }
            }
          }
          if(!methodFound)
          {
            result.add(element);
          }
        }
      }
      catch (Exception e1)
      {
        e1.printStackTrace();
      }
      e.setStackTrace(result.toArray(new StackTraceElement[0]));
    }
  }
}