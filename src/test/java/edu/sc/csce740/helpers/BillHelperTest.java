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
}
