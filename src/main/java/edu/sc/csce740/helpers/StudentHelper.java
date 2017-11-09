package edu.sc.csce740.helpers;

import edu.sc.csce740.model.StudentRecord;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class StudentHelper {
    String fileName;
    GsonBuilder builder;
    List<StudentRecord> studentRecords;

    public StudentHelper() {
        this.fileName = "";
        this.studentRecords = null;
        this.builder = new GsonBuilder();
    }

    public StudentHelper(String fileName) {
        this.fileName = fileName;
        this.studentRecords = null;
        this.builder = new GsonBuilder();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<StudentRecord> getStudents() {
        return studentRecords;
    }

    public void setStudents(List<StudentRecord> studentRecords) {
        this.studentRecords = studentRecords;
    }

    public void readStudentRecords()
            throws IOException {
        Type studentRecordType = new TypeToken<List<StudentRecord>>() {}.getType();
        Gson gson = builder.create();

        File file;
        file = new File(fileName);

        studentRecords = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), studentRecordType);
    }

    public void writeStudentRecords()
            throws IOException {
        Gson gson = builder.setPrettyPrinting().create();

        File file;
        file = new File(fileName);

        FileUtils.writeStringToFile(file, gson.toJson(studentRecords), "UTF-8");
    }

    public List<String> getStudentsByCollege(String college) {
        List<String> studentList = new ArrayList<String>();

        for( StudentRecord student: studentRecords ){
            if(college.equalsIgnoreCase(student.getCollege().toString())) {
                studentList.add(student.getStudent().getId());
            }
        }

        return studentList;
    }

    public List<String> getGraduateStudents() {
        List<String> studentList = new ArrayList<String>();

        for( StudentRecord student: studentRecords ){
            switch(student.getClassStatus()) {
                case MASTERS:
                case PHD:
                    studentList.add(student.getStudent().getId());
                    break;
                case FRESHMAN:
                case SOPHOMORE:
                case JUNIOR:
                case SENIOR:
                case GRADUATED:
                default: //Do nothing
                    break;
            }
        }

        return studentList;
    }

    public void addStudentRecord(StudentRecord newStudentRecord) {
        if( studentRecords != null ) {
            studentRecords.add(newStudentRecord);
        }
    }

    public void removeStudentRecord(StudentRecord studentRecord) {
        if( studentRecords != null ) {
            studentRecords.remove(studentRecord);
        }
    }

    public StudentRecord findStudentRecord(String studentId) {
        if( studentRecords != null ) {
            for (StudentRecord studentRecord : studentRecords) {
                if (studentRecord.getStudent().getId().equalsIgnoreCase(studentId)) {
                    return studentRecord;
                }
            }
        }
        return null;
    }

    public void printStudentRecords() {
        for (StudentRecord student : studentRecords) {
            System.out.println(student);
        }
    }
}
