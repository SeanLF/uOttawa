/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sean
 */
@WebServlet(name = "CalculatorServlet", urlPatterns = {"/calculate"})
public class CalculatorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); PrintWriter out = response.getWriter(); out.println("<html>");
        out.println("<head>");
        out.println("<title>Calculator response</title>"); out.println("</head>");
        out.println("<body>");
        String sfirst = request.getParameter("First"); String ssecond = request.getParameter("Second"); 
        String sAge =  request.getParameter("Age"); String sGender =  request.getParameter("Gender"); 
        Boolean valid = true;
        String sM = "Client Information is valid";
        String fM = "Client Information is invalid";
        try {
            double age = Double.parseDouble(sAge); 
            if((age > 0) && (age <120))
            {}else{
                fM += "<br>invalid age";
                valid = false;
            }
                    
            if( sGender.equals("M") || sGender.equals("F")){
                valid = valid;
            }else{
                fM += "<br>invalid gender";
                valid = false;
            }
                    
            if(sfirst.matches("[a-zA-Z]+") && 
                    ssecond.matches("[a-zA-Z]+") && 
                    Character.isUpperCase(sfirst.charAt(0)) && 
                    Character.isUpperCase(ssecond.charAt(0))){}else{
                valid = false;
                fM += "<br>invalid names";
            }
            if(valid){
                out.println("<h1>" + sM + "</h1>");
            } else {
                out.println("<h1>" + fM + "</h1>");
            }
            
             
        } catch (Exception e) {
            out.println("<h1>Caught an exception. Check if you inputs are valid!</h1>"); 
        }
        
        out.println("</body>"); out.println("</html>"); /* */
        out.close();
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
