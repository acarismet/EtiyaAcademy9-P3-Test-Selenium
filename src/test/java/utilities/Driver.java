package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.NoSuchElementException;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            String headless = ConfigReader.getProperty("headless");

            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    FirefoxOptions options = new FirefoxOptions();
                    if ("1".equals(headless)) {
                        options.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(options);
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid browser specified in config.properties");
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    // For Single Element
    public static WebElement waitForElement(WebDriver driver, By locator, String action, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = null;
        switch (action.toLowerCase()) {
            case "visit":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            case "click":
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            default:
                throw new IllegalArgumentException("Invalid wait action specified");
        }
        return element;
    }

    public static boolean waitForUrl(String expectedUrl, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // For multiple elements
    public static List<WebElement> waitForMultipleElements(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // For an element with a specific attribute (data-test, src, formcontrolname)
    public static WebElement waitForElementWithAttribute(WebDriver webDriver, By locator, String attributeValue, String action, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeout));
        return wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            // Check if the element has the specified attribute (data-test, formcontrolname, or src)
            if (element.getAttribute("data-test") != null && element.getAttribute("data-test").equals(attributeValue) ||
                    element.getAttribute("formcontrolname") != null && element.getAttribute("formcontrolname").equals(attributeValue) ||
                    element.getAttribute("src") != null && element.getAttribute("src").equals(attributeValue)) {
                return element;
            }
            return null; // If not found, return null to keep waiting
        });
    }


    // To Get Element With Specific Text
    public static WebElement waitForElementWithText(WebDriver driver, By locator, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(driverInstance -> {
            List<WebElement> elements = driverInstance.findElements(locator);
            for (WebElement element : elements) {
                if (element.getText().equals(text)) {
                    return element;
                }
            }
            return null; // Continue waiting if no element with the specified text is found
        });
    }



    // Go to specific url and wait
    public static boolean navigateToUrlAndWait(String url, int timeout) {
        driver.get(url);
        return waitForUrl(url, timeout);
    }

    public static void clickElement(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 saniye bekle
            wait.until(ExpectedConditions.elementToBeClickable(element)); // Element tıklanabilir olana kadar bekle
            element.click();
            System.out.println("Element clicked successfully.");
        } catch (NoSuchElementException e) {
            System.out.println("Element not found.");
        } catch (WebDriverException e) {
            System.out.println("Normal click failed: " + e.getMessage() + ". JavaScript will try clicking.");
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
                System.out.println("Clicking with JavaScript was successful.");
            } catch (Exception jsException) {
                System.out.println("The JavaScript click also failed: " + jsException.getMessage());
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    public static void takeScreenshot(WebDriver driver, String directory, String fileName, int waitTime) throws IOException {
        new File(directory).mkdirs();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(directory + File.separator + fileName));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
