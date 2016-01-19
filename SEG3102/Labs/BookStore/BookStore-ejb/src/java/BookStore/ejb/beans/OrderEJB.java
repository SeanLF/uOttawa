/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.ejb.beans;

import BookStore.ejb.items.ShoppingCart;
import BookStore.ejb.persistence.Item;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ssome
 */
@Stateful
public class OrderEJB implements OrderEJBLocal {
    @EJB
    ItemFacadeLocal ItemEJB;

    private ShoppingCart cart;

    /**
     * Creates a new instance of OrderEJBBean
     */
    public OrderEJB() {
        cart = new ShoppingCart() ;
    }

    @Override
    public ShoppingCart getCart() {
        return cart;
    }

    @Override
    public void addItem(String itemID) {
        Item item = ItemEJB.find(itemID);
        cart.addItem(item);
    }


    @Override
    public void setNumOrdered(String itemID, int numItems) {
        Item item = ItemEJB.find(itemID);
        cart.setNumOrdered(item, numItems);
    }


    @Override
    public List getItemsOrdered() {
        return cart.getItemsOrdered();
    }

    @Override
    public double getOrderTotal() {
        return cart.getOrderTotal();
    }
}
