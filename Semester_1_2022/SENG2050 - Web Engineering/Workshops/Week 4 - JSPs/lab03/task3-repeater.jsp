<%
    String rowsStr = request.getParameter("rows");
    String columnsStr = request.getParameter("columns");
    String content = request.getParameter("content");

    boolean error = false;

    int rows = 0;
    int columns = 0;

    try {
        rows = Integer.parseInt(rowsStr);
        columns = Integer.parseInt(columnsStr);
    } catch (NumberFormatException e) {
        error = true;
    }

    if (rows < 0 || columns < 0) {
        error = true;
    }
%>
<html>
    <head>
        <title>Task 3 - Repeater</title>
    </head>
    <body>
        <% if (error) { %>
            <p>
                The parameters are invalid!
            </p>
        <% } else { %>
            <p>
                Printing out a <%= rows %> by <%= columns %> table containing <%= content %>.
            </p>
            <table>
                <% for (int r = 0; r < rows; r++) { %>
                    <tr>
                        <% for (int c = 0; c < columns; c++) { %>
                            <td>
                                <%= content %>
                            </td>
                        <% } %>
                    </tr>
                <% } %>
            </table>
        <% } %>
    </body>
</html>