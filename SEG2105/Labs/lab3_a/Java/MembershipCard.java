/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3087 modeling language!*/


import java.util.*;

/**
 * Membership card belonging to a particular person
 */
// line 38 "model.ump"
public class MembershipCard
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MembershipCard Attributes
  private int number;

  //MembershipCard Associations
  private MembershipCardType membershipCardType;
  private List<Purchase> purchases;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MembershipCard(int aNumber, MembershipCardType aMembershipCardType)
  {
    number = aNumber;
    boolean didAddMembershipCardType = setMembershipCardType(aMembershipCardType);
    if (!didAddMembershipCardType)
    {
      throw new RuntimeException("Unable to create membershipCard due to membershipCardType");
    }
    purchases = new ArrayList<Purchase>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public MembershipCardType getMembershipCardType()
  {
    return membershipCardType;
  }

  public Purchase getPurchase(int index)
  {
    Purchase aPurchase = purchases.get(index);
    return aPurchase;
  }

  public List<Purchase> getPurchases()
  {
    List<Purchase> newPurchases = Collections.unmodifiableList(purchases);
    return newPurchases;
  }

  public int numberOfPurchases()
  {
    int number = purchases.size();
    return number;
  }

  public boolean hasPurchases()
  {
    boolean has = purchases.size() > 0;
    return has;
  }

  public int indexOfPurchase(Purchase aPurchase)
  {
    int index = purchases.indexOf(aPurchase);
    return index;
  }

  public boolean setMembershipCardType(MembershipCardType aMembershipCardType)
  {
    boolean wasSet = false;
    if (aMembershipCardType == null)
    {
      return wasSet;
    }

    MembershipCardType existingMembershipCardType = membershipCardType;
    membershipCardType = aMembershipCardType;
    if (existingMembershipCardType != null && !existingMembershipCardType.equals(aMembershipCardType))
    {
      existingMembershipCardType.removeMembershipCard(this);
    }
    membershipCardType.addMembershipCard(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfPurchases()
  {
    return 0;
  }

  public boolean addPurchase(Purchase aPurchase)
  {
    boolean wasAdded = false;
    if (purchases.contains(aPurchase)) { return false; }
    MembershipCard existingMembershipCard = aPurchase.getMembershipCard();
    if (existingMembershipCard == null)
    {
      aPurchase.setMembershipCard(this);
    }
    else if (!this.equals(existingMembershipCard))
    {
      existingMembershipCard.removePurchase(aPurchase);
      addPurchase(aPurchase);
    }
    else
    {
      purchases.add(aPurchase);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePurchase(Purchase aPurchase)
  {
    boolean wasRemoved = false;
    if (purchases.contains(aPurchase))
    {
      purchases.remove(aPurchase);
      aPurchase.setMembershipCard(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPurchaseAt(Purchase aPurchase, int index)
  {  
    boolean wasAdded = false;
    if(addPurchase(aPurchase))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPurchases()) { index = numberOfPurchases() - 1; }
      purchases.remove(aPurchase);
      purchases.add(index, aPurchase);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePurchaseAt(Purchase aPurchase, int index)
  {
    boolean wasAdded = false;
    if(purchases.contains(aPurchase))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPurchases()) { index = numberOfPurchases() - 1; }
      purchases.remove(aPurchase);
      purchases.add(index, aPurchase);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPurchaseAt(aPurchase, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    MembershipCardType placeholderMembershipCardType = membershipCardType;
    this.membershipCardType = null;
    placeholderMembershipCardType.removeMembershipCard(this);
    for(Purchase aPurchase : purchases)
    {
      aPurchase.setMembershipCard(null);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "membershipCardType = "+(getMembershipCardType()!=null?Integer.toHexString(System.identityHashCode(getMembershipCardType())):"null")
     + outputString;
  }
}