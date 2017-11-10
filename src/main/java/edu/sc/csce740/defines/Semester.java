package edu.sc.csce740.defines;

/**
 * Possible Semesters
 * <li>{@link #FALL}</li>
 * <li>{@link #SPRING}</li>
 * <li>{@link #SUMMER}</li>
 */
public enum Semester {
    /**
     * The Fall Semester
     */
    FALL,

    /**
     * The Spring Semester
     */
    SPRING,

    /**
     * The Summer Semester
     */
    SUMMER;

    /**
     * Convert a Semester to a string.
     * @return A string representation of a Semester.
     */
    @Override
    public String toString() {
        switch(this) {
            case FALL:
                return "FALL";
            case SPRING:
                return "SPRING";
            case SUMMER:
                return "SUMMER";
            default:
                return super.toString();
        }
    }
}
