package edu.sc.csce740.model;

import edu.sc.csce740.defines.Semester;
import edu.sc.csce740.exceptions.InvalidDateException;

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
        this();

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
     * Determine if this date falls between two other dates
     * @param start the beginning of the Date range
     * @param end   the end of the Date range
     * @return  true if this Date is between the parameters. False otherwise.
     */
    public boolean isBetween(Date start, Date end) {
        return this.isBefore(end) && this.isAfter(start);
    }

    /**
     * Determine if this date is before/earlier than a given date.
     * @param d some Date
     * @return true if this Date is earlier than the given Date.
     */
    public boolean isBefore(Date d) {
        if(this.year < d.year) {
            return true;
        }

        if(this.year == d.year && this.month < d.month) {
            return true;
        }

        if(this.year == d.year && this.month == d.month && this.day < d.day) {
            return true;
        }

        return false;
    }

    /**
     * Determine if this date is after/later than a given date.
     * @param d some Date
     * @return true if this Date is later than the given Date.
     */
    public boolean isAfter(Date d) {
        if(this.year > d.year) {
            return true;
        }

        if(this.year == d.year && this.month > d.month) {
            return true;
        }

        if(this.year == d.year && this.month == d.month && this.day > d.day) {
            return true;
        }

        return false;
    }

    /**
     * Gets the Semester that corresponds to a date.
     * @return a Semester that corresponds to the date.
     * @throws InvalidDateException
     */
    public Semester getSemeter() throws InvalidDateException {
        switch(this.month) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return Semester.SPRING;
            case 6:
            case 7:
                return Semester.SUMMER;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return Semester.FALL;
            default:
                throw new InvalidDateException();
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
