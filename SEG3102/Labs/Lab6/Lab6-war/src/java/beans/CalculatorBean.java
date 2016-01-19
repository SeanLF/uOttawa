package beans;
import Lab6.ejb.CalculatorEJBLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
@Named(value = "calculatorBean")
@RequestScoped
public class CalculatorBean {
    @EJB
    private CalculatorEJBLocal calculatorEJB;
    private double first;
    private double second;
    private String result;
    /**
     * Creates a new instance of CalculatorBean
     */
    public CalculatorBean() {
    }
    /**
     * @return the first
     */
    public double getFirst() {
        return first;
}
    /**
     * @param first the first to set
     */
    public void setFirst(double first) {
        this.first = first;
}
    /**
     * @return the second
     */
    public double getSecond() {
        return second;
}
    /**
     * @param second the second to set
     */
    public void setSecond(double second) {
        this.second = second;
}
    /**
     * @return the result
     */
    public String getResult() {
        return result;
}
    /**
     * @param result the result to set
     */
public void setResult(String result) {
        this.result = result;
}
    public void add(){
        result = Double.toString(calculatorEJB.add(first, second));
    }
    public void subtract(){
        result = Double.toString(calculatorEJB.subtract(first, second));
    }
    public void divide(){
        result = Double.toString(calculatorEJB.divide(first, second));
    }
    public void multiply(){
        result = Double.toString(calculatorEJB.multiply(first, second));
} }