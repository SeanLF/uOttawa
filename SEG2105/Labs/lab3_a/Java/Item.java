/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3087 modeling language!*/


import java.util.*;

// line 10 "model.ump"
public class Item
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private boolean byWeight;
  private boolean taxable;
  private int defaultPriceInCents;

  //Item Associations
  private Store stockedIn;
  private List<Purchase> purchases;
  private List<SpecialPrice> specialPrices;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(boolean aByWeight, boolean aTaxable, int aDefaultPriceInCents)
  {
    byWeight = aByWeight;
    taxable = aTaxable;
    defaultPriceInCents = aDefaultPriceInCents;
    purchases = new ArrayList<Purchase>();
    specialPrices = new ArrayList<SpecialPrice>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setByWeight(boolean aByWeight)
  {
    boolean wasSet = false;
    byWeight = aByWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setTaxable(boolean aTaxable)
  {
    boolean wasSet = false;
    taxable = aTaxable;
    wasSet = true;
    return wasSet;
  }

  public boolean setDefaultPriceInCents(int aDefaultPriceInCents)
  {
    boolean wasSet = false;
    defaultPriceInCents = aDefaultPriceInCents;
    wasSet = true;
    return wasSet;
  }

  /**
   * If true, then the price per kg; otherwise price per item
   */
  public boolean getByWeight()
  {
    return byWeight;
  }

  public boolean getTaxable()
  {
    return taxable;
  }

  /**
   * Price in cents; can be overridden by special prices
   */
  public int getDefaultPriceInCents()
  {
    return defaultPriceInCents;
  }

  public boolean isByWeight()
  {
    return byWeight;
  }

  public boolean isTaxable()
  {
    return taxable;
  }

  public Store getStockedIn()
  {
    return stockedIn;
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

  public boolean setStockedIn(Store aStockedIn)
  {
    boolean wasSet = false;
    Store existingStockedIn = stockedIn;
    stockedIn = aStockedIn;
    if (existingStockedIn != null && !existingStockedIn.equals(aStockedIn))
    {
      existingStockedIn.removeItem(this);
    }
    if (aStockedIn != null)
    {
      aStockedIn.addItem(this);
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfPurchases()
  {
    return 0;
  }

  public Purchase addPurchase(String aNumberOfItems)
  {
    return new Purchase(aNumberOfItems, this);
  }

  public boolean addPurchase(Purchase aPurchase)
  {
    boolean wasAdded = false;
    if (purchases.contains(aPurchase)) { return false; }
    Item existingItem = aPurchase.getItem();
    boolean isNewItem = existingItem != null && !this.equals(existingItem);
    if (isNewItem)
    {
      aPurchase.setItem(this);
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
    //Unable to remove aPurchase, as it must always have a item
    if (!this.equals(aPurchase.getItem()))
    {
      purchases.remove(aPurchase);
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

  public static int minimumNumberOfSpecialPrices()
  {
    return 0;
  }

  public SpecialPrice addSpecialPrice(int aPriceInCents, int aNumberToBuyToGetPrice, MembershipCardType aMembershipCardType)
  {
    return new SpecialPrice(aPriceInCents, aNumberToBuyToGetPrice, this, aMembershipCardType);
  }

  public boolean addSpecialPrice(SpecialPrice aSpecialPrice)
  {
    boolean wasAdded = false;
    if (specialPrices.contains(aSpecialPrice)) { return false; }
    Item existingItem = aSpecialPrice.getItem();
    boolean isNewItem = existingItem != null && !this.equals(existingItem);
    if (isNewItem)
    {
      aSpecialPrice.setItem(this);
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
    //Unable to remove aSpecialPrice, as it must always have a item
    if (!this.equals(aSpecialPrice.getItem()))
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
    if (stockedIn != null)
    {
      Store placeholderStockedIn = stockedIn;
      this.stockedIn = null;
      placeholderStockedIn.removeItem(this);
    }
    for(int i=purchases.size(); i > 0; i--)
    {
      Purchase aPurchase = purchases.get(i - 1);
      aPurchase.delete();
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
            "byWeight" + ":" + getByWeight()+ "," +
            "taxable" + ":" + getTaxable()+ "," +
            "defaultPriceInCents" + ":" + getDefaultPriceInCents()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "stockedIn = "+(getStockedIn()!=null?Integer.toHexString(System.identityHashCode(getStockedIn())):"null")
     + outputString;
  }
}