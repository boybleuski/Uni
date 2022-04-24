package seng2050_2022;

import java.io.*;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/Home"})
public class Home extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
			PrintWriter out = response.getWriter();
			HtmlGenerator htmlGenerator = new HtmlGenerator();
			out.println(htmlGenerator.start());
			out.println(htmlGenerator.head("Hello World"));
			out.println(htmlGenerator.startBody());
			out.println(htmlGenerator.header("Hello World"));
			
			LinkedList<String> colours = new LinkedList<>();
			colours.add(htmlGenerator.anchor("Page2?colour=Blue","Blue"));
			colours.add(htmlGenerator.anchor("Page2?colour=Yellow","Yellow"));
			colours.add(htmlGenerator.anchor("Page2?colour=Red","Red"));
			colours.add(htmlGenerator.anchor("Page2?colour=Green","Green"));
			out.println(htmlGenerator.unorderedList(colours));
			
			out.println("<ul>");
			for(char linkText='A'; linkText<='H'; linkText++){
				out.println(htmlGenerator.indent("<li><a href=\"Page2?letter="+linkText+"\">"+linkText+"</a></li>",1));
			}
			out.println("</ul>");
			
			out.println(htmlGenerator.endBody());
			out.println(htmlGenerator.end());
		}
}