/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.ejb.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ssome
 */
@Entity
public class Item implements Serializable {
     private static final long serialVersionUID = 1L;
        @Id
    private String itemID;
    private String shortDescription;
    private String longDescription;
    private double cost;
    private String category;

    public Item() {

    }

    /** Creates a new instance of Item
     * @param itemID
     * @param shortDescription
     * @param longDescription
     * @param cost
     * @param cat */
    public Item(String itemID, String shortDescription, String longDescription, double cost, String cat) {
        this.itemID=itemID;
        this.shortDescription=shortDescription;
        this.longDescription=longDescription;
        this.cost=cost;
        this.category=cat;
    }

    public void setItemID(String itemId) {
        this.itemID = itemId;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCategory(String category) {
        this.category=category;
    }

    public void setShortDescription(String shortDesc) {
        this.shortDescription=shortDesc;
    }

    public void setLongDescription(String longDesc) {
        this.longDescription=longDesc;
    }

    public String getItemID() {
        return itemID;
    }

    public double getCost() {
        return cost;
    }

    public String getCategory() {
        return category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.itemID != null ? this.itemID.hashCode() : 0);
        return hash;
    }

     @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item)object;
        if (this.itemID != other.itemID && (this.itemID == null || !this.itemID.equals(other.itemID))) return false;
        return true;
    }

     @Override
    public String toString() {
        return "Library.persistence.Item[id=" + itemID + "]";
    }
}
