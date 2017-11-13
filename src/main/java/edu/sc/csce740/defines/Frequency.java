package edu.sc.csce740.defines;

/**
 * Is the fee base off of:
 * <li>{@link #HOUR}</li>
 * <li>{@link #SEMESTER}</li>
 * <li>{@link #ONE_TIME}</li>
 */
public enum Frequency {
    /**
     * This fee is based on the number of credit hours.
     */
    HOUR,

    /**
     * This fee is charged each semester.
     */
    SEMESTER,

    /**
     * This is a one time fee.
     */
    ONE_TIME;

    /**
     * Convert a fee Frequency to a string.
     * @return A string representation of a fee Frequency.
     */
    @Override
    public String toString() {
        switch(this) {
            case HOUR:
                return "HOUR";
            case SEMESTER:
                return "SEMESTER";
            case ONE_TIME:
                return "ONE_TIME";
            default:
                return super.toString();
        }
    }
}
