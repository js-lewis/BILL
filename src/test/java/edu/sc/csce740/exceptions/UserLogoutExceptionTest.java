package edu.sc.csce740.exceptions;

import edu.sc.csce740.BILL;
import org.junit.Assert;
import org.junit.Test;

public class UserLogoutExceptionTest {
    @Test
    public void testRecordDataLoadException(){
        try {
            BILL testBill = new BILL();
            testBill.logOut();
            Assert.assertTrue(false);
        }
        catch(UserLogoutException e)
        {
            Assert.assertEquals("UserLogoutException{}", e.toString());
        }
    }

}
