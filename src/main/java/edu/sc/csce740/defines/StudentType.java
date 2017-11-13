package edu.sc.csce740.defines;

/**
 * Denotes the group of students a fee applies to.
 * <li>{@link #FULL_TIME}</li>
 * <li>{@link #PART_TIME}</li>
 * <li>{@link #ALL}</li>
 */
public enum StudentType {
    /**
     * This fee applies to full-time students.
     */
    FULL_TIME,

    /**
     * This fee applies to part-time students.
     */
    PART_TIME,

    /**
     * This fee applies to all students.
     */
    ALL;

    /**
     * Convert a StudentType to a string.
     * @return A string representation of a StudentType.
     */
    @Override
    public String toString() {
        switch(this) {
            case FULL_TIME:
                return "FULL_TIME";
            case PART_TIME:
                return "PART_TIME";
            case ALL:
                return "ALL";
            default:
                return super.toString();
        }
    }
}
