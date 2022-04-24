<%
    String name = request.getParameter("name");
    String age = request.getParameter("age");
    String address = request.getParameter("address");
    String occupation = request.getParameter("occupation");
%>
<html>
    <head>
        <title>Task 1 - Echo</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Name</td>
                <td><%= name %></td>
            </tr>
            <tr>
                <td>Age</td>
                <td><%= age %></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><%= address %></td>
            </tr>
            <tr>
                <td>Occupation</td>
                <td><%= occupation %></td>
            </tr>
        </table>
    </body>
</html>