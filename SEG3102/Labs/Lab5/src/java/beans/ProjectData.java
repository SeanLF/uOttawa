/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.*;

/**
 *
 * @author ssome
 */
@Named(value = "projectData")
@RequestScoped
@ViewScoped
public class ProjectData implements Serializable {
    private String project_id;
    private String name;
    private String startdate;
    private String duration;
    private String status;
    private List<SelectItem> allEmps; // list of items to select
    private List<String> participantEmps; // selected items
    private Object lead;
    private String addstatus;
    private List<Project> lookupResults;
    @PersistenceContext(unitName = "App")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    /**
     * Creates a new instance of empData
     */
    
    public ProjectData(){
    }
    
    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }

    public void setLookupResults(List<Project> results) {
        this.lookupResults = results;
    }
    
    public List<Project> getLookupResults() {
        return lookupResults;
    }
    // show results if any
    public boolean getShowResults() {
        return (lookupResults != null) && !lookupResults.isEmpty();
    }
    // show message if no result
    public boolean getShowMessage() {
        return (lookupResults != null) && lookupResults.isEmpty();
    }

    /**
     * @return the project_id
     */
    public String getProject_id() {
        return project_id;
    }

    /**
     * @param project_id the project_id to set
     */
    public void setProject_id(String project_id) {
        this.project_id = project_id;
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
     * @return the startdate
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
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

    /**
     * @return the participantEmps
     */
    public List<String> getParticipantEmps() {
        return this.participantEmps;        
    }

    /**
     * @param participantEmps the participantEmps to set
     */
    public void setParticipantEmps(List<String> participantEmps) {
        this.participantEmps = participantEmps;
    }

    /**
     * @return the lead
     */
    public Object getLead() {
        return lead;
    }

    /**
     * @param lead the lead to set
     */
    public void setLead(Object lead) {
        this.lead = lead;
    }

    /**
     * @return the allEmps
     */
    public List<SelectItem> getAllEmps() {
        return this.allEmps;
    }
        
    

    /**
     * @param list the allEmps to set
     */
    public void setAllEmps(List<SelectItem> list) {
        this.allEmps = list;
    }
    
    public List<SelectItem> getEmployeeList() {
        List<Employee> allEmps = DBHelper.findAllEmployees(em);
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        
        for (Employee e : allEmps) {
            SelectItem se = new SelectItem();
            se.setLabel(e.getNAME() + " " + e.getLASTNAME());
            se.setValue(e.getEMPLOYEE_ID());
            
            selectItems.add(se);
        }
        
        setAllEmps(selectItems);
        return selectItems;
    }

}
