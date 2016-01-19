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
@Named(value = "addressData")
@RequestScoped
public class AddressData {
    private String employee_id;
    private String streetnumber;
    private String streetname;
    private String unit;
    private String city;
    private String province;
    private String postal;
    private String addstatus;
    private List<Address> lookupResults;
    /**
     * Creates a new instance of empData
     */
    public AddressData() {
    }

    
    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }

    public void setLookupResults(List<Address> results) {
        this.lookupResults = results;
    }
    
    public List<Address> getLookupResults() {
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
     * @return the streetNumber
     */
    public String getStreetnumber() {
        return streetnumber;
    }

    /**
     * @param streetnumber the streetNumber to set
     */
    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    /**
     * @return the streetName
     */
    public String getStreetname() {
        return streetname;
    }

    /**
     * @param streetname the streetName to set
     */
    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the postal
     */
    public String getPostal() {
        return postal;
    }

    /**
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        this.postal = postal;
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
}
