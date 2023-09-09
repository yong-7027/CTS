package Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";

    public static String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(new Date());
    }

    public static String getCurrentTime(){
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
        return timeFormat.format(new Date());
    }

    public static boolean dateValidation(String date, String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false); // 关闭宽松解析

        try {
            dateFormat.parse(date);

            return true;
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter again.\n");

            return false;
        }
    }
}