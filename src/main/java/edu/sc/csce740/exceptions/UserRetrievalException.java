package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when a User is unable to be found.
 */
public class UserRetrievalException extends Exception {
    /**
     * A constructor for a User Retrieval Exception.
     */
    public UserRetrievalException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a User Retrieval Exception.
     */
    @Override
    public String toString() {
        return "UserRetrievalException{}";
    }
}
