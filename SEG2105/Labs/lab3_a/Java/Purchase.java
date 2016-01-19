/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3087 modeling language!*/



/**
 * Purchase of a certain number of a particular item
 */
// line 48 "model.ump"
public class Purchase
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Purchase Attributes
  private String numberOfItems;

  //Purchase Associations
  private Item item;
  private MembershipCard membershipCard;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Purchase(String aNumberOfItems, Item aItem)
  {
    numberOfItems = aNumberOfItems;
    boolean didAddItem = setItem(aItem);
    if (!didAddItem)
    {
      throw new RuntimeException("Unable to create purchase due to item");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfItems(String aNumberOfItems)
  {
    boolean wasSet = false;
    numberOfItems = aNumberOfItems;
    wasSet = true;
    return wasSet;
  }

  public String getNumberOfItems()
  {
    return numberOfItems;
  }

  public Item getItem()
  {
    return item;
  }

  public MembershipCard getMembershipCard()
  {
    return membershipCard;
  }

  public boolean setItem(Item aItem)
  {
    boolean wasSet = false;
    if (aItem == null)
    {
      return wasSet;
    }

    Item existingItem = item;
    item = aItem;
    if (existingItem != null && !existingItem.equals(aItem))
    {
      existingItem.removePurchase(this);
    }
    item.addPurchase(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setMembershipCard(MembershipCard aMembershipCard)
  {
    boolean wasSet = false;
    MembershipCard existingMembershipCard = membershipCard;
    membershipCard = aMembershipCard;
    if (existingMembershipCard != null && !existingMembershipCard.equals(aMembershipCard))
    {
      existingMembershipCard.removePurchase(this);
    }
    if (aMembershipCard != null)
    {
      aMembershipCard.addPurchase(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Item placeholderItem = item;
    this.item = null;
    placeholderItem.removePurchase(this);
    if (membershipCard != null)
    {
      MembershipCard placeholderMembershipCard = membershipCard;
      this.membershipCard = null;
      placeholderMembershipCard.removePurchase(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "numberOfItems" + ":" + getNumberOfItems()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "item = "+(getItem()!=null?Integer.toHexString(System.identityHashCode(getItem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "membershipCard = "+(getMembershipCard()!=null?Integer.toHexString(System.identityHashCode(getMembershipCard())):"null")
     + outputString;
  }
}