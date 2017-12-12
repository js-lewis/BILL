package edu.sc.csce740.exceptions;

import org.junit.Assert;
import org.junit.Test;

public class PaymentExceptionTest {
    @Test
    public void testPaymentException() {
        try {
            throw new PaymentException();
        } catch (Exception e) {
            Assert.assertEquals("PaymentException{}", e.toString());
        }
    }
}
