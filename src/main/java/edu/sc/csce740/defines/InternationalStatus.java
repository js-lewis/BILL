package edu.sc.csce740.defines;

/**
 * Internation Student Status choices
 * <li>{@link #SHORT_TERM}</li>
 * <li>{@link #SPONSORED}</li>
 * <li>{@link #NONE}</li>
 */
public enum InternationalStatus {
    /**
     * Short term students
     */
    SHORT_TERM,

    /**
     * Sponsored students
     */
    SPONSORED,

    /**
     * No special status
     */
    NONE;

    /**
     * Convert an International Status to a string.
     * @return A string representation of an International Status.
     */
    @Override
    public String toString() {
        switch(this) {
            case SHORT_TERM:
                return "SHORT_TERM";
            case SPONSORED:
                return "SPONSORED";
            case NONE:
                return "NONE";
            default:
                return super.toString();
        }
    }
}
