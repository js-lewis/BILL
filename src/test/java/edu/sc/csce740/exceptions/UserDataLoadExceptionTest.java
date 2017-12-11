package edu.sc.csce740.exceptions;

import edu.sc.csce740.BILL;
import org.junit.Assert;
import org.junit.Test;

public class UserDataLoadExceptionTest {
    @Test
    public void testUserDataLoadException(){
        try {
            BILL testBill = new BILL();
            testBill.loadUsers("someinvalidfile");
            Assert.assertTrue(false);
        }
        catch(UserDataLoadException e)
        {
            Assert.assertEquals("UserDataLoadException{}", e.toString());
        }
    }
}
