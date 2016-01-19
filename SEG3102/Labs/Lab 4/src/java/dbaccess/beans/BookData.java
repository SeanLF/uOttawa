/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.beans;

/**
 *
 * @author ssome
 */
public class BookData {
    private String id;
    private String name;
    private String author;
    private String price;
    private String description;
    private String type;
    
    /** Creates a new instance of UserData */
    public BookData() {
    }
    

    public boolean isComplete() {
        if (getId() == null || getName() == null || getAuthor() == null || getPrice() == null || getDescription() == null || getType() == null) {
            return false;
        }
        return !"".equals(id) && !"".equals(name) && !"".equals(author) && !"".equals(price) && !"".equals(description) && !"".equals(type);
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
