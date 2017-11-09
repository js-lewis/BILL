package edu.sc.csce740.helpers;

import edu.sc.csce740.model.StudentRecord;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.lang.reflect.Type;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class StudentHelper {
    String fileName;
    GsonBuilder builder;
    List<StudentRecord> students;

    public StudentHelper(String fileName) {
        this.fileName = fileName;
        students = null;
        builder = new GsonBuilder();
    }

    public List<StudentRecord> readStudentRecords()
            throws IOException {
        Type studentRecordType = new TypeToken<List<StudentRecord>>() {}.getType();
        Gson gson = builder.create();

        File file;
        file = new File(fileName);

        students = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), studentRecordType);

        return students;
    }

    public void printStudentRecords() {
        for (StudentRecord student : students) {
            System.out.println(student);
        }
    }
}
