/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.beans;

/**
 *
 * @author Sean
 */
public class CalcInput {
    private double first;
    private double second;
    private String add;
    private String subtract;
    private String multiply;
    private String divide;
    private double result;

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
     * @return the add
     */
    public String getAdd() {
        return add;
    }

    /**
     * @param add the add to set
     */
    public void setAdd(String add) {
        this.add = add;
    }

    /**
     * @return the subtract
     */
    public String getSubtract() {
        return subtract;
    }

    /**
     * @param subtract the subtract to set
     */
    public void setSubtract(String subtract) {
        this.subtract = subtract;
    }

    /**
     * @return the multiply
     */
    public String getMultiply() {
        return multiply;
    }

    /**
     * @param multiply the multiply to set
     */
    public void setMultiply(String multiply) {
        this.multiply = multiply;
    }

    /**
     * @return the divide
     */
    public String getDivide() {
        return divide;
    }

    /**
     * @param divide the divide to set
     */
    public void setDivide(String divide) {
        this.divide = divide;
    }

    /**
     * @return the result
     */
     public double getResult() {
        // compute result
        if (add != null) result = first+second;
        else if (subtract != null) result = first-second;
        else if (multiply != null) result = first*second;
        else if (divide != null) result = first/second;
        return result;
}

    /**
     * @param result the result to set
     */
    public void setResult(double result) {
        this.result = result;
    }
    
}
