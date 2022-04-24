import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = {"/MessagePage"})
public class MessagePage extends HttpServlet {
    private List<String> subMessageList = new ArrayList<String>();
    private String parentName = "";
    private String parentTitle = "";
    private String parentContent = "";
    private String parentDate = "";
    
    /** MessagePage GET method - called on load.
     * @param (HttpServletRequest) request - GET request.
     * @param (HttpServletResponse) response - GET response.
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        parentName = request.getParameter("userName");
        parentTitle = request.getParameter("msgTitle");
        parentContent = request.getParameter("msgContent");
        parentDate = request.getParameter("msgDate");
        
        String html = HtmlGen.head("Message");
        html += "<body>" + HtmlGen.h1("Message from " + parentName);
        html += HtmlGen.getParentMessage(parentName, parentTitle, parentContent, parentDate);
        html += HtmlGen.messageTable(subMessageList);

        // Prevent loading form if excessive replies exist.
        if (subMessageList.size() < 10) {
            html += HtmlGen.form("ForumPage");
        } else {
            html += HtmlGen.noMoreMessages();
        }

        PrintWriter out = response.getWriter();
        out.println(html);
    }

    /** MessagePage POST method - called when form is submitted.
     * @param (HttpServletRequest) request - POST request.
     * @param (HttpServletResponse) response - POST response.
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add reply to list on submission.        
        String html = HtmlGen.head("Message from " + parentName);
        html += "<body>" + HtmlGen.h1("Message from " + parentName);
        html += HtmlGen.getParentMessage(parentName, parentTitle, parentContent, parentDate);
        html += HtmlGen.messageTable(subMessageList);

        // Prevent loading form if excessive replies exist.
        if (subMessageList.size() < 10) {
            html += HtmlGen.form("ForumPage");
        } else {
            html += HtmlGen.noMoreMessages();
        }

        PrintWriter out = response.getWriter();
        out.println(html);

        // POST/REDIRECT/GET to solve the duplicate data problem.
        response.sendRedirect("/Assignment1/ForumPage");
    }

}