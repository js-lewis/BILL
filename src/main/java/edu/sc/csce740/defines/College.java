package edu.sc.csce740.defines;

/**
 * College choices
 * <li>{@link #ARTS_AND_SCIENCES}</li>
 * <li>{@link #ENGINEERING_AND_COMPUTING}</li>
 * <li>{@link #GRADUATE_SCHOOL}</li>
 */
public enum College {
    /**
     * College of Arts and Sciences
     */
    ARTS_AND_SCIENCES,

    /**
     * College of Engineering and Computing
     */
    ENGINEERING_AND_COMPUTING,

    /**
     * The Graduate School
     */
    GRADUATE_SCHOOL;

    /**
     * Convert a College to a string.
     * @return A string representation of a College.
     */
    @Override
    public String toString() {
        switch(this) {
            case ARTS_AND_SCIENCES:
                return "ARTS_AND_SCIENCES";
            case ENGINEERING_AND_COMPUTING:
                return "ENGINEERING_AND_COMPUTING";
            case GRADUATE_SCHOOL:
                return "GRADUATE_SCHOOL";
            default:
                return super.toString();
        }
    }
}
