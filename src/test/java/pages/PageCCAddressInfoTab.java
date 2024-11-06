package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;
import utilities.Constants;

import org.openqa.selenium.WebDriver;

public class PageCCAddressInfoTab {
    private WebDriver driver;

    public PageCCAddressInfoTab(WebDriver driver) { this.driver = driver; }

    // Text "There is no address"
    public String getNoAddressText() {
        return Driver.waitForElement(driver, By.cssSelector("text-2xl text-black font-semibold"), "visit", 10).getText();
    }


    // Add Address Pop-Up Inputs
    public WebElement getCityInput() {
        return Driver.waitForElement(driver, By.id("city"), "click", 10);
    }

    public WebElement getDistrictInput() {
        return Driver.waitForElement(driver, By.id("district"), "click", 10);
    }

    public WebElement getStreetInput() {
        return Driver.waitForElement(driver, By.id("street"), "click", 10);
    }

    public WebElement getHouseFlatInput() {
        return Driver.waitForElement(driver, By.id("houseFlat"), "click", 10);
    }

    public WebElement getNeighbourhoodInput() {
        return Driver.waitForElement(driver, By.id("neighbourhood"), "click", 10);
    }

    public WebElement getAddressDescriptionInput() {
        return Driver.waitForElement(driver, By.id("description"), "click", 10);
    }


    // Add Address Pop-Up Buttons
    public WebElement getSaveButton() {
        return Driver.waitForElement(driver, By.cssSelector("div.sm\\:gap-8:nth-child(3) > app-button:nth-child(2) > button:nth-child(1)"), "click", 10);
    }

    public WebElement getCancelButton() {
        return Driver.waitForElement(driver, By.cssSelector("div.sm\\:gap-8:nth-child(3) > app-button:nth-child(1) > button:nth-child(1)"), "click", 10);
    }

    public WebElement getCloseXButton() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-address-info/app-popup/div/div/button"), "click", 10);
    }



    // Navigating Buttons
    public WebElement getNextButton() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-address-info/main/div/div/div[2]/div[2]/app-button[2]/button"), "click", 10);
    }

    public WebElement getAddButton() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-address-info/main/div/div/div[2]/div[1]/app-button[2]/button"), "click", 10);
    }

    public WebElement getPreviousButton() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-address-info/main/div/div/div[2]/div[2]/app-button[1]/button"), "click", 10);
    }

    public WebElement getExitButton() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-address-info/main/div/div/div[2]/div[1]/app-button[1]/button"), "click", 10);
    }

    // Wait and Get URL
    public boolean waitForSpecificUrl(String expectedUrl, int timeout) {
        return Driver.waitForUrl(expectedUrl, timeout);
    }
    public String getCurrentPageUrl() {
        return Driver.getCurrentUrl();
    }
}
