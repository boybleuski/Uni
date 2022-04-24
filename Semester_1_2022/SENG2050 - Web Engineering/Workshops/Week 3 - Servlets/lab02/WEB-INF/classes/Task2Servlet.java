import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import pkg.HTMLGen;

@WebServlet(urlPatterns = {"/task2"})
public class Task2Servlet extends HttpServlet {
    public void doGet(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        out.println(HTMLGen.doctype());
        out.println("<html>");
        out.println("<head>");
        out.println(HTMLGen.title("Task 2"));
        out.println("</head>");
        out.println("<body>");
        out.println(HTMLGen.heading(1, "Task 2"));
        out.println(HTMLGen.p("This is a paragraph"));
        out.println("</body>");
        out.println("</html>");
    }
}