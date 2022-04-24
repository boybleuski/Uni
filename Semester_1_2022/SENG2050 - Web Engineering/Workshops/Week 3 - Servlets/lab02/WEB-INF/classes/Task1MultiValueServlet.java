import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/task1-multi"})
public class Task1MultiValueServlet extends HttpServlet {
    public void doGet(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws IOException, ServletException {

        String[] numbers = request.getParameterValues("number");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Task1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Single Value Parameter</h1>");
        
        if (numbers == null || numbers.length == 0) {
            out.println("<p>No number values were passed!</p>");
        } else {    
            out.println("<p>These numbers were provided</p>");

            out.println("<ul>");
            for (String number : numbers) {
                out.println("<li>" + number + "</li>");
            }
            out.println("</ul>");
        }

        out.println("See the URI for the passed value!");
        out.println("</body>");
        out.println("</html>");
    }
}