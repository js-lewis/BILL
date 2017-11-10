package edu.sc.csce740.defines;

/**
 * User system Roles
 * <li>{@link #STUDENT}</li>
 * <li>{@link #ADMIN}</li>
 * <li>{@link #GRADUATE_PROGRAM_COORDINATOR}</li>
 */
public enum Role {
    /**
     * This user is a Student.
     */
    STUDENT,

    /**
     * This user is an Administrator.
     */
    ADMIN,

    /**
     * This user is a Graduate Program Co-ordinator.
     */
    GRADUATE_PROGRAM_COORDINATOR;

    /**
     * Convert a user's Role to a string.
     * @return A string representation of the Role of a User.
     */
    @Override
    public String toString() {
        switch (this) {
            case STUDENT:
                return "STUDENT";
            case ADMIN:
                return "ADMIN";
            case GRADUATE_PROGRAM_COORDINATOR:
                return "GRADUATE_PROGRAM_COORDINATOR";
            default:
                return super.toString();
        }
    }
}
