package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;
import utilities.Constants;

public class PageFirst {
    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public PageFirst(WebDriver driver) {
        this.driver = driver;
    }


    // Login
    public WebElement getLoginEmailInput() {
        return Driver.waitForElement(driver, By.id("floatingInput"), "visit", 10);
    }
    public WebElement getLoginPasswordInput() {
        return Driver.waitForElement(driver, By.id("floatingPassword"), "visit", 10);
    }
    public WebElement getLoginButton() {
        return Driver.waitForElement(driver, By.xpath("// html/body/app-root/app-login-page/app-login/div/div[1]/form/div[2]/app-button/button"), "click", 10);
    }

    // Wait and Get URL
    public boolean waitForSpecificUrl(String expectedUrl, int timeout) {
        return Driver.waitForUrl(expectedUrl, timeout);
    }
    public String getCurrentPageUrl() {
        return Driver.getCurrentUrl();
    }

    // Navigating URLs
    public boolean navigateToCustomerCreate() {
        return Driver.navigateToUrlAndWait("http://localhost:4200/customer-create", 10);
    }

    // Demographic Information Inputs
    public WebElement getFirstNameInput() {
        return Driver.waitForElement(driver, By.id("firstName"), "click", 10);
    }

    public WebElement getMiddleNameInput() {
        return Driver.waitForElement(driver, By.id("middleName"), "click", 10);
    }

    public WebElement getLastNameInput() {
        return Driver.waitForElement(driver, By.id("lastName"), "click", 10);
    }

    public WebElement getBirthDateInput() {
        return Driver.waitForElement(driver, By.id("birthDate"), "click", 10);
    }

    public WebElement getGenderDropdown() {
        return Driver.waitForElement(driver, By.id("gender"), "click", 10);
    }

    // Gender Options
    public WebElement getMaleOption() {
        return Driver.waitForMultipleElements(driver, By.cssSelector("#gender option[value='M']"), 10).get(0);
    }
    public WebElement getOtherOption() {
        return Driver.waitForMultipleElements(driver, By.cssSelector("#gender option[value='O']"), 10).get(0);
    }
    public WebElement getFemaleOption() {
        return Driver.waitForMultipleElements(driver, By.cssSelector("#gender option[value='F']"), 10).get(0);
    }


    public WebElement getFatherNameInput() {
        return Driver.waitForElement(driver, By.id("fatherName"), "click", 10);
    }

    public WebElement getMotherNameInput() {
        return Driver.waitForElement(driver, By.id("motherName"), "click", 10);
    }

    public WebElement getNationalityIdInput() {
        return Driver.waitForElement(driver, By.id("nationalityId"), "click", 10);
    }

    // Radio Buttons For Nationality
    public WebElement getRadioTurkish() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-customer-create/main/div/div/div[1]/div/label[1]/input"), "click", 10);
    }
    public WebElement getRadioOther() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-customer-create/main/div/div/div[1]/div/label[2]/input"), "click", 10);
    }


    // Navigating Buttons
    public WebElement getNextButton() {
        return Driver.waitForElement(driver, By.cssSelector(".justify-around > app-button:nth-child(2) > button:nth-child(1)"), "click", 10);
    }

    public WebElement getExitButton() {
        return Driver.waitForElement(driver, By.cssSelector(".justify-around > app-button:nth-child(1) > button:nth-child(1)"), "click", 10);
    }

    // Attribute like data-test, src
    // (driver, tagName, attributeValue, actionPurpose(visit = visible, click = clickable), waitTimeSecond)
    public String getInvalidMessage() {
        return Driver.waitForElementWithAttribute(driver, By.tagName("h3"), "error", "visit", 10).getText();
    }
}
