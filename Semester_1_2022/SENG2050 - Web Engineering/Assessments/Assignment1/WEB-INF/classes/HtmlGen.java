import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HtmlGen {
    /** Generate <head> HTML.
     * @param (String) heading - The heading of the page.
     * @return (String) - Head as HTML.
     */
    public static String head(String heading) {
        String html = "<head>";
        html += "<title>" + heading + "</title>"; 
        html += "<script src=\"/Assignment1/js/validate.js\"></script>"; 
        html += "<link rel=\"stylesheet\" href=\"/Assignment1/css/ForumPage.css\">";
        html += "</head>";

        return html;
    }

    /** Generate <h1> HTML.
     * @param (String) content - The content of the heading.
     * @return (String) - <h1> as HTML.
     */
    public static String h1(String content) {
        return "<h1>" + content + "</h1>";
    }

    /** Output all messages as HTML.
     * @param (List<String>) msgList - List of stringified HTML messages.
     * @return (String) - List as HTML.
     */
    public static String messageTable(List<String> msgList) {
        String html = "";

        for (int index=0; index<msgList.size(); index++) {
            html += msgList.get(index);
        }
        
        return html;
    }

    /** Generate message form as HTML.
     * @param (String) formAction - Servlet to post form to.
     * @return (String) - Message as HTML.
     */
    public static String form(String formAction) {
        String html = "<form action=\"" + formAction + "\" method=\"post\" onsubmit=\"return validate()\">";
        html += "<label for=\"userName\">User Name</label>";
        html += "<input type=\"text\" name=\"userName\" id=\"userName\" /><br>";
        html += "<label for=\"msgTitle\">Title</label>";
        html += "<input type=\"text\" name=\"msgTitle\" id=\"msgTitle\" /><br>";
        html += "<label for=\"msgContent\">Content</label>";
        html += "<input type=\"text\" name=\"msgContent\" id=\"msgContent\" /><br>";
        html += "<input type=\"submit\" value=\"Post\" /></form>";
        
        return html;
    }

    /** Error message to replace form with when max messages reached.
     * @return (String) - Error message as HTML.
     */
    public static String noMoreMessages() {
        String html = "<div>";
        html += "<p>You cannot submit any more messages.</p>";
        html += "</div>";

        return html;
    }

    /** Generate HTML for an existing message.
     * @param (int) index - Index of message in list.
     * @param (String) userName - User name entered.
     * @param (String) msgTitle - Title entered for message.
     * @param (String) msgContent - Content of message.
     * @return (String) - Message as HTML.
     */
    public static String buildMessage(int index, String userName, String msgTitle, String msgContent) {
        // Convert date to AUS standard.
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String msgDate = format.format(new Date()); 

        String msgHtml = "<a href=\"MessagePage?index=" + index + "&userName=" + userName + "&msgTitle=" + msgTitle + "&msgContent=" + msgContent + "&msgDate=" + msgDate + "\">";
        msgHtml += "<table>";
        msgHtml += "<tr><td class=\"message-label\">Name:</td><td>" + userName + "</td></tr>";
        msgHtml += "<tr><td class=\"message-label\">Title:</td><td>" + msgTitle + "</td></tr>";
        msgHtml += "<tr><td class=\"message-label\">Time:</td><td>" + msgDate + "</td></tr>";
        msgHtml += "</table></a>";

        return msgHtml;
    }
    
    /** Generate HTML for a parent message.
     * @param (String) userName - User name entered.
     * @param (String) msgTitle - Title entered for message.
     * @param (String) msgContent - Content of message.
     * @param (String) msgDate - Date message was posted.
     * @return (String) - Message as HTML.
     */
    public static String getParentMessage(String userName, String msgTitle, String msgContent, String msgDate) {
        // add link.
        String msgHtml = "<table>";
        msgHtml += "<tr><td class=\"message-label\">Name:</td><td>" + userName + "</td></tr>";
        msgHtml += "<tr><td class=\"message-label\">Title:</td><td>" + msgTitle + "</td></tr>";
        msgHtml += "<tr><td class=\"message-label\">Content:</td><td>" + msgContent + "</td></tr>";
        msgHtml += "<tr><td class=\"message-label\">Time:</td><td>" + msgDate + "</td></tr>";
        msgHtml += "</table></a>";

        return msgHtml;
    }
}