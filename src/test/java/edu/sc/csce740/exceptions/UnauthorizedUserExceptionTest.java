package edu.sc.csce740.exceptions;

import edu.sc.csce740.BILL;
import edu.sc.csce740.defines.Constants;
import org.junit.Assert;
import org.junit.Test;

public class UnauthorizedUserExceptionTest {
    @Test
    public void testRecordDataLoadException(){
        try {
            BILL testBill = new BILL();
            //load the data
            testBill.loadUsers(Constants.USERS_BASE_FILE);
            testBill.loadRecords(Constants.RECORDS_BASE_FILE);

            testBill.logIn( Constants.STUDENT_EAC_UNDER);

            testBill.getStudentIDs();
            Assert.assertTrue(false);
        }
        catch(UnauthorizedUserException e)
        {
            Assert.assertEquals("UnauthorizedUserException{}", e.toString());
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }

}
