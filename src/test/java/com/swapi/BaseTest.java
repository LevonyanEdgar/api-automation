package com.swapi;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        //should be for common before methode functionality
    }

    @AfterMethod
    public void tearDownMethod() {
        //should be for common after methode functionality
    }

}
