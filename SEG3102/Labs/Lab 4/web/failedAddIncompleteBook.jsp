<%-- 
    Document   : failedAddIncomplete
    Created on : 26-Sep-2014, 8:20:51 PM
    Author     : sfloyd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Failed Add Incomplete Page</title>
    </head>
    <body>
        <c:import url="/queryParamsBook.jsp"/>
        <h1>Book addition failed, missing fields need to be specified</h1>
    </body>
</html>
