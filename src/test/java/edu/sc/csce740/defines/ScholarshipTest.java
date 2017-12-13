package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class ScholarshipTest {
    @Test
    public void testScholarship_WOODROW_ToString(){
        Assert.assertEquals("WOODROW", Scholarship.WOODROW.toString());
    }

    @Test
    public void testScholarship_ATHLETIC_ToString(){
        Assert.assertEquals("ATHLETIC", Scholarship.ATHLETIC.toString());
    }

    @Test
    public void testScholarship_DEPARTMENTAL_ToString(){
        Assert.assertEquals("DEPARTMENTAL", Scholarship.DEPARTMENTAL.toString());
    }

    @Test
    public void testScholarship_NONE_ToString(){
        Assert.assertEquals("NONE", Scholarship.NONE.toString());
    }

    @Test
    public void testScholarship_GENERAL_ToString(){
        Assert.assertEquals("GENERAL", Scholarship.GENERAL.toString());
    }

    @Test
    public void testScholarship_SIMS_ToString(){
        Assert.assertEquals("SIMS", Scholarship.SIMS.toString());
    }
}
