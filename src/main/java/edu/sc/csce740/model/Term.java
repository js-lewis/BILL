package edu.sc.csce740.model;

//Import required enumerications
import edu.sc.csce740.defines.Semester;

/**
 * This class represents a Term in the BILL system.
 */
public class Term {
    /**
     *  The Semester of the Term
     */
    private Semester semester;

    /**
     * The year as an integer between 0 and 9999.
     */
    private int year;

    /**
     * The default constructor for a Term.
     */
    public Term() {
        this.semester = Semester.FALL;
        this.year = 2017;
    }

    /**
     * The constuctor for a Term.
     * @param semester  the semester of the Term.
     * @param year      the year of the Term.
     */
    public Term(Semester semester, int year) {
        this.semester = semester;
        this.year = year;
    }

    /**
     * Gets the Semster of the Term.
     * @return the Semester of the Term.
     */
    public Semester getSemester() {
        return semester;
    }

    /**
     * Sets the Semester of the Term.
     * @param semester  the new Semester for the Term.
     */
    public void setSemester(Semester semester) {
        this.semester = semester;
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
     * A String representation of a Term object.
     * @return a String with the Term data.
     */
    @Override
    public String toString() {
        return "semester: " + getSemester() + "\n" +
                "year: " + getYear() + "\n";
    }
}
