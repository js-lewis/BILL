package edu.sc.csce740.model;

/**
 * This class represents a Date in the BILL system.
 */
public class Date {
    /**
     * The month as an integer between 1 and 12.
     */
    private int month;

    /**
     * The day as an integer between 1 and 31.
     */
    private int day;

    /**
     * The year as an integer between 0 and 9999.
     */
    private int year;

    /**
     * The default constructor for a Date.
     */
    public Date() {
        this.month = 1;
        this.day = 1;
        this.year = 1900;
    }

    /**
     * The constructor for a Date.
     * @param month the month of the Date.
     * @param day   the day of the Date.
     * @param year  the year of the Date.
     */
    public Date(int month, int day, int year) {
        this.month = 1;
        this.day = 1;
        this.year = 1900;

        setMonth(month);
        setDay(day);
        setYear(year);
    }

    /**
     * Gets the Date's month.
     * @return the month as an integer between 1 and 12.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set the Date's month. If the month is not between 1 and 12, it will not be changed.
     * @param month the new month for this Date.
     */
    public void setMonth(int month) {
        if(month >= 1 && month <= 12) {
            this.month = month;
        }
    }

    /**
     * Gets the Date's day.
     * @return the day as an integer between 1 and 31.
     */
    public int getDay() {
        return day;
    }

    /**
     * Set the Date's day. If the day is not between 1 and 31, it will not be changed.
     * @param month the new month for this Date.
     */
    public void setDay(int day) {
        if(day >= 1 && day <= 31) {
            this.day = day;
        }
    }

    /**
     * Gets the Date's year.
     * @return the year as a 4 digit integer.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the Date's year. If the year is not between 0 and 9999, it will not be changed.
     * @param year the new year for this Date.
     */
    public void setYear(int year) {
        if(year >= 0 && year <= 9999) {
            this.year = year;
        }
    }

    /**
     * A String representation of a Date object.
     * @return a String with the Date data.
     */
    @Override
    public String toString() {
        return "month: " + getMonth() + "\n" +
               "day: " + getDay() + "\n" +
               "year: " + getYear() + "\n";
    }
}
