<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator Processing</title>
    </head>
    <body>
    <h1>Calculator Results</h1>
    <jsp:useBean id="cinput" scope="page" class="calculator.beans.CalcInput" />
    <jsp:setProperty name="cinput" property="*" />
    <h1>Result is: ${cinput.result} </h1>
    </body>
</html>