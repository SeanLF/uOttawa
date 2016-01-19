/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.servlets;

import dbaccess.beans.UserData;
import dbaccess.beans.BookData;
import dbaccess.persistence.DBHelper;
import dbaccess.persistence.User;
import dbaccess.persistence.Book;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author ssome
 */
@WebServlet(name = "LookupServlet", urlPatterns = {"/LookupServlet"})
public class LookupServlet extends HttpServlet {
    @PersistenceContext(unitName = "Lab_4PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // extract request parameters to a user data bean using
        // apache BeanUtils
        UserData userData = new UserData();
        BookData bookData = new BookData();
        try {
            BeanUtils.populate(userData,request.getParameterMap());
            BeanUtils.populate(bookData, request.getParameterMap());
            String nextPage = null;

            // set up the bean as request parameter
            request.setAttribute("userData",userData);
            request.setAttribute("bookData", bookData);
            // check the operation and dispatch accordingly
            if (request.getParameter("lookup") != null) {
                List<User> results = null;
                if (!"".equals(userData.getId())) {
                    // lookup by id
                    results = getUserById(em,userData);
                } else if (!"".equals(userData.getName())) {
                    // lookup by name
                    results = getUsersByName(em,userData);
                } else if (!"".equals(userData.getBirthdate())) {
                    // lookup by birthdate
                    results = getUsersByBirthDate(em,userData);
                } 
                if (results != null) {
                    // users have been found
                    request.setAttribute("results",results);
                    //dispatch to JSP for displaying results
                    nextPage = "/userResults.jsp";
                } else {
                    // users not found
                    // dispatch to error page
                    nextPage = "/lookupError.jsp";
                }
            } else if (request.getParameter("add") != null) {
                if (userData.isComplete()) {
                    if (DBHelper.addUser(em,utx,userData)) {
                        nextPage = "/successfulAdd.jsp";
                    } else {
                        nextPage = "/failedAdd.jsp";
                    }
                } else {
                    nextPage = "/failedAddIncomplete.jsp";
                }
            } else if(request.getParameter("lookupBook")!=null){
                List<Book> results = null;
                if (!"".equals(bookData.getId())) {
                    // lookup by id
                    results = getBookById(em,bookData);
                } else if (!"".equals(bookData.getName())) {
                    // lookup by name
                    results = getBookByName(em,bookData);
                } else if (!"".equals(bookData.getAuthor())) {
                    // lookup by author
                    results = getBookByAuthor(em,bookData);
                } else if (!"".equals(bookData.getDescription())) {
                    // lookup by description
                    results = getBookByDescription(em,bookData);
                } else if (!"".equals(bookData.getDescription())) {
                    // lookup by price
                    results = getBookByPrice(em,bookData);
                }  else if (!"".equals(bookData.getType())) {
                    // lookup by type
                    results = getBookByType(em,bookData);
                } 
                if (results != null) {
                    // users have been found
                    request.setAttribute("results",results);
                    //dispatch to JSP for displaying results
                    nextPage = "/bookResults.jsp";
                } else {
                    // users not found
                    // dispatch to error page
                    nextPage = "/lookupErrorBook.jsp";
                }
            } else if(request.getParameter("addBook")!=null){
                if (bookData.isComplete()) {
                    if (DBHelper.addBook(em,utx,bookData)) {
                        nextPage = "/successfulAddBook.jsp";
                    } else {
                        nextPage = "/failedAddBook.jsp";
                    }
                } else {
                    nextPage = "/failedAddIncompleteBook.jsp";
                }
            } else if(request.getParameter("deleteBook")!=null){
                if (DBHelper.deleteBook(em,utx, bookData.getId())) {
                        nextPage = "/successfulDeleteBook.jsp";
                    } else {
                        nextPage = "/failedDeleteBook.jsp";
                    }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request,response);
        } catch (IllegalAccessException | InvocationTargetException | ServletException | IOException ex) {
        } 
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>

    /**
     * Find a user by id and check if any that the other fields are valid
     */
    private List<User> getUserById(EntityManager em,UserData userData) {
        User user = DBHelper.findUser(em,userData.getId());
        if (user == null)  {
            return null;
        } else if (user.matches(userData)) {
            ArrayList<User> result = new ArrayList<>();
            result.add(user);
            return result;
        }
        return null;
    }

    private List<User> getUsersByName(EntityManager em,UserData userData) {
       List<User> allresults = DBHelper.findUsersByName(em,userData.getName());
       if (allresults == null) return null;
       return checkResults(allresults,userData);          
    }

    private List getUsersByBirthDate(EntityManager em,UserData userData) {
       List<User> allresults = DBHelper.findUsersByBirthDate(em,userData.getBirthdate());
       if (allresults == null) return null;
       return checkResults(allresults,userData);       
    }
    
    private List<Book> getBookById(EntityManager em,BookData bookData) {
        Book book = DBHelper.findBook(em,bookData.getId());
        if (book == null)  {
            return null;
        } else if (book.matches(bookData)) {
            ArrayList<Book> result = new ArrayList<>();
            result.add(book);
            return result;
        }
        return null;
    }

    private List<Book> getBookByName(EntityManager em,BookData bookData) {
       List<Book> allresults = DBHelper.findBookByName(em,bookData.getName());
       if (allresults == null) return null;
       return checkResults(allresults,bookData);          
    }

    private List getBookByAuthor(EntityManager em,BookData bookData) {
       List<Book> allresults = DBHelper.findBookByAuthor(em,bookData.getAuthor());
       if (allresults == null) return null;
       return checkResults(allresults,bookData);       
    }
    
    private List getBookByPrice(EntityManager em,BookData bookData) {
       List<Book> allresults = DBHelper.findBookByPrice(em,bookData.getPrice());
       if (allresults == null) return null;
       return checkResults(allresults,bookData);       
    }
    
    private List getBookByDescription(EntityManager em,BookData bookData) {
       List<Book> allresults = DBHelper.findBookByDescription(em,bookData.getDescription());
       if (allresults == null) return null;
       return checkResults(allresults,bookData);       
    }
    
    private List getBookByType(EntityManager em,BookData bookData) {
       List<Book> allresults = DBHelper.findBookByType(em,bookData.getType());
       if (allresults == null) return null;
       return checkResults(allresults,bookData);       
    }
    
    private List<User> checkResults(List<User> allresults,UserData userData) {
        ArrayList<User> results = new ArrayList<>();
        for (User user: allresults) {
            if (user.matches(userData)) results.add(user);
        }
        if (results.isEmpty())  {
            return null;
        } else {
            return results;
        }
    }
    
     private List<Book> checkResults(List<Book> allresults,BookData bookData) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book: allresults) {
            if (book.matches(bookData)) results.add(book);
        }
        if (results.isEmpty())  {
            return null;
        } else {
            return results;
        }
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
