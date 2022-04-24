package seng2050_2022;

import java.io.*;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/Page2"})
public class Page2 extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
			PrintWriter out = response.getWriter();
			HtmlGenerator htmlGenerator = new HtmlGenerator();
			out.println(htmlGenerator.start());
			
			
			String colour = request.getParameter("colour");
			if(colour != null){
				out.println(htmlGenerator.head("Hello World", new String[]{"css/site.css"}));
				out.println(htmlGenerator.startBody(colour));
				out.println(htmlGenerator.indent(htmlGenerator.header("Hellow World"), 1));
				out.println("You selected colour "+ colour);
			}

			String letter = request.getParameter("letter");
			if(letter != null){
				out.println(htmlGenerator.head("Hello World"));
				out.println(htmlGenerator.startBody());
				out.println(htmlGenerator.indent(htmlGenerator.header("Hellow World"), 1));
				out.println("You selected letter "+ letter);				
			}
			
			out.println(htmlGenerator.endBody());
			out.println(htmlGenerator.end());
		}
}