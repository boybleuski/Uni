<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%! 
    public String getCurrentDateWithFormat(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }
%>

<%
    String format = "dd-MM-yy hh-mm-ss a";
    if (request.getParameter("format") != null)
        format = request.getParameter("format");
%>

<html>
    <head>
        <title>Task 4b - Date Format</title>
    </head>
    <body>
        <p>
            It is currently <%= getCurrentDateWithFormat(format) %>, in format '<%= format %>'.
        </p>
        <p>
            The current date in other formats:
            <ul>
                <li><a href="/lab03/task4b.jsp?format=dd-MM-yy%20hh-mm-ss%20a">Local Date and Time</a></li>
                <li><a href="/lab03/task4b.jsp?format=dd-MM-yyyy">Domestic &amp; Logical Style</a></li>
                <li><a href="/lab03/task4b.jsp?format=yyyy-MM-dd">International Standard</a></li>
                <li><a href="/lab03/task4b.jsp?format=MM-dd-yyyy">US Style</a></li>
            </ul>
        </p>
    </body>
</html>