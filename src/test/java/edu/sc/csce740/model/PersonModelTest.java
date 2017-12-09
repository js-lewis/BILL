package edu.sc.csce740.model;

import org.junit.Assert;
import org.junit.Test;

public class PersonModelTest {
    @Test
    public void testPersonModelSetGetId(){
        Person testPerson = new Person();
        testPerson.setId("Happy123");
        Assert.assertEquals("Happy123", testPerson.getId());
    }
}
