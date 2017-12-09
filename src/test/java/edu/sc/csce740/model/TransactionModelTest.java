package edu.sc.csce740.model;

import edu.sc.csce740.defines.TransactionType;
import org.junit.Assert;
import org.junit.Test;

public class TransactionModelTest {
    @Test
    public void testTransactionModelSetGetType()
    {
        Transaction testTransaction = new Transaction();
        testTransaction.setType(TransactionType.CHARGE);
        Assert.assertEquals(TransactionType.CHARGE, testTransaction.getType());
    }

    @Test
    public void testTransactionModelSetGetDate()
    {
        Transaction testTransaction = new Transaction();
        Date testDate = new Date(1, 1, 2017);
        testTransaction.setTransactionDate(testDate);
        Assert.assertEquals(2017, testTransaction.getTransactionDate().getYear());
    }

    @Test
    public void testTransactionModelSetGetNote()
    {
        Transaction testTransaction = new Transaction();
        testTransaction.setNote("testing 123");
        Assert.assertEquals("testing 123", testTransaction.getNote());
    }
}
