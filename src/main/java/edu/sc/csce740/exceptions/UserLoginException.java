package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when a User login fails.
 */
public class UserLoginException extends Exception {
    /**
     * A constructor for a User Login Exception.
     */
    public UserLoginException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a User Login Exception.
     */
    @Override
    public String toString() {
        return "UserLoginException{}";
    }
}
