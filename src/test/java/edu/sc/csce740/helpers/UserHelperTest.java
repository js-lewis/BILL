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
     * Method to test the readUsers method happy path. This reads in the provided users.txt file and checks to ensure
     *  that 5 users were loaded.
     * @throws Exception
     */
    @BeforeClass
    public static void classSetup() {

    }

    //Tests for readUsers function.

    /**
     * Method to test the readUsers method happy path. This reads in the provided file and checks to ensure
     * that 5 users were loaded.
     * @throws IOException if there's error reading from the file.
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
     * Method to test the readUsers method with an empty file. This reads in the provided empty file and checks to
     * ensure that 0 users were loaded, but that an empty list is available.
     * @throws IOException if there's error reading from the file.
     */
    @Test
    public void testReadUsersEmptyFile()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.EMPTY_FILE);
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
        Assert.assertEquals( users.size(), 0 );
    }

    /**
     * Method to test the readUsers method behavior when the file does not exist. This reads from a non-existant file
     * and checks to ensure the IOException is thrown.
     * @throws IOException if there's error reading from the file.
     */
    @Test(expected = IOException.class)
    public void testReadUsersWithExc()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.NO_FILE);
        userHelper.readUsers();
    }

    //TODO: Add test for malformed JSON file.

    //Tests for addUser

    /**
     * Method to test the addUser's method behavior. This should just add a User to the User's List
     * @throws IOException if there's error reading from the file.
     */
    @Test
    public void testAddUserWhenNew()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.USERS_BASE_FILE);
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
        //Check the number of users.
        Assert.assertEquals( users.size(), Constants.BASE_NUMBER_OF_USERS );
        //Check to make sure the user isn't in there.
        Assert.assertNull( userHelper.findUser(Constants.NEW_USER_FIRST_NAME));
        User newUser = new User(Constants.NEW_USER_ID, Constants.NEW_USER_ID, Constants.NEW_USER_LAST_NAME,
                Constants.NEW_USER_ROLE, Constants.NEW_USER_COLLEGE);
        userHelper.addUser(newUser);
        Assert.assertEquals(users.size(), Constants.BASE_NUMBER_OF_USERS+1);
        Assert.assertEquals( userHelper.findUser(Constants.NEW_USER_ID), newUser);
    }

    /**
     * Method to test the addUser's method behavior. This should just add a User to the User's List
     * @throws IOException if there's error reading from the file.
     */
    @Test
    public void testAddUserWhenExists()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.USERS_BASE_FILE);
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
        //Check the number of users.
        Assert.assertEquals( users.size(), Constants.BASE_NUMBER_OF_USERS );
        //Check to make sure the user exists.
        User existing = userHelper.findUser(Constants.STUDENT_EAC_UNDER);
        Assert.assertNotNull(existing);
        //Modify the user.
        Assert.assertNotEquals(existing.getLastName(), Constants.NEW_USER_LAST_NAME);
        existing.setLastName(Constants.NEW_USER_LAST_NAME);
        //Add the modified user back.
        userHelper.addUser(existing);
        //Find the user again and check the modified value.
        User modified = userHelper.findUser(Constants.STUDENT_EAC_UNDER);
        Assert.assertNotNull(modified);
        Assert.assertEquals(modified.getLastName(), existing.getLastName());
    }

    //Tests for removeUser

    /**
     * Method to test the removeUser's method behavior. Finds an existing user and removes them. Then searches to ensure
     * that the User is no longer in the list.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testRemoveUser()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.USERS_BASE_FILE);
        userHelper.readUsers();
        List<User> users = userHelper.getUsers();
        //Check the number of users.
        Assert.assertEquals( users.size(), Constants.BASE_NUMBER_OF_USERS );
        //Check to make sure the user exists.
        User toRemove = userHelper.findUser(Constants.STUDENT_AAS_GRAD);
        Assert.assertNotNull(toRemove);
        //Remove the User
        userHelper.removeUser(toRemove);
        //Check the number of users.
        Assert.assertEquals( users.size(), Constants.BASE_NUMBER_OF_USERS-1 );
        //Check to make sure the user exists.
        User existing = userHelper.findUser(Constants.STUDENT_AAS_GRAD);
        Assert.assertNull(existing);
    }

    //Tests for findUser

    /**
     * Method to test the findUser's method behavior. Returns the User if found. Null if not found. User is found in
     * this test.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testFindUserWhenExists()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.USERS_BASE_FILE);
        userHelper.readUsers();
        //Check to see if the user is found and the ID is correct.
        User existing = userHelper.findUser(Constants.ADMIN_GRAD);
        Assert.assertNotNull( existing);
        Assert.assertEquals( existing.getId(), Constants.ADMIN_GRAD);
    }

    /**
     * Method to test the findUser's method behavior. Returns the User if found. Null if not found. User is not found in
     * this test.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testFindUserWhenNotExists()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.USERS_BASE_FILE);
        userHelper.readUsers();
        //The new user shouldn't be in the list and shouldn't be found.
        Assert.assertNull( userHelper.findUser(Constants.NEW_USER_ID));
    }

    //Tests for printUser
    /**
     * Method to test the printUsers method behavior. Should print the User's List.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testPrintUsers()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.USERS_BASE_FILE);
        userHelper.readUsers();
        //userHelper.printUsers();
        //TODO: Redirect standard out and check that the correct thing is printed.
    }

    /**
     * Method to test the printUsers method behavior. Should print nothing.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testPrintUsersEmptyList()
            throws IOException {
        userHelper = new UserHelper();
        userHelper.setFileName(Constants.EMPTY_FILE);
        userHelper.readUsers();
        userHelper.printUsers();
        //TODO: Redirect standard out and check that nothing is printed.
    }

    @After
    public void finish() {

    }
}
