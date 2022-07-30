/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.maven;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static cc.altius.maven.AllAltiusUtil.round;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author altius
 */
public class AllAltiusUtil {
    
    
    
    
    /**
     * Rounds a double to the No of digits
     *
     * @param originalNumber double to be rounded
     * @param noOfDigits no of digits to round to
     * @return returns the rounded number
     */
    static public double round(double originalNumber, int noOfDigits) {
        double finalNumber = Math.round(originalNumber * Math.pow(10, noOfDigits));
        return finalNumber / (Math.pow(10, noOfDigits));
    }
    
    
     public static String fetchData(String variableName, HttpServletRequest request, HttpSession session, String defaultValue) {
        String value;
        String tmpValue = ((String) session.getAttribute(variableName) == null ? defaultValue : (String) session.getAttribute(variableName));
        value = (request.getParameter(variableName) == null ? tmpValue : (String) request.getParameter(variableName));
        putData(variableName, session, value);
        return value;
    }

    public static int fetchData(String variableName, HttpServletRequest request, HttpSession session, int defaultValue) {
        Integer value;
        Integer tmpValue = ((Integer) session.getAttribute(variableName) == null ? defaultValue : (Integer) session.getAttribute(variableName));
        try {
            value = (request.getParameter(variableName) == null ? tmpValue : Integer.parseInt((String) request.getParameter(variableName)));
        } catch(NumberFormatException n) {
            value = defaultValue;
        }
        putData(variableName, session, value);
        return value.intValue();
    }
    
    public static void putData(String variableName, HttpSession session, String value) {
        session.setAttribute(variableName, value);
    }

    public static void putData(String variableName, HttpSession session, int value) {
        session.setAttribute(variableName, value);
    }
    
    
     /**
     * Indian time zone
     */
    static public String IST = "Asia/Calcutta";
    /**
     * London time zone
     */
    static public String GMT = "Europe/London";
    /**
     * New York or Eastern time zone
     */
    static public String EST = "US/Eastern";
    /**
     * Central time zone
     */
    static public String CST = "US/Central";
    /**
     * Mountain time zone
     */
    static public String MST = "US/Mountain";
    /**
     * Los Angeles or Pacific time zone
     */
    static public String PST = "US/Pacific";
    /**
     * UTC time zone
     */
    static public String UTC = "Etc/UTC";
    /**
     * Gulf time zone
     */
    static public String GST = "Asia/Dubai";
    /**
     * "yyyy-MM-dd" Date format
     */
    static public String YMD = "yyyy-MM-dd";
    /**
     * "yyyy-MM-dd" Date format
     */
    static public String DMY = "dd-MM-yyyy";
    /**
     * "dd-MM-yyyy" Date format
     */
    static public String DMMY = "dd-MMM-yyyy";
    /**
     * "dd-MMM-yyyy" Date format
     */
    static public String MMY = "MMM-yyyy";
    /**
     * "MMM-yyyy" Date format
     */
    static public String MMMMDY = "MMMMM dd, yyyy";
    /**
     * "MMMMM dd, yyyy" Date format
     */
    static public String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * "yyyy-MM-dd HH:mm:ss" Date format
     */
    static public String YMDHM = "yyyy-MM-dd HH:mm";
    /**
     * "yyyy-MM-dd HH:mm" Date format
     */
    static public String DMYHMS = "dd-MM-yyyy HH:mm:ss";
    /**
     * "dd-MM-yyyy HH:mm:ss" Date format
     */
    static public String DMYHM = "dd-MM-yyyy HH:mm";
    /**
     * "dd-MM-yyyy HH:mm" Date format
     */
    static public String Y = "yyyy";
    static public String[] dayName = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static public String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October"};
    
    /**
     * Returns the current date in a String format
     *
     * @param timeZone Select from the TimeZones available in DateUtils
     * @param format Select from the Formats available in DateUtils
     * @return Returns Date value in format specified for the given time zone.
     */
    static public String getCurrentDateString(String timeZone, String format) {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(TimeZone.getTimeZone(timeZone));
        return df.format(now);
    }

    /**
     * Returns the current date object for the time zone specified
     *
     * @param timeZone Select from the TimeZones available in DateUtils
     * @return Returns current Date object for the given time zone.
     */
    static public Date getCurrentDateObject(String timeZone) {
        String format = AllAltiusUtil.YMDHMS;
        DateFormat df = new SimpleDateFormat(format);
        Date tmpDate = null;
        try {
            tmpDate = df.parse(getCurrentDateString(timeZone, format));
        } catch (ParseException pe) {
            tmpDate = new Date();
        }
        return tmpDate;
    }
    
     /**
     * Returns the date as an offset from the current date as a Date object.
     *
     * @param timeZone Select from the TimeZones available in DateUtils
     * @param days The number of days that you want as the offset. Can be
     * positive or negative.
     * @return Returns Date object offset from the current date for the given
     * time zone.
     */
    static public Date getOffsetFromCurrentDateObject(String timeZone, int days) {
        Date now = getCurrentDateObject(timeZone);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(now);
        c1.add(Calendar.DATE, days);
        return c1.getTime();
    }

    
    /**
     * Checks if a String is either null or empty
     *
     * @param input String to check
     * @return returns true if the String was null or empty
     */
    public static boolean isBlank(String input) {
        if (input == null) {
            return true;
        } else {
            return input.isEmpty();
        }
    }
    
    /**
     * Format a Date object into a standard format.
     *
     * @param dt Date object that you want to work on.
     * @param format Select the format that you want output in. Select from the
     * formats available in DateUtils.
     * @return Returns the Date in your specified format.
     */
    static public String formatDate(Date dt, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(dt);
    }
    
    
    /**
     * Compares two Strings that are in HH:mm:ss format to see which one is
     * greater. Returns -1 if d1 is less than d2, 1 if d1 is greater than d2, 0
     * if both are equal.
     *
     * @param t1 Time to be compared. Must be in HH:mm:ss format.
     * @param t2 Time to be compared. Must be in HH:mm:ss format.
     * @return Returns -1 if d1 is less than d2, 1 if d1 is greater than d2, 0
     * if both are equal.
     */
    private static int compareTime(String t1, String t2) {
        int hour1 = Integer.parseInt(t1.substring(0, 2));
        int min1 = Integer.parseInt(t1.substring(3, 5));
        int sec1 = Integer.parseInt(t1.substring(6, 8));
        int hour2 = Integer.parseInt(t2.substring(0, 2));
        int min2 = Integer.parseInt(t2.substring(3, 5));
        int sec2 = Integer.parseInt(t2.substring(6, 8));
        if (hour1 < hour2) {
            return (-1);
        } else if (hour1 > hour2) {
            return (1);
        } else if (min1 < min2) {
            return (-1);
        } else if (min1 > min2) {
            return (1);
        } else if (sec1 < sec2) {
            return (-1);
        } else if (sec1 > sec2) {
            return (1);
        } else {
            return (0);
        }
    }
    
    
    /**
     * Compares two Date that are in yyyy-MM-dd format to see which one is
     * greater. The assumption is that both the Dates are from the same time
     * zone returns -1 if d1 is less than d2, 1 if d1 is greater than d2, 0 if
     * both are equal.
     *
     * @param d1 Date to be compared. Could be either yyyy-MM-dd or yyyy-MM-dd
     * HH:mm:ss formats.
     * @param d2 Date to be compared. Could be either yyyy-MM-dd or yyyy-MM-dd
     * HH:mm:ss formats.
     * @return Returns -1 if d1 is less than d2, 1 if d1 is greater than d2, 0
     * if both are equal.
     */
    static public int compareDates(String d1, String d2) {
        java.util.Calendar startDt = java.util.Calendar.getInstance();
        java.util.Calendar stopDt = java.util.Calendar.getInstance();
        // length is greater than 10 it is safe to assume that the hours min and seconds are also part of the string
        // if its not > 10 then only yyyy-mm-dd is the string pattern
        boolean compareTime;
        String time1 = "", time2 = "";
        if (d1.length() <= 10 || d2.length() <= 10) {
            startDt.set(Integer.parseInt(d1.substring(0, 4)), Integer.parseInt(d1.substring(5, 7)) - 1, Integer.parseInt(d1.substring(8, 10)));
            stopDt.set(Integer.parseInt(d2.substring(0, 4)), Integer.parseInt(d2.substring(5, 7)) - 1, Integer.parseInt(d2.substring(8, 10)));
            compareTime = false;
        } else {
            startDt.set(Integer.parseInt(d1.substring(0, 4)), Integer.parseInt(d1.substring(5, 7)) - 1, Integer.parseInt(d1.substring(8, 10)), Integer.parseInt(d1.substring(11, 13)), Integer.parseInt(d1.substring(14, 16)), Integer.parseInt(d1.substring(17, 19)));
            stopDt.set(Integer.parseInt(d2.substring(0, 4)), Integer.parseInt(d2.substring(5, 7)) - 1, Integer.parseInt(d2.substring(8, 10)), Integer.parseInt(d2.substring(11, 13)), Integer.parseInt(d2.substring(14, 16)), Integer.parseInt(d2.substring(17, 19)));
            compareTime = true;
            time1 = d1.substring(11, 19);
            time2 = d2.substring(11, 19);
        }
        int year1, year2, month1, month2, date1, date2;

        year1 = startDt.get(java.util.Calendar.YEAR);
        month1 = startDt.get(java.util.Calendar.MONTH);
        date1 = startDt.get(java.util.Calendar.DAY_OF_MONTH);

        year2 = stopDt.get(java.util.Calendar.YEAR);
        month2 = stopDt.get(java.util.Calendar.MONTH);
        date2 = stopDt.get(java.util.Calendar.DAY_OF_MONTH);

        if (year1 == year2 && month1 == month2 && date1 == date2) {
            if (compareTime) {
                return compareTime(time1, time2);
            } else {
                return (0);
            }
        } else if (year1 < year2 || (year1 == year2 && month1 < month2) || (year1 == year2 && month1 == month2 && date1 < date2)) {
            return (-1);
        } else {
            return (1);
        }
    }

    /**
     * Replaces single quotes and double quotes with the escaped sequence for
     * those quotes
     *
     * @param s String to check
     * @return escaped String
     */
    public static String escapeQuotes(String s) {
        CharSequence source1 = "\'";
        CharSequence target1 = "\\\'";
        CharSequence source2 = "\"";
        CharSequence target2 = "\\\"";
        return s.replace(source1, target1).replace(source2, target2);
    }
    
    private static final int MIN_LENGTH = 8;
    protected static java.util.Random r = new java.util.Random();
    protected static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
        'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    protected static char[] goodSmallCaseChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
        'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * Get a random generated String that is only in small case or digits
     *
     * @return Returns the random generated String of size 8
     */
    public static String getSmallCasePassword() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MIN_LENGTH; i++) {
            sb.append(goodSmallCaseChar[r.nextInt(goodSmallCaseChar.length)]);
        }
        return sb.toString();
    }

    /**
     * Get a random generated String that is only in small case or digits
     *
     * @param length Length that you want the string to be
     * @return Returns the random generated String of size `length`
     */
    public static String getSmallCasePassword(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(goodSmallCaseChar[r.nextInt(goodSmallCaseChar.length)]);
        }
        return sb.toString();
    }
}
