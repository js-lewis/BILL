package edu.sc.csce740.model;

import edu.sc.csce740.defines.ClassStatus;
import edu.sc.csce740.defines.College;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BillModelTest {
    @Test
    public void testBillModelDefaultConstructorBalance1Hundred() {
        Bill testBill = new Bill();
        testBill.setBalance(100.0);
        Assert.assertEquals(100.0, testBill.getBalance(), 0.1);
    }

    @Test
    public void testBillModelDefaultConstructorStudentGetterSetter() {
        Bill testBill = new Bill();
        Student testStudent = new Student();
        testStudent.setFirstName("Sally");
        testBill.setStudent(testStudent);
        Assert.assertEquals("Sally", testBill.getStudent().getFirstName());
    }

    @Test
    public void testBillModelDefaultConstructorCollegeGetterSetter() {
        Bill testBill = new Bill();
        testBill.setCollege(College.GRADUATE_SCHOOL);
        Assert.assertEquals(College.GRADUATE_SCHOOL, testBill.getCollege());
    }

    @Test
    public void testBillModelDefaultConstructorHas1Transaction() {
        Bill testBill = new Bill();
        Transaction testTransaction = new Transaction();
        testBill.addTransaction(testTransaction);
        Assert.assertTrue(testBill.getTransactions().size() == 1);
    }

    @Test
    public void testBillModelDefaultConstructorSetTransactions() {
        Bill testBill = new Bill();
        Transaction testTransaction = new Transaction();
        List<Transaction> testTransations = new ArrayList<Transaction>();
        testTransations.add(testTransaction);
        testBill.setTransactions(testTransations);
        Assert.assertTrue(testBill.getTransactions().size() == 1);
    }

    @Test
    public void testBillModelDefaultConstructorToString() {
        Bill testBill = new Bill();
        Assert.assertTrue(testBill.toString().length() > 0);
    }
    @Test
    public void testBillModelContructorClassStatusJunior() {
        Bill testBill = new Bill(new Student(), College.ARTS_AND_SCIENCES, ClassStatus.FRESHMAN, 0.0, null);
        testBill.setClassStatus(ClassStatus.JUNIOR);
        Assert.assertEquals(ClassStatus.JUNIOR, testBill.getClassStatus());
    }
}
