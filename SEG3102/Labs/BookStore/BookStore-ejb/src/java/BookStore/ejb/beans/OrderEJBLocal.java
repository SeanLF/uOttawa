/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.ejb.beans;

import BookStore.ejb.items.ShoppingCart;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ssome
 */
@Local
public interface OrderEJBLocal {

    public ShoppingCart getCart();

    public void addItem(String itemID);

    public void setNumOrdered(String itemID, int numItems);

    public List getItemsOrdered();

    public double getOrderTotal();
    
}
