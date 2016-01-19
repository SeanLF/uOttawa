package BookStore.ejb.items;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import BookStore.ejb.persistence.Item;
import java.io.Serializable;

/**
 *
 * @author ssome
 */
public class ItemOrder implements Serializable {
  private Item item;
  private int numItems;

  public ItemOrder(Item item) {
    setItem(item);
    setNumItems(1);
  }

  public Item getItem() {
    return(item);
  }

  protected final void setItem(Item item) {
    this.item = item;
  }

  public String getItemID() {
    return(getItem().getItemID());
  }

  public String getShortDescription() {
    return(getItem().getShortDescription());
  }

  public String getLongDescription() {
    return(getItem().getLongDescription());
  }

  public double getUnitCost() {
    return(getItem().getCost());
  }

  public int getNumItems() {
    return(numItems);
  }

  public final void setNumItems(int n) {
    this.numItems = n;
  }

  public void incrementNumItems() {
    setNumItems(getNumItems() + 1);
  }

  public void cancelOrder() {
    setNumItems(0);
  }

  public double getTotalCost() {
    return(getNumItems() * getUnitCost());
  }
  public String toString() {
      return "ItemOrder[" + this.item + " " + this.numItems + "]";
  }
}
