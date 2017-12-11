package edu.sc.csce740.exceptions;

import edu.sc.csce740.helpers.BillHelper;
import org.junit.Assert;
import org.junit.Test;

public class BillRetrievalExceptionTest {
    @Test
    public void testBillRetrievalException(){
        try {
            BillHelper billHelperTest = new BillHelper();
            billHelperTest.retrieveBill(null, null, null);
            Assert.assertTrue(false);
        }
        catch(BillRetrievalException e)
        {
            Assert.assertEquals("BillRetrievalException{}", e.toString());
        }
    }

}
