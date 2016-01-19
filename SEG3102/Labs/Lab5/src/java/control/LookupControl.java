/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DBHelper;
import persistence.*;

/**
 *
 * @author ssome
 */
@Named(value = "lookupControl")
@RequestScoped
public class LookupControl implements Serializable {
    @Inject
    private EmployeeData empData;
    @Inject
    private AddressData addressData;
    @Inject
    private ProjectData projectData;
    @Inject
    private WorksOnData worksOnData;
    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    /**
     * Creates a new instance of LookupControl
     */
    public LookupControl() {
    }
    public void lookup() {
       List<Employee> results = new ArrayList();
       if (!"".equals(empData.getId())) {
            // lookup by id
            results = getEmployeeById(em,empData);
       } else if (!"".equals(empData.getName())) {
            // lookup by name
            results = getEmployeesByName(em,empData);
       } else if (!"".equals(empData.getLastName())) {
            // lookup by name
            results = getEmployeesByLastName(em,empData);
       } 
       empData.setLookupResults(results);
    }
    public void lookupProject() {
        List<Project> p = new ArrayList();
        List<Object[]> woAndEmps = new ArrayList();
        List<Employee> emps = new ArrayList();
        String lead = "";
        
       if (!"".equals(projectData.getName())) {
            // lookup by name
            p = getProjectByName(em,projectData);
            woAndEmps = getWorkersByProjectName(em, projectData);
       }
       
       for(Object[] w : woAndEmps){
           Employee e = (Employee) w[1];
           emps.add(e);
           WorksOn wo = (WorksOn)w[0];
           if(wo.isLEADS())
                   lead = e.getNAME() + " " + e.getLASTNAME();
       }
       
       projectData.setLookupResults(p);
       projectData.setLead(lead);
       empData.setLookupResults(emps);
    }
    public void add() {
        if (DBHelper.addEmployee(em,utx,empData, addressData)) {
            empData.setAddstatus("The Employee Was Successfuly Added");
        } else {
            empData.setAddstatus("Addition of the Employee Failed");
        }
    }
    
    public void addProject(){
        if(DBHelper.addProject(em, utx, projectData)) {
            projectData.setAddstatus("The Project Was Successfuly Added");
        } else {
            projectData.setAddstatus("Addition of the Project Failed");
        }
    }
    
    public void getEmployeeList() {
        List<Employee> allEmps = DBHelper.findAllEmployees(em);
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        
        for (Employee e : allEmps) {
            SelectItem se = new SelectItem();
            se.setLabel(e.getNAME() + " " + e.getLASTNAME());
            se.setValue(e.getEMPLOYEE_ID());
            
            selectItems.add(se);
        }
        
        projectData.setAllEmps(selectItems);
        //return selectItems;
    }
    
        /**
     * Find a user by id and check if any that the other fields are valid
     */
    private List<Employee> getEmployeeById(EntityManager em,EmployeeData empData) {
        ArrayList<Employee> result = new ArrayList<>();
        Employee emp = DBHelper.findEmployee(em,empData.getId());
        if (emp != null && emp.matches(empData)) {
            result.add(emp);  
        }
        return result;
    }

    private List<Employee> getEmployeesByName(EntityManager em,EmployeeData empData) {
       List<Employee> allresults = DBHelper.findEmployeesByName(em,empData.getName());
       if (allresults == null) return null;
       return checkResults(allresults,empData);          
    }

    private List getEmployeesByLastName(EntityManager em,EmployeeData empData) {
       List<Employee> allresults = DBHelper.findEmployeesByLastName(em,empData.getLastName());
       if (allresults == null) return null;
       return checkResults(allresults,empData);       
    }
    
    private List<Employee> checkResults(List<Employee> allresults,EmployeeData empData) {
        ArrayList<Employee> results = new ArrayList<>();
        for (Employee emp: allresults) {
            if (emp.matches(empData)) results.add(emp);
        }
        return results;
    }
    
    private List getProjectByName(EntityManager em,ProjectData projectData) {
       List result = DBHelper.findProjectByName(em,projectData.getName());
       if (result == null) return null;
       return result;         
    }
    
    private List getWorkersByProjectName(EntityManager em,ProjectData projectData) {
       List result = DBHelper.findWorkersByProjectName(em,projectData.getName());
       if (result == null) return null;
       return result;         
    }
}
