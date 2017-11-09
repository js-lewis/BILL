package edu.sc.csce740.helpers;

import edu.sc.csce740.model.User;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.lang.reflect.Type;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class UserHelper {
    String fileName;
    GsonBuilder builder;
    List<User> users;

    public UserHelper() {
        this.fileName = "";
        this.users = null;
        this.builder = new GsonBuilder();
    }

    public UserHelper(String fileName) {
        this.fileName = fileName;
        this.users = null;
        this.builder = new GsonBuilder();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void readUsers()
            throws IOException {
        Type userType = new TypeToken<List<User>>() {}.getType();
        Gson gson = builder.create();

        File file;
        file = new File(fileName);

        users = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), userType);
    }

    public void writeUsers()
            throws IOException {
        Gson gson = builder.setPrettyPrinting().create();

        File file;
        file = new File(fileName);

        FileUtils.writeStringToFile(file, gson.toJson(users), "UTF-8");
    }

    public void addUser(User newUser) {
        if( users != null ) {
            users.add(newUser);
        }
    }

    public void removeUser(User newUser) {
        if( users != null ) {
            users.remove(newUser);
        }
    }

    public User findUser(String userId) {
        if( users != null ) {
            for (User user : users) {
                if (user.getId().equalsIgnoreCase(userId)) {
                    return user;
                }
            }
        }
        return null;
    }

    public void printUsers() {
        if(users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
}
