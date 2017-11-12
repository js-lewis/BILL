package edu.sc.csce740.helpers;

import edu.sc.csce740.model.Fees;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(fees.getGeneralFees().size(), 22);
        Assert.assertEquals(fees.getCollegeFees().size(), 18);
    }
}
