package edu.sc.csce740.helpers;

import edu.sc.csce740.defines.ClassStatus;
import edu.sc.csce740.defines.Constants;
import edu.sc.csce740.exceptions.BillGenerationException;

import edu.sc.csce740.model.Bill;
import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.Fees;
import edu.sc.csce740.model.StudentRecord;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BillHelperTest {
    @Test
    public void testReadFees() {
        BillHelper billHelper = new BillHelper();
        Fees fees = billHelper.getFees();
        Assert.assertEquals(3, fees.getResidentUnderGradTuition().size());
        Assert.assertEquals(14, fees.getNonResidentUnderGradTuition().size());
        Assert.assertEquals(3, fees.getActiveDutyTuition().size());
        Assert.assertEquals(3, fees.getResidentGraduateTuition().size());
        Assert.assertEquals(4, fees.getNonResidentGraduateTuition().size());
        Assert.assertEquals(17, fees.getGeneralFees().size());
        Assert.assertEquals(18, fees.getCollegeFees().size());
    }

    @Test
    public void testGenerateBill()
            throws BillGenerationException, IOException {
        StudentHelper studentHelper = new StudentHelper();
        studentHelper.setFileName(Constants.RECORDS_BASE_FILE);
        studentHelper.readStudentRecords();

        BillHelper billHelper = new BillHelper();
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.EAC_FRESHMAN));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.AAS_SOPHOMORE));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.AAS_JUNIOR));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.EAC_SENIOR));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.EAC_MASTERS));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.EAC_PHD));
        billHelper.generateBill(studentHelper.findStudentRecord(Constants.AAS_PHD));

    }

    @Test
    public void testGenerateCollegeFees()
            throws BillGenerationException, IOException {
        Course[] courses = {
            new Course("Anthropology 161", "ANTH 161", 4, false)
        };

        StudentRecord stuRec = new StudentRecord();
        stuRec.setClassStatus(ClassStatus.GRADUATED);
        stuRec.addCourse(courses[0]);

        BillHelper billHelper = new BillHelper();
        Bill bill = billHelper.generateBill(stuRec);
        System.out.println(bill);
    }
}
