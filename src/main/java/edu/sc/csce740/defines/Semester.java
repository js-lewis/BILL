package edu.sc.csce740.defines;

public enum Semester {
    FALL,
    SPRING,
    SUMMER;

    @Override
    public String toString() {
        switch(this) {
            case FALL:
                return "FALL";
            case SPRING:
                return "SPRING";
            case SUMMER:
                return "SUMMER";
            default:
                return super.toString();
        }
    }
}
