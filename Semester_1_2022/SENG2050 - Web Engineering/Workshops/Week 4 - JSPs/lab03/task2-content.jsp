<%
    String name = request.getParameter("name");
    String ageStr = request.getParameter("age");

    String msg = null;

    int age = 0;
    try {
        age = Integer.parseInt(ageStr);
    } catch (NumberFormatException ex) {
        msg = "No age found!";
    }

    if (msg == null) {
        if (age < 18) msg = "You're too young to visit this site!";
        else if (age > 67) msg = "Go home grampa!";
        else msg = "Welcome to our site, " + name + ".";
    }
%>

<html>
    <head>
        <title>Task 2 - Content</title>
    </head>
    <body>
        <em>Look at the source code to see how this page is generated!</em>
        <h1>Method 1 - Using a variable</h1>
        <p>
            <%= msg %>
        </p>

        <h1>Method 2 - Using multiple scriptlets</h1>
        <% if (msg != null) { %>
            <p><%= msg %></p>
        <% } else if (age < 18) { %>
            <p>You're too young to visit this site!</p>
        <% } else if (age > 67) { %>
            <p>Go home grampa!</p>
        <% } else { %>
            <p>Welcome to our site, <%= name %>.</p>
        <% } %>
    </body>
</html>