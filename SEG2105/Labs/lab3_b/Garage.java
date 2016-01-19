/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/



/**
 * This is a more fully-featured state machine for
 * a garage door corresponding to the diagram above
 */
// line 3 "garage.ump"
public class Garage
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Garage Attributes
  private boolean entranceClear;

  //Garage State Machines
  enum GarageDoor { Closed, Opening, Open, Closing }
  private GarageDoor GarageDoor;
  enum StateMachine1 { topLevel }
  enum StateMachine1TopLevel { Null, thread1 }
  private StateMachine1 stateMachine1;
  private StateMachine1TopLevel stateMachine1TopLevel;

  //Garage Do Activity Threads
  Thread doActivityStateMachine1TopLevelThread1Thread = null;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Garage()
  {
    entranceClear = true;
    setGarageDoor(GarageDoor.Closed);
    setStateMachine1TopLevel(StateMachine1TopLevel.Null);
    setStateMachine1(StateMachine1.topLevel);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEntranceClear(boolean aEntranceClear)
  {
    boolean wasSet = false;
    entranceClear = aEntranceClear;
    wasSet = true;
    return wasSet;
  }

  public boolean getEntranceClear()
  {
    return entranceClear;
  }

  public boolean isEntranceClear()
  {
    return entranceClear;
  }
  
  public void obstruction(){
	  entranceClear = false;
  }
  
  public void clearObstruction(){
	  entranceClear = true;
  }

  public String getGarageDoorFullName()
  {
    String answer = GarageDoor.toString();
    return answer;
  }

  public String getStateMachine1FullName()
  {
    String answer = stateMachine1.toString();
    if (stateMachine1TopLevel != StateMachine1TopLevel.Null) { answer += "." + stateMachine1TopLevel.toString(); }
    return answer;
  }

  public GarageDoor getGarageDoor()
  {
    return GarageDoor;
  }

  public StateMachine1 getStateMachine1()
  {
    return stateMachine1;
  }

  public StateMachine1TopLevel getStateMachine1TopLevel()
  {
    return stateMachine1TopLevel;
  }

  @umplesourcefile(line={10,19,24}, file={"garage.ump","garage.ump","garage.ump"}, javaline={106,111,120}, length={1,1,1})
  public boolean pressButton()
  {
    boolean wasEventProcessed = false;
    
    GarageDoor aGarageDoor = GarageDoor;
    switch (aGarageDoor)
    {
      case Closed:
        exitGarageDoor();
        // line 10 "garage.ump"
        turnLightOn();
        setGarageDoor(GarageDoor.Opening);
        wasEventProcessed = true;
        break;
      case Open:
        if (getEntranceClear())
        {
          setGarageDoor(GarageDoor.Closing);
          wasEventProcessed = true;
          break;
        }
        break;
      case Closing:
        // line 24 "garage.ump"
        flashLightOn();
        setGarageDoor(GarageDoor.Opening);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean openingCompleted()
  {
    boolean wasEventProcessed = false;
    
    GarageDoor aGarageDoor = GarageDoor;
    switch (aGarageDoor)
    {
      case Opening:
        setGarageDoor(GarageDoor.Open);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean closingCompleted()
  {
    boolean wasEventProcessed = false;
    
    GarageDoor aGarageDoor = GarageDoor;
    switch (aGarageDoor)
    {
      case Closing:
        setGarageDoor(GarageDoor.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterTopLevel()
  {
    boolean wasEventProcessed = false;
    
    StateMachine1TopLevel aStateMachine1TopLevel = stateMachine1TopLevel;
    switch (aStateMachine1TopLevel)
    {
      case Null:
        setStateMachine1TopLevel(StateMachine1TopLevel.thread1);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitTopLevel()
  {
    boolean wasEventProcessed = false;
    
    StateMachine1TopLevel aStateMachine1TopLevel = stateMachine1TopLevel;
    switch (aStateMachine1TopLevel)
    {
      case thread1:
        setStateMachine1TopLevel(StateMachine1TopLevel.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  @umplesourcefile(line={9}, file={"garage.ump"}, javaline={211}, length={1})
  private void exitGarageDoor()
  {
    switch(GarageDoor)
    {
      case Closed:
        // line 9 "garage.ump"
        triggerNormalEnergyMode();
        break;
    }
  }

  private void setGarageDoor(GarageDoor aGarageDoor)
  {
    GarageDoor = aGarageDoor;

    // entry actions and do activities
    switch(GarageDoor)
    {
      case Closed:
        // line 7 "garage.ump"
        stopMotor();
        // line 8 "garage.ump"
        triggerEnergySaveMode();
        break;
      case Opening:
        // line 13 "garage.ump"
        runMotorForward();
        break;
      case Open:
        // line 17 "garage.ump"
        stopMotor();
        break;
      case Closing:
        // line 22 "garage.ump"
        runMotorInReverse();
        break;
    }
  }

  private void exitStateMachine1()
  {
    switch(stateMachine1)
    {
      case topLevel:
        exitTopLevel();
        break;
    }
  }

  private void setStateMachine1(StateMachine1 aStateMachine1)
  {
    stateMachine1 = aStateMachine1;

    // entry actions and do activities
    switch(stateMachine1)
    {
      case topLevel:
        if (stateMachine1TopLevel == StateMachine1TopLevel.Null) { setStateMachine1TopLevel(StateMachine1TopLevel.thread1); }
        break;
    }
  }

  private void exitStateMachine1TopLevel()
  {
    switch(stateMachine1TopLevel)
    {
      case thread1:
        if (doActivityStateMachine1TopLevelThread1Thread != null) { doActivityStateMachine1TopLevelThread1Thread.interrupt(); }
        break;
    }
  }

  private void setStateMachine1TopLevel(StateMachine1TopLevel aStateMachine1TopLevel)
  {
    stateMachine1TopLevel = aStateMachine1TopLevel;
    if (stateMachine1 != StateMachine1.topLevel && aStateMachine1TopLevel != StateMachine1TopLevel.Null) { setStateMachine1(StateMachine1.topLevel); }

    // entry actions and do activities
    switch(stateMachine1TopLevel)
    {
      case thread1:
        doActivityStateMachine1TopLevelThread1Thread = new DoActivityThread(this,"doActivityStateMachine1TopLevelThread1");
        break;
    }
  }

  private void doActivityStateMachine1TopLevelThread1()
  {
    try
    {
      System.out.println("Garage door is now active");
    pressButton();
    Thread.sleep(1000);  // wait a second
    openingCompleted();
    System.exit(0);;
      Thread.sleep(1);
    }
    catch (InterruptedException e)
    {

    }
  }

  private static class DoActivityThread extends Thread
  {
    Garage controller;
    String doActivityMethodName;
    
    public DoActivityThread(Garage aController,String aDoActivityMethodName)
    {
      controller = aController;
      doActivityMethodName = aDoActivityMethodName;
      start();
    }
    
    public void run()
    {
      if ("doActivityStateMachine1TopLevelThread1".equals(doActivityMethodName))
      {
        controller.doActivityStateMachine1TopLevelThread1();
      }
    }
  }

  public void delete()
  {}

  @umplesourcefile(line={37},file={"garage.ump"},javaline={333},length={4})
  public boolean runMotorInReverse(){
    System.out.println("Running motor in reverse");
    return true;
  }

  @umplesourcefile(line={42},file={"garage.ump"},javaline={339},length={4})
  public boolean flashLightOn(){
    System.out.println("Flashing light on");
    return true;
  }

  @umplesourcefile(line={47},file={"garage.ump"},javaline={345},length={4})
  public boolean turnLightOn(){
    System.out.println("Turning light on");
    return true;
  }

  @umplesourcefile(line={52},file={"garage.ump"},javaline={351},length={4})
  public boolean turnLightOff(){
    System.out.println("Turning light off");
    return true;
  }

  @umplesourcefile(line={57},file={"garage.ump"},javaline={357},length={4})
  public boolean runMotorForward(){
    System.out.println("Running motor forwards");
    return true;
  }

  @umplesourcefile(line={62},file={"garage.ump"},javaline={363},length={4})
  public boolean triggerEnergySaveMode(){
    System.out.println("Triggering Energy Saving Mode");
    return true;
  }

  @umplesourcefile(line={67},file={"garage.ump"},javaline={369},length={4})
  public boolean stopMotor(){
    System.out.println("Stopping motor");
    return true;
  }

  @umplesourcefile(line={72},file={"garage.ump"},javaline={375},length={4})
  public boolean triggerNormalEnergyMode(){
    System.out.println("Triggering Normal Energy Mode");
         return true;
  }

  @umplesourcefile(line={77},file={"garage.ump"},javaline={381},length={4})
  public boolean waitawhile(){
    System.out.println("Waiting");
    return true;
  }

  @umplesourcefile(line={82},file={"garage.ump"},javaline={387},length={4})
  public boolean test(){
    System.out.println("Testing");
         return true;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "entranceClear" + ":" + getEntranceClear()+ "]"
     + outputString;
  }
}