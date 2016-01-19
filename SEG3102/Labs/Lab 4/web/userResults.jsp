<%-- 
    Document   : userResults
    Created on : 26-Sep-2014, 8:22:22 PM
    Author     : ssome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Lookup Results</title>
        <style>
    #results {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
    }

    #results td, #results th {
        font-size: 1em;
        border: 1px solid #98bf21;
        padding: 3px 7px 2px 7px;
    }

    #results th {
        font-size: 1.1em;
        text-align: left;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #A7C942;
        color: #ffffff;
    }

    #results tr.alt td {
        color: #000000;
        background-color: #EAF2D3;
    }
</style>
    </head>
    <body>
        <c:import url="/queryParams.jsp"/>
    <h1>Lookup Results</h1>
    <table id="results">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Birth Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${results}">
                <tr>
                    <td><c:out value="${user.USER_ID}"/></td>
                    <td><c:out value="${user.NAME}"/></td>
                    <td><c:out value="${user.BIRTHDATE}"/></td>   
                  </tr>
            </c:forEach>          
        </tbody>
    </table>

    
    
    </body>
</html>
