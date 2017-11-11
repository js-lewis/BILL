package edu.sc.csce740.helpers;

//Files for testing
import edu.sc.csce740.defines.Constants;

import edu.sc.csce740.model.StudentRecord;

//JUnit imports
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class StudentHelperTest {
    private StudentHelper studentHelper;

    /**
     * Method to test the ReadUsers method happy path. This reads in the provided users.txt file and checks to ensure
     * that 5 users were loaded.
     *
     * @throws Exception
     */
    @BeforeClass
    public static void classSetup() {

    }

    /**
     * Method to test the ReadStudentRecords method happy path. This reads in the provided students.txt file and checks
     * to ensure that 2 users were loaded.
     *
     * @throws Exception
     */
    @Test
    public void testReadStudents()
            throws IOException {
        studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        List<StudentRecord> records = studentHelper.getStudents();
        Assert.assertEquals(records.size(), Constants.BASE_NUMBER_OF_RECORDS);
    }

    @Test
    public void testFindStudentRecord()
            throws IOException {
        studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        StudentRecord sampleRec = studentHelper.findStudentRecord("mhunt");
        Assert.assertEquals(sampleRec.getStudent().getPhone(), "999-999-9999");
    }

    /**
     * Method to test the Exception thrown when there's an error reading the file.
     *
     * @throws Exception
     */
    @Test(expected = IOException.class)
    public void testReadStudentsWithExc()
            throws IOException {
        studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.NO_FILE);
        studentHelper.readStudentRecords();
    }

    @After
    public void finish() {

    }
}