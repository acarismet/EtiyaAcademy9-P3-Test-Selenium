package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utilities.Constants;
import utilities.Driver;

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

    // ####### VALID LOGIN
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

    // ####### NAVIGATE TO CUSTOMER CREATION PAGE BY URL
    public void getToCustomerCreatePage () {
        validLogin();
        boolean isCustomerCreateLoaded = pageLogin.navigateToCustomerCreate();
        System.out.println("Customer Create loaded: " + isCustomerCreateLoaded);
        assert isCustomerCreateLoaded : "FAILED TO LOAD CUSTOMER CREATE";
        System.out.println("Customer Create loaded successfully!" + pageLogin.getCurrentPageUrl());
    }

    // ####### FIRST TAB - CREATE DEMOGRAPHIC INFORMATION
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

    // ####### CHECK NO ADDRESS IN ADDRESS INFO TAB
    boolean isNoAddress = false;
    public void checkNoAddress() {
        assertEquals(Constants.noAddressText, pageCCAddressInfoTab.getNoAddressText(), "There is already address for this customer");
        isNoAddress = true;
    }

    // ####### CHECK ADDRESS INFO TAB URL
    boolean isReachedToAddress = false;
    public void checkReachedToAddress() {
        isReachedToAddress = pageCCAddressInfoTab.waitForSpecificUrl(Constants.customerSearchURL, 10);
        System.out.println("URL reached: " + isReachedToAddress);

        String currentUrl = pageCCAddressInfoTab.getCurrentPageUrl();
        System.out.println("Current Page URL: " + currentUrl);
    }

    // ####### SECOND TAB - CREATE ADDRESS INFORMATION
    public void createAddressInformationFromScratch() {
        createDemographicInformation();
        if(isReachedToAddress) {
            if(isNoAddress) {
                pageCCAddressInfoTab.getAddButton().click();
                pageCCAddressInfoTab.getCityInput().sendKeys(Constants.customerAddressCity);
                pageCCAddressInfoTab.getDistrictInput().sendKeys(Constants.customerAddressDistrict);
                pageCCAddressInfoTab.getStreetInput().sendKeys(Constants.customerAddressStreet);
                pageCCAddressInfoTab.getHouseFlatInput().sendKeys(Constants.customerAddressHouseFlat);
                pageCCAddressInfoTab.getNeighbourhoodInput().sendKeys(Constants.customerAddressNeighbourhood);
                pageCCAddressInfoTab.getAddressDescriptionInput().sendKeys(Constants.customerAddressDescription);
                pageCCAddressInfoTab.getSaveButton().click();
                pageCCAddressInfoTab.getNextButton().click();
            } else {
                System.out.println("There shouldn't be any address for the new customer");
            }
        } else {
            System.out.println("User couldn't be redirected to the Address Information Tab");
        }

    }

    // ####### CHECK CONTACT INFO TAB URL
    boolean isReachedToContact = false;
    public void checkReachedToContact() {
        isReachedToContact = pageCCContactInfoTab.waitForSpecificUrl(Constants.customerSearchURL, 10);
        System.out.println("URL reached: " + isReachedToContact);

        String currentUrl = pageCCContactInfoTab.getCurrentPageUrl();
        System.out.println("Current Page URL: " + currentUrl);
    }

    // ####### THIRD TAB - CREATE CONTACT INFORMATION
    public void createContactInformationFromScratch() {
        createAddressInformationFromScratch();
        if (isReachedToContact) {
            pageCCContactInfoTab.getEmailInput().sendKeys(Constants.customerContactEmail);
            pageCCContactInfoTab.getPhoneInput().sendKeys(Constants.customerContactPhone);
            pageCCContactInfoTab.getFaxInput().sendKeys(Constants.customerContactFax);
            pageCCContactInfoTab.getHomePhoneInput().sendKeys(Constants.customerContactHomePhone);
            pageCCContactInfoTab.getSaveButton().click();
        } else {
            System.out.println("User couldn't be redirected to the Contact Information Tab");
        }
    }

    boolean isReachedToCustomerSearch = false;
    public void checkReachedToCustomerSearch() {

    }

    public void checkCreatedCustomerByNationality() {
        createDemographicInformation();
        System.out.println("User couldn't be redirected to the Customer Search Page");
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
