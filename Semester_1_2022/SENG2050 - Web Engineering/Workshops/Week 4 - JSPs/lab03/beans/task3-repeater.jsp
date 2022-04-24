<%@ page errorPage="error.jsp" %>  

<jsp:useBean id="input" class="lab3.InputBean" scope="request" />
<jsp:setProperty name="input" property="rows" param="rows" />
<jsp:setProperty name="input" property="columns" param="columns" />
<jsp:setProperty name="input" property="content" param="content" />

<html>
    <head>
        <title>Task 3 - Repeater</title>
    </head>
    <body>
        <table>
            <% for (int r = 0; r < input.getRows(); r++) { %>
                <tr>
                    <% for (int c = 0; c < input.getColumns(); c++) { %>
                        <td>
                            <%= input.getContent() %>
                        </td>
                    <% } %>
                </tr>
            <% } %>
        </table>
    </body>
</html>