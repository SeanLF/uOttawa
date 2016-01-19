<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<pre><code>
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

This is a list of the names of all of the colors in the current color scheme:

&lt;ol>
 &lt;<font color="blue"><b>c:forEach var="entry" items="<font color="blue"><b>\${scheme}</b></font>"</b></font>&gt;
  &lt;li><font color="blue"><b>\${entry.key}</b></font>&lt;/li>
 &lt;/<font color="blue"><b>c:forEach</b></font>&gt;
&lt;/ol>
</code></pre>

<p>
<hr>

This is a list of the names of all of the colors in the current color scheme:

<ol>
 <c:forEach var="entry" items="${scheme}">
  <li>${entry.key}</li>
 </c:forEach>
</ol>
