<%@ page errorPage="error.jsp" %>  

<jsp:useBean id="echo" class="lab3.EchoBean" scope="request" />
<jsp:setProperty name="echo" property="name" param="name" />
<jsp:setProperty name="echo" property="age" param="age" />
<jsp:setProperty name="echo" property="address" param="address" />
<jsp:setProperty name="echo" property="occupation" param="occupation" />

<html>
    <head>
        <title>Task 1 - Echo</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Name</td>
                <td><jsp:getProperty name="echo" property="name" /></td>
            </tr>
            <tr>
                <td>Age</td>
                <td><jsp:getProperty name="echo" property="age" /></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><jsp:getProperty name="echo" property="address" /></td>
            </tr>
            <tr>
                <td>Occupation</td>
                <td><jsp:getProperty name="echo" property="occupation" /></td>
            </tr>
        </table>
    </body>
</html>