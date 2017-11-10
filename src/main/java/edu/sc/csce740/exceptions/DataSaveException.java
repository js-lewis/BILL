package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when the data was unable to be saved.
 */
public class DataSaveException extends Exception {
    /**
     * A constructor for a Data Save Exception.
     */
    public DataSaveException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Data Save Exception.
     */
    @Override
    public String toString() {
        return "DataSaveException{}";
    }
}
