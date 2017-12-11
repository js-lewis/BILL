package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class FrequencyTest {
    @Test
    public void testFrequency_HOUR_ToString(){
        Assert.assertEquals("HOUR", Frequency.HOUR.toString());
    }

    @Test
    public void testFrequency_SEMESTER_ToString(){
        Assert.assertEquals("SEMESTER", Frequency.SEMESTER.toString());
    }

    @Test
    public void testFrequency_ONE_TIME_ToString(){
        Assert.assertEquals("ONE_TIME", Frequency.ONE_TIME.toString());
    }
}
