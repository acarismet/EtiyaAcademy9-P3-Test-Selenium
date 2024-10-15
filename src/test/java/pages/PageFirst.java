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

    // From Here You Can Add Locator Finding Methods to Recall In Test

    // Single Element
    public WebElement getUsernameInput() {
        return Driver.waitForElement(driver, By.id("user-name"), "visit", 10);
    }
    public WebElement getPasswordInput() {
        return Driver.waitForElement(driver, By.id("password"), "visit", 10);
    }
    public WebElement getLoginButton() {
        return Driver.waitForElement(driver, By.id("login-button"), "click", 10);
    }

    // URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Attribute like data-test, src
    // (driver, tagName, attributeValue, actionPurpose(visit = visible, click = clickable), waitTimeSecond)
    public String getInvalidMessage() {
        return Driver.waitForElementWithAttribute(driver, By.tagName("h3"), "error", "visit", 10).getText();
    }
}
