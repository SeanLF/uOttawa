<%-- 
    Document   : index
    Created on : 26-Sep-2014, 8:20:10 PM
    Author     : ssome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Client Lookup</title>
  </head>
  <body>
      <h1>Client Information Form</h1>
      <form name="userForm" action="LookupServlet" method="POST">
          <table>
              <thead>
                  <tr>
                      <th></th>
                      <th></th>
                  </tr>
              </thead>
              <tbody>
                  <tr>
                     <td>Client id</td>
                     <td><input type="text" name="id" value="" /></td>
                  </tr>
                  <tr>
                     <td>Client name</td>
                     <td><input type="text" name="name" value="" /></td>
                  </tr>
                  <tr>
                     <td>Client birthdate </td>
                     <td><input type="text" name="birthdate" value="" /> </td>
                  </tr>
              </tbody>
          </table>
          <table>
              <thead>
                  <tr>
                      <th></th>
                      <th></th>
                      <th></th>
                  </tr>
              </thead>
              <tbody>
                  <tr>
                      <td><input type="submit" value="Lookup" name="lookup" /></td>
                      <td><input type="submit" value="Add" name="add" /></td>
                      <td><input type="reset" value="Cancel" name="cancel" /></td>
                  </tr>
              </tbody>
          </table>
      </form>
  </body>
</html>
