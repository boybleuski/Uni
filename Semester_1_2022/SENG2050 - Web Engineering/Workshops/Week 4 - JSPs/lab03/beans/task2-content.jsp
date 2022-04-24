<%@ page errorPage="error.jsp" %>  

<jsp:useBean id="data" class="lab3.ContentBean" scope="request" />
<jsp:setProperty name="data" property="name" param="name" />
<jsp:setProperty name="data" property="age" param="age" />

<html>
    <head>
        <title>Task 2 - Content</title>
    </head>
    <body>
        <jsp:getProperty name="data" property="message" />
    </body>
</html>