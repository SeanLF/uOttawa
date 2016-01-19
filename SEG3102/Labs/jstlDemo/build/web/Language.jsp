<%-- 
    Document   : AllData
    Created on : Sep 24, 2014, 9:01:27 PM
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Data - table</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>This table has ${fn:length(langscheme)} rows.</p>
        
        <TABLE BORDER="1" BGCOLOR="WHITE">
<TR><TH>Cid</TH><TH>First Name</TH><TH>Last Name</TH>
    <TH>Company</TH><TH>City</TH><TH>Username</TH><TH>Language</TH></TR>

  <!-- Loop over color entries in the scheme -->
  <c:forEach var="entry" items="${langscheme}">

    <!-- Set two variables to improve readability of HTML below -->
    <c:set var="cid" value="${entry.key}"/>
    <c:set var="firstname" value="${entry.value.firstname}"/>
    <c:set var="lastname" value="${entry.value.lastname}"/>
    <c:set var="company" value="${entry.value.company}"/>
    <c:set var="city" value="${entry.value.city}"/>
    <c:set var="username" value="${entry.value.username}"/>
    <c:set var="language" value="${entry.value.language}"/>

    <TR>
      <TD>${cid}</TD>
      <TD>${firstname}</td>
      <TD>${lastname}</td>
      <TD>${company}</td>
      <TD>${city}</td>
      <TD>${username}</td>
      <TD>${language}</td>
    </TR>

   </c:forEach>
    </body>
</html>
