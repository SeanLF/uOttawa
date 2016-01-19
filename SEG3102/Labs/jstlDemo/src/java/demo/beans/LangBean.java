/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo.beans;

public class LangBean {
   String _cid;
   String _firstname = null;
   String _lastname = null;
   String _company = null;
   String _city = null;
   String _username = null;
   String _language = null;

   public LangBean(int cid, String firstname, String lastname, String company, String city, String username, String language) {
      _cid = String.format("%03d", cid);
      _firstname = firstname.trim();
      _lastname = lastname.trim();
      _company = company.trim();
      _city = city.trim();
      _username = username.trim();
      _language = language.trim();
   }
   
   public static LangBean newInstance(String cid, String firstname, String lastname, String company, String city, String username, String language) {
          return new LangBean(Integer.valueOf(cid), firstname, lastname, company, city, username, language);
   }

   public String getCid() {
      return _cid;
   }
   
   public String getFirstname() {
      return _firstname;
   }
   
   public String getLastname() {
      return _lastname;
   }
   
   public String getCompany() {
      return _company;
   }
   
   public String getCity() {
      return _city;
   }
   
   public String getUsername() {
      return _username;
   }
   
   public String getLanguage() {
      return _language;
   }
}
