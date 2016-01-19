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
@FacesValidator("postalCodeValidator")
public class PostalCodeValidator implements Validator {
    
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
        String spostal = (String)value;
        try {
            if(spostal.length() != 7){
                throw new Exception();
            } else {
                spostal = spostal.toUpperCase(Locale.ENGLISH);
                char[] digit = spostal.toCharArray();
                if(digit[3] != ' ') throw new Exception();
                int[] alpha = {0, 2, 5};
                int[] num = {1, 4, 6};
                for(int i = 0; i < alpha.length; i++) {
                    if(digit[alpha[i]] < 'A' || digit[alpha[i]] > 'Z') throw new Exception();
                }
                for(int i = 0; i < num.length; i++) {
                    if(digit[num[i]] < '0' || digit[num[i]] > '9') throw new Exception();
                }
            }
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Postal Code is not valid.");
            message.setDetail("Postal Code is not valid. Try A#A #A# format.");
            context.addMessage("", message);
            throw new ValidatorException(message);
        }
    }
    
}
