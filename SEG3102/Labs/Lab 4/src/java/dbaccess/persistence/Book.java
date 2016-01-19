/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.persistence;

import dbaccess.beans.BookData;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

/**
 *
 * @author ssome
 */
@Entity
@Table(name="Book")
public class Book implements Serializable {
    private static long serialVersionUID = 1L;
@Id
@Column(name="BOOK_ID")
    private String BOOK_ID;
@Column(name="NAME")
    private String NAME;
@Column(name="AUTHOR")
    private String AUTHOR;
@Column(name="PRICE")
    private String PRICE;
@Column(name="DESCRIPTION")
    private String DESCRIPTION;
@Column(name="TYPE")
    private String TYPE;
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    
     
    
    /** Creates a new instance of User */
    public Book() {
      
    }
    public Book(String id, String name, String author, String price, String description, String type) {
        this.BOOK_ID = id;
        this.NAME = name;
        this.AUTHOR =  author;
        this.PRICE = price;
        this.DESCRIPTION = description;
        this.TYPE = type;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += this.getBOOK_ID().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book)object;
        return (this.getBOOK_ID().equals(other.BOOK_ID));
    }

    @Override
    public String toString() {
        return "dbaccess.persistence.Books[id=" + getBOOK_ID() + "]";
    }

    /**
     * 
     * @param book
     * @return true if this User matches the userData bean
     */
    public boolean matches(BookData book) {
        if (!"".equals(book.getId()) && !this.getBOOK_ID().trim().equals(book.getId().trim())) {
            return false;
        } else if (!"".equals(book.getName()) && !this.getNAME().trim().equals(book.getName().trim())) {
                return false;
        } else if (!"".equals(book.getAuthor()) && !this.getAUTHOR().trim().equals(book.getAuthor().trim())) {
                return false;
        } else if (!"".equals(book.getPrice()) && !this.getPRICE().trim().equals(book.getPrice().trim())) {
                return false;
        } else if (!"".equals(book.getDescription()) && !this.getDESCRIPTION().trim().equals(book.getDescription().trim())) {
                return false;
        } else if (!"".equals(book.getType()) && !this.getTYPE().trim().equals(book.getType().trim())) {
                return false;
        }
        return true;
    }

    /**
     * @return the BOOK_ID
     */
    public String getBOOK_ID() {
        return BOOK_ID;
    }

    /**
     * @param BOOK_ID the BOOK_ID to set
     */
    public void setBOOK_ID(String BOOK_ID) {
        this.BOOK_ID = BOOK_ID;
    }

    /**
     * @return the NAME
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * @param NAME the NAME to set
     */
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    /**
     * @return the AUTHOR
     */
    public String getAUTHOR() {
        return AUTHOR;
    }

    /**
     * @param AUTHOR the AUTHOR to set
     */
    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    /**
     * @return the PRICE
     */
    public String getPRICE() {
        return PRICE;
    }

    /**
     * @param PRICE the PRICE to set
     */
    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    /**
     * @return the DESCRIPTION
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    /**
     * @param DESCRIPTION the DESCRIPTION to set
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    /**
     * @return the TYPE
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     * @param TYPE the TYPE to set
     */
    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
    
}
