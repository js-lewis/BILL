package edu.sc.csce740.helpers;

import edu.sc.csce740.defines.ClassStatus;
import edu.sc.csce740.defines.Constants;

import edu.sc.csce740.exceptions.BillGenerationException;

import edu.sc.csce740.helpers.PrintHelper;

import edu.sc.csce740.model.Bill;
import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.Fees;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.Transaction;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class BillHelperTest {
    private static final double delta = 0.0001;

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
    public void testGenerateAASCollegeFees()
            throws BillGenerationException, IOException {
        BillHelper billHelper = new BillHelper();

        Course[] courses = {
            new Course("Placeholder Text", "ART EDUCATION 101", 4, false),
            new Course("Placeholder Text", "ART HISTORY 105", 4, false),
            new Course("Placeholder Text", "DANCE 102", 4, false),
            new Course("Placeholder Text", "GEOLOGY 735", 4, false),
            new Course("Placeholder Text", "MARINE SCIENCE 460", 4, false),
            new Course("Placeholder Text", "MARINE SCIENCE 101", 4, false),
            new Course("Placeholder Text", "MEDIA ART 101", 4, false),
            new Course("Placeholder Text", "STUDIO ART 101", 4, false)
        };
        double[] fees = {
            40.0,
            40.0,
            75.0,
            75.0,
            300.0,
            105.0,
            100.0,
            100.0
        };  // = 835.0

        for (int i = 0; i < courses.length; ++i) {
            StudentRecord stuRec = new StudentRecord();
            stuRec.setClassStatus(ClassStatus.JUNIOR);
            stuRec.addCourse(courses[i]);

            Bill bill = new Bill();
            bill = billHelper.generateCollegeCharges(stuRec, bill);
            List<Transaction> transactions = bill.getTransactions();

            Assert.assertEquals(1, transactions.size());
            Assert.assertEquals(fees[i],
                    transactions.get(0).getAmount().doubleValue(), delta);
        }
    }
}
