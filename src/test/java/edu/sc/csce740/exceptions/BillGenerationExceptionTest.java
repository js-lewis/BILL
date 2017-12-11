package edu.sc.csce740.exceptions;

import edu.sc.csce740.helpers.BillHelper;
import org.junit.Assert;
import org.junit.Test;

public class BillGenerationExceptionTest {
    @Test
    public void testBillGenerationException(){
        try {
            BillHelper billHelperTest = new BillHelper();
            billHelperTest.generateBill(null);
            Assert.assertTrue(false);
        }
        catch(BillGenerationException e)
        {
            Assert.assertEquals("BillGenerationException{}", e.toString());
        }
    }
}
