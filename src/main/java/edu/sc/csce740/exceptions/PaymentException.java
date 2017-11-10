package edu.sc.csce740.exceptions;

/**
 * This exception is thrown when a payment is unabled to be made on a given Student's account.
 */
public class PaymentException extends Exception {
    /**
     * A constructor for a Payment Exception.
     */
    public PaymentException() {
    }

    /**
     * Convert the Exception to a string.
     * @return A string representation of a Payment Exception.
     */
    @Override
    public String toString() {
        return "PaymentException{}";
    }
}
