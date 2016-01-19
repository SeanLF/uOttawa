/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.persistence;

import dbaccess.beans.UserData;
import dbaccess.beans.BookData;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.*;


/**
 *
 * @author ssome
 */
public class DBHelper {
    public static User findUser(EntityManager em,String id) {
        User u = em.find(User.class, id);
        return u;
    }
    
    public static List findUsersByName(EntityManager em,String name) {
        Query query = em.createQuery(
                "SELECT u FROM User u" +
                " WHERE u.NAME = :userName");
        query.setParameter("userName",name);
        return performQuery(query);
    }
    
    public static List findUsersByBirthDate(EntityManager em, String sdate) {
        try {
            Date bdate = Date.valueOf(sdate);
            Query query = em.createQuery(
                "SELECT u FROM User u" +
                " WHERE u.BIRTHDATE = :bdate");
            query.setParameter("bdate",bdate);
            return performQuery(query);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }
    
    private static List performQuery(final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } 
        ArrayList<User> results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }

   public static boolean addUser(EntityManager em, UserTransaction utx, UserData userData) {
        try {
            utx.begin();
            User nuser = new User();
            nuser.setUSER_ID(userData.getId());
            nuser.setNAME(userData.getName());
            nuser.setBIRTHDATE(Date.valueOf(userData.getBirthdate()));
            em.persist(nuser);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;
    }
   
   public static boolean addBook(EntityManager em, UserTransaction utx, BookData book) {
        try {
            utx.begin();
            Book nbook = new Book();
            nbook.setBOOK_ID(book.getId());
            nbook.setNAME(book.getName());
            nbook.setAUTHOR(book.getAuthor());
            nbook.setPRICE(book.getPrice());
            nbook.setDESCRIPTION(book.getDescription());
            nbook.setTYPE(book.getType());
            em.persist(nbook);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;
    }
   
   public static Book findBook(EntityManager em,String id) {
        Book b = em.find(Book.class, id);
        return b;
    }
    
    public static List findBookByName(EntityManager em,String name) {
        Query query = em.createQuery(
                "SELECT b FROM Book b" +
                " WHERE b.NAME = :name");
        query.setParameter("name",name);
        return performQuery(query);
    }
    
    public static List findBookByAuthor(EntityManager em, String sauthor) {
            Query query = em.createQuery(
                "SELECT b FROM Book b" +
                " WHERE b.AUTHOR = :author");
            query.setParameter("author",sauthor);
            return performQuery(query);
    }
    
    public static List findBookByDescription(EntityManager em, String sdesc) {
            Query query = em.createQuery(
                "SELECT b FROM Book b" +
                " WHERE b.DESCRIPTION = :desc");
            query.setParameter("desc",sdesc);
            return performQuery(query);
    }
    
     public static List findBookByPrice(EntityManager em, String sprice) {
            Query query = em.createQuery(
                "SELECT b FROM Book b" +
                " WHERE b.PRICE = :price");
            query.setParameter("price",sprice);
            return performQuery(query);
    }
     
      public static List findBookByType(EntityManager em, String stype) {
            Query query = em.createQuery(
                "SELECT b FROM Book b" +
                " WHERE b.TYPE = :type");
            query.setParameter("type",stype);
            return performQuery(query);
    }
    
    public static boolean deleteBook(EntityManager em, UserTransaction utx, String id){
        try{
            utx.begin();
            Book book = em.find(Book.class, id);
            em.remove(book);
            utx.commit();
            //em.createQuery("DELETE FROM APP.Book b WHERE APP.BOOK_ID = :p")                    .setParameter("p", id)                 .executeUpdate();
            return true;
        } catch(Exception eeex){
            eeex.printStackTrace();
        }
        return false;
    }
}
