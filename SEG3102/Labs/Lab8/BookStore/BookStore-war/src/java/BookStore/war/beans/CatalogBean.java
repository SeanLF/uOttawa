/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.war.beans;

import BookStore.ejb.beans.ItemFacadeLocal;
import BookStore.ejb.beans.OrderEJBLocal;
import BookStore.ejb.persistence.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ssome
 */
@Named(value = "catalogBean")
@SessionScoped
public class CatalogBean implements Serializable {
    @EJB
    private ItemFacadeLocal itemFacade;
     
    @EJB
    private OrderEJBLocal orderEJB;
    
    private String orderBookId;
    private int updateOrderNumber;
    private String catalogTitle;
    private ArrayList<CatalogItem> items;
    private double orderTotal;
    
    /**
     * Get the value of catalogTitle
     *
     * @return the value of catalogTitle
     */
    public String getCatalogTitle() {
        return catalogTitle;
    }

    /**
     * Set the value of catalogTitle
     *
     * @param catalogTitle new value of catalogTitle
     */
    public void setCatalogTitle(String catalogTitle) {
        this.catalogTitle = catalogTitle;
    }

        /**
     * Get the value of orderBookId
     *
     * @return the value of orderBookId
     */
    public String getOrderBookId() {
        return orderBookId;
    }

    /**
     * Set the value of orderBookId
     *
     * @param orderBookId new value of orderBookId
     */
    public void setOrderBookId(String orderBookId) {
        this.orderBookId = orderBookId;
    }


    /**
     * @return the items
     */
    public ArrayList<CatalogItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<CatalogItem> items) {
        this.items = items;
    }
    
        /**
     * @return the updateOrderNumber
     */
    public int getUpdateOrderNumber() {
        return updateOrderNumber;
    }

    /**
     * @param updateOrderNumber the updateOrderNumber to set
     */
    public void setUpdateOrderNumber(int updateOrderNumber) {
        this.updateOrderNumber = updateOrderNumber;
    }
    
    /**
     * Creates a new instance of CatalogBean
     */
    public CatalogBean() {
    }
    
    public String getKidsBooks() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        catalogTitle = bundle.getString("kidsCatalogTitle");
        items=new ArrayList<>();
        List<Item> resultList = itemFacade.findByCategory("kids");
        if (resultList != null) {
            for (Item resultList1 : resultList) {
                Item x = (Item) resultList1;
                items.add(new CatalogItem(x.getItemID(),x.getShortDescription(),x.getLongDescription(),x.getCost(),x.getCategory()));
            }
        }
        return "catalogPage";
    }
    
    public String getTechBooks() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        catalogTitle = bundle.getString("techCatalogTitle"); 
        items=new ArrayList<>();
        List<Item> resultList = itemFacade.findByCategory("tech");
        if (resultList != null) {
            for (Item resultList1 : resultList) {
                Item x = (Item) resultList1;
                items.add(new CatalogItem(x.getItemID(),x.getShortDescription(),x.getLongDescription(),x.getCost(),x.getCategory()));
            }
        }
        return "catalogPage";
    }
    
    public List getItemsOrdered() {
        return orderEJB.getItemsOrdered();
    }
    
    public void orderBook() {
        orderEJB.addItem(orderBookId);
    }
    public void updateOrder() {
        orderEJB.setNumOrdered(orderBookId, updateOrderNumber);
    }

        /**
     * @return the orderTotal
     */
    public double getOrderTotal() {
        return orderTotal;
    }

    /**
     * @param orderTotal the orderTotal to set
     */
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * @return the orderTaxes
     */
    public double getOrderTaxes() {
        // apply 13% taxes
        double orderTaxes = orderTotal * 0.13;
        return orderTaxes;
    }

    /**
     * @return the orderShipping
     */
    public double getOrderShipping() {
        // shipping is 5% + $10
        double orderShipping = orderTotal * 0.05 + 10.0;
        return orderShipping;
    }

    public double getGrandTotal() {
        return orderTotal + getOrderTaxes() + getOrderShipping();
    }
    
    public String checkout() {
        orderTotal = orderEJB.getOrderTotal();
        return "checkoutPage";
    }
}
