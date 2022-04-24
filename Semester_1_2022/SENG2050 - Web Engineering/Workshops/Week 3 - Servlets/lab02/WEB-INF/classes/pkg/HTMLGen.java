package pkg;

public class HTMLGen {
    public static String doctype() {
        return "<!doctype html>";
    }

    public static String title(String title) {
        return "<title>" + title + "</title>";
    } 

    public static String heading(int level, String text) {
        return "<h" + level + ">" + text + "</h" + level + ">";
    }

    public static String css(String link) {
        return "<link href=\"" + link + "\" rel=\"stylesheet\" />";
    }

    public static String script(String href) {
        return "<script src=\"" + href + "\"></script>";
    }

    public static String p(String text) {
        return "<p>" + text + "</p>";
    } 
}