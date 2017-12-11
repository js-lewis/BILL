package edu.sc.csce740.exceptions;

import edu.sc.csce740.BILL;
import org.junit.Assert;
import org.junit.Test;

public class UnknownUserExceptionTest {
    @Test
    public void testUknownUserException(){
        try {
            BILL testBill = new BILL();
            testBill.logIn("someinvaliduser");
            Assert.assertTrue(false);
        }
        catch(UnknownUserException e)
        {
            Assert.assertEquals("UnknownUserException{}", e.toString());
        }
        catch (Exception e)
        {
            Assert.assertTrue(false);
        }
    }
}
