package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when a bill is unabled to be generated for a given Student.
 */
public class BillGenerationException extends Exception {
    /**
     * A constructor for a Bill Generation Exception.
     */
    public BillGenerationException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Bill Generation Exception.
     */
    @Override
    public String toString() {
        return "BillGenerationException{}";
    }
}
