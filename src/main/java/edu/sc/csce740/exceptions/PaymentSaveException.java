package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when a payment made on a given Student's account is unable to be saved.
 */
public class PaymentSaveException extends Exception {
    /**
     * A constructor for a Payment Save Exception.
     */
    public PaymentSaveException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Payment Save Exception.
     */
    @Override
    public String toString() {
        return "PaymentSaveException{}";
    }
}
