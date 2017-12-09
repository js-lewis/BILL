package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class InternationalStatusTest {
    @Test
    public void testInternationalStatus_SHORT_TERM_ToString(){
        Assert.assertEquals("SHORT_TERM", InternationalStatus.SHORT_TERM.toString());
    }

    @Test
    public void testInternationalStatus_SPONSORED_ToString(){
        Assert.assertEquals("SPONSORED", InternationalStatus.SPONSORED.toString());
    }

    @Test
    public void testInternationalStatus_NONE_ToString(){
        Assert.assertEquals("NONE", InternationalStatus.NONE.toString());
    }
}
