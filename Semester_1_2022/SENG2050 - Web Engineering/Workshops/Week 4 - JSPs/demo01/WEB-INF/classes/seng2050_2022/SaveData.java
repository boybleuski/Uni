package seng2050_2022;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/SaveData"})
public class SaveData extends HttpServlet {
	
	private String contextPath;
	
	@Override
	public void init(final ServletConfig config) {
        contextPath = config.getServletContext().getRealPath("/");
    }
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		HtmlGenerator htmlGenerator = new HtmlGenerator();
				
		String typeParameter = request.getParameter("type");
		if(typeParameter == null){
			out.println("<p>Please provide the type parameter.");
			return;
		}

		ObjectOutputStream saveStream = new ObjectOutputStream(
			new FileOutputStream("SaveData.dat"));		
		Object[] dataToSave;		
		if(typeParameter.equals("numbers")){
			dataToSave = new Integer[]{1,3,4,5,2,3,333,234567,0};
		}else if(typeParameter.equals("characters")){
			dataToSave = new Character[]{'a','b','f','k'};
		}else{
			dataToSave = new Object[0];
		}
		saveStream.writeObject(dataToSave);
		saveStream.flush();
		saveStream.close();
		
		dataToSave = null;
		
		out.println("<p>Your file was saved. Check directory " + contextPath);
		
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("SaveData.dat"));
		try{
			Object[] fromFile = (Object[]) inputStream.readObject();
			inputStream.close();
			
			out.println("<ul>");
			for(Object o : fromFile){
				out.println("<li>"+ o +"</li>");
			}
			out.println("</ul>");
		}
		catch(ClassNotFoundException c){
			out.println("There was an issue reading the file");
		}
	}
}