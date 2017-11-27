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
    public void testGenerateCollegeFees()
            throws BillGenerationException, IOException {
        BillHelper billHelper = new BillHelper();

        Course[] courses = {
            new Course("Anthropology 161", "ANTH 161", 4, false),
            new Course("Marine Sciences 201", "MARINE SCIENCE 201", 4, false),
            new Course("Media Arts 100", "MEDIA ARTS 100", 4, false),
            new Course("Marine Sciences 460", "MSCI 460", 4, false)
        };
        double[] fees = {
            210.0,
            210.0,
            100.0,
            300.0
        };
        String[] notes = {
            "Arts and Sciences Science Lab",
            "Arts and Sciences Science Lab",
            "Arts and Sciences Media Lab",
            "Arts and Sciences Marine Science"
        };

        for (int i = 0; i < courses.length; ++i) {
            StudentRecord stuRec = new StudentRecord();
            stuRec.setClassStatus(ClassStatus.GRADUATED);
            stuRec.addCourse(courses[i]);

            Bill bill = new Bill();
            billHelper.generateCollegeCharges(stuRec, bill);
            List<Transaction> transactions = bill.getTransactions();

            Assert.assertEquals(1, transactions.size());
            Assert.assertEquals(fees[i],
                    transactions.get(0).getAmount().doubleValue(), delta);
            Assert.assertEquals(notes[i], transactions.get(0).getNote());
        }
    }
}
