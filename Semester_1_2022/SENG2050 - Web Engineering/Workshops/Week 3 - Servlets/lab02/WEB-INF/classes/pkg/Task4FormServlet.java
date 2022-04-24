package pkg;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/task4"})
public class Task4FormServlet extends HttpServlet {
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
        out.println(HTMLGen.heading(1, "Task 4"));
        out.println(HTMLGen.p("This form is submitted via GET"));
        out.println("<form action=\"/lab02/task4-submission\" method=\"GET\">");
        out.println("<input type=\"text\" name=\"name\" />");
        out.println("<input type=\"submit\" />");
        out.println("</form>");
        out.println("<hr />");
        out.println(HTMLGen.p("This form is submitted via POST"));
        out.println("<form action=\"/lab02/task4-submission\" method=\"POST\">");
        out.println("<input type=\"text\" name=\"name\" />");
        out.println("<input type=\"submit\" />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}