package edu.sc.csce740.defines;

public enum Scholarship {
    WOODROW,
    DEPARTMENTAL,
    GENERAL,
    ATHLETIC,
    SIMS,
    NONE;

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
