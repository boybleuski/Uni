public class HtmlGen{
	public static String doctype(){
		return "<!DOCTYPE html>\n<html lang=\"en\">";
	}

	public static String head(String title){
		return "<head><title>" + title + "</head></title>";
	}

	public static String h1(String heading){
		return "<h1>" + heading + "</h1>";
	}

	//Other methods to generate frequently used tags
	//maybe generate a table
	//...
	//...
}
