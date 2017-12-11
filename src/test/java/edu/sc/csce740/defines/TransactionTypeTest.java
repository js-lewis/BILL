package edu.sc.csce740.defines;

import org.junit.Assert;
import org.junit.Test;

public class TransactionTypeTest {
    @Test
    public void testTransactionType_PAYMENT_ToString(){
        Assert.assertEquals("PAYMENT", TransactionType.PAYMENT.toString());
    }

    @Test
    public void testTransactionType_CHARGE_ToString(){
        Assert.assertEquals("CHARGE", TransactionType.CHARGE.toString());
    }
}
