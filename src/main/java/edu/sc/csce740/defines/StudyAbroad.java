package edu.sc.csce740.defines;

public enum StudyAbroad {
    REGULAR,
    COHORT,
    NONE;

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
