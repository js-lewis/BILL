package edu.sc.csce740.model;

import edu.sc.csce740.defines.Semester;
import edu.sc.csce740.exceptions.InvalidDateException;
import org.junit.Assert;
import org.junit.Test;

public class DateModelTest {
    @Test
    public void testDateModelSetGetMonth(){
        Date testDate = new Date();
        testDate.setMonth(11);
        Assert.assertEquals(11, testDate.getMonth());
    }

    @Test
    public void testDateModelSetGetYear(){
        Date testDate = new Date();
        testDate.setYear(1991);
        Assert.assertEquals(1991, testDate.getYear());
    }

    @Test
    public void testDateModelSetGetDay(){
        Date testDate = new Date();
        testDate.setDay(19);
        Assert.assertEquals(19, testDate.getDay());
    }

    @Test
    public void testDateModelBefore()
    {
        Date dateBefore = new Date(1,1,2017);
        Date dateAfter = new Date(12,31,2017);
        Assert.assertTrue(dateBefore.isBefore(dateAfter));
    }

    @Test
    public void testDateModelBeforeWhenAfter()
    {
        Date dateBefore = new Date(1,1,2017);
        Date dateAfter = new Date(12,31,2017);
        Assert.assertFalse(dateAfter.isBefore(dateBefore));
    }

    @Test
    public void testDateModelBeforeYear()
    {
        Date dateBefore = new Date(1,1,2016);
        Date dateAfter = new Date(12,31,2017);
        Assert.assertTrue(dateBefore.isBefore(dateAfter));
    }

    @Test
    public void testDateModelBeforeDay()
    {
        Date dateBefore = new Date(1,1,2016);
        Date dateAfter = new Date(1,2,2016);
        Assert.assertTrue(dateBefore.isBefore(dateAfter));
    }

    @Test
    public void testDateModelBetween()
    {
        Date dateBefore = new Date(1,1,2017);
        Date dateAfter = new Date(12,31,2017);
        Date dateBetween = new Date(7,1,2017);
        Assert.assertTrue(dateBetween.isBetween(dateBefore, dateAfter));
    }

    @Test
    public void testDateModelAfter()
    {
        Date dateBefore = new Date(1,1,2017);
        Date dateAfter = new Date(12,31,2017);
        Assert.assertTrue(dateAfter.isAfter(dateBefore));
    }

    @Test
    public void testDateModelAfterWhenBefore()
    {
        Date dateBefore = new Date(1,1,2017);
        Date dateAfter = new Date(12,31,2017);
        Assert.assertFalse(dateBefore.isAfter(dateAfter));
    }

    @Test
    public void testDateModelAfterYear()
    {
        Date dateBefore = new Date(1,1,2017);
        Date dateAfter = new Date(12,31,2018);
        Assert.assertTrue(dateAfter.isAfter(dateBefore));
    }

    @Test
    public void testDateModelAfterDay()
    {
        Date dateBefore = new Date(1,1,2017);
        Date dateAfter = new Date(1,2,2017);
        Assert.assertTrue(dateAfter.isAfter(dateBefore));
    }

    @Test
    public void testDateModelGetSemesterSpring(){
        Date testDate = new Date(1, 1, 2017);
        try
        {
            Semester semester = testDate.getSemeter();
            Assert.assertEquals(Semester.SPRING, semester);
        }
        catch(InvalidDateException e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testDateModelGetSemesterSummer(){
        Date testDate = new Date(6, 1, 2017);
        try
        {
            Semester semester = testDate.getSemeter();
            Assert.assertEquals(Semester.SUMMER, semester);
        }
        catch(InvalidDateException e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testDateModelGetSemesterFall(){
        Date testDate = new Date(9, 1, 2017);
        try
        {
            Semester semester = testDate.getSemeter();
            Assert.assertEquals(Semester.FALL, semester);
        }
        catch(InvalidDateException e)
        {
            Assert.assertTrue(false);
        }
    }

//    @Test(expected = InvalidDateException.class)
//    public void testDateModelGetSemesterInvalidMonth() throws InvalidDateException {
//        Date testDate = new Date(0, 1, 2017);
//        Semester semester = testDate.getSemeter();
//    }

    @Test
    public void testDateModelToStringGTZero(){
        Date dateBetween = new Date(7,1,2017);
        Assert.assertTrue(dateBetween.toString().length() > 0);
    }

}
