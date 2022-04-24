<%@page import="java.util.concurrent.atomic.AtomicInteger" %>
<%! AtomicInteger counter = new AtomicInteger(); %>

<html>
    <head>
        <title>Task 4a - Counter</title>
    </head>
    <body>
        <p>
            This page has been accessed <%= counter.incrementAndGet() %> time(s).
        </p>
    </body>
</html>