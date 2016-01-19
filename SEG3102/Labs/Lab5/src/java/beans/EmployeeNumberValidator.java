/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ssome
 */
@FacesValidator("employeeNumberValidator")
public class EmployeeNumberValidator implements Validator {
    
    /**
     *
     * @param context
     * @param component
     * @param value
     */
    @Override
    public void validate(FacesContext context,
                        UIComponent component,
                        Object value)  throws ValidatorException {
        String snumber = (String)value;
        try {
            Integer.parseInt(snumber);
            if(snumber.length() != 8) throw new Exception();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Employee number is not valid");
            message.setDetail("Employee # is not valid. Please enter a 8 digit number");
            context.addMessage("", message);
            throw new ValidatorException(message);
        }
    }
    
}
