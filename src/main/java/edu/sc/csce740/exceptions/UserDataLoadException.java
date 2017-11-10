package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when Users are unable to be loaded.
 */
public class UserDataLoadException extends Exception {
    /**
     * A constructor for a User Data Load Exception.
     */
    public UserDataLoadException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a User Data Load Exception.
     */
    @Override
    public String toString() {
        return "UserDataLoadException{}";
    }
}
