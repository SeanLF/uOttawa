/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import persistence.Employee;

/**
 *
 * @author ssome
 */
@Named(value = "empData")
@RequestScoped
public class EmployeeData {
    private String id;
    private String name;
    private String lastname;
    private String addstatus;
    private List<Employee> lookupResults;
    /**
     * Creates a new instance of empData
     */
    public EmployeeData() {
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
     * @return the lastName
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * @param lastname the lastName to set
     */
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    
    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }

    public void setLookupResults(List<Employee> results) {
        this.lookupResults = results;
    }
    
    public List<Employee> getLookupResults() {
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
}
