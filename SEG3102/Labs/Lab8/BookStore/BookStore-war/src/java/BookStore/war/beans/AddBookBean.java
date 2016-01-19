/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookStore.war.beans;

import BookStore.ejb.beans.ItemFacadeLocal;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ssome
 */
@Named(value = "addBookBean")
@RequestScoped
public class AddBookBean {
    @EJB
    private ItemFacadeLocal itemFacade;
    private String category;
    private String id;
    private String shortDescription;
    private String longDescription;
    private String status;
    
    private double cost;

    /**
     * Get the value of cost
     *
     * @return the value of cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Set the value of cost
     *
     * @param cost new value of cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    
    /**
     * Creates a new instance of addBookBean
     */
    public AddBookBean() {
    }
    

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return the longDescription
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * @param longDescription the longDescription to set
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void addBook() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        if (itemFacade.addItem(id, shortDescription,longDescription,cost, category)) {
           status = bundle.getString("addOk");
        } else {
           status = bundle.getString("addFail");
        }
    }
}


