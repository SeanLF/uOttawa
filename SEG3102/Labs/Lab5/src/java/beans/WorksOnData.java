/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import persistence.*;

/**
 *
 * @author ssome
 */
@Named(value = "woData")
@RequestScoped
public class WorksOnData {
    private String project_id;
    private String id;
    private String employee_id;
    private String leads;
    private String addstatus;
    private List<WorksOn> lookupResults;
    /**
     * Creates a new instance of empData
     */
    public WorksOnData() {
    }

    
    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }

    public void setLookupResults(List<WorksOn> results) {
        this.lookupResults = results;
    }
    
    public List<WorksOn> getLookupResults() {
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
     * @return the employee_id
     */
    public String getEmployee_id() {
        return employee_id;
    }

    /**
     * @param employee_id the employee_id to set
     */
    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * @return the leads
     */
    public String getLeads() {
        return leads;
    }

    /**
     * @param leads the leads to set
     */
    public void setLeads(String leads) {
        this.leads = leads;
    }

}
