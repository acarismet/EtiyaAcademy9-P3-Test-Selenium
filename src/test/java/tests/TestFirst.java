package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.*;
import utilities.Constants;
import utilities.Driver;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFirst {
    private WebDriver driver;
    private PageLogin pageLogin;
    private PageCCDemographicInfoTab pageCCDemographicInfoTab;
    private PageCCAddressInfoTab pageCCAddressInfoTab;
    private PageCCContactInfoTab pageCCContactInfoTab;
    private PageCustomerSearch pageCustomerSearch;

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
        pageCustomerSearch = new PageCustomerSearch(driver);
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // ####### VALID LOGIN

    public void validLogin () throws IOException {
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_empty_login.png", 5);
        pageLogin.getLoginEmailInput().sendKeys(Constants.loginValidEmail);
        System.out.println("Email entered => " + Constants.loginValidEmail);
        pageLogin.getLoginPasswordInput().sendKeys(Constants.loginValidPassword);
        System.out.println("Password entered => " + Constants.loginValidPassword);
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_filled_login.png", 5);
        pageLogin.getLoginButton().click();

        boolean isUrlReached = pageLogin.waitForSpecificUrl(Constants.customerSearchURL, 10);
        System.out.println("URL reached: " + isUrlReached);

        String currentUrl = pageLogin.getCurrentPageUrl();
        System.out.println("Current Page URL: " + currentUrl);
        assert isUrlReached : "FAILED TO REACH EXPECTED URL";
        System.out.println("Reached URL successfully!");
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_logged_in.png", 5);
    }

    // ####### NAVIGATE TO CUSTOMER CREATION PAGE BY URL

    public void getToCustomerCreatePage () throws IOException {
        validLogin();
        boolean isCustomerCreateLoaded = pageLogin.navigateToCustomerCreate();
        System.out.println("Customer Create loaded: " + isCustomerCreateLoaded);
        assert isCustomerCreateLoaded : "FAILED TO LOAD CUSTOMER CREATE";
        System.out.println("Customer Create loaded successfully!" + pageLogin.getCurrentPageUrl());
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_reached_empty_creation.png", 5);
    }

    // ####### FIRST TAB - CREATE DEMOGRAPHIC INFORMATION

    public void createDemographicInformation() throws IOException {
        getToCustomerCreatePage();
        pageCCDemographicInfoTab.getFirstNameInput().sendKeys(Constants.customerFirstName);
        pageCCDemographicInfoTab.getMiddleNameInput().sendKeys(Constants.customerMiddleName);
        pageCCDemographicInfoTab.getLastNameInput().sendKeys(Constants.customerLastName);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        pageCCDemographicInfoTab.getBirthDateInput().sendKeys(Constants.customerBirthday);
//        WebElement birthDateInput = pageCCDemographicInfoTab.getBirthDateInput();
//        js.executeScript("arguments[0].click();", birthDateInput);
//        js.executeScript("arguments[0].value='1967-12-05';", birthDateInput);
        pageCCDemographicInfoTab.getGenderDropdown().click();
        pageCCDemographicInfoTab.getMaleOption().click();
        pageCCDemographicInfoTab.getFatherNameInput().sendKeys(Constants.customerFatherName);
        pageCCDemographicInfoTab.getMotherNameInput().sendKeys(Constants.customerMotherName);
        pageCCDemographicInfoTab.getNationalityIdInput().sendKeys(Constants.customerNationalityID);
        scrollToBottom();
        pageCCDemographicInfoTab.getRadioTurkish().click();
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_filled_demographic.png", 5);
        pageCCDemographicInfoTab.getNextButton();
        System.out.println(pageCCDemographicInfoTab.getNextButton().getText());
//        pageCCDemographicInfoTab.getNextButton().click();
        WebElement nextButton = pageCCDemographicInfoTab.getNextButton();
        Driver.clickElement(nextButton);
        System.out.println("Clicked next button");
    }


    // ####### SECOND TAB - CREATE ADDRESS INFORMATION

    public void createAddressInformationFromScratch() throws IOException {
        createDemographicInformation();
        scrollToBottom();
        pageCCAddressInfoTab.getAddButton();
        WebElement addButton = pageCCAddressInfoTab.getAddButton();
        Driver.clickElement(addButton);
        pageCCAddressInfoTab.getCityInput().sendKeys(Constants.customerAddressCity);
        pageCCAddressInfoTab.getDistrictInput().sendKeys(Constants.customerAddressDistrict);
        pageCCAddressInfoTab.getStreetInput().sendKeys(Constants.customerAddressStreet);
        pageCCAddressInfoTab.getHouseFlatInput().sendKeys(Constants.customerAddressHouseFlat);
        pageCCAddressInfoTab.getNeighbourhoodInput().sendKeys(Constants.customerAddressNeighbourhood);
        pageCCAddressInfoTab.getAddressDescriptionInput().sendKeys(Constants.customerAddressDescription);
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_filled_address.png", 5);
        pageCCAddressInfoTab.getSaveButton();
        WebElement saveButton = pageCCAddressInfoTab.getSaveButton();
        Driver.clickElement(saveButton);
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_ready_address.png", 5);
        pageCCAddressInfoTab.getNextButton();
        WebElement nextButton = pageCCAddressInfoTab.getNextButton();
        Driver.clickElement(nextButton);

    }


    // ####### THIRD TAB - CREATE CONTACT INFORMATION

    public void createContactInformationFromScratch() throws IOException {
        createAddressInformationFromScratch();
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_empty_contact.png", 5);
        pageCCContactInfoTab.getEmailInput().sendKeys(Constants.customerContactEmail);
        pageCCContactInfoTab.getPhoneInput().sendKeys(Constants.customerContactPhone);
        pageCCContactInfoTab.getFaxInput().sendKeys(Constants.customerContactFax);
        pageCCContactInfoTab.getHomePhoneInput().sendKeys(Constants.customerContactHomePhone);
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_filled_contact.png", 5);
        pageCCContactInfoTab.getSaveButton();
        WebElement saveButton = pageCCContactInfoTab.getSaveButton();
        Driver.clickElement(saveButton);
    }



    // ####### ALL SUCCESSFUL STEPS HOLDER
    public void checkCreatedCustomerByNationality() throws IOException {
        createContactInformationFromScratch();
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_reached_customer_search.png", 5);
        pageCustomerSearch.getNationalIDInput().sendKeys(Constants.customerNationalityID);
        pageCustomerSearch.getSearchButton();
        WebElement SearchButton = pageCustomerSearch.getSearchButton();
        Driver.clickElement(SearchButton);
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_searched_customer.png", 5);

        pageCustomerSearch.elementWithSpecificText();
        WebElement NatIdWithSpecificText = pageCustomerSearch.getNationalIDInput();
        if (NatIdWithSpecificText != null) {
            System.out.println("Created Customer Found: " + NatIdWithSpecificText.getText());
        } else {
            System.out.println("Element with specified text not found.");
        }

    }


    @Test
    public void testPart() throws IOException {
        createDemographicInformation();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPart2() throws IOException {
        createContactInformationFromScratch();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void testSuccessfulCustomerCreation() throws IOException
    {
        checkCreatedCustomerByNationality();
        System.out.println("\n######## testSuccessfulCustomerCreation Ends ########\n");
    }




    @AfterEach
    public void tearDown() {
        // Close the browser after the test
        Driver.quitDriver();
    }
}
