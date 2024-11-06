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



    // Attribute like data-test, src
    // (driver, tagName, attributeValue, actionPurpose(visit = visible, click = clickable), waitTimeSecond)
    public String getInvalidMessage() {
        return Driver.waitForElementWithAttribute(driver, By.tagName("h3"), "error", "visit", 10).getText();
    }
}
