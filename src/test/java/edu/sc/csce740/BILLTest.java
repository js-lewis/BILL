package edu.sc.csce740;

// Exception imports
import edu.sc.csce740.exceptions.RecordDataLoadException;
import edu.sc.csce740.exceptions.UserDataLoadException;

// Helper imports
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

import java.util.List;

public class BILLTest {
    @Before
    public void setup() {

    }

    @Test
    public void loadUsersTest()
            throws UserDataLoadException {
        BILL billInst = new BILL();

        billInst.loadUsers("resources/users.json");

        UserHelper userHelper = billInst.getUserHelper();
        List<User> users = userHelper.getUsers();
        Assert.assertEquals( users.size(), 5 );
    }

    @Test
    public void loadRecordsTest()
            throws RecordDataLoadException {
        BILL billInst = new BILL();

        billInst.loadRecords("resources/students.json");

        StudentHelper studentHelper = billInst.getStudentHelper();
        List<StudentRecord> studentRecords = studentHelper.getStudents();
        Assert.assertEquals( studentRecords.size(), 2 );
    }

    @After
    public void cleanup() {

    }
}
