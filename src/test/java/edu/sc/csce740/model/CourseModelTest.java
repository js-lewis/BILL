package edu.sc.csce740.model;

import org.junit.Assert;
import org.junit.Test;

public class CourseModelTest {
    @Test
    public void testCourseConstructorWithParameters(){
        Course testCourse = new Course("", "CSCE740", 3, true);
        testCourse.setName("Software Engineering");
        Assert.assertEquals("Software Engineering", testCourse.getName());
    }

    @Test
    public void testCourseSetGetId(){
        Course testCourse = new Course();
        testCourse.setId("CSCE740");
        Assert.assertEquals("CSCE740" ,testCourse.getId());
    }

    @Test
    public void testCourseSetGetNumCreditsPositiveNumber(){
        Course testCourse = new Course();
        testCourse.setNumCredits(3);
        Assert.assertEquals(3 ,testCourse.getNumCredits());
    }

    @Test
    public void testCourseSetGetNumCreditsNegativeNumber(){
        Course testCourse = new Course();
        testCourse.setNumCredits(-3);
        Assert.assertEquals(0 ,testCourse.getNumCredits());
    }

    @Test
    public void testCourseSetGetOnline(){
        Course testCourse = new Course();
        testCourse.setOnline(true);
        Assert.assertEquals(true ,testCourse.isOnline());
    }

    @Test
    public void testCourseToStringGTZero(){
        Course testCourse = new Course();
        Assert.assertTrue(testCourse.toString().length() > 0);
    }
}
