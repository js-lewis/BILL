package edu.sc.csce740.helpers;

//Files for testing
import edu.sc.csce740.defines.College;
import edu.sc.csce740.defines.Constants;

//Model imports
import edu.sc.csce740.model.StudentRecord;

//JUnit imports
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

//Java imports
import java.io.IOException;
import java.util.List;

public class StudentHelperTest {
    @BeforeClass
    public static void classSetup() {

    }

    //Tests for readStudentsRecords

    /**
     * Method to test the ReadStudentRecords method happy path. This reads in the provided students.txt file and checks
     * to ensure that 2 users were loaded.
     *
     * @throws Exception
     */
    @Test
    public void testReadStudents()
            throws IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        List<StudentRecord> studentRecords = studentHelper.getStudents();
        Assert.assertEquals(studentRecords.size(), Constants.BASE_NUMBER_OF_RECORDS);
    }

    /**
     * Method to test the readUsers method with an empty file. This reads in the provided empty file and checks to
     * ensure that 0 users were loaded, but that an empty list is available.
     * @throws IOException if there's error reading from the file.
     */
    @Test
    public void testReadStudentsEmptyFile()
            throws IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.EMPTY_FILE);
        studentHelper.readStudentRecords();
        List<StudentRecord> studentRecords = studentHelper.getStudents();
        Assert.assertEquals( studentRecords.size(), 0 );
    }

    /**
     * Method to test the Exception thrown when there's an error reading the file.
     * @throws Exception
     */
    @Test(expected = IOException.class)
    public void testReadStudentsWithExc()
            throws IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.NO_FILE);
        studentHelper.readStudentRecords();
    }

    //TODO: Add test for malformed JSON file.

    //Tests for writeStudentRecords
    //TODO: Write tests for writeStudentRecords

    //Tests for addStudentRecord
    //TODO: Write tests for addStudentRecord

    //Tests for removeStudentRecord
    @Test
    public void testRemoveStudentRecord()
            throws IOException{
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        List<StudentRecord> studentRecords = studentHelper.getStudents();
        //Check for the number of records.
        Assert.assertEquals(studentRecords.size(), Constants.BASE_NUMBER_OF_RECORDS);
        //Check to make sure the student record exists.
        StudentRecord toRemove = studentHelper.findStudentRecord(Constants.STUDENT_AAS_GRAD);
        Assert.assertNotNull(toRemove);
        //Remove the Student Record
        studentHelper.removeStudentRecord(toRemove);
        //Check the number of student records.
        Assert.assertEquals(studentRecords.size(), Constants.BASE_NUMBER_OF_RECORDS-1);
        //Check to make sure the student record no longer exists.
        StudentRecord existing = studentHelper.findStudentRecord(Constants.STUDENT_AAS_GRAD);
        Assert.assertNull(existing);
    }

    //Tests for findStudentRecord
    /**
     * Method to test the findStudentRecord's method behavior. Returns the Student Record if found. Null if not found.
     * StudentRecord is found in this test.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testFindStudentRecordWhenExists()
            throws IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        //Check to see if the user if found and if the ID is correct.
        StudentRecord sampleRec = studentHelper.findStudentRecord( Constants.AAS_SOPHOMORE);
        Assert.assertEquals(sampleRec.getStudent().getId(), Constants.AAS_SOPHOMORE);
    }

    /**
     * Method to test the findStudentRecord's method behavior. Returns the Student Record if found. Null if not found.
     * StudentRecord is not found in this test.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testFindStudentRecordWhenNotExists()
            throws IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        //Check to see if the user if found and if the ID is correct.
        StudentRecord sampleRec = studentHelper.findStudentRecord( Constants.NEW_STUDENT_ID);
        Assert.assertNull(sampleRec);
    }

    //Tests getStudentsByCollege
    /**
     * Method to test the getStudentsbyCollege method behavior. Returns the students enrolled in that college. This
     * test looks for Arts and Sciences students.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testGetStudentsByCollegeAAS()
            throws Exception {
        //Read in the Data
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();

        //Get the List
        List<String> results = studentHelper.getStudentsByCollege(College.ARTS_AND_SCIENCES);

        //Test that it contains the correct number of students and they're in the list
        Assert.assertEquals(results.size(), Constants.BASE_NUMBER_AAS);

        Assert.assertTrue(results.contains(Constants.AAS_SOPHOMORE));
        Assert.assertTrue(results.contains(Constants.AAS_JUNIOR));
        Assert.assertTrue(results.contains(Constants.AAS_PHD));

        //Also test to make sure that students who shouldn't be in there aren't
        Assert.assertFalse(results.contains(Constants.EAC_FRESHMAN));
        Assert.assertFalse(results.contains(Constants.EAC_SENIOR));
        Assert.assertFalse(results.contains(Constants.EAC_MASTERS));
        Assert.assertFalse(results.contains(Constants.EAC_PHD));
    }

    /**
     * Method to test the getStudentsbyCollege method behavior. Returns the students enrolled in that college. This
     * test looks for Engineering students.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testGetStudentsByCollegeEAC()
            throws Exception {
        //Read in the Data
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();

        //Get the List
        List<String> results = studentHelper.getStudentsByCollege(College.ENGINEERING_AND_COMPUTING);

        //Test that it contains the correct number of students and they're in the list
        Assert.assertEquals(results.size(), Constants.BASE_NUMBER_EAC);

        Assert.assertTrue(results.contains(Constants.EAC_FRESHMAN));
        Assert.assertTrue(results.contains(Constants.EAC_SENIOR));
        Assert.assertTrue(results.contains(Constants.EAC_MASTERS));
        Assert.assertTrue(results.contains(Constants.EAC_PHD));

        //Also test to make sure that students who shouldn't be in there aren't
        Assert.assertFalse(results.contains(Constants.AAS_SOPHOMORE));
        Assert.assertFalse(results.contains(Constants.AAS_JUNIOR));
        Assert.assertFalse(results.contains(Constants.AAS_PHD));
    }

    //Tests getGraduateStudents
    /**
     * Method to test the getGraduateStudents method behavior. Returns the students enrolled in that college.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testGetGraduateStudents()
            throws Exception {
        //Read in the Data
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();

        //Get the List
        List<String> results = studentHelper.getGraduateStudents();

        //Test that it contains the correct number of students and they're in the list
        Assert.assertEquals(results.size(), Constants.BASE_NUMBER_GRAD);

        Assert.assertTrue(results.contains(Constants.EAC_MASTERS));
        Assert.assertTrue(results.contains(Constants.EAC_PHD));
        Assert.assertTrue(results.contains(Constants.AAS_PHD));

        //Also test to make sure that students who shouldn't be in there aren't
        Assert.assertFalse(results.contains(Constants.AAS_SOPHOMORE));
        Assert.assertFalse(results.contains(Constants.AAS_JUNIOR));
        Assert.assertFalse(results.contains(Constants.EAC_FRESHMAN));
        Assert.assertFalse(results.contains(Constants.EAC_SENIOR));
    }

    //Tests printStudentRecords
    /**
     * Method to test the printStudentRecords method behavior. Should print the Student Records List.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testPrintStudentRecords()
            throws IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();
        //userHelper.printStudentRecords();
        //TODO: Redirect standard out and check that the correct thing is printed.
    }

    /**
     * Method to test the printStudentRecords method behavior. Should print nothing.
     * @throws IOException if there's an error reading from the file.
     */
    @Test
    public void testPrintStudentRecordsEmptyList()
            throws IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.EMPTY_FILE);
        studentHelper.readStudentRecords();
        studentHelper.printStudentRecords();
        //TODO: Redirect standard out and check that nothing is printed.
    }

    @After
    public void finish() {

    }
}