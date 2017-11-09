package edu.sc.csce740.model;

public class Date {
    private int month;
    private int day;
    private int year;

    public Date() {
    }

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        //TODO: Add range checks
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        //TODO: Add range checks
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        //TODO: Add range checks
        this.year = year;
    }

    @Override
    public String toString() {
        return "month: " + getMonth() + "\n" +
               "day: " + getDay() + "\n" +
               "year: " + getYear() + "\n";
    }
}
