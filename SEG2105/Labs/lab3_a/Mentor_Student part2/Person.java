/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3087 modeling language!*/



/**
 * Introductory example of Umple showing classes,
 * attribute, association, generalization, methods
 * and the mixin capability. Generate java and run this.
 * 
 * The output will be:
 * The mentor of Tom The Student is Nick The Mentor
 * The students of Nick The Mentor are [Tom The Student]
 */
// line 11 "model.ump"
// line 30 "model.ump"
// line 48 "model.ump"
public class Person
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  /**
   * Attribute, string by default
   */
  @umplesourcefile(line={13},file={"model.ump"},javaline={60},length={3})
  public String toString(){
    return(getName());
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  //  @umplesourcefile(line={33},file={"model.ump"},javaline={72},length={9})
  @umplesourcefile(line={34},file={"model.ump"},javaline={73},length={10})
  public static void main(String [ ] args) 
  {
    Thread.currentThread().setUncaughtExceptionHandler(new UmpleExceptionHandler());
    Thread.setDefaultUncaughtExceptionHandler(new UmpleExceptionHandler());
    Mentor m = new Mentor("Nick The Mentor");
    Student s = new Student("Tom The Student");
    Student t = new Student("Me the 2nd student");
    Student u = new Student("Z the 3d student");
    s.setMentor(m);
    t.setMentor(m);
    if(u.setMentor(m)){
      System.out.print("worked");
    }
    else {
      System.out.println("Warning, can't add more than 2 students!");}
    System.out.println("The mentor of " +   s  +  " is " +   s.getMentor());
    System.out.println("The students of " +   m  +  " are " +   m.getStudents());
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