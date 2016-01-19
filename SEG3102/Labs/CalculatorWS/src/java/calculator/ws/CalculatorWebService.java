package calculator.ws;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
@WebService(serviceName = "CalculatorWebService")
public class CalculatorWebService {
    @WebMethod(operationName = "add")
    public double addNumbers(@WebParam(name = "first") double first,
                             @WebParam(name = "second") double second) {
        return first+second;
    }
    @WebMethod(operationName = "subtract")
    public double subtractNumbers(@WebParam(name = "first") double first,
                                  @WebParam(name = "second") double second) {
        return first-second;
    }
    @WebMethod(operationName = "multiply")
    public double multiplyNumbers(@WebParam(name = "first") double first,
                                  @WebParam(name = "second") double second) {
        return first*second;

    }
    @WebMethod(operationName = "divide")
    public double divideNumbers(@WebParam(name = "first") double first, @WebParam(name = "second") double second) {
        return first/second;
    }
}