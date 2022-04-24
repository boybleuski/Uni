import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = {"/ForumPage"})
public class ForumPage extends HttpServlet {
    private List<String> messageList = new ArrayList<String>();

    /** ForumPage GET method - called on load.
     * @param (HttpServletRequest) request - GET request.
     * @param (HttpServletResponse) response - GET response.
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String html = HtmlGen.head("Discussion Board");
        html += "<body>" + HtmlGen.h1("Discussion Board");
        html += HtmlGen.messageTable(messageList);

        html += HtmlGen.form("ForumPage");

        PrintWriter out = response.getWriter();
        out.println(html);
    }


    /** ForumPage POST method - called when form is submitted.
     * @param (HttpServletRequest) request - POST request.
     * @param (HttpServletResponse) response - POST response.
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = messageList.size();
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        
        String userName = request.getParameter("userName");
        String msgContent = request.getParameter("msgContent");
        String msgTitle = request.getParameter("msgTitle");

        messageList.add(HtmlGen.buildMessage(id, userName, msgTitle, msgContent));

        String html = HtmlGen.head("Discussion Board");
        html += "<body>" + HtmlGen.h1("Discussion Board");
        html += HtmlGen.messageTable(messageList);
        html += HtmlGen.form("ForumPage");

        
        PrintWriter out = response.getWriter();
        out.println(html);

        // POST/REDIRECT/GET to solve the duplicate data problem.
        response.sendRedirect("/Assignment1/ForumPage");
    }
}