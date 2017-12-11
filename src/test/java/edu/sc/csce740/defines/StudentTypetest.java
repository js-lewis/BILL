package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class StudentTypetest {
    @Test
    public void testStudentType_FULL_TIME_ToString(){
        Assert.assertEquals("FULL_TIME", StudentType.FULL_TIME.toString());
    }

    @Test
    public void testStudentType_PART_TIME_ToString(){
        Assert.assertEquals("PART_TIME", StudentType.PART_TIME.toString());
    }

    @Test
    public void testStudentType_ALL_ToString(){
        Assert.assertEquals("ALL", StudentType.ALL.toString());
    }
}
