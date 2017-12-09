package edu.sc.csce740.model;

import edu.sc.csce740.defines.*;
import org.junit.Assert;
import org.junit.Test;

public class StudentRecordModelTest {

    @Test
    public void testStudentRecordConstructor(){
        StudentRecord testStudentRecord = new StudentRecord(new Student(),
                College.ARTS_AND_SCIENCES,
                new Term(),
                new Term(),
                ClassStatus.FRESHMAN,
                false,
                false,
                InternationalStatus.NONE,
                true,
                false,
                false,
                false,
                Scholarship.NONE,
                StudyAbroad.NONE,
                false,
                false,
                null,
                null);
        testStudentRecord.setCollege(College.ENGINEERING_AND_COMPUTING);
        Assert.assertEquals(College.ENGINEERING_AND_COMPUTING, testStudentRecord.getCollege());
    }

    @Test
    public void testStudentRecordToStringGtZero(){
        StudentRecord testStudentRecord = new StudentRecord();
        Assert.assertTrue(testStudentRecord.toString().length() > 0);
    }

    @Test
    public void testStudentRecordSetGetTermBegan(){
        StudentRecord testStudentRecord = new StudentRecord();
        Term testTerm = new Term(Semester.FALL, 2017);
        testStudentRecord.setTermBegan(testTerm);
        Assert.assertTrue(testStudentRecord.getTermBegan().getSemester() == Semester.FALL);
    }

    @Test
    public void testStudentRecordSetGetGradAssistant(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setGradAssistant(true);
        Assert.assertTrue(testStudentRecord.isGradAssistant());
    }

    @Test
    public void testStudentRecordSetGetInternational(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setInternational(true);
        Assert.assertTrue(testStudentRecord.isInternational());
    }

    @Test
    public void testStudentRecordSetGetCapstoneEnrolled(){
        StudentRecord testStudentRecord = new StudentRecord();
        Term testTerm = new Term(Semester.FALL, 2017);
        testStudentRecord.setCapstoneEnrolled(testTerm);
        Assert.assertTrue(testStudentRecord.getCapstoneEnrolled().getSemester() == Semester.FALL);
    }

    @Test
    public void testStudentRecordSetGetInternationalStatus(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setInternationalStatus(InternationalStatus.SPONSORED);
        Assert.assertEquals(InternationalStatus.SPONSORED, testStudentRecord.getInternationalStatus());
    }

    @Test
    public void testStudentRecordSetGetResident(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setResident(true);
        Assert.assertTrue(testStudentRecord.isResident());
    }

    @Test
    public void testStudentRecordSetGetActiveDuty(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setActiveDuty(true);
        Assert.assertTrue(testStudentRecord.isActiveDuty());
    }

    @Test
    public void testStudentRecordSetGetVeteran(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setVeteran(true);
        Assert.assertTrue(testStudentRecord.isVeteran());
    }

    @Test
    public void testStudentRecordSetGetFreeTuition(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setFreeTuition(true);
        Assert.assertTrue(testStudentRecord.isFreeTuition());
    }

    @Test
    public void testStudentRecordSetGetStudyAbroad(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setStudyAbroad(StudyAbroad.COHORT);
        Assert.assertEquals(StudyAbroad.COHORT, testStudentRecord.getStudyAbroad());
    }

    @Test
    public void testStudentRecordSetGetNationalStudentExchange(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setNationalStudentExchange(true);
        Assert.assertTrue(testStudentRecord.isNationalStudentExchange());
    }

    @Test
    public void testStudentRecordSetGetOutsideInsurance(){
        StudentRecord testStudentRecord = new StudentRecord();
        testStudentRecord.setOutsideInsurance(true);
        Assert.assertTrue(testStudentRecord.isOutsideInsurance());
    }
}
