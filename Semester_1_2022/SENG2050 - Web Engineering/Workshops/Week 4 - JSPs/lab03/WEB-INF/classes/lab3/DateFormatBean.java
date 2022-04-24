package lab3;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateFormatBean {

    private String format = "dd-MM-yy hh-mm-ss a";

    public DateFormatBean() {}

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
