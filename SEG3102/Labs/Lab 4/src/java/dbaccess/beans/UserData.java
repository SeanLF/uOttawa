/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.beans;

/**
 *
 * @author ssome
 */
public class UserData {
    private String id;
    private String name;
    private String birthdate;
    
    /** Creates a new instance of UserData */
    public UserData() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isComplete() {
        if (id == null || name == null || birthdate == null) {
            return false;
        }
        return !"".equals(id) && !"".equals(name) && !"".equals(birthdate);
    }    
}
