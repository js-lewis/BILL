package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class SemesterTest {
    @Test
    public void testSemester_FALL_ToString(){
        Assert.assertEquals("FALL", Semester.FALL.toString());
    }

    @Test
    public void testSemester_SPRING_ToString(){
        Assert.assertEquals("SPRING", Semester.SPRING.toString());
    }

    @Test
    public void testSemester_SUMMER_ToString(){
        Assert.assertEquals("SUMMER", Semester.SUMMER.toString());
    }
}
