package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    // ####### VALID LOGIN

    public void validLogin () throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_empty_login.png", 5);
        pageLogin.getLoginEmailInput().sendKeys(Constants.loginValidEmail);
        System.out.println("Email entered => " + Constants.loginValidEmail);
        pageLogin.getLoginPasswordInput().sendKeys(Constants.loginValidPassword);
        System.out.println("Password entered => " + Constants.loginValidPassword);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_filled_login.png", 5);
        pageLogin.getLoginButton().click();

        boolean isUrlReached = pageLogin.waitForSpecificUrl(Constants.customerSearchURL, 10);
        System.out.println("URL reached: " + isUrlReached);

        String currentUrl = pageLogin.getCurrentPageUrl();
        System.out.println("Current Page URL: " + currentUrl);
        assert isUrlReached : "FAILED TO REACH EXPECTED URL";
        System.out.println("Reached URL successfully!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_logged_in.png", 5);
    }

    // ####### NAVIGATE TO CUSTOMER CREATION PAGE BY URL

    public void getToCustomerCreatePage () throws IOException {
        validLogin();
        boolean isCustomerCreateLoaded = pageLogin.navigateToCustomerCreate();
        System.out.println("Customer Create loaded: " + isCustomerCreateLoaded);
        assert isCustomerCreateLoaded : "FAILED TO LOAD CUSTOMER CREATE";
        System.out.println("Customer Create loaded successfully!" + pageLogin.getCurrentPageUrl());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_reached_empty_creation.png", 5);
    }

    // ####### FIRST TAB - CREATE DEMOGRAPHIC INFORMATION

    public void createDemographicInformation() throws IOException {
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_filled_demographic.png", 5);
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

    public void createAddressInformationFromScratch() throws IOException {
        createDemographicInformation();
        checkReachedToAddress();
        if(isReachedToAddress) {
            checkNoAddress();
            if(isNoAddress) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_empty_address.png", 5);
                pageCCAddressInfoTab.getAddButton().click();
                pageCCAddressInfoTab.getCityInput().sendKeys(Constants.customerAddressCity);
                pageCCAddressInfoTab.getDistrictInput().sendKeys(Constants.customerAddressDistrict);
                pageCCAddressInfoTab.getStreetInput().sendKeys(Constants.customerAddressStreet);
                pageCCAddressInfoTab.getHouseFlatInput().sendKeys(Constants.customerAddressHouseFlat);
                pageCCAddressInfoTab.getNeighbourhoodInput().sendKeys(Constants.customerAddressNeighbourhood);
                pageCCAddressInfoTab.getAddressDescriptionInput().sendKeys(Constants.customerAddressDescription);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_filled_address.png", 5);
                pageCCAddressInfoTab.getSaveButton().click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_ready_address.png", 5);
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

    public void createContactInformationFromScratch() throws IOException {
        createAddressInformationFromScratch();
        checkReachedToContact();
        if (isReachedToContact) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_empty_contact.png", 5);
            pageCCContactInfoTab.getEmailInput().sendKeys(Constants.customerContactEmail);
            pageCCContactInfoTab.getPhoneInput().sendKeys(Constants.customerContactPhone);
            pageCCContactInfoTab.getFaxInput().sendKeys(Constants.customerContactFax);
            pageCCContactInfoTab.getHomePhoneInput().sendKeys(Constants.customerContactHomePhone);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_filled_contact.png", 5);
            pageCCContactInfoTab.getSaveButton().click();
        } else {
            System.out.println("User couldn't be redirected to the Contact Information Tab");
        }
    }

    boolean isReachedToCustomerSearch = false;
    public void checkReachedToCustomerSearch() {
        isReachedToCustomerSearch = pageCustomerSearch.waitForSpecificUrl(Constants.customerSearchURL, 10);
        System.out.println("URL reached: " + isReachedToCustomerSearch);

        String currentUrl = pageCustomerSearch.getCurrentPageUrl();
        System.out.println("Current Page URL: " + currentUrl);
    }

    public void checkCreatedCustomerByNationality() throws IOException {
        createContactInformationFromScratch();
        checkReachedToCustomerSearch();
        if (isReachedToCustomerSearch) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_reached_customer_search.png", 5);
            pageCustomerSearch.getNationalIDInput().sendKeys(Constants.customerNationalityID);
            pageCustomerSearch.getSearchButton().click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Driver.takeScreenshot(driver, "../screenshots/screenshots_4_create_customer", "test_1_CustomerCreate_searched_customer.png", 5);
            pageCustomerSearch.elementWithSpecificText();
            WebElement NatIdWithSpecificText = pageCustomerSearch.getNationalIDInput();
            if (NatIdWithSpecificText != null) {
                System.out.println("Created Customer Found: " + NatIdWithSpecificText.getText());
            } else {
                System.out.println("Element with specified text not found.");
            }
        } else {
            System.out.println("User couldn't be redirected to the Customer Search Page");
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
