/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.ejb.beans;

import BookStore.ejb.persistence.Item;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ssome
 */
@Local
public interface ItemFacadeLocal {

    void create(Item item);

    void edit(Item item);

    void remove(Item item);

    Item find(Object id);

    List<Item> findAll();

    List<Item> findRange(int[] range);

    int count();
    
    public boolean addItem(String itemID, String shortDesc, String longDesc, double cost, String category);
    
    public List<Item> findByCategory(String category);
    
}
