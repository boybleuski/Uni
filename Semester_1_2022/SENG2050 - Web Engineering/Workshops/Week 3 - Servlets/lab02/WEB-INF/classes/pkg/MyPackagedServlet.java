package pkg;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/packaged"})
public class MyPackagedServlet extends HttpServlet {
    public void doGet(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        out.println(HTMLGen.doctype());
        out.println("<html>");
        out.println("<head>");
        out.println(HTMLGen.title("Task 3"));
        out.println("</head>");
        out.println("<body>");
        out.println(HTMLGen.heading(1, "Task 3"));
        out.println(HTMLGen.p("This Servlet is in a package"));
        out.println(HTMLGen.p("You can import a class from another package using a Java 'import' declaration. See Task2Servlet.java for an example!"));
        out.println(HTMLGen.p("A class in a package must have a package declaration as the first line of the .java file."));
        out.println(HTMLGen.p("From the classes directory, you can compile a class in a package with <code>javac pkg/*.java</code>"));
        out.println(HTMLGen.p("If you have a mix of classes both in and out of packages you can use <code>javac *.java pkg/*.java</code>"));
        out.println("</body>");
        out.println("</html>");
    }
}