<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<code><pre>
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<font color="red">&lt;!-- Initialize local variables --></font>
&lt;<font color="blue"><b>c:set var="cols" value="<font color="blue"><b>\${param['cols']}</b></font>"/</b></font>&gt;
&lt;<font color="blue"><b>c:set var="col" value="0"/</b></font>&gt;

<font color="red">&lt;!-- use the "choose" element to limit number of columns --></font>
&lt;<font color="blue"><b>c:choose</b></font>&gt;
  &lt;<font color="blue"><b>c:when test="<font color="blue"><b>\${cols </b></font>&gt; 0 &amp;&amp; cols &lt;= 10}</b></font>">

    This is a grid of the names of all of the colors in the
    current color scheme:
    &lt;p>

    &lt;TABLE BORDER="0" BGCOLOR="#404040">
    &lt;TR>
    &lt;<font color="blue"><b>c:forEach var="entry" items="<font color="blue"><b>\${scheme}</b></font>"</b></font>&gt;
      &lt;<font color="blue"><b>c:set var="color" value="<font color="blue"><b>\${entry.value}</b></font>"/</b></font>&gt;
    
      &lt;TD>&lt;FONT COLOR="<font color="blue"><b>\${color.rgbHex}</b></font>"><font color="blue"><b>\${entry.key}</b></font>&lt;/FONT>&lt;/TD>
    
      &lt;<font color="blue"><b>c:set var="col" value="<font color="blue"><b>\${col+1}</b></font>"/</b></font>&gt;
    
      &lt;<font color="blue"><b>c:if test="<font color="blue"><b>\${(col % cols) == 0}</b></font>"</b></font>&gt;
        &lt;/TR>&lt;TR>
      &lt;/<font color="blue"><b>c:if</b></font>&gt;
    
    &lt;/<font color="blue"><b>c:forEach</b></font>&gt;
    &lt;/TR>
    
    &lt;/TABLE>
  &lt;/<font color="blue"><b>c:when</b></font>&gt;


  &lt;<font color="blue"><b>c:otherwise</b></font>&gt;  <font color="red">&lt;!-- Complain about unreasonable input --></font>

<font color="blue"><b>\${cols}</b></font> is an unreasonable number of columns.
&lt;p>
Click &lt;a href="index.jsp">here&lt;/a> to try again.
  &lt;/<font color="blue"><b>c:otherwise</b></font>&gt;

&lt;/<font color="blue"><b>c:choose</b></font>&gt;
</pre></code>
<p>
<hr>

<!-- Initialize local variables -->
<c:set var="cols" value="${param['cols']}"/>
<c:set var="col" value="0"/>

<!-- use the "choose" element to limit number of columns -->
<c:choose>
  <c:when test="${cols > 0 && cols <= 10}">

    This is a grid of the names of all of the colors in the
    current color scheme:
    <p>

    <TABLE BORDER="0" BGCOLOR="#404040">
    <TR>
    <c:forEach var="entry" items="${scheme}">
      <c:set var="color" value="${entry.value}"/>
    
      <TD><FONT COLOR="${color.rgbHex}">${entry.key}</FONT></TD>
    
      <c:set var="col" value="${col+1}"/>
    
      <c:if test="${(col % cols) == 0}">
        </TR><TR>
      </c:if>
    
    </c:forEach>
    </TR>
    
    </TABLE>
  </c:when>

  <c:otherwise>  <!-- Complain about unreasonable input -->

${cols} is an unreasonable number of columns.
<p>
Click <a href="index.jsp">here</a> to try again.
  </c:otherwise>

</c:choose>
