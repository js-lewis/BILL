package edu.sc.csce740.helpers;

import edu.sc.csce740.model.User;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
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
            throws Exception {
        userHelper = new UserHelper();
        userHelper.setFileName("resources/users.json");
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
        Assert.assertEquals( users.size(), 5 );
        //assert( users.size() == 5 );
    }

    /**
     * Method to test the ReadUsers method happy path. This reads in the provided users.txt file and checks to ensure
     *  that 5 users were loaded.
     * @throws Exception
     */
    @Test(expected = IOException.class)
    public void testReadUsersWithExc()
            throws Exception {
        userHelper = new UserHelper();
        userHelper.setFileName("resources/noFile");
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
    }

    @After
    public void finish() {

    }
}
