package edu.sc.csce740.helpers;

import edu.sc.csce740.model.User;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.List;
import java.lang.reflect.Type;
import java.io.File;
import java.io.Reader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class UserHelper {
    String fileName;
    GsonBuilder builder;

    public UserHelper(String fileName) {
        this.fileName = fileName;
        builder = new GsonBuilder();
    }

    public List<User> readUsers()
            throws IOException {
        Type userType = new TypeToken<List<User>>() {}.getType();
        Gson gson = builder.create();

        File file;
        file = new File(fileName);

        List<User> users = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), userType);

        return users;
    }

//    public void writeUsers( List<User> users )
//            throws UserDataLoadException {
//
//    }
}
