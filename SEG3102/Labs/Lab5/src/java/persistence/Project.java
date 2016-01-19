/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import beans.*;
import java.sql.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ssome
 */
@Entity
@Table(name="Project")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    
     @Id
    private String ID;
    private String PROJ_NAME;
    private Date STARTDATE;
    private String DURATION;
    private String STATUS;
    
    /** Creates a new instance of WorksOn */
    public Project() {
      
    }
    public Project(String id, String name, Date startdate,String duration, String status){
        this.ID = id;
        this.PROJ_NAME = name;
        this.STARTDATE = startdate;
        this.DURATION = duration;
        this.STATUS = status;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project)object;
        return (this.getID().equals(other.getID()));
    }

    @Override
    public String toString() {
        return "dbaccess.persistence.Project[id=" + getID() + "]";
    }

    /**
     * 
     * @param prData
     * @return true if this WorksOn matches the woData bean
     */
    public boolean matches(ProjectData prData) {
        if (!"".equals(prData.getProject_id()) && !this.getID().trim().equals(prData.getProject_id().trim())) {
            return false;
        } else if (!"".equals(prData.getName()) && !this.getNAME().trim().equals(prData.getName().trim())) {
                return false;
        } else if (!"".equals(prData.getDuration()) && !this.getDURATION().trim().equals(prData.getDuration().trim())) {
                return false;
        } else if (!"".equals(prData.getStartdate())){
            Date ddate = Date.valueOf(prData.getStartdate());
            if (!this.getSTARTDATE().equals(ddate)) return false;
        } else if (!"".equals(prData.getStatus()) && !this.getSTATUS().trim().equals(prData.getStatus().trim())) {
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
     * @return the NAME
     */
    public String getNAME() {
        return PROJ_NAME;
    }

    /**
     * @param NAME the NAME to set
     */
    public void setNAME(String NAME) {
        this.PROJ_NAME = NAME;
    }

    /**
     * @return the STARTDATE
     */
    public Date getSTARTDATE() {
        return STARTDATE;
    }

    /**
     * @param STARTDATE the STARTDATE to set
     */
    public void setSTARTDATE(Date STARTDATE) {
        this.STARTDATE = STARTDATE;
    }

    /**
     * @return the DURATION
     */
    public String getDURATION() {
        return DURATION;
    }

    /**
     * @param DURATION the DURATION to set
     */
    public void setDURATION(String DURATION) {
        this.DURATION = DURATION;
    }

    /**
     * @return the STATUS
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     * @param STATUS the STATUS to set
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}