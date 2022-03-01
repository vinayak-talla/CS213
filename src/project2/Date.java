package project2;

import java.util.StringTokenizer;
import java.util.Calendar;

/**
 * A class that represents a date in MM/DD/YYYY format
 *
 * @author Alvin Alex, Vinayak Talla
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int MAXMONTH = 31;
    public static final int MINMONTH = 30;
    public static final int FEBNOTLEAP = 28;
    public static final int FEBLEAP = 29;

    /**
     * Constructor for date class
     * Instantiates date object with year, month, and day taken from parameter
     * @param date the date that needs to be created
     */
    public Date(String date){
        StringTokenizer st = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(st.nextToken());
        this.day = Integer.parseInt(st.nextToken());
        this.year = Integer.parseInt(st.nextToken());
    }

    /**
     * Constructor for Date class
     * Instantiates date object with year, month, and day from current time
     */
    public Date(){
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH)+1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * Helper method to determine whether days in month is correct
     * @param max the max number of days in a specific month
     * @return boolean value that is whether days in month is correct
     */
    private boolean checkDaysInMonth(int max){
        boolean validDate = false;
        if(this.day > 0 && this.day <= max){
            validDate = true;
        }
        else{
            validDate = false;
        }
        return validDate;
    }

    /**
     * Method that determines if the date of birth is not today or a day in the future
     * @return boolean value that is whether the date of birth is valid
     */
    public boolean isValidDOB(){
        Date todayDate = new Date();
        if(compareTo(todayDate) == 0 || compareTo(todayDate) == 1){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Method that determines whether the calendar date is a real date
     * @return boolean value that is whether calendar date is valid
     */
    public boolean isValid(){
        boolean validCalendarDate = false;
        if(this.month < 13 && this.month > 0) {
            if(this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 8 || this.month == 10 || this.month == 12) {
                validCalendarDate = checkDaysInMonth(MAXMONTH);
            }
            if(this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11){
                validCalendarDate = checkDaysInMonth(MINMONTH);
            }
            if(this.month == 2){
                if(this.year % QUADRENNIAL == 0){
                    if(this.year % CENTENNIAL == 0){
                        if(this.year % QUATERCENTENNIAL == 0){
                            validCalendarDate = checkDaysInMonth(FEBLEAP);
                        }
                        else{
                            validCalendarDate = checkDaysInMonth(FEBNOTLEAP);
                        }
                    }
                    else{
                        validCalendarDate = checkDaysInMonth(FEBLEAP);
                    }
                }
                else{
                    validCalendarDate = checkDaysInMonth(FEBNOTLEAP);
                }
            }
        }
        return validCalendarDate;
    }

    /**
     * Method to compare Date objects to each other
     * @param date object that is being compared
     * @return int value of 1, 0, or -1
     */
    @Override
    public int compareTo(Date date){
        if( this.year > date.year) {
            return 1;
        }
        else if(this.year == date.year) {
            if(this.month == date.month) {
                if (this.day == date.day) {
                    return 0;
                }
                else if(this.day > date.day) {
                    return 1;
                }
                else if(this.day < date.day) {
                    return -1;
                }
            }
            else if(this.month > date.month) {
                return 1;
            }
            else if(this.month < date.month){
                return -1;
            }
        }
        return -1;
    }

    /**
     * Method to print out Date object
     * @return Date object formatted as a string
     */
    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

}