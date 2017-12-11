package edu.sc.csce740.exceptions;

import edu.sc.csce740.BILL;
import org.junit.Assert;
import org.junit.Test;

public class UserRetrievalExceptionTest {
    @Test
    public void testRecordDataLoadException(){
        try {
            BILL testBill = new BILL();
            testBill.getRecord("someinvaliduser");
            Assert.assertTrue(false);
        }
        catch(UserRetrievalException e)
        {
            Assert.assertEquals("UserRetrievalException{}", e.toString());
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }
}
