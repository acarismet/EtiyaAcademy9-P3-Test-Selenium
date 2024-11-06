package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;
import utilities.Constants;

public class PageCustomerSearch {
    private WebDriver driver;

    public PageCustomerSearch(WebDriver driver) {this.driver = driver;}

//    public WebElement getNationalIDInput(String formControlName) {
//        return Driver.waitForElementWithAttribute(driver, By.tagName("input"), formControlName, "visit", 10);
//    }
    public WebElement getNationalIDInput() {
        return Driver.waitForElementWithAttribute(driver, By.id("floatingInput"), "nationalityId", "visit", 10);
    }

    // Navigating Buttons
    public WebElement getSearchButton() {
        return Driver.waitForElement(driver, By.cssSelector("div.flex:nth-child(15) > app-button:nth-child(2) > button:nth-child(1)"), "click", 10);
    }

    public WebElement getCancelButton() {
        return Driver.waitForElement(driver, By.cssSelector("div.flex:nth-child(15) > app-button:nth-child(1) > button:nth-child(1)"), "click", 10);
    }


    public WebElement elementWithSpecificText() {
        return Driver.waitForElementWithText(driver, By.cssSelector("td.px-4:nth-child(1)"), Constants.customerNationalityID, 10);
    }




    // Wait and Get URL
    public boolean waitForSpecificUrl(String expectedUrl, int timeout) {
        return Driver.waitForUrl(expectedUrl, timeout);
    }
    public String getCurrentPageUrl() {
        return Driver.getCurrentUrl();
    }
}
