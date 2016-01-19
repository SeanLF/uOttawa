/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3087 modeling language!*/


import java.util.*;

/**
 * Type of membership card such as AirMiles
 */
// line 33 "model.ump"
public class MembershipCardType
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MembershipCardType Attributes
  private String name;

  //MembershipCardType Associations
  private List<MembershipCard> membershipCards;
  private List<SpecialPrice> specialPrices;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MembershipCardType(String aName)
  {
    name = aName;
    membershipCards = new ArrayList<MembershipCard>();
    specialPrices = new ArrayList<SpecialPrice>();
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

  public MembershipCard getMembershipCard(int index)
  {
    MembershipCard aMembershipCard = membershipCards.get(index);
    return aMembershipCard;
  }

  public List<MembershipCard> getMembershipCards()
  {
    List<MembershipCard> newMembershipCards = Collections.unmodifiableList(membershipCards);
    return newMembershipCards;
  }

  public int numberOfMembershipCards()
  {
    int number = membershipCards.size();
    return number;
  }

  public boolean hasMembershipCards()
  {
    boolean has = membershipCards.size() > 0;
    return has;
  }

  public int indexOfMembershipCard(MembershipCard aMembershipCard)
  {
    int index = membershipCards.indexOf(aMembershipCard);
    return index;
  }

  public SpecialPrice getSpecialPrice(int index)
  {
    SpecialPrice aSpecialPrice = specialPrices.get(index);
    return aSpecialPrice;
  }

  public List<SpecialPrice> getSpecialPrices()
  {
    List<SpecialPrice> newSpecialPrices = Collections.unmodifiableList(specialPrices);
    return newSpecialPrices;
  }

  public int numberOfSpecialPrices()
  {
    int number = specialPrices.size();
    return number;
  }

  public boolean hasSpecialPrices()
  {
    boolean has = specialPrices.size() > 0;
    return has;
  }

  public int indexOfSpecialPrice(SpecialPrice aSpecialPrice)
  {
    int index = specialPrices.indexOf(aSpecialPrice);
    return index;
  }

  public static int minimumNumberOfMembershipCards()
  {
    return 0;
  }

  public MembershipCard addMembershipCard(int aNumber)
  {
    return new MembershipCard(aNumber, this);
  }

  public boolean addMembershipCard(MembershipCard aMembershipCard)
  {
    boolean wasAdded = false;
    if (membershipCards.contains(aMembershipCard)) { return false; }
    MembershipCardType existingMembershipCardType = aMembershipCard.getMembershipCardType();
    boolean isNewMembershipCardType = existingMembershipCardType != null && !this.equals(existingMembershipCardType);
    if (isNewMembershipCardType)
    {
      aMembershipCard.setMembershipCardType(this);
    }
    else
    {
      membershipCards.add(aMembershipCard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMembershipCard(MembershipCard aMembershipCard)
  {
    boolean wasRemoved = false;
    //Unable to remove aMembershipCard, as it must always have a membershipCardType
    if (!this.equals(aMembershipCard.getMembershipCardType()))
    {
      membershipCards.remove(aMembershipCard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMembershipCardAt(MembershipCard aMembershipCard, int index)
  {  
    boolean wasAdded = false;
    if(addMembershipCard(aMembershipCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembershipCards()) { index = numberOfMembershipCards() - 1; }
      membershipCards.remove(aMembershipCard);
      membershipCards.add(index, aMembershipCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMembershipCardAt(MembershipCard aMembershipCard, int index)
  {
    boolean wasAdded = false;
    if(membershipCards.contains(aMembershipCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembershipCards()) { index = numberOfMembershipCards() - 1; }
      membershipCards.remove(aMembershipCard);
      membershipCards.add(index, aMembershipCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMembershipCardAt(aMembershipCard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSpecialPrices()
  {
    return 0;
  }

  public SpecialPrice addSpecialPrice(int aPriceInCents, int aNumberToBuyToGetPrice, Item aItem)
  {
    return new SpecialPrice(aPriceInCents, aNumberToBuyToGetPrice, aItem, this);
  }

  public boolean addSpecialPrice(SpecialPrice aSpecialPrice)
  {
    boolean wasAdded = false;
    if (specialPrices.contains(aSpecialPrice)) { return false; }
    MembershipCardType existingMembershipCardType = aSpecialPrice.getMembershipCardType();
    boolean isNewMembershipCardType = existingMembershipCardType != null && !this.equals(existingMembershipCardType);
    if (isNewMembershipCardType)
    {
      aSpecialPrice.setMembershipCardType(this);
    }
    else
    {
      specialPrices.add(aSpecialPrice);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecialPrice(SpecialPrice aSpecialPrice)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecialPrice, as it must always have a membershipCardType
    if (!this.equals(aSpecialPrice.getMembershipCardType()))
    {
      specialPrices.remove(aSpecialPrice);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSpecialPriceAt(SpecialPrice aSpecialPrice, int index)
  {  
    boolean wasAdded = false;
    if(addSpecialPrice(aSpecialPrice))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecialPrices()) { index = numberOfSpecialPrices() - 1; }
      specialPrices.remove(aSpecialPrice);
      specialPrices.add(index, aSpecialPrice);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecialPriceAt(SpecialPrice aSpecialPrice, int index)
  {
    boolean wasAdded = false;
    if(specialPrices.contains(aSpecialPrice))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecialPrices()) { index = numberOfSpecialPrices() - 1; }
      specialPrices.remove(aSpecialPrice);
      specialPrices.add(index, aSpecialPrice);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecialPriceAt(aSpecialPrice, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=membershipCards.size(); i > 0; i--)
    {
      MembershipCard aMembershipCard = membershipCards.get(i - 1);
      aMembershipCard.delete();
    }
    for(int i=specialPrices.size(); i > 0; i--)
    {
      SpecialPrice aSpecialPrice = specialPrices.get(i - 1);
      aSpecialPrice.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}