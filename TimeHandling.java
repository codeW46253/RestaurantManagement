/**
 * To manage TimeDate Handling for better
 * time formation
 * 
 * @author Zul Iskandar
 * @version 1.0 [July 10th, 2024]
 */

public class TimeHandling {
    // Constant
    public static final int DD_MM_YYYY = 0;
    public static final int MN_DD_YYYY = 1;
    public static final int MM_DD_YYYY = 2;
    public static final int YYYY_MM_DD = 3;

    public static final int HH_MM_24    = 0;
    public static final int HH_MM_SS_24 = 1;
    public static final int HH_MM_12    = 2;
    public static final int HH_MM_SS_12 = 3;
 
    // Attributes
    private int second;
    private int minute;
    private int hour;

    private int year;
    private int month; private String monthName;
    private int day;

    private boolean is24Hour;
    private boolean isPM;

    // Constructor
    /** Set to default value */
    public TimeHandling() { // No Param Constructor
        second = 0;
        minute = 0;
        hour   = 0;
        year   = 0;
        month  = 0;
        day    = 0;

        is24Hour = true;
        isPM = false;
    }

    /** Set both Time and Date
     * @param hour   - Enter Hour value [0 - 23]
     * @param minute - Enter Minute value [0 - 59]
     * @param second - Enter Seconds value [0 - 59]
     * 
     * @param year  - Enter Year value
     * @param month - Enter Month value
     * @param day   - Enter Day value
    */
    public TimeHandling(  // Time-Date Constructor
        int hour,
        int minute,
        int second,

        int year,
        int month,
        int day,

        boolean is24Hour
    ) {
        setTime(hour, minute, second, is24Hour);
        setDate(year, month, day);
    }

    // Accessor
    /** Get Formated Date */
    public String getFormatedDate(int format) {
        switch (format) {
            case 0:
                // return in format DD-MM-YYYY
                return String.format("%2d/%2d/%4d", day, month, year );

            case 1:
                // return in format MMM DD, YYYY 
                return String.format("%s %2d, %4d", monthName, day, year);

            case 2:
                // return in format MM-DD-YYYY
                return String.format("%2d/%2d/%4d", month, day, year);
            
            case 3:
                // return in format YYYY-MM-DD
                return String.format("%4d/%2d/%2d", year, month, day);
        
            default:
                return "invalidFormat";
        }
    }

    /** Get Formated Time */
    public String getFormatedTime(int format) {
        switch (format) {
            case 0:
                return (is24Hour) ? String.format("%2d:%2d", hour, minute) : "Time is not in 24-Hour Format";

            case 1:
                return (is24Hour) ? String.format("%2d:%2d:%2d", hour, minute, second) : "Time is not in 24-Hour Format";

            case 2:
                return (!is24Hour) ? String.format("%2d:%2d %s", hour, minute, (isPM ? "PM" : "AM")) : "Time is not in 12-Hour Format";

            case 3:
                return (!is24Hour) ? String.format("%2d:%2d:%2d %s", hour, minute, second, (isPM ? "PM" : "AM")) : "Time is not in 12-Hour Format";
        
            default:
                return "invalidFormat";
        }
    }

    /** Get Formated Date */
    public String getFormatedDateTime(int dateFormat, int timeFormat) {
        return getFormatedDate(dateFormat) + " " + getFormatedTime(dateFormat);
    }

    // Modifier
    /** Set a specific Time
     * @param hour   - Enter Hour value [0 - 23]
     * @param minute - Enter Minute value [0 - 59]
     * @param second - Enter Seconds value [0 - 59]
     * 
     * @param is24Hour - Set true for 24-hour system, false for 12-hour system
    */
    public void setTime(int hour, int minute, int second, boolean is24Hour) {
        this.second = second % 60;
        this.minute = (minute + (second / 60)) % 60;
        this.hour   = (hour + (minute / 60) + (second / 360)) % 24;

        this.is24Hour = is24Hour;
        this.isPM = (this.hour % 24) >= 12;
    }

    /** Set a specific Date
     * @param year  - Enter Year value
     * @param month - Enter Month value
     * @param day   - Enter Day value
    */
    public void setDate(int year, int month, int day) {
        this.year = year;

        this.month  = (month % 12 == 0 ? 12 : month % 12);
        if (this.month == 1)  {
            monthName = "Jan";
            this.day = (day % 31 == 0 ? 31 : day % 31);
        }
        if (this.month == 2)  {
            monthName = "Feb";
            this.day = (this.year % 4 == 0 ? (day % 29 == 0 ? 29 : day % 29) : (day % 28 == 0 ? 28 : day % 28));
        }
        if (this.month == 3)  {
            monthName = "Mar";
            this.day = (day % 31 == 0 ? 31 : day % 31);
        }
        if (this.month == 4)  {
            monthName = "Apr";
            this.day = (day % 30 == 0 ? 30 : day % 30);
        }
        if (this.month == 5)  {
            monthName = "May";
            this.day = (day % 31 == 0 ? 31 : day % 31);
        }
        if (this.month == 6)  {
            monthName = "June";
            this.day = (day % 30 == 0 ? 30: day % 30);
        }
        if (this.month == 7)  {
            monthName = "July";
            this.day = (day % 31 == 0 ? 31: day % 31);
        }
        if (this.month == 8)  {
            monthName = "Aug";
            this.day = (day % 31 == 0 ? 31: day % 31);
        }
        if (this.month == 9)  {
            monthName = "Sep";
            this.day = (day % 30 == 0 ? 30: day % 30);
        }
        if (this.month == 10) {
            monthName = "Oct";
            this.day = (day % 31 == 0 ? 31: day % 31);
        }
        if (this.month == 11) {
            monthName = "Nov";
            this.day = (day % 30 == 0 ? 30: day % 30);
        }
        if (this.month == 12) {
            monthName = "Dis";
            this.day = (day % 31 == 0 ? 31: day % 31);
        }
    }

    /** Switch format from 24-hour system to 12-hour system */
    public void switch24To12Format() {
        is24Hour = false;
        hour %= 12;
    }

    /** Switch format from 12-hour system to 124-hour system */
    public void switch12To24Format() {
        is24Hour = true;

        if (isPM) hour += 12;
        if (hour >= 24) hour %= 24;
    }

    /** Set the time wheather it is AM or PM */
    public void setPM(boolean isPM) {
        this.isPM = isPM;
    }

    // toString
    /** Return the class as String */
    public String toString() {
        return String.format("{ TimeHandling: { Date: { Year: %04d, Month: %s [ %02d ], Day: %02d }, Time: { Hour: %02d, Minute: %02d, Second: %02d }, Is24Hour: %s, IsPM: %s} }",
        year,
        monthName,
        month,
        day,
        hour,
        minute,
        second,
        (is24Hour ? "True" : "False"),
        (isPM ? "True" : "False"));
    }
}
