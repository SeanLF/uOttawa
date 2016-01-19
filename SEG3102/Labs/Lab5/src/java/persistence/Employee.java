/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import beans.EmployeeData;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ssome
 */
@Entity
@Table(name="Employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
     @Id
    private String EMPLOYEE_ID;
    private String NAME;
    private String LASTNAME;    
    
    /** Creates a new instance of User */
    public Employee() {
      
    }
    public Employee(String id, String name, String lastName){
        this.EMPLOYEE_ID = id;
        this.NAME = name;
        this.LASTNAME = lastName;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee)object;
        return (this.getEMPLOYEE_ID().equals(other.getEMPLOYEE_ID()));
    }

    @Override
    public String toString() {
        return "dbaccess.persistence.Employee[id=" + getEMPLOYEE_ID() + "]";
    }

    /**
     * 
     * @param empData
     * @return true if this Employee matches the empData bean
     */
    public boolean matches(EmployeeData empData) {
        if (!"".equals(empData.getId()) && !this.getEMPLOYEE_ID().trim().equals(empData.getId().trim())) {
            return false;
        } else if (!"".equals(empData.getName()) && !this.getNAME().trim().equals(empData.getName().trim())) {
                return false;
        } else if (!"".equals(empData.getLastName()) && !this.getLASTNAME().trim().equals(empData.getLastName().trim())) {
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
     * @return the BIRTHDATE
     */
    public String getLASTNAME() {
        return LASTNAME;
    }

    /**
     * @param LASTNAME the LASTNAME to set
     */
    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }
}