/**
 * 
 */
package datetime;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import vehicle.Vehicle;

/**
 * @author sankalpa
 *
 */

public class DateTime implements Serializable {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private int seconds;

    public DateTime() {
        Date();
        Time();

    }

    public DateTime(int day, int month, int year, int hour, int minutes, int seconds) {
        super();
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;

        // Check if the entered Date&Time is valid
        try {
            boolean valid = this.isValidDateTime();
            if (!valid) {
                throw new IllegalArgumentException("Date&Time Entered is invalid. Format used is dd/mm/yyyy hh:mm:ss");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return string version of this dateTime in dd/mm/yyyy HH:MM:SS format
    public String getDateTime() {
        return this.getDate() + " " + this.getTime();
    }

    // Return double representing the dateTime in terms of seconds
    public double getDateTimeInSeconds() {
        return this.getDateInSeconds() + this.getTimeInSeconds();
    }

    // Checks if the dateTime entered is a proper and valid dateTime in dd/mm/yyyy HH:MM:SS format
    private boolean isValidDateTime() {
        // Check if time component is valid
        boolean validDate = this.isValidTime();
        // Check if date component is valid
        boolean validTime = this.isValidDate();

        if (validTime && validDate) {
            return true;
        }

        return false;
    }

    // Return double representing the difference of two dateTimes in terms of hours
    public double getDateTimeDiffHours(DateTime otherTime) {
        double diffInSeconds = this.getDateTimeInSeconds() - otherTime.getDateTimeInSeconds();
        return (diffInSeconds / 3600);
    }

    // Return date as String format
    @Override
    public String toString() {
        return "DateTime{"
                + "date=" + getDate()
                + ", time=" + getTime()
                + '}';
    }

    // Hashmap used to store the number of days in each month Later the days for each month are modified according to leap years
    private Map<Integer, Integer> maxDaysInMonth = new HashMap<Integer, Integer>() {
        {
            put(1, 31);
            put(2, 28);
            put(3, 31);
            put(4, 30);
            put(5, 31);
            put(6, 30);
            put(7, 31);
            put(8, 31);
            put(9, 30);
            put(10, 31);
            put(11, 30);
            put(12, 31);
        }
    };

    
    public void Date() {
        LocalDateTime currentTime = LocalDateTime.now();
        this.day = currentTime.getDayOfMonth();
        this.month = currentTime.getMonthValue();
        this.year = currentTime.getYear();
    }

    public void Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;

        try {
            boolean valid = this.isValidDate();
            if (!valid) {
                throw new IllegalArgumentException("Invalid..!! Please refer format used is dd/mm/yyyy");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // Return string version of this date in dd/mm/yyyy format
    public String getDate() {
        String formattedDay = String.format("%02d", this.day);
        String formattedMonth = String.format("%02d", this.month);

        return formattedDay + "/" + formattedMonth + "/" + this.year;
    }

    // Checks if this date is the dd/mm/yyyy format and performs a preliminary range check on day, month, and year
    private boolean validateDate() {
        // Define the validation pattern in regex
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean flag = false;
        // Check if the date matches the pattern
        if (this.getDate().matches(pattern)) {
            flag = true;
        }
        return flag;
    }

    // Return true of the year is a leap year.
    private boolean isLeapYear(int year) {
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    // Checks if the date entered is a proper and valid date in dd/mm/yyyy format
    public boolean isValidDate() {
        // Check whether the basic date validation constraints are followed
        boolean valid = validateDate();
        if (!valid) {
            return false;
        }

        // Check if the Date is in a leap year and adjust the days per each month accordingly.
        boolean isLeap = this.isLeapYear(this.year);
        if (isLeap) {
            this.maxDaysInMonth.put(2, 29);
        } else {
            this.maxDaysInMonth.put(2, 28);
        }

        // Check if the value given for the month is valid
        if (this.month <= 0 || this.month > 12) {
            return false;
        }

        // Check if the value given for the day is valid
        int maxDay = this.maxDaysInMonth.get(this.month);
        if (this.day <= 0 || this.day > maxDay) {
            return false;
        }

        return true;

    }

    // Return double representing the date in terms of seconds
    public double getDateInSeconds() {
        double seconds;
        seconds = (this.year * 31556952) + (this.month * 2628000) + (this.day * 86400);
        return seconds;
    }

    // Return string version of this date
    public String toStringDate() {
        return "Date{"
                + "day=" + day
                + ", month=" + month
                + ", year=" + year
                + ", maxDaysInMonth=" + maxDaysInMonth
                + '}';
    }

    public void Time() {
        LocalDateTime currentTime = LocalDateTime.now();
        this.hour = currentTime.getHour();
        this.minutes = currentTime.getMinute();
        this.seconds = currentTime.getSecond();
    }

    public void Time(int hour, int minutes, int seconds) {
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;

        try {
            boolean valid = this.isValidTime();
            if (!valid) {
                throw new IllegalArgumentException("Time Entered is invalid. Format used is HH:MM:SS");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    // Return string version of this time in HH:MM:SS format
    public String getTime() {
        String formattedHour = String.format("%02d", this.hour);
        String formattedMinutes = String.format("%02d", this.minutes);
        String formattedSeconds = String.format("%02d", this.seconds);

        return formattedHour + ":" + formattedMinutes + ":" + formattedSeconds;
    }

    // Checks if this time is the HH:MM:SS format and performs a preliminary range check on hour, minutes, and seconds
    private boolean validateTime() {
        // Define the validation pattern in regex
        String pattern = "([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])";
        boolean flag = false;
        // Check if the time matches the pattern
        if (this.getTime().matches(pattern)) {
            flag = true;
        }
        return flag;
    }

    // Checks if the time entered is a proper and valid time in HH:MM:SS format
    public boolean isValidTime() {
        boolean valid = this.validateTime();
        if (!valid) {
            return false;
        }

        return true;
    }

    // Return double representing the time in terms of seconds
    public double getTimeInSeconds() {
        double seconds;
        seconds = (this.hour * 3600) + (this.minutes * 60) + this.seconds;
        return seconds;
    }

    // Return string version of this time
    public String toStringTime() {
        return "Time{"
                + "hour=" + hour
                + ", minutes=" + minutes
                + ", seconds=" + seconds
                + '}';
    }

}
