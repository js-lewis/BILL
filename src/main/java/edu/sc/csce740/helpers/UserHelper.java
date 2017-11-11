package edu.sc.csce740.helpers;

//Model imports
import edu.sc.csce740.model.User;

//GSON imports
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//Apache IO imports
import org.apache.commons.io.FileUtils;

//Java imports
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;
import java.io.File;
import java.io.IOException;

public class UserHelper {
    /**
     * The name of the file to read from and write to. This is expected to be a JSON file.
     */
    private String fileName;

    /**
     * The GSON object for reading and writing JSON.
     */
    private GsonBuilder builder;

    /**
     * The list of users.
     */
    List<User> users;

    /**
     * The default constructor for the helper.
     */
    public UserHelper() {
        this.fileName = "";
        this.users = new ArrayList<User>();
        this.builder = new GsonBuilder();
    }

    /**
     * A constructor for the helper that sets only the filename to read/write the JSON to.
     * @param fileName
     */
    public UserHelper(String fileName) {
        this.fileName = fileName;
        this.users = new ArrayList<User>();
        this.builder = new GsonBuilder();
    }

    /**
     * Gets the filename that's currently set to read from / write to.
     * @return  a string containing the filename.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the filename to a file to read/write the JSON to.
     * @param fileName  the new filename to read from or write to.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets a list of the Users that have been loaded into the helper.
     * @return a list of User objects currently loaded in the object. This list can be empty.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the List of Users that have been loaded into the helper.
     * @param users the new List of users.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Reads in a List of User objects and loads them into the helper from the file already saved in the helper. The
     * file is expected to be in JSON format.
     * @throws IOException if the file cannot be read from.
     */
    public void readUsers()
            throws IOException {
        //Gets the object that the JSON should match.
        Type userType = new TypeToken<List<User>>() {}.getType();
        //Create the GSON object.
        Gson gson = builder.create();
        //Setup the File for IO.
        File file;
        file = new File(fileName);

        //Read the JSON file into a string and parse the JSON from that String into a List<User>
        users = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), userType);

        //If users is null here, the file was empty. Create an empty List instead.
        if(users == null) {
            users = new ArrayList<User>();
        }
    }

    /**
     * Writes a the List of User objects that has been loaded into the helper to the file set in the helper.
     * @throws IOException if the File cannot be written to.
     */
    public void writeUsers()
            throws IOException {
        //Set up the GSON object to print the JSON "pretty".
        Gson gson = builder.setPrettyPrinting().create();
        //Setup the File for IO.
        File file;
        file = new File(fileName);

        //Write the JSON to a file.
        FileUtils.writeStringToFile(file, gson.toJson(users), "UTF-8");
    }

    /**
     * Adds a User to the Users List if that user is not already in the list. Otherwise the user is modified.
     * @param newUser   the new User to add.
     */
    public void addUser(User newUser) {
        //If users is null there's been a problem so don't add anything.
        if( users != null ) {
            //Look to see if the user exists already.
            User existing = findUser(newUser.getId());
            //If the user doesn't exist then add them to the List
            if( null == existing) {
                users.add(newUser);
            //Otherwise remove the existing User and add the new User so as to not have duplicate ID's
            } else {
                users.remove(existing);
                users.add(newUser);
            }
        }
    }

    /**
     * Removes a User from the Users List.
     * @param oldUser   the User to remove.
     */
    public void removeUser(User oldUser) {
        //As long as the List isn't null, try to remove the user.
        if( users != null ) {
            users.remove(oldUser);
        }
    }

    /**
     * Finds a User in the Users List by ID. This is currently done by iterating through the list.
     * @param userId    the ID of the User to find
     * @return the User object whose
     */
    public User findUser(String userId) {
        //Check to make sure the list isn't null
        if( users != null ) {
            //Look through the list User by User
            for (User user : users) {
                //If the ID matches, return the user. This assumes no duplicate ID's
                if (user.getId().equalsIgnoreCase(userId)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * Print out the Users in the User list. This is for debugging purposes.
     */
    public void printUsers() {
        if(users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
}
