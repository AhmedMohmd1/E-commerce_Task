package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
import utils.ScreenshotUtils;
import utils.LoggerUtil;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    // Fluent interface methods
    protected WebElement smartWait(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitTillPresence(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void WaitUntilVisibleElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }

    protected BasePage clickElement(By locator) {
        try {
            highlightElement(locator);
            smartWait(locator).click();
            LoggerUtil.info("Clicked element: " + locator.toString());
        } catch (Exception e) {
            ScreenshotUtils.captureScreenshot(driver, "click_failure");
            throw new RuntimeException("Failed to click element: " + locator, e);
        }
        return this;
    }

    protected BasePage enterText(By locator, String text) {
        try {
            WebElement element = smartWait(locator);
            highlightElement(locator);
            element.clear();
            element.sendKeys(text);
            LoggerUtil.info("Entered text '" + text + "' in element: " + locator.toString());
        } catch (Exception e) {
            ScreenshotUtils.captureScreenshot(driver, "text_entry_failure");
            throw new RuntimeException("Failed to enter text in element: " + locator, e);
        }
        return this;
    }

    // Advanced wait strategies
    protected WebElement waitForElementWithCustomCondition(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(driver -> {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed() && element.isEnabled() ? element : null;
        });
    }

    public void scrollTillVisible(By element) {
        WebElement webElement = driver.findElement(element);
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public void waitUntilVisibleElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    // Element highlighting for debugging
    private void highlightElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            Thread.sleep(100);
            js.executeScript("arguments[0].style.border=''", element);
        } catch (Exception e) {
            // Silently handle highlighting failures
        }
    }
}
