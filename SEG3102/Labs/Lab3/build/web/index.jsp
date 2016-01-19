<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator Form</title>
    </head>
    <body>
     <h1>Calculator</h1>
      <form name="calculateForm" action="calculator.jsp">
          <table border="2" cellspacing="2" cellpadding="2">
              <thead>
                  <tr>
                      <th></th>
                      <th></th>
                  </tr>
              </thead>
              <tbody>
                  <tr>
                      <td>First Number: <input type="text" name="first" value="0" /></td>
                      <td>Second Number: <input type="text" name="second" value="0" /></td>
</tr> <tr>
                      <td><input type="submit" value="Add" name="add" /></td>
                      <td><input type="submit" value="Subtract" name="subtract" /></td>
                  </tr>
                  <tr>
                      <td><input type="submit" value="Multiply" name="multiply" /></td>
                      <td><input type="submit" value="Divide" name="divide" /></td>
                  </tr>
              </tbody>
          </table>
      </form>
      </body>
</html>