package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when a User logout fails.
 */
public class UserLogoutException extends Exception {
    /**
     * A constructor for a User Logout Exception.
     */
    public UserLogoutException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a User Logout Exception.
     */
    @Override
    public String toString() {
        return "UserLogoutException{}";
    }
}
