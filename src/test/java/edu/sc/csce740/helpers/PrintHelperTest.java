package edu.sc.csce740.helpers;

import java.util.ArrayList;
import java.util.List;

import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.Fee;
import edu.sc.csce740.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class PrintHelperTest {
    @Test
    public void ValidList_ValidTransaction_ReturnsStringGreaterThan1(){
        // arrange
        List<Transaction> testTransactions = new ArrayList<>();
        Transaction testTransaction = new Transaction();
        testTransactions.add(testTransaction);
        // act
        String result = PrintHelper.transactionListToString(testTransactions);
        // assert
        Assert.assertTrue(result.length() > 0);
    }

    @Test
    public void ValidList_NoTransactions_ReturnsStringLength0(){
        // arrange
        List<Transaction> testTransactions = new ArrayList<>();
        // act
        String result = PrintHelper.transactionListToString(testTransactions);
        // assert
        Assert.assertTrue(result.length() == 0);
    }

    @Test
    public void NullList_NoTransactions_ReturnsStringLength0(){
        // arrange
        // act
        String result = PrintHelper.transactionListToString(null);
        // assert
        Assert.assertTrue(result.length() == 0);
    }

    @Test
    public void ValidList_ValidCourse_ReturnsStringGreaterThan1(){
        // arrange
        List<Course> testCourses = new ArrayList<>();
        Course testCourse = new Course();
        testCourses.add(testCourse);
        // act
        String result = PrintHelper.courseListToString(testCourses);
        // assert
        Assert.assertTrue(result.length() > 0);
    }

    @Test
    public void ValidList_NoCourses_ReturnsStringLength0(){
        // arrange
        List<Course> testCourses = new ArrayList<>();
        // act
        String result = PrintHelper.courseListToString(testCourses);
        // assert
        Assert.assertTrue(result.length() == 0);
    }

    @Test
    public void NullList_NoCourses_ReturnsStringLength0(){
        // arrange
        // act
        String result = PrintHelper.courseListToString(null);
        // assert
        Assert.assertTrue(result.length() == 0);
    }

    @Test
    public void ValidList_ValidFees_ReturnsStringGreaterThan1(){
        // arrange
        List<Fee> testFees = new ArrayList<>();
        Fee testFee = new Fee();
        testFees.add(testFee);
        // act
        String result = PrintHelper.feeListToString(testFees);
        // assert
        Assert.assertTrue(result.length() > 0);
    }

    @Test
    public void ValidList_NoFees_ReturnsStringLength0(){
        // arrange
        List<Fee> testFees = new ArrayList<>();
        // act
        String result = PrintHelper.feeListToString(testFees);
        // assert
        Assert.assertTrue(result.length() == 0);
    }

    @Test
    public void NullList_NoFees_ReturnsStringLength0(){
        // arrange
        // act
        String result = PrintHelper.feeListToString(null);
        // assert
        Assert.assertTrue(result.length() == 0);
    }
}
