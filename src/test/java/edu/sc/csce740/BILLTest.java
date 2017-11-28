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
//TODO: Comments and change test names to begin with test
    @Test
    public void testLoadUsers()
            throws Exception {
        BILL billInst = new BILL();

        billInst.loadUsers(Constants.USERS_BASE_FILE);

        UserHelper userHelper = billInst.getUserHelper();
        List<User> users = userHelper.getUsers();
        Assert.assertEquals( users.size(), Constants.BASE_NUMBER_OF_USERS );
    }

    @Test
    public void testLoadRecords()
            throws Exception {
        BILL billInst = new BILL();

        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        StudentHelper studentHelper = billInst.getStudentHelper();
        List<StudentRecord> studentRecords = studentHelper.getStudents();
        Assert.assertEquals( studentRecords.size(), Constants.BASE_NUMBER_OF_RECORDS );
    }

    @Test
    public void testLogIn()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( Constants.ADMIN_GRAD);

        Assert.assertEquals(billInst.getCurrentUser(), billInst.getUserHelper().findUser(Constants.ADMIN_GRAD));
    }

    @Test
    public void testLogOut()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( Constants.STUDENT_EAC_UNDER);

        Assert.assertEquals(billInst.getCurrentUser(), billInst.getUserHelper().findUser(Constants.STUDENT_EAC_UNDER));

        billInst.logOut();

        Assert.assertNull(billInst.getCurrentUser());
    }

    @Test
    public void testGetUser()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( Constants.ADMIN_EAC);

        Assert.assertEquals(billInst.getCurrentUser(), billInst.getUserHelper().findUser(Constants.ADMIN_EAC));
        Assert.assertEquals(billInst.getUser(), Constants.ADMIN_EAC);
    }

    @Test
    public void testGetStudentIDsAAS()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( Constants.ADMIN_AAS);

        Assert.assertEquals(billInst.getStudentIDs().size(), Constants.BASE_NUMBER_AAS);

        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.AAS_SOPHOMORE));
        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.AAS_JUNIOR));
        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.AAS_PHD));

        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.EAC_FRESHMAN));
        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.EAC_SENIOR));
        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.EAC_MASTERS));
        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.EAC_PHD));
    }

    @Test
    public void testGetStudentIDsEAC()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn( Constants.ADMIN_EAC);

        Assert.assertEquals(billInst.getStudentIDs().size(), Constants.BASE_NUMBER_EAC);

        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.EAC_FRESHMAN));
        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.EAC_SENIOR));
        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.EAC_MASTERS));
        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.EAC_PHD));

        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.AAS_SOPHOMORE));
        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.AAS_JUNIOR));
        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.AAS_PHD));
    }

    @Test
    public void testGetStudentIDsGrad()
            throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn(Constants.ADMIN_GRAD);

        Assert.assertEquals(billInst.getStudentIDs().size(), Constants.BASE_NUMBER_GRAD);

        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.EAC_MASTERS));
        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.EAC_PHD));
        Assert.assertTrue(billInst.getStudentIDs().contains(Constants.AAS_PHD));

        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.AAS_SOPHOMORE));
        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.AAS_JUNIOR));
        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.EAC_FRESHMAN));
        Assert.assertFalse(billInst.getStudentIDs().contains(Constants.EAC_SENIOR));
    }

    @Test
    public void testGetRecord()
            throws Exception {

        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn(Constants.ADMIN_AAS);

        Assert.assertEquals(billInst.getRecord(Constants.STUDENT_AAS_GRAD),
                billInst.getStudentHelper().findStudentRecord(Constants.STUDENT_AAS_GRAD));
    }

    @Test
    public void testApplyPayment()
            throws Exception {

        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_MOD_FILE);
        billInst.loadRecords(Constants.RECORDS_MOD_FILE);

        billInst.logIn(Constants.STUDENT_EAC_UNDER);
        billInst.applyPayment( Constants.STUDENT_EAC_UNDER, new BigDecimal(50.00), "Just a test.");
    }

    @Test
    public void printBill() throws Exception {
        BILL billInst = new BILL();

        //load the data
        billInst.loadUsers(Constants.USERS_BASE_FILE);
        billInst.loadRecords(Constants.RECORDS_BASE_FILE);

        billInst.logIn(Constants.ADMIN_AAS);
        //System.out.println(billInst.viewCharges(Constants.AAS_PHD, 1, 1, 2016, 1, 1, 2017));
        //System.out.println(billInst.generateBill(Constants.AAS_SOPHOMORE));   //Checked
        //System.out.println(billInst.generateBill(Constants.AAS_JUNIOR));      //Checked
        //System.out.println(billInst.generateBill(Constants.AAS_PHD));         //Checked

        //System.out.println();
        //System.out.println("--------------------------------------------------------------------------------------------");
        //System.out.println();

        billInst.logOut();
        billInst.logIn(Constants.ADMIN_EAC);
        //System.out.println(billInst.generateBill(Constants.EAC_FRESHMAN));    //Checked
        //System.out.println(billInst.generateBill(Constants.EAC_SENIOR));      //Checked
        //System.out.println(billInst.generateBill(Constants.EAC_MASTERS));     //Checked
        //System.out.println(billInst.generateBill(Constants.EAC_PHD));         //Checked
    }

    @After
    public void cleanup() {

    }
}
