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

    public WebElement getLoginEmailInput() {
        return Driver.waitForElement(driver, By.id("floatingInput"), "visit", 10);
    }
    public WebElement getLoginPasswordInput() {
        return Driver.waitForElement(driver, By.id("floatingPassword"), "visit", 10);
    }
    public WebElement getLoginButton() {
        return Driver.waitForElement(driver, By.xpath("// html/body/app-root/app-login-page/app-login/div/div[1]/form/div[2]/app-button/button"), "click", 10);
    }

    public boolean waitForSpecificUrl(String expectedUrl, int timeout) {
        return Driver.waitForUrl(expectedUrl, timeout);
    }

    public String getCurrentPageUrl() {
        return Driver.getCurrentUrl();
    }

    public boolean navigateToCustomerCreate() {
        return Driver.navigateToUrlAndWait("http://localhost:4200/customer-create", 10);
    }

    // Attribute like data-test, src
    // (driver, tagName, attributeValue, actionPurpose(visit = visible, click = clickable), waitTimeSecond)
    public String getInvalidMessage() {
        return Driver.waitForElementWithAttribute(driver, By.tagName("h3"), "error", "visit", 10).getText();
    }
}
