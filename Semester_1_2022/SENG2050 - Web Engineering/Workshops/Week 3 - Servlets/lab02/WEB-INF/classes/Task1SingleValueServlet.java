import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/task1-single"})
public class Task1SingleValueServlet extends HttpServlet {
    public void doGet(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws IOException, ServletException {

        String name = request.getParameter("name");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Task1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Single Value Parameter</h1>");
        
        out.println("<p>");
        if (name == null) {
            out.println("No name was passed!");
        } else {    
            out.println("You passed the name value: '" + name + "'");
        }
        out.println("</p>");

        out.println("See the URI for the passed value!");
        out.println("</body>");
        out.println("</html>");
    }
}