package edu.sc.csce740.exceptions;

import edu.sc.csce740.BILL;
import org.junit.Assert;
import org.junit.Test;

public class RecordDataLoadExceptionTest {
    @Test
    public void testRecordDataLoadException(){
        try {
            BILL testBill = new BILL();
            testBill.loadRecords("someinvalidfile");
            Assert.assertTrue(false);
        }
        catch(RecordDataLoadException e)
        {
            Assert.assertEquals("RecordDataLoadException{}", e.toString());
        }
    }
}
