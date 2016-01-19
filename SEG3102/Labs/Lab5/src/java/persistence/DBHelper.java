/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import beans.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author ssome
 */
public class DBHelper {
    public static Employee findEmployee(EntityManager em,String id) {
        Employee u = em.find(Employee.class, id);
        return u;
    }
    
    public static List findEmployeesByName(EntityManager em,String name) {
        Query query = em.createQuery(
                "SELECT e FROM Employee e" +
                " WHERE e.NAME = :empName");
        query.setParameter("empName",name);
        return performQuery(query);
    }
    
    public static List findEmployeesByLastName(EntityManager em, String lastname) {
        Query query = em.createQuery(
                "SELECT e FROM Employee e" +
                " WHERE e.LASTNAME = :empLastName");
        query.setParameter("empLastName",lastname);
        return performQuery(query);
    }
    
    private static List performQuery(final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } 
        ArrayList<Employee> results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }

   public static boolean addEmployee(EntityManager em, UserTransaction utx, EmployeeData empData, AddressData adrData) {
        try {
            utx.begin();
            Employee nemp = new Employee();
            Address naddr = new Address();
            nemp.setEMPLOYEE_ID(empData.getId());
            nemp.setNAME(empData.getName());
            nemp.setLASTNAME(empData.getLastName());
            naddr.setEMPLOYEE_ID(empData.getId());
            naddr.setSTREETNUMBER(adrData.getStreetnumber());
            naddr.setSTREETNAME(adrData.getStreetname());
            naddr.setCITY(adrData.getCity());
            naddr.setPROVINCE(adrData.getProvince());
            naddr.setPOSTAL(adrData.getPostal());
            em.persist(nemp);
            em.persist(naddr);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;
    }
   
   public static boolean addProject(EntityManager em, UserTransaction utx, ProjectData prData){
       try {
            utx.begin();
            Project pr = new Project();
            
            List<String> empList = prData.getParticipantEmps();
            
            pr.setID(prData.getProject_id());
            pr.setNAME(prData.getName());
            pr.setSTARTDATE(Date.valueOf(prData.getStartdate()));
            pr.setDURATION(prData.getDuration());
            pr.setSTATUS(prData.getStatus());
            
            for(Iterator<String> i = empList.iterator(); i.hasNext(); ) {
                WorksOn wo = new WorksOn();
                String emp = i.next();
                boolean leads = prData.getLead().equals(emp);
                
                wo.setID(prData.getProject_id()+prData.getLead()+emp);
                wo.setPROJECT_ID(prData.getProject_id());
                wo.setEMPLOYEE_ID(emp);
                wo.setLEADS(leads);
                
                em.persist(wo);
            }
            
            em.persist(pr);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;
   }
   
   public static List<Employee> findAllEmployees(EntityManager em) {
        Query query = em.createQuery(
                "SELECT e  FROM Employee e");
        return performQuery(query);
   }
   
   public static List findProjectByName(EntityManager em,String name) {
        Query query = em.createQuery(
                "SELECT p FROM Project p" +
                " WHERE p.PROJ_NAME = :projName");
        query.setParameter("projName",name);
        return query.getResultList();
    }
   
   public static List findWorkersByProjectName(EntityManager em,String name) {
       String queryString = "SELECT wo, e FROM WorksOn wo, Project p, Employee e WHERE p.PROJ_NAME = :projName and p.ID = wo.PROJECT_ID "
               + "and e.EMPLOYEE_ID = wo.EMPLOYEE_ID";
               //"select wo from WorksOn wo JOIN wo.project p where p.proj_name = :projName";
//"select wo FROM WorksOn wo, Project  WHERE Project.\"NAME\" = :projName and  Project.ID = WorksOn.PROJECT_ID";
        Query query = em.createQuery(queryString);
        
                //"SELECT wo "+
                   //     "from WorksOn wo inner join fetch wo.project as p WHERE p.name = :projName");
        query.setParameter("projName",name);
        return query.getResultList();
    }
   
}