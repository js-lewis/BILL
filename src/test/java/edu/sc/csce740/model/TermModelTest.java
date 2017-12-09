package edu.sc.csce740.model;

import edu.sc.csce740.defines.Semester;
import org.junit.Assert;
import org.junit.Test;

public class TermModelTest {

    @Test
    public void testTermToStringGtZero(){
        Term testTerm = new Term();
        Assert.assertTrue(testTerm.toString().length() > 0);
    }

    @Test
    public void testTermSetGetSemester(){
        Term testTerm = new Term();
        testTerm.setSemester(Semester.SUMMER);
        Assert.assertEquals(Semester.SUMMER, testTerm.getSemester());
    }

    @Test
    public void testTermSetGetYear(){
        Term testTerm = new Term();
        testTerm.setYear(2017);
        Assert.assertEquals(2017, testTerm.getYear());
    }
}
