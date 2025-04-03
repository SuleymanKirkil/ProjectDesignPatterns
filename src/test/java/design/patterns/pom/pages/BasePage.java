package design.patterns.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = Logger.getLogger(BasePage.class.getName());
    protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        PageFactory.initElements(driver, this);
        logger.info("Page initialized: " + this.getClass().getSimpleName());
    }

    // Navigation Methods
    public void navigateBack() {
        logger.info("Navigating back");
        driver.navigate().back();
    }

    public void navigateTo(String url) {
        logger.info("Navigating to URL: " + url);
        driver.get(url);
    }

    // Common Element Actions with Waits
    protected void click(WebElement element, String elementName) {
        try {
            logger.info("Clicking on element: " + elementName);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException e) {
            logger.severe("Element not clickable: " + elementName);
            throw e;
        }
    }

    protected void sendKeys(WebElement element, String text, String elementName) {
        try {
            logger.info("Sending keys to element: " + elementName + ", text: " + text);
            wait.until(ExpectedConditions.visibilityOf(element)).clear();
            element.sendKeys(text);
        } catch (TimeoutException e) {
            logger.severe("Element not visible for sending keys: " + elementName);
            throw e;
        }
    }

    protected String getText(WebElement element, String elementName) {
        try {
            logger.info("Getting text from element: " + elementName);
            return wait.until(ExpectedConditions.visibilityOf(element)).getText();
        } catch (TimeoutException e) {
            logger.severe("Element not visible for getting text: " + elementName);
            throw e;
        }
    }

    protected boolean isElementDisplayed(WebElement element, String elementName) {
        try {
            logger.info("Checking if element is displayed: " + elementName);
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            logger.warning("Element not visible: " + elementName);
            return false;
        }
    }

    protected boolean isElementEnabled(WebElement element, String elementName) {
        try {
            logger.info("Checking if element is enabled: " + elementName);
            return wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
        } catch (TimeoutException e) {
            logger.warning("Element not clickable: " + elementName);
            return false;
        }
    }

    protected boolean isElementSelected(WebElement element, String elementName) {
        try {
            logger.info("Checking if element is selected: " + elementName);
            return wait.until(ExpectedConditions.visibilityOf(element)).isSelected();
        } catch (TimeoutException e) {
            logger.warning("Element not visible for selection check: " + elementName);
            return false;
        }
    }

    protected String getAttribute(WebElement element, String attribute, String elementName) {
        try {
            logger.info("Getting attribute '" + attribute + "' from element: " + elementName);
            return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute(attribute);
        } catch (TimeoutException e) {
            logger.severe("Element not visible for getting attribute: " + elementName);
            throw e;
        }
    }

    // Wait Methods
    protected void waitForElementToBeVisible(WebElement element, String elementName) {
        try {
            logger.info("Waiting for element to be visible: " + elementName);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            logger.severe("Element not visible after waiting: " + elementName);
            throw e;
        }
    }

    protected void waitForElementToBeClickable(WebElement element, String elementName) {
        try {
            logger.info("Waiting for element to be clickable: " + elementName);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            logger.severe("Element not clickable after waiting: " + elementName);
            throw e;
        }
    }

    // Screenshot Method
    protected void takeScreenshot(String testName) {
        try {
            logger.info("Taking screenshot for test: " + testName);
            // Screenshot implementation can be added here
        } catch (Exception e) {
            logger.severe("Failed to take screenshot: " + e.getMessage());
        }
    }
} 