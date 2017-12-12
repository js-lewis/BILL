package edu.sc.csce740.exceptions;

import org.junit.Assert;
import org.junit.Test;

public class InvalidDateExceptionTest {
    @Test
    public void testInvalidDateException() {
        try {
            throw new InvalidDateException();
        } catch (Exception e) {
            Assert.assertEquals("InvalidDateException{}", e.toString());
        }
    }
}
