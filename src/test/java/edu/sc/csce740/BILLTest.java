package edu.sc.csce740;

//Files for testing
import edu.sc.csce740.defines.Constants;

// Helper imports
import edu.sc.csce740.exceptions.UserLoginException;
import edu.sc.csce740.helpers.UserHelper;
import edu.sc.csce740.helpers.StudentHelper;

// Model imports
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.User;

// JUnit imports
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BILLTest {
    @Before
    public void setup() {

    }

    @Test
    public void loadUsersTest()
            throws Exception {
        BILL billInst = new BILL();

        billInst.loadUsers(Constants.USERS_BASE_FILE);

        UserHelper userHelper = billInst.getUserHelper();
        List<User> users = userHelper.getUsers();
        Assert.assertEquals( users.size(), 5 );
    }

    @Test
    public void loadRecordsTest()
            throws Exception {
        BILL billInst = new BILL();

        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        StudentHelper studentHelper = billInst.getStudentHelper();
        List<StudentRecord> studentRecords = studentHelper.getStudents();
        Assert.assertEquals( studentRecords.size(), 2 );
    }

    @Test
    public void logInTest()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( "ggay");

        Assert.assertEquals(billInst.getCurrentUser(), billInst.getUserHelper().findUser("ggay"));
    }

    @Test
    public void logOutTest()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( "mmatthews");

        Assert.assertEquals(billInst.getCurrentUser(), billInst.getUserHelper().findUser("mmatthews"));

        billInst.logOut();

        Assert.assertEquals(billInst.getCurrentUser(), null);
    }

    @Test
    public void getUserTest()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( "rbob");

        Assert.assertEquals(billInst.getCurrentUser(), billInst.getUserHelper().findUser("rbob"));
        Assert.assertEquals(billInst.getUser(), "rbob");
    }

    @Test
    public void getStudentIDsTestUndergrad()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( "rbob");

        Assert.assertEquals(billInst.getCurrentUser(), billInst.getUserHelper().findUser("rbob"));
        Assert.assertEquals(billInst.getUser(), "rbob");
        Assert.assertEquals(billInst.getStudentIDs().size(), 1);
        Assert.assertEquals(billInst.getStudentIDs().get(0),"ggay");
    }

    @Test
    public void getStudentIDsTestGrad()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( "mmatthews");

        Assert.assertEquals(billInst.getCurrentUser(), billInst.getUserHelper().findUser("mmatthews"));
        Assert.assertEquals(billInst.getUser(), "mmatthews");
        Assert.assertEquals(billInst.getStudentIDs().size(), 1);
        Assert.assertEquals(billInst.getStudentIDs().get(0),"mhunt");
    }

    @Test
    public void getRecordTest()
            throws Exception {

        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( "jsmith");

        Assert.assertEquals(billInst.getRecord("mhunt"), billInst.getStudentHelper().findStudentRecord("mhunt"));
    }

    @Test
    public void applyPaymentTest()
            throws Exception {

        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( "jsmith");
        billInst.applyPayment( "mhunt", new BigDecimal(50.00), "Just a test.");
    }

    @After
    public void cleanup() {

    }
}
