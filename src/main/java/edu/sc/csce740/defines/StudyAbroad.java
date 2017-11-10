package edu.sc.csce740.defines;

/**
 * Possible Study Abroad statuses
 * <li>{@link #REGULAR}</li>
 * <li>{@link #COHORT}</li>
 * <li>{@link #NONE}</li>
 */
public enum StudyAbroad {
    /**
     * A Regular Study Abroad session.
     */
    REGULAR,

    /**
     * A Study Abroad Cohort.
     */
    COHORT,

    /**
     * No Study Abroad status.
     */
    NONE;

    /**
     * Convert a Study Abroad status to a string.
     * @return A string representation of a Study Abroad status.
     */
    @Override
    public String toString() {
        switch (this) {
            case REGULAR:
                return "REGULAR";
            case COHORT:
                return "COHORT";
            case NONE:
                return "NONE";
            default:
                return super.toString();
        }
    }
}
