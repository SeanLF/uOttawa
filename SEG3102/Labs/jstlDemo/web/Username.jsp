<%-- 
    Document   : Username
    Created on : Sep 27, 2014, 8:27:40 AM
    Author     : Sean
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Username search</title>
    </head>
    <body>
        <h1>Username search<br>
        <!-- Initialize local variables -->
<c:set var="username" value="${param['username']}"/>

<!-- use the "choose" element to limit number of columns -->
<c:choose>
  <c:when test="${username != ''}">
    <p>
    <c:forEach var="entry" items="${langscheme}">
      <c:set var="color" value="${entry.value}"/>    
      <c:if test="${username == entry.value.username}">
        <c:set var="cid" value="${entry.key}"/>
    <c:set var="firstname" value="${entry.value.firstname}"/>
    <c:set var="lastname" value="${entry.value.lastname}"/>
    <c:set var="company" value="${entry.value.company}"/>
    <c:set var="city" value="${entry.value.city}"/>
    <c:set var="username" value="${entry.value.username}"/>
    <c:set var="language" value="${entry.value.language}"/>
    <table border="1"><TR><TH>Cid</TH><TH>First Name</TH><TH>Last Name</TH>
    <TH>Company</TH><TH>City</TH><TH>Username</TH><TH>Language</TH></TR>
    <TR>
      <TD>${cid}</TD>
      <TD>${firstname}</td>
      <TD>${lastname}</td>
      <TD>${company}</td>
      <TD>${city}</td>
      <TD>${username}</td>
      <TD>${language}</td>
    </TR>
      </c:if>
    
    </c:forEach>
    </TR>
    
    </TABLE>
  </c:when>

  <c:otherwise>  <!-- Complain about unreasonable input -->

${username} was not found.
<p>
Click <a href="index.jsp">here</a> to try again.
  </c:otherwise>

</c:choose>
</h1>
    </body>
</html>
