package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when Student Records are unable to be loaded.
 */
public class RecordDataLoadException extends Exception {
    /**
     * A constructor for a Record Data Load Exception.
     */
    public RecordDataLoadException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Record Data Load Exception.
     */
    @Override
    public String toString() {
        return "RecordDataLoadException{}";
    }
}
