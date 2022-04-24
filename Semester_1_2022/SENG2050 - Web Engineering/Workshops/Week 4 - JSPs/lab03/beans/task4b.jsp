<%@ page errorPage="error.jsp" %>  

<jsp:useBean id="date" class="lab3.DateFormatBean" scope="request" />
<jsp:setProperty name="date" property="format" param="format" />

<html>
    <head>
        <title>Task 4b - Date Format</title>
    </head>
    <body>
        <p>
            It is currently <jsp:getProperty name="date" property="currentDate" />, in format '<jsp:getProperty name="date" property="format" />'.
        </p>
        <p>
            The current date in other formats:
            <ul>
                <li><a href="/lab03/beans/task4b.jsp?format=dd-MM-yy%20hh-mm-ss%20a">Local Date and Time</a></li>
                <li><a href="/lab03/beans/task4b.jsp?format=dd-MM-yyyy">Domestic &amp; Logical Style</a></li>
                <li><a href="/lab03/beans/task4b.jsp?format=yyyy-MM-dd">International Standard</a></li>
                <li><a href="/lab03/beans/task4b.jsp?format=MM-dd-yyyy">US Style</a></li>
            </ul>
        </p>
    </body>
</html>