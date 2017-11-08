package edu.sc.csce740.defines;

public enum InternationalStatus {
    SHORT_TERM,
    SPONSORED,
    NONE;

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
