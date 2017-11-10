package edu.sc.csce740.defines;

/**
 * Student Class Status choices
 * <li>{@link #FRESHMAN}</li>
 * <li>{@link #SOPHOMORE}</li>
 * <li>{@link #JUNIOR}</li>
 * <li>{@link #SENIOR}</li>
 * <li>{@link #MASTERS}</li>
 * <li>{@link #PHD}</li>
 * <li>{@link #GRADUATED}</li>
 */

public enum ClassStatus {
    /**
     * Freshman: Students typically in their first year with less than 30 hours.
     */
    FRESHMAN,

    /**
     * Sophomore: Students typically in their second year with more than 30 but less than 60 hours.
     */
    SOPHOMORE,

    /**
     * Junior: Students typically in their third year with more than 60 but less than 90 hours.
     */
    JUNIOR,

    /**
     * Senior: Students typically in their forth year with more than 90 hours but who haven't graduated.
     */
    SENIOR,

    /**
     * Masters: Students working on a graduate degree less than PhD.
     */
    MASTERS,

    /**
     * PhD: Students working on a doctorate degree.
     */
    PHD,

    /**
     * Graduated: Students who have graduated.
     */
    GRADUATED;

    /**
     * Convert the Class Status to a string.
     * @return A string representation of a Class Status.
     */
    @Override
    public String toString() {
        switch(this) {
            case FRESHMAN:
                return "FRESHMAN";
            case SOPHOMORE:
                return "SOPHOMORE";
            case JUNIOR:
                return "JUNIOR";
            case SENIOR:
                return "SENIOR";
            case MASTERS:
                return "MASTERS";
            case PHD:
                return "PHD";
            case GRADUATED:
                return "GRADUATED";
            default:
                return super.toString();
        }
    }
}