package edu.sc.csce740.defines;

/**
 * Types of Scholarships
 * <li>{@link #WOODROW}</li>
 * <li>{@link #DEPARTMENTAL}</li>
 * <li>{@link #GENERAL}</li>
 * <li>{@link #ATHLETIC}</li>
 * <li>{@link #SIMS}</li>
 * <li>{@link #NONE}</li>
 */
public enum Scholarship {
    /**
     * The Woodrow Scholars Award
     */
    WOODROW,

    /**
     * A Departmental Scholarship
     */
    DEPARTMENTAL,

    /**
     * A General Scholarship
     */
    GENERAL,

    /**
     * An Athletic Scholarhip
     */
    ATHLETIC,

    /**
     * Sims Scholars Award
     */
    SIMS,

    /**
     * No Scholarship
     */
    NONE;

    /**
     * Convert a Scholarship to a string.
     * @return A string representation of a Scholarship.
     */
    @Override
    public String toString() {
        switch (this) {
            case WOODROW:
                return "WOODROW";
            case DEPARTMENTAL:
                return "DEPARTMENTAL";
            case GENERAL:
                return "GENERAL";
            case ATHLETIC:
                return "ATHLETIC";
            case SIMS:
                return "SIMS";
            case NONE:
                return "NONE";
            default:
                return super.toString();
        }
    }
}
