package org.obs.testingcommands;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNg1 {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
    }
    @AfterMethod
    public  void afterMethod(){
        System.out.println("afterMethod");
    }
    @Test(priority = 1)
    public  void testCaseMethod1(){
        System.out.println("testCaseMethod1");
    }
    @Test(priority = 2)
    public  void testCaseMethod2(){
        System.out.println("testCaseMethod2");
    }

}
