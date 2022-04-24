package pkg;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/task4-submission"})
public class Task4FormSubmissionServlet extends HttpServlet {
    public void doGet(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws IOException, ServletException {
        String name = request.getParameter("name");

        PrintWriter out = response.getWriter();
        out.println(HTMLGen.doctype());
        out.println("<html>");
        out.println("<head>");
        out.println(HTMLGen.title("Task 3"));
        out.println("</head>");
        out.println("<body>");
        out.println(HTMLGen.heading(1, "Task 4 Data"));
        out.println(HTMLGen.p("The name is: " + name));
        out.println(HTMLGen.p("This data was retreived via GET"));
        out.println(HTMLGen.p("You can see the parameter in the address bar."));
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws IOException, ServletException {
        String name = request.getParameter("name");

        PrintWriter out = response.getWriter();
        out.println(HTMLGen.doctype());
        out.println("<html>");
        out.println("<head>");
        out.println(HTMLGen.title("Task 3"));
        out.println("</head>");
        out.println("<body>");
        out.println(HTMLGen.heading(1, "Task 4 Data"));
        out.println(HTMLGen.p("The name is: " + name));
        out.println(HTMLGen.p("This data was retreived via POST"));
        out.println(HTMLGen.p("The parameter was sent in the HTTP body. You cannot see it in the address bar."));
        out.println("</body>");
        out.println("</html>");
    }
}