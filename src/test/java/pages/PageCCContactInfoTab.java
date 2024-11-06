package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;
import utilities.Constants;

import org.openqa.selenium.WebDriver;

public class PageCCContactInfoTab {
    private WebDriver driver;

    public PageCCContactInfoTab(WebDriver driver) {this.driver = driver;}

    // Contact Info Inputs
    public WebElement getEmailInput() {
        return Driver.waitForElement(driver, By.id("email"), "click", 10);
    }

    public WebElement getPhoneInput() {
        return Driver.waitForElement(driver, By.id("phone"), "click", 10);
    }

    public WebElement getFaxInput() {
        return Driver.waitForElement(driver, By.id("fax"), "click", 10);
    }

    public WebElement getHomePhoneInput() {
        return Driver.waitForElement(driver, By.id("homePhone"), "click", 10);
    }

    // Navigating Buttons
    public WebElement getSaveButton() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-customer-create/main/div/div/form/div[5]/app-button[3]/button"), "click", 10);
    }

    public WebElement getPreviousButton() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-customer-create/main/div/div/form/div[5]/app-button[2]/button"), "click", 10);
    }

    public WebElement getExitButton() {
        return Driver.waitForElement(driver, By.xpath("/html/body/app-root/app-customer-create/main/div/div/form/div[5]/app-button[1]/button"), "click", 10);
    }



    // Wait and Get URL
    public boolean waitForSpecificUrl(String expectedUrl, int timeout) {
        return Driver.waitForUrl(expectedUrl, timeout);
    }
    public String getCurrentPageUrl() {
        return Driver.getCurrentUrl();
    }
}
