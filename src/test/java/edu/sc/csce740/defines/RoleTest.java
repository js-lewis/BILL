package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class RoleTest {
    @Test
    public void testTransactionType_STUDENT_ToString(){
        Assert.assertEquals("STUDENT", Role.STUDENT.toString());
    }

    @Test
    public void testTransactionType_ADMIN_ToString(){
        Assert.assertEquals("ADMIN", Role.ADMIN.toString());
    }
}
