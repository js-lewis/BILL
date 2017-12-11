package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class CollegeTest {
    @Test
    public void testCollege_ARTS_AND_SCIENCES_ToString(){
        Assert.assertEquals("ARTS_AND_SCIENCES", College.ARTS_AND_SCIENCES.toString());
    }

    @Test
    public void testCollege_ENGINEERING_AND_COMPUTING_ToString(){
        Assert.assertEquals("ENGINEERING_AND_COMPUTING", College.ENGINEERING_AND_COMPUTING.toString());
    }

    @Test
    public void testCollege_GRADUATE_SCHOOL_ToString(){
        Assert.assertEquals("GRADUATE_SCHOOL", College.GRADUATE_SCHOOL.toString());
    }
}
