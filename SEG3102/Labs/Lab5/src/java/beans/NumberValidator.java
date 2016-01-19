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
@FacesValidator("numberValidator")
public class NumberValidator implements Validator {
    
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
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Not a number.");
            message.setDetail("Please enter a number!.");
            context.addMessage("", message);
            throw new ValidatorException(message);
        }
    }
    
}
