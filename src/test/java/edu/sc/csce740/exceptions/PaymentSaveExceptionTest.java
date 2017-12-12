package edu.sc.csce740.exceptions;

import org.junit.Assert;
import org.junit.Test;

public class PaymentSaveExceptionTest {
    @Test
    public void testPaymentSaveException() {
        try {
            throw new PaymentSaveException();
        } catch (Exception e) {
            Assert.assertEquals("PaymentSaveException{}", e.toString());
        }
    }
}
