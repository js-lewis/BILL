package edu.sc.csce740.model;

import org.junit.Assert;
import org.junit.Test;

public class FeesModelTest {

    @Test
    public void testFeesToStringGtZero(){
        Fees testFees = new Fees();
        Assert.assertTrue(testFees.toString().length() > 0);
    }
}
