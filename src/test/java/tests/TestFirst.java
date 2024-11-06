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
        driver.get("http://localhost:4200/login");

        // Initialize the PageLogin object
        pageFirst = new PageFirst(driver);
    }

    public void validLogin () {
        pageFirst.getLoginEmailInput().sendKeys(Constants.loginValidEmail);
        System.out.println("Email entered => " + Constants.loginValidEmail);
        pageFirst.getLoginPasswordInput().sendKeys(Constants.loginValidPassword);
        System.out.println("Password entered => " + Constants.loginValidPassword);
        pageFirst.getLoginButton().click();

        boolean isUrlReached = pageFirst.waitForSpecificUrl(Constants.customerSearchURL, 10);
        System.out.println("URL reached: " + isUrlReached);

        String currentUrl = pageFirst.getCurrentPageUrl();
        System.out.println("Current Page URL: " + currentUrl);
        assert isUrlReached : "FAILED TO REACH EXPECTED URL";
        System.out.println("Reached URL successfully!");
    }

    public void getToCustomerCreatePage () {
        validLogin();
        boolean isCustomerCreateLoaded = pageFirst.navigateToCustomerCreate();
        System.out.println("Customer Create loaded: " + isCustomerCreateLoaded);
        assert isCustomerCreateLoaded : "FAILED TO LOAD CUSTOMER CREATE";
        System.out.println("Customer Create loaded successfully!" + pageFirst.getCurrentPageUrl());
    }


    public void createDemographicInformation() {
        getToCustomerCreatePage();
        pageFirst.getFirstNameInput().sendKeys();
    }

//    @Test
//    public void testValidLogin()
//    {
//        validLogin();
//        System.out.println("\n######## testValidLogin Ends ########\n");
//    }

    @Test
    public void testDemographicCreate()
    {
        getToCustomerCreatePage();
        System.out.println("\n######## testDemographicCreate Ends ########\n");
    }


    @AfterEach
    public void tearDown() {
        // Close the browser after the test
        Driver.quitDriver();
    }
}
