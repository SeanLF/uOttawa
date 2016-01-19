/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import beans.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ssome
 */
@Entity
@Table(name="Address")
public class Address implements Serializable {
    private static long serialVersionUID = 1L;
    
     @Id
    private String EMPLOYEE_ID; 
    private String STREETNUMBER;
    private String STREETNAME; 
    private String UNIT; 
    private String CITY; 
    private String PROVINCE; 
    private String POSTAL; 
    
    /** Creates a new instance of User */
    public Address() {
      
    }
    public Address(String empId, String streetNumber, String streetName, String city, String province, String postal){
        this.EMPLOYEE_ID = empId;
        this.STREETNUMBER = streetNumber;
        this.STREETNAME = streetName;
        this.CITY = city;
        this.PROVINCE = province;
        this.POSTAL = postal;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += this.getEMPLOYEE_ID().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address)object;
        return (this.getEMPLOYEE_ID().equals(other.getEMPLOYEE_ID()));
    }

    @Override
    public String toString() {
        return "dbaccess.persistence.Address[id=" + getEMPLOYEE_ID() + "]";
    }

    /**
     * 
     * @param adrData
     * @return true if this Employee matches the empData bean
     */
    public boolean matches(AddressData adrData) {
        if (!"".equals(adrData.getEmployee_id()) && !this.getEMPLOYEE_ID().trim().equals(adrData.getEmployee_id().trim())) {
                return false;
        } else if (!"".equals(adrData.getStreetnumber()) && !this.getSTREETNUMBER().trim().equals(adrData.getStreetnumber().trim())) {
                return false;
        } else if (!"".equals(adrData.getStreetname()) && !this.getSTREETNAME().trim().equals(adrData.getStreetname().trim())) {
                return false;
        } else if (!"".equals(adrData.getUnit()) && !this.getUNIT().trim().equals(adrData.getUnit().trim())) {
                return false;
        } else if (!"".equals(adrData.getCity()) && !this.getCITY().trim().equals(adrData.getCity().trim())) {
                return false;
        } else if (!"".equals(adrData.getPostal()) && !this.getPOSTAL().trim().equals(adrData.getPostal().trim())) {
                return false;
        } else if (!"".equals(adrData.getProvince()) && !this.getPROVINCE().trim().equals(adrData.getProvince().trim())) {
                return false;
        }
        return true;
    }

    /**
     * @return the EMPLOYEE_ID
     */
    public String getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }

    /**
     * @param EMPLOYEE_ID the EMPLOYEE_ID to set
     */
    public void setEMPLOYEE_ID(String EMPLOYEE_ID) {
        this.EMPLOYEE_ID = EMPLOYEE_ID;
    }

    /**
     * @return the STREETNUMBER
     */
    public String getSTREETNUMBER() {
        return STREETNUMBER;
    }

    /**
     * @param STREETNUMBER the STREETNUMBER to set
     */
    public void setSTREETNUMBER(String STREETNUMBER) {
        this.STREETNUMBER = STREETNUMBER;
    }

    /**
     * @return the STREETNAME
     */
    public String getSTREETNAME() {
        return STREETNAME;
    }

    /**
     * @param STREETNAME the STREETNAME to set
     */
    public void setSTREETNAME(String STREETNAME) {
        this.STREETNAME = STREETNAME;
    }

    /**
     * @return the UNIT
     */
    public String getUNIT() {
        return UNIT;
    }

    /**
     * @param UNIT the UNIT to set
     */
    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    /**
     * @return the CITY
     */
    public String getCITY() {
        return CITY;
    }

    /**
     * @param CITY the CITY to set
     */
    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    /**
     * @return the PROVINCE
     */
    public String getPROVINCE() {
        return PROVINCE;
    }

    /**
     * @param PROVINCE the PROVINCE to set
     */
    public void setPROVINCE(String PROVINCE) {
        this.PROVINCE = PROVINCE;
    }

    /**
     * @return the POSTAL
     */
    public String getPOSTAL() {
        return POSTAL;
    }

    /**
     * @param POSTAL the POSTAL to set
     */
    public void setPOSTAL(String POSTAL) {
        this.POSTAL = POSTAL;
    }

    
}