<jsp:useBean id="counter" class="lab3.CounterBean" scope="page" />
<html>
    <head>
        <title>Task 4a - Counter</title>
    </head>
    <body>
        <p>
            This page has been accessed <%= counter.getNextValue() %> time(s).
        </p>
        <hr />
        <p>
            This page uses a 'page' scoped bean. This means the bean is accessible only from this page.
        </p>
        <p>
            But, we have 3 other scopes to pick from. Try changing the scope to 'application', 'request', and 'session'.
        </p>
        <p>
            If we use an 'application' scoped bean, a single counter will be shared between <em>all</em> pages. Try copying the <code>jsp:useBean</code> and <code>jsp:getProperty</code> tags to another page to test this out.
        </p>
        <p>
            If we use a 'request' scoped bean, a counter will be created for each request. The counter should never pass 1.
        </p>
        <p>
            If we use a 'session' scoped bean, a counter will be created for each user. The counter will track the number of times each user visits the page. Note: Open this page in multiple 'private' or 'igcognito' browser windows to see what happens.
        </p>
    </body>
</html>