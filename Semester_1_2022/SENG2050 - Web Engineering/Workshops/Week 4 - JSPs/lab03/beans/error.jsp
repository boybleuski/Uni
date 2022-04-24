<%@ page isErrorPage="true" %>  

<html>
    <head>
        <title>An error occured</title>
    </head>
    <body>
        <h3>Sorry an error occured!</h3>  
        
        <p>
            Please check your inputs and try again.
        </p>

        <p>
            Exception is: <%= exception.getMessage() %>  
        </p>
    </body>
</html>