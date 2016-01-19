/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/



// line 18 "Sorted.ump"
public class Registration
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Registration Associations
  private Student student;
  private Course course;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Registration(Student aStudent, Course aCourse)
  {
    boolean didAddStudent = setStudent(aStudent);
    if (!didAddStudent)
    {
      throw new RuntimeException("Unable to create registration due to student");
    }
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create registration due to course");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  @umplesourcefile(line={25},file={"Sorted.ump"},javaline={50},length={2})
  /**
   * Derived delegated attribute used for sorting printing
   */

  public String getName()
  {
    return getStudent().getName();
  }

  @umplesourcefile(line={28},file={"Sorted.ump"},javaline={59},length={2})
  /**
   * Derived delegated attribute used for sorting printing
   */

  public String getCode()
  {
    return getCourse().getCode();
  }

  public Student getStudent()
  {
    return student;
  }

  public Course getCourse()
  {
    return course;
  }

  public boolean setStudent(Student aStudent)
  {
    boolean wasSet = false;
    if (aStudent == null)
    {
      return wasSet;
    }

    Student existingStudent = student;
    student = aStudent;
    if (existingStudent != null && !existingStudent.equals(aStudent))
    {
      existingStudent.removeRegistration(this);
    }
    student.addRegistration(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setCourse(Course aCourse)
  {
    boolean wasSet = false;
    if (aCourse == null)
    {
      return wasSet;
    }

    Course existingCourse = course;
    course = aCourse;
    if (existingCourse != null && !existingCourse.equals(aCourse))
    {
      existingCourse.removeRegistration(this);
    }
    course.addRegistration(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Student placeholderStudent = student;
    this.student = null;
    placeholderStudent.removeRegistration(this);
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeRegistration(this);
  }

  @umplesourcefile(line={31},file={"Sorted.ump"},javaline={121},length={3})
   public String toString(){
    return "Registration: " + getName() + ":" + getCode();
  }

}