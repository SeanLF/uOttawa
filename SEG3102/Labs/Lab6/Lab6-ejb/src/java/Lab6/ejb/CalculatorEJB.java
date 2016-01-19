package Lab6.ejb;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
@Stateless
public class CalculatorEJB implements Lab6.ejb.CalculatorEJBLocal {
    /** Creates a new instance of CalculatorEJBBean */
    public CalculatorEJB() {
    }
    @Override
    public double add(double a, double b) {
return a + b; }
    @Override
    public double subtract(double a, double b) {
return a - b; }
    @Override
    public double multiply(double a, double b) {
return a * b; }
    @Override
    public double divide(double a, double b) {
        if (b == 0.0) {
            throw new EJBException("Divide by zero error");
}
return a / b; }
}