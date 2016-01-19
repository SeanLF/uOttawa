/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.war.beans;

/**
 *
 * @author ssome
 */
public final class CatalogItem {
private String itemID;
  private String shortDescription;
  private String longDescription;
  private double cost;
  private String category;

  public CatalogItem(String itemID, String shortDescription,
                     String longDescription, double cost, String category) {
    setItemID(itemID);
    setShortDescription(shortDescription);
    setLongDescription(longDescription);
    setCost(cost);
    setCategory(category);
  }

  public String getItemID() {
    return(itemID);
  }

  public void setItemID(String itemID) {
    this.itemID = itemID;
  }
  public String getCategory() {
    return(category);
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getShortDescription() {
    return(shortDescription);
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getLongDescription() {
    return(longDescription);
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public double getCost() {
    return(cost);
  }

  public void setCost(double cost) {
    this.cost = cost;
  }
}
