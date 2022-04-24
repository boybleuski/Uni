package seng2050_2022;

import java.util.List;

public class HtmlGenerator{
	
	public String indent(String content, int count){
		StringBuilder result = new StringBuilder();
		for(int i=0; i<count; i++){
			result.append("\t");
		}
		return result.append(content).toString();
	}
	
	public String header(String contents){	
		return "<h1>" + contents + "</h1>\n";
	}
	
	public String start(){
		return "<!DOCTYPE html>\n<html>\n";
	}
	
	public String head(String title){
		return head(title,new String[0]);
	}
	
	public String head(String title, String[] css){
		StringBuilder result = new StringBuilder("<head>\n");
		result.append(indent("<title>"+ title +"</title>\n",1));
		
		for(String stylesheet : css){
			result.append(indent("<link rel=\"stylesheet\" type=\"text/css\" href=\""+ stylesheet +"\">\n",1));
		}
		
		return result.append("</head>\n").toString();
	}
	
	public String javaScript(String src){
		return "<script src=\""+src+ "\"></script>";
	}
	
	public String startBody(String cssClass){
		return "<body class=\""+cssClass+"\">\n";	
	}
	
	public String startBody(){
		return "<body>\n";
	}
	
	public String endBody(){
		return "</body>\n";
	}
		
	public String end(){
		return "</html>\n";
	}
	
	public String unorderedList(List<String> contents){
		StringBuilder result = new StringBuilder("<ul>\n");
		for(String content : contents){
			result.append(indent("<li>"+content+"</li>\n",1));
		}
		return result.append("</ul>").toString();
	}
	
	public String anchor(String href, String content){
		return "<a href=\""+href+"\">"+content+"</a>";
	}
	
}