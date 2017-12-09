package edu.sc.csce740.model;

import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.Role;
import org.junit.Assert;
import org.junit.Test;

public class UserModelTest {

    @Test
    public void testUserToStringGTZero(){
        User testUser = new User();
        Assert.assertTrue(testUser.toString().length() > 0);
    }

    @Test
    public void testUserSetGetRole(){
        User testUser = new User();
        testUser.setRole(Role.STUDENT);
        Assert.assertEquals(Role.STUDENT, testUser.getRole());
    }

    @Test
    public void testUserSetGetCollege(){
        User testUser = new User();
        testUser.setCollege(College.GRADUATE_SCHOOL);
        Assert.assertEquals(College.GRADUATE_SCHOOL, testUser.getCollege());
    }
}
