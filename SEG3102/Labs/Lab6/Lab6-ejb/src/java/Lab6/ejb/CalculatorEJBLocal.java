package Lab6.ejb;
import javax.ejb.Local;
/**
 * This is the business interface for CalculatorEJB enterprise bean.
 */
@Local
public interface CalculatorEJBLocal {
    public double add(double a, double b);
    public double subtract(double a, double b);
    public double multiply(double a, double b);
    public double divide(double a, double b);
}