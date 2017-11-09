package edu.sc.csce740.model;

import edu.sc.csce740.defines.Semester;

public class Term {
    private Semester semester;
    private int year;

    public Term() {
    }

    public Term(Semester semester, int year) {
        this.semester = semester;
        this.year = year;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "semester: " + getSemester() + "\n" +
                "year: " + getYear() + "\n";
    }
}
