<%-- 
    Document   : index
    Created on : 26-Sep-2014, 8:20:10 PM
    Author     : ssome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Book Lookup</title>
  </head>
  <body>
      <h1>Book Information Form</h1>
      <form name="bookForm" action="LookupServlet" method="POST" style="font-size: 25px;">
          <table>
              <thead>
                  <tr>
                      <th></th>
                      <th></th>
                  </tr>
              </thead>
              <tbody>
                  <tr>
                     <td>Book id</td>
                     <td><input type="text" name="id" value="" /></td>
                  </tr>
                  <tr>
                     <td>Book name</td>
                     <td><input type="text" name="name" value="" /></td>
                  </tr>
                  <tr>
                     <td>Book Author </td>
                     <td><input type="text" name="author" value="" /> </td>
                  </tr>
                  <tr>
                     <td>Book Price </td>
                     <td><input type="text" name="price" value="" /> </td>
                  </tr>
                  <tr>
                     <td>Book Description </td>
                     <td><textarea name="description" value=""></textarea> </td>
                  </tr>
                  <tr>
                     <td>Book Type </td>
                     <td><select name="type">
                             <option value="">Select an option</option>
                             <option value="Kids">Kids book</option>
                             <option value="Tech">Tech book </option>
                         </select> </td>
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
                      <td><input type="submit" value="Lookup" name="lookupBook" /></td>
                      <td><input type="submit" value="Add" name="addBook" /></td>
                      <td><input type="submit" value="Delete" name="deleteBook" /></td>
                      <td><input type="reset" value="Cancel" name="cancelBook" /></td>
                  </tr>
              </tbody>
          </table>
      </form>
  </body>
</html>
