package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.PageFirst;
import utilities.Constants;
import utilities.Driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestFirst {
    private WebDriver driver;
    private PageFirst pageFirst;

    @BeforeEach
    public void setUp() {
        // Initialize WebDriver and open the login page
        driver = Driver.getDriver();
        driver.get("https://www.saucedemo.com/");

        // Initialize the PageLogin object
        pageFirst = new PageFirst(driver);
    }

    public void validLogin () {
        pageFirst.getUsernameInput().sendKeys(Constants.standardValidUsername);
        pageFirst.getPasswordInput().sendKeys(Constants.validPassword);
        pageFirst.getLoginButton().click();
    }

    public void invalidLogin () {
        pageFirst.getUsernameInput().sendKeys(Constants.invalidUsername);
        pageFirst.getPasswordInput().sendKeys(Constants.invalidPassword);
        pageFirst.getLoginButton().click();
    }

    public void lockedOutLogin () {
        pageFirst.getUsernameInput().sendKeys(Constants.lockedOutUsername);
        pageFirst.getPasswordInput().sendKeys(Constants.validPassword);
        pageFirst.getLoginButton().click();
    }

    @Test
    public void testValidLogin()
    {
        validLogin();
        assertEquals(Constants.homePageURL, pageFirst.getCurrentUrl(), "Login Failed");
        System.out.println("\n########## TEST VALID LOGIN ##########");
        System.out.println("\nExpected URL: " + Constants.homePageURL);
        System.out.println("Actual URL: " + pageFirst.getCurrentUrl());
        System.out.println("\n##### ##### ##### ##### ##### #####");
    }

    @Test
    public void testInvalidLogin()
    {
        invalidLogin();
        assertEquals(Constants.errorMessageInvalidInput, pageFirst.getInvalidMessage());
        System.out.println("\n########## TEST INVALID LOGIN ##########");
        System.out.println("\nExpected Error Message: " + Constants.errorMessageInvalidInput);
        System.out.println("Actual Error message: " + pageFirst.getInvalidMessage());
        System.out.println("\n##### ##### ##### ##### ##### #####");
    }

    @Test
    public void testLockedUserLogin() {
        lockedOutLogin();
        assertEquals(Constants.lockedOutErrorMessage, pageFirst.getInvalidMessage());
        System.out.println("\n########## TEST LOCKED OUT USER LOGIN ##########");
        System.out.println("\nExpected Error Message: " + Constants.lockedOutErrorMessage);
        System.out.println("Actual Error message: " + pageFirst.getInvalidMessage());
        System.out.println("\n##### ##### ##### ##### ##### #####");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after the test
        Driver.quitDriver();
    }
}
