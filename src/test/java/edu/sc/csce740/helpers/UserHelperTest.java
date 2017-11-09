package edu.sc.csce740.helpers;

import edu.sc.csce740.model.User;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

public class UserHelperTest {
    private UserHelper userHelper;

    @Before
    public void setup() {
        userHelper = new UserHelper();
    }

    @Test
    public void testReadUsers()
            throws Exception {
        userHelper.setFileName("resources/users.json");
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
        Assert.assertEquals( users.size(), 5 );
        //assert( users.size() == 5 );
    }

    @Test(expected = IOException.class)
    public void testReadUsersWithExc()
            throws Exception {
        userHelper.setFileName("resources/noFile");
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
    }

    @After
    public void finish() {

    }
}
