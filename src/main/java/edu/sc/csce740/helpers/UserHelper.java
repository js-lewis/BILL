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

    public UserHelper(String fileName) {
        this.fileName = fileName;
        users = null;
        builder = new GsonBuilder();
    }

    public List<User> readUsers()
            throws IOException {
        Type userType = new TypeToken<List<User>>() {}.getType();
        Gson gson = builder.create();

        File file;
        file = new File(fileName);

        users = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), userType);

        return users;
    }

    public void writeUsers()
            throws IOException {
        Gson gson = builder.create();

        File file;
        file = new File(fileName);

        FileUtils.writeStringToFile(file, gson.toJson(users), "UTF-8");
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }

    public void removeUser(User newUser) {
        users.remove(newUser);
    }

    public void printUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}
