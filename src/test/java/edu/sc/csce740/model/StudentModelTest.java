package edu.sc.csce740.model;

import org.junit.Assert;
import org.junit.Test;

public class StudentModelTest {
    @Test
    public void testStudentModelSetGetPhone(){
        Student testStudent = new Student();
        testStudent.setPhone("8031234567");
        Assert.assertEquals("8031234567", testStudent.getPhone());
    }

    @Test
    public void testStudentModelSetGetEmailAddress(){
        Student testStudent = new Student();
        testStudent.setEmailAddress("test@test.com");
        Assert.assertEquals("test@test.com", testStudent.getEmailAddress());
    }

    @Test
    public void testStudentModelSetGetAddressStreet(){
        Student testStudent = new Student();
        testStudent.setAddressStreet("1040 Main Street");
        Assert.assertEquals("1040 Main Street", testStudent.getAddressStreet());
    }

    @Test
    public void testStudentModelSetGetAddressCity(){
        Student testStudent = new Student();
        testStudent.setAddressCity("Columbia");
        Assert.assertEquals("Columbia", testStudent.getAddressCity());
    }

    @Test
    public void testStudentModelSetGetAddressState(){
        Student testStudent = new Student();
        testStudent.setAddressState("SC");
        Assert.assertEquals("SC", testStudent.getAddressState());
    }

    @Test
    public void testStudentModelSetGetAddressPostalCode(){
        Student testStudent = new Student();
        testStudent.setAddressPostalCode("29803");
        Assert.assertEquals("29803", testStudent.getAddressPostalCode());
    }
}
