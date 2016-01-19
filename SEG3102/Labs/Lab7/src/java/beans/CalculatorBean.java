package beans;
import calculator.ws.CalculatorWebService_Service;
import javax.enterprise.context.RequestScoped; 
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
@Named(value = "calculatorBean") @RequestScoped
public class CalculatorBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CalculatorWS/CalculatorWebService.wsdl")
    private CalculatorWebService_Service service;
private double first; private double second; private String result; /**
* Creates a new instance of CalculatorBean
*/
public CalculatorBean() { }
/**
* @return the first
*/
public double getFirst() {
return first; }
/**
* @param first the first to set */
public void setFirst(double first) { this.first = first;
} /**
* @return the second
*/
public double getSecond() {
return second; }
/**
* @param second the second to set */
public void setSecond(double second) { this.second = second;
}
/**
* @return the result */
public String getResult() { return result;
} /**
* @param result the result to set
*/
public void setResult(String result) {
this.result = result; }

    private double add(double first, double second) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        calculator.ws.CalculatorWebService port = service.getCalculatorWebServicePort();
        return port.add(first, second);
    }

    private double divide(double first, double second) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        calculator.ws.CalculatorWebService port = service.getCalculatorWebServicePort();
        return port.divide(first, second);
    }

    private double multiply(double first, double second) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        calculator.ws.CalculatorWebService port = service.getCalculatorWebServicePort();
        return port.multiply(first, second);
    }

    private double subtract(double first, double second) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        calculator.ws.CalculatorWebService port = service.getCalculatorWebServicePort();
        return port.subtract(first, second);
    }
    
    public void add() {
result = Double.toString(add(first, second));
}
public void subtract() {
result = Double.toString(subtract(first, second)); }
public void divide() {
result = Double.toString(divide(first, second));
}
public void multiply() {
result = Double.toString(multiply(first, second)); }
}