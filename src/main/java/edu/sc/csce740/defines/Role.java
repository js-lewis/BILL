package edu.sc.csce740.defines;

public enum Role {
    STUDENT,
    GRADUATE_PROGRAM_COORDINATOR;

    @Override
    public String toString() {
        switch (this) {
            case STUDENT:
                return "STUDENT";
            case GRADUATE_PROGRAM_COORDINATOR:
                return "GRADUATE_PROGRAM_COORDINATOR";
            default:
                return super.toString();
        }
    }
}
