package edu.sc.csce740.helpers;

//Model imports
import edu.sc.csce740.defines.College;
import edu.sc.csce740.model.StudentRecord;

//GSON imports
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//Java imports
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.io.File;
import java.io.IOException;

//Apache commons File Util imports
import org.apache.commons.io.FileUtils;

/**
 * This class contains the business logic associated with getting, modifying and saving StudentRecords in the BILL
 * system. This includes adding transactions to the Student's record.
 */
public class StudentHelper {
    /**
     * The name of the file to read from and write to. This is expected to be a JSON file.
     */
    String fileName;

    /**
     * The GSON object for reading and writing JSON.
     */
    GsonBuilder builder;

    /**
     * The list of student records.
     */
    List<StudentRecord> studentRecords;

    /**
     * The default constructor for the helper.
     */
    public StudentHelper() {
        this.fileName = "";
        this.studentRecords = new ArrayList<StudentRecord>();
        this.builder = new GsonBuilder();
    }

    /**
     * A constructor for the helper that sets only the filename to read/write the JSON to.
     * @param fileName
     */
    public StudentHelper(String fileName) {
        this.fileName = fileName;
        this.studentRecords = new ArrayList<StudentRecord>();
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
     * Gets a list of the Student Records that have been loaded into the helper.
     * @return a list of Student Record objects currently loaded in the object. This list can be empty.
     */
    public List<StudentRecord> getStudents() {
        return studentRecords;
    }

    /**
     * Sets the List of Student Records that have been loaded into the helper.
     * @param studentRecords the new List of users.
     */
    public void setStudents(List<StudentRecord> studentRecords) {
        this.studentRecords = studentRecords;
    }

    /**
     * Reads in a List of Student Records objects and loads them into the helper from the file already saved in the
     * helper. The file is expected to be in JSON format.
     * @throws IOException if the file cannot be read from.
     */
    public void readStudentRecords()
            throws IOException {
        //Gets the object that the JSON should match.
        Type studentRecordType = new TypeToken<List<StudentRecord>>() {}.getType();
        //Create the GSON object.
        Gson gson = builder.create();
        //Setup the File for IO.
        File file;
        file = new File(fileName);

        studentRecords = gson.fromJson(FileUtils.readFileToString(file, "UTF-8"), studentRecordType);

        //If users is null here, the file was empty. Create an empty List instead.
        if(studentRecords == null) {
            studentRecords = new ArrayList<StudentRecord>();
        }
    }

    /**
     * Writes a the List of Student Record objects that has been loaded into the helper to the file set in the helper.
     * @throws IOException if the File cannot be written to.
     */
    public void writeStudentRecords()
            throws IOException {
        //Set up the GSON object to print the JSON "pretty".
        Gson gson = builder.setPrettyPrinting().create();
        //Setup the File for IO.
        File file;
        file = new File(fileName);

        //Write the JSON to a file.
        FileUtils.writeStringToFile(file, gson.toJson(studentRecords), "UTF-8");
    }

    /**
     * Adds a Student Record to the List if that Student Records is not already in the list. Otherwise the Student
     * Record is modified.
     * @param newStudentRecord   the new StudentRecord to add.
     */
    public void addStudentRecord(StudentRecord newStudentRecord) {
        //If studentRecords is null there's been a problem so don't add anything.
        if( studentRecords != null ) {
            //Look to see if the Student Record exists already.
            StudentRecord existing = findStudentRecord(newStudentRecord.getStudent().getId());
            //If the Student Records doesn't exist then add it to the List
            if( null == existing) {
                studentRecords.add(newStudentRecord);
                //Otherwise remove the existing Student Records and add the new one so as to not have duplicate ID's
            } else {
                studentRecords.remove(existing);
                studentRecords.add(newStudentRecord);
            }
        }
    }

    /**
     * Removes a Student Record from the Student Records List.
     * @param oldStudentRecord   the StudentRecord to remove.
     */
    public void removeStudentRecord(StudentRecord oldStudentRecord) {
        if( studentRecords != null ) {
            studentRecords.remove(oldStudentRecord);
        }
    }

    /**
     * Finds a Student Record in the Student Records List by ID. This is currently done by iterating through the list.
     * @param studentId    the ID of the Student in the Student Record to find
     * @return the Student Record object whose Student's ID matches the input parameter. Null if there's no match.
     */
    public StudentRecord findStudentRecord(String studentId) {
        //Check to make sure the list isn't null
        if( studentRecords != null ) {
            //Look through the list Student Record by Student Record
            for (StudentRecord studentRecord : studentRecords) {
                //If the Student's ID matches, return the Student Record. This assumes no duplicate ID's
                if (studentRecord.getStudent().getId().equalsIgnoreCase(studentId)) {
                    return studentRecord;
                }
            }
        }
        return null;
    }

    /**
     * Gets a List of Strings where each string is the student ID of a student enrolled in the input college.
     * @param college   the college being searched for.
     * @return the List of Student ID's associated with the College.
     */
    public List<String> getStudentsByCollege(College college) {
        //Setup an empty list of String's
        List<String> studentList = new ArrayList<String>();

        //Check to make sure the list isn't null.
        if( studentRecords != null ) {
            //Look through the list Student Record by Student Record.
            for (StudentRecord student : studentRecords) {
                //If the Student's College matches, add the Student's ID to the list to return.
                if (college == student.getCollege()) {
                    studentList.add(student.getStudent().getId());
                }
            }
        }

        //Return the completed list.
        return studentList;
    }

    /**
     * Gets a List of Strings where each string is the student ID of a graduate student.
     * @return the List of Student ID's enrolled in a graduate program.
     */
    public List<String> getGraduateStudents() {
        //Setup an empty list of String's
        List<String> studentList = new ArrayList<String>();

        //Check to make sure the list isn't null
        if( studentRecords != null ) {
            //Look through the list Student Record by Student Record.
            for (StudentRecord student : studentRecords) {
                switch (student.getClassStatus()) {
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
        }

        //Return the completed list.
        return studentList;
    }

    /**
     * Print out the Student Records in the List. This is for debugging purposes.
     */
    public void printStudentRecords() {
        if(studentRecords != null) {
            for (StudentRecord student : studentRecords) {
                System.out.println(student);
            }
        }
    }
}
