package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when a specified User cannot be found.
 */
public class UnknownUserException extends Exception {
    /**
     * A constructor for a Unknown User Exception.
     */
    public UnknownUserException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Unknown User Exception.
     */
    @Override
    public String toString() {
        return "UnknownUserException{}";
    }
}
