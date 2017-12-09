package edu.sc.csce740.model;

import edu.sc.csce740.defines.FeeType;
import edu.sc.csce740.defines.Frequency;
import edu.sc.csce740.defines.StudentType;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class FeeModelTest {
    @Test
    public void testFeeModelConstructor(){
        Fee testFee = new Fee(StudentType.FULL_TIME, FeeType.GENERAL, Frequency.ONE_TIME, new BigDecimal(0), "");
        testFee.setAmount(new BigDecimal(100));
        Assert.assertEquals(100, testFee.getAmount().intValue());
    }

    @Test
    public void testFeeModelSetGetStudentType(){
        Fee testFee = new Fee();
        testFee.setStudentType(StudentType.FULL_TIME);
        Assert.assertEquals(StudentType.FULL_TIME, testFee.getStudentType());
    }

    @Test
    public void testFeeModelSetGetFeeType(){
        Fee testFee = new Fee();
        testFee.setFeeType(FeeType.GENERAL);
        Assert.assertEquals(FeeType.GENERAL, testFee.getFeeType());
    }

    @Test
    public void testFeeModelSetGetFrequency(){
        Fee testFee = new Fee();
        testFee.setFrequency(Frequency.ONE_TIME);
        Assert.assertEquals(Frequency.ONE_TIME, testFee.getFrequency());
    }

    @Test
    public void testFeeModelSetGetNote(){
        Fee testFee = new Fee();
        testFee.setNote("testing");
        Assert.assertEquals("testing", testFee.getNote());
    }
}
