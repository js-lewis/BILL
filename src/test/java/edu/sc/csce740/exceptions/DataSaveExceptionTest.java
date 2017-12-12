package edu.sc.csce740.exceptions;

import org.junit.Assert;
import org.junit.Test;

public class DataSaveExceptionTest {
    @Test
    public void testDataSaveException() {
        try {
            throw new DataSaveException();
        } catch (Exception e) {
            Assert.assertEquals("DataSaveException{}", e.toString());
        }
    }
}
