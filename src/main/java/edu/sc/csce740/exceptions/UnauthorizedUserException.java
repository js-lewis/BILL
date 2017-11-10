package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when an action is unable to be performed due to User permissions.
 */
public class UnauthorizedUserException extends Exception {
    /**
     * A constructor for a Unauthorized User Exception.
     */
    public UnauthorizedUserException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Unauthorized User Exception.
     */
    @Override
    public String toString() {
        return "UnauthorizedUserException{}";
    }
}
