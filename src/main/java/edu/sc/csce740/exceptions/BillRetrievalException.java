package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when a bill is unabled to be retrieved for a given Student.
 */
public class BillRetrievalException extends Exception {
    /**
     * A constructor for a Bill Generation Exception.
     */
    public BillRetrievalException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Bill Retvieval Exception.
     */
    @Override
    public String toString() {
        return "BillRetrievalException{}";
    }
}
