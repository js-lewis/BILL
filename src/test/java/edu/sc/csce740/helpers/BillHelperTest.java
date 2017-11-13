package edu.sc.csce740.helpers;

import edu.sc.csce740.defines.Constants;
import edu.sc.csce740.exceptions.BillGenerationException;
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
        Assert.assertEquals(fees.getResidentUnderGradTuition().size(), 2);
        Assert.assertEquals(fees.getNonResidentUnderGradTuition().size(), 12);
        Assert.assertEquals(fees.getActiveDutyTuition().size(), 2);
        Assert.assertEquals(fees.getResidentGraduateTuition().size(), 2);
        Assert.assertEquals(fees.getNonResidentGraduateTuition().size(), 2);
        Assert.assertEquals(fees.getGeneralFees().size(), 20);
        Assert.assertEquals(fees.getCollegeFees().size(), 18);
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
}
