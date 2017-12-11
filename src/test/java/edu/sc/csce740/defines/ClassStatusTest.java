package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class ClassStatusTest {
    @Test
    public void testClassStatus_FRESHMAN_ToString(){
        Assert.assertEquals("FRESHMAN", ClassStatus.FRESHMAN.toString());
    }

    @Test
    public void testClassStatus_SOPHOMORE_ToString(){
        Assert.assertEquals("SOPHOMORE", ClassStatus.SOPHOMORE.toString());
    }

    @Test
    public void testClassStatus_JUNIOR_ToString(){
        Assert.assertEquals("JUNIOR", ClassStatus.JUNIOR.toString());
    }

    @Test
    public void testClassStatus_SENIOR_ToString(){
        Assert.assertEquals("SENIOR", ClassStatus.SENIOR.toString());
    }

    @Test
    public void testClassStatus_MASTERS_ToString(){
        Assert.assertEquals("MASTERS", ClassStatus.MASTERS.toString());
    }

    @Test
    public void testClassStatus_PHD_ToString(){
        Assert.assertEquals("PHD", ClassStatus.PHD.toString());
    }

    @Test
    public void testClassStatus_GRADUATED_ToString(){
        Assert.assertEquals("GRADUATED", ClassStatus.GRADUATED.toString());
    }
}
