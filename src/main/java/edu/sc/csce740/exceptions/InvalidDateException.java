package edu.sc.csce740.exceptions;

public class InvalidDateException extends Exception {
    /**
     * A constructor for a Invalid Date Exception.
     */
    public InvalidDateException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Invalid Date Exception.
     */
    @Override
    public String toString() {
        return "InvalidDateException{}";
    }
}
