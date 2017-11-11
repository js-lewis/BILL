package edu.sc.csce740.helpers;

import edu.sc.csce740.model.User;

//Files for testing
import edu.sc.csce740.defines.Constants;

//JUnit includes
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

//To catch IOExceptions
import java.io.IOException;

//To support the User List
import java.util.List;

public class UserHelperTest {
    private UserHelper userHelper;

    /**
     * Method to test the ReadUsers method happy path. This reads in the provided users.txt file and checks to ensure
     *  that 5 users were loaded.
     * @throws Exception
     */
    @BeforeClass
    public static void classSetup() {

    }

    /**
     * Method to test the ReadUsers method happy path. This reads in the provided users.txt file and checks to ensure
     *  that 5 users were loaded.
     * @throws Exception
     */
    @Test
    public void testReadUsers()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.USERS_BASE_FILE);
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
        Assert.assertEquals( users.size(), Constants.BASE_NUMBER_OF_USERS );
    }

    /**
     * Method to test the ReadUsers method happy path. This reads in the provided users.txt file and checks to ensure
     *  that 5 users were loaded.
     * @throws Exception
     */
    @Test(expected = IOException.class)
    public void testReadUsersWithExc()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.NO_FILE);
        userHelper.readUsers();
    }

    @After
    public void finish() {

    }
}
