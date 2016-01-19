<%@ page language="java" import="java.io.*,demo.beans.*" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<HTML>

<!-- This page must be visited first, to initialize this bean -->


<jsp:useBean id="scheme"
             class="demo.beans.ColorScheme"
             scope="session"></jsp:useBean>
    
    <jsp:useBean id="langscheme"
             class="demo.beans.LangScheme"
             scope="session"><!-- Initialize this bean --></jsp:useBean>

<HEAD><TITLE>Enterprise Java Tech Tips</TITLE>
<BODY>

<H1>JSTL Tag Demo</H1>
<p>
<ol>
<li><a href="Count.jsp">Print the number of elements in the color scheme</a><br>
<li><a href="ListColorNames.jsp">List the color names in the scheme</a><br>
<li><a href="ShowColors.jsp">Show sample text and swatches of each color</a><br>
<li><form method=post action="ColorNameGrid.jsp">
Display a grid of color names with this number of columns:
<input name="cols" type="text" size="3">
<input type="submit" value="Go">
</form>
<br>
<li><a href="Language.jsp">Language table</a><br>
<li><form method=post action="Username.jsp">
Find this username:
<input name="username" type="text">
<input type="submit" value="Go">
</form>
<li><form method=post action="LangSearch.jsp">
Display a grid with people having this language
<input name="language" type="text">
<input type="submit" value="Go">
</form>
</ol>
</BODY>
</HTML>

