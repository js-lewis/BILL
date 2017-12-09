package edu.sc.csce740.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FeesModelTest {

    @Test
    public void testFeesToStringGtZero(){
        Fees testFees = new Fees();
        Assert.assertTrue(testFees.toString().length() > 0);
    }

    @Test
    public void testFeesSetGetResidentUnderGradTuition(){
        Fees testFees = new Fees();
        List<Fee> tuitionList = new ArrayList<Fee>();
        tuitionList.add(new Fee());
        testFees.setResidentUnderGradTuition(tuitionList);
        Assert.assertTrue(testFees.getResidentUnderGradTuition().size() == 1);
    }

    @Test
    public void testFeesSetGetNonResidentUnderGradTuition(){
        Fees testFees = new Fees();
        List<Fee> tuitionList = new ArrayList<Fee>();
        tuitionList.add(new Fee());
        testFees.setNonResidentUnderGradTuition(tuitionList);
        Assert.assertTrue(testFees.getNonResidentUnderGradTuition().size() == 1);
    }

    @Test
    public void testFeesSetGetActiveDutyTuition(){
        Fees testFees = new Fees();
        List<Fee> tuitionList = new ArrayList<Fee>();
        tuitionList.add(new Fee());
        testFees.setActiveDutyTuition(tuitionList);
        Assert.assertTrue(testFees.getActiveDutyTuition().size() == 1);
    }

    @Test
    public void testFeesSetGetResidentGraduateTuition(){
        Fees testFees = new Fees();
        List<Fee> tuitionList = new ArrayList<Fee>();
        tuitionList.add(new Fee());
        testFees.setResidentGraduateTuition(tuitionList);
        Assert.assertTrue(testFees.getResidentGraduateTuition().size() == 1);
    }

    @Test
    public void testFeesSetGetNonresidentGraduateTuition(){
        Fees testFees = new Fees();
        List<Fee> tuitionList = new ArrayList<Fee>();
        tuitionList.add(new Fee());
        testFees.setNonResidentGraduateTuition(tuitionList);
        Assert.assertTrue(testFees.getNonResidentGraduateTuition().size() == 1);
    }

    @Test
    public void testFeesSetGetGeneralFees(){
        Fees testFees = new Fees();
        List<Fee> tuitionList = new ArrayList<Fee>();
        tuitionList.add(new Fee());
        testFees.setGeneralFees(tuitionList);
        Assert.assertTrue(testFees.getGeneralFees().size() == 1);
    }

    @Test
    public void testFeesSetGetCollegeFees(){
        Fees testFees = new Fees();
        List<Fee> tuitionList = new ArrayList<Fee>();
        tuitionList.add(new Fee());
        testFees.setCollegeFees(tuitionList);
        Assert.assertTrue(testFees.getCollegeFees().size() == 1);
    }
}
