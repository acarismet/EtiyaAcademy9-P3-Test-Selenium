package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.PageCCAddressInfoTab;
import pages.PageCCContactInfoTab;
import pages.PageCCDemographicInfoTab;
import pages.PageLogin;
import utilities.Constants;
import utilities.Driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestFirst {
    private WebDriver driver;
    private PageLogin pageLogin;
    private PageCCDemographicInfoTab pageCCDemographicInfoTab;
    private PageCCAddressInfoTab pageCCAddressInfoTab;
    private PageCCContactInfoTab pageCCContactInfoTab;

    @BeforeEach
    public void setUp() {
        // Initialize WebDriver and open the login page
        driver = Driver.getDriver();
        driver.get("http://localhost:4200/login");

        // Initialize the Page Objects object
        pageLogin = new PageLogin(driver);
        pageCCDemographicInfoTab = new PageCCDemographicInfoTab(driver);
        pageCCAddressInfoTab = new PageCCAddressInfoTab(driver);
        pageCCContactInfoTab = new PageCCContactInfoTab(driver);
    }

    public void validLogin () {
        pageLogin.getLoginEmailInput().sendKeys(Constants.loginValidEmail);
        System.out.println("Email entered => " + Constants.loginValidEmail);
        pageLogin.getLoginPasswordInput().sendKeys(Constants.loginValidPassword);
        System.out.println("Password entered => " + Constants.loginValidPassword);
        pageLogin.getLoginButton().click();

        boolean isUrlReached = pageLogin.waitForSpecificUrl(Constants.customerSearchURL, 10);
        System.out.println("URL reached: " + isUrlReached);

        String currentUrl = pageLogin.getCurrentPageUrl();
        System.out.println("Current Page URL: " + currentUrl);
        assert isUrlReached : "FAILED TO REACH EXPECTED URL";
        System.out.println("Reached URL successfully!");
    }

    public void getToCustomerCreatePage () {
        validLogin();
        boolean isCustomerCreateLoaded = pageLogin.navigateToCustomerCreate();
        System.out.println("Customer Create loaded: " + isCustomerCreateLoaded);
        assert isCustomerCreateLoaded : "FAILED TO LOAD CUSTOMER CREATE";
        System.out.println("Customer Create loaded successfully!" + pageLogin.getCurrentPageUrl());
    }


    public void createDemographicInformation() {
        getToCustomerCreatePage();
        pageCCDemographicInfoTab.getFirstNameInput().sendKeys(Constants.customerFirstName);
        pageCCDemographicInfoTab.getMiddleNameInput().sendKeys(Constants.customerMiddleName);
        pageCCDemographicInfoTab.getLastNameInput().sendKeys(Constants.customerLastName);
        pageCCDemographicInfoTab.getBirthDateInput().click();
        pageCCDemographicInfoTab.getBirthDateInput().sendKeys(Constants.customerBirthday);
        pageCCDemographicInfoTab.getGenderDropdown().click();
        pageCCDemographicInfoTab.getMaleOption().click();
        pageCCDemographicInfoTab.getFatherNameInput().sendKeys(Constants.customerFatherName);
        pageCCDemographicInfoTab.getMotherNameInput().sendKeys(Constants.customerMotherName);
        pageCCDemographicInfoTab.getNationalityIdInput().sendKeys(Constants.customerNationalityID);
        pageCCDemographicInfoTab.getNextButton().click();
    }

    @Test
    public void testValidLogin()
    {
        validLogin();
        System.out.println("\n######## testValidLogin Ends ########\n");
    }

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
