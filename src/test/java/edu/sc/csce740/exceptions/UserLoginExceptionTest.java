package edu.sc.csce740.exceptions;

import edu.sc.csce740.BILL;
import edu.sc.csce740.defines.Constants;
import org.junit.Assert;
import org.junit.Test;

public class UserLoginExceptionTest {
    @Test
    public void testUserLoginException(){
        try {
            BILL billInst = new BILL();

            //load the data
            billInst.loadUsers(Constants.USERS_BASE_FILE);
            billInst.loadRecords(Constants.RECORDS_BASE_FILE);

            billInst.logIn( Constants.ADMIN_GRAD);
            billInst.logIn("somebogususer");
            Assert.assertTrue(false);
        }
        catch(UserLoginException e)
        {
            Assert.assertEquals("UserLoginException{}", e.toString());
        }
        catch(Exception e){
            Assert.assertTrue(false);
        }
    }

}
