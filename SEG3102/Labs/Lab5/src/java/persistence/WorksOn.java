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
@Table(name="WorksOn")
public class WorksOn implements Serializable {
    private static final long serialVersionUID = 1L;
    
     @Id
    private String ID;
    private String EMPLOYEE_ID;
    private String PROJECT_ID;
    private boolean LEADS;
    
    /** Creates a new instance of WorksOn */
    public WorksOn() {
      
    }
    public WorksOn(String id, String empid, String projid,String bool){
        this.ID = id;
        this.EMPLOYEE_ID = empid;
        this.PROJECT_ID = projid;
        this.LEADS = Boolean.getBoolean(bool);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += this.getID().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorksOn)) {
            return false;
        }
        WorksOn other = (WorksOn)object;
        return (this.getID().equals(other.getID()));
    }

    @Override
    public String toString() {
        return "dbaccess.persistence.WorksOn[id=" + getID() + "]";
    }

    /**
     * 
     * @param woData
     * @return true if this WorksOn matches the woData bean
     */
    public boolean matches(WorksOnData woData) {
        if (!"".equals(woData.getId()) && !this.getID().trim().equals(woData.getId().trim())) {
            return false;
        } else if (!"".equals(woData.getEmployee_id()) && !this.getEMPLOYEE_ID().trim().equals(woData.getEmployee_id().trim())) {
                return false;
        } else if (!"".equals(woData.getProject_id()) && !this.getPROJECT_ID().trim().equals(woData.getProject_id().trim())) {
                return false;
        } else if (!"".equals(woData.getLeads()) && !Boolean.valueOf(this.isLEADS()).toString().trim().equals(woData.getLeads().trim())) {
                return false;
        } else if (!"".equals(woData.getId()) && !this.getID().trim().equals(woData.getId().trim())) {
                return false;
        }
        return true;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
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
     * @return the PROJECT_ID
     */
    public String getPROJECT_ID() {
        return PROJECT_ID;
    }

    /**
     * @param PROJECT_ID the PROJECT_ID to set
     */
    public void setPROJECT_ID(String PROJECT_ID) {
        this.PROJECT_ID = PROJECT_ID;
    }

    /**
     * @return the LEADS
     */
    public boolean isLEADS() {
        return LEADS;
    }

    /**
     * @param LEADS the LEADS to set
     */
    public void setLEADS(boolean LEADS) {
        this.LEADS = LEADS;
    }
}