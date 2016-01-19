/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.ejb.beans;

import BookStore.ejb.persistence.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ssome
 */
@Stateless
public class ItemFacade extends AbstractFacade<Item> implements ItemFacadeLocal {
    @PersistenceContext(unitName = "BookStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }

    @Override
    public boolean addItem(String itemID, String shortDesc, String longDesc, double cost, String category) {
        Item item= new Item(itemID, shortDesc, longDesc, cost, category);
        try {
            create(item);
        } catch (Exception e) {
            return false ;
        }
        return true;
    }

    @Override
    public List<Item> findByCategory(String category) {
        try {
            Query query = em.createQuery(
                "SELECT u FROM Item u" +
                " WHERE u.category = :ItemCategory");
            query.setParameter("ItemCategory",category);

            List resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
        }
        return null;
    }    
}
