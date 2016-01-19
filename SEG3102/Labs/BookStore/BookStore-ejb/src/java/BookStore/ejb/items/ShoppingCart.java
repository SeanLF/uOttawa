/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.ejb.items;

import BookStore.ejb.persistence.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ssome
 */
public class ShoppingCart implements Serializable {

  private ArrayList<ItemOrder> itemsOrdered;

  /** Builds an empty shopping cart. */

  public ShoppingCart() {
    itemsOrdered = new ArrayList<ItemOrder>();
  }

  /** Returns List of ItemOrder entries giving
   *  Item and number ordered. Declared as List instead
   *  of ArrayList so that underlying implementation
   *  can be changed later.
   */

  public List getItemsOrdered() {
    return(itemsOrdered);
  }

  /** Looks through cart to see if it already contains
   *  an order entry corresponding to item ID. If it does,
   *  increments the number ordered. If not, looks up
   *  Item in catalog and adds an order entry for it.
   */

  public synchronized void addItem(Item item) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItem().equals(item)) {
        order.incrementNumItems();
        return;
      }
    }
    ItemOrder newOrder = new ItemOrder(item);
    itemsOrdered.add(newOrder);
  }

  /** Looks through cart to find order entry corresponding
   *  to item ID listed. If the designated number
   *  is positive, sets it. If designated number is 0
   *  (or, negative due to a user input error), deletes
   *  item from cart.
   */

  public synchronized void setNumOrdered(Item item,
                                         int numOrdered) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItem().equals(item)) {
        if (numOrdered <= 0) {
          itemsOrdered.remove(i);
        } else {
          order.setNumItems(numOrdered);
        }
        return;
      }
    }
    ItemOrder newOrder =
      new ItemOrder(item);
    itemsOrdered.add(newOrder);
  }

    public double getOrderTotal() {
        double total = 0.0;
        for (ItemOrder order: itemsOrdered) {
            total = total + order.getTotalCost();
        }
        return total;
    }
}
