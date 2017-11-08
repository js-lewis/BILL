package edu.sc.csce740.defines;

public enum ClassStatus {
    FRESHMAN,
    SOPHOMORE,
    JUNIOR,
    SENIOR,
    MASTERS,
    PHD,
    GRADUATED;

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