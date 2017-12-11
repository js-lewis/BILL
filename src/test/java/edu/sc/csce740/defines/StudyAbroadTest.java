package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class StudyAbroadTest {
    @Test
    public void testStudyAbroad_REGULAR_ToString(){
        Assert.assertEquals("REGULAR", StudyAbroad.REGULAR.toString());
    }

    @Test
    public void testStudyAbroad_COHORT_ToString(){
        Assert.assertEquals("COHORT", StudyAbroad.COHORT.toString());
    }

    @Test
    public void testStudyAbroad_NONE_ToString(){
        Assert.assertEquals("NONE", StudyAbroad.NONE.toString());
    }
}
