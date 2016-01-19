<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<pre><code>
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

This table demonstrates all of the colors in the current scheme.

&lt;TABLE BORDER="1" BGCOLOR="WHITE">
&lt;TR>&lt;TH>Name&lt;/TH>&lt;TH>Sample Text&lt;/TH>&lt;TH>Color Swatch&lt;/TH>
&lt;TH>Hex Code&lt;/TH>&lt;TH>Opposite Color&lt;/TH>&lt;/TR>

  <font color="red">&lt;!-- Loop over color entries in the scheme --></font>
  &lt;<font color="blue"><b>c:forEach var="entry" items="<font color="blue"><b>\${scheme}</b></font>"</b></font>&gt;

    <font color="red">&lt;!-- Set two variables to improve readability of HTML below --></font>
    &lt;<font color="blue"><b>c:set var="color" value="<font color="blue"><b>\${entry.value.rgbHex}</b></font>"/</b></font>&gt;
    &lt;<font color="blue"><b>c:set var="name" value="<font color="blue"><b>\${entry.key}</b></font>"/</b></font>&gt;

    &lt;TR>
      &lt;TD><font color="blue"><b>\${name}</b></font>&lt;/TD>
      &lt;TD>&lt;FONT COLOR="<font color="blue"><b>\${color}</b></font>"><font color="blue"><b>\${name}</b></font>&lt;/FONT>&lt;/TD>
      &lt;TD BGCOLOR="<font color="blue"><b>\${color}</b></font>"><font color="blue"><b>\${name}</b></font>&lt;/FONT>&lt;/TD>
      &lt;TD><font color="blue"><b>\${color}</b></font>&lt;/TD>
      &lt;TD BGCOLOR="<font color="blue"><b>\${entry.value.rgbComplement}</b></font>"><font color="blue"><b>\${name}</b></font>&lt;/TD>
    &lt;/TR>

   &lt;/<font color="blue"><b>c:forEach</b></font>&gt;
</code></pre>
<p>
<hr>

This table demonstrates all of the colors in the current scheme.

<TABLE BORDER="1" BGCOLOR="WHITE">
<TR><TH>Name</TH><TH>Sample Text</TH><TH>Color Swatch</TH>
<TH>Hex Code</TH><TH>Opposite Color</TH></TR>

  <!-- Loop over color entries in the scheme -->
  <c:forEach var="entry" items="${scheme}">

    <!-- Set two variables to improve readability of HTML below -->
    <c:set var="color" value="${entry.value.rgbHex}"/>
    <c:set var="name" value="${entry.key}"/>

    <TR>
      <TD>${name}</TD>
      <TD><FONT COLOR="${color}">${name}</FONT></TD>
      <TD BGCOLOR="${color}">${name}</FONT></TD>
      <TD>${color}</TD>
      <TD BGCOLOR="${entry.value.rgbComplement}">${name}</TD>
    </TR>

   </c:forEach>

