package Common;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

/**
 * Utilities
 * Common WebDriver helper methods
 */
public class Utilities {

    // ===== WAIT =====
    public static WebElement waitForVisible(By locator, int timeoutSeconds) {
        WebDriverWait wait =
                new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator, int timeoutSeconds) {
        WebDriverWait wait =
                new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForVisible(By locator) {
        WebDriverWait wait =
                new WebDriverWait(
                        Constant.WEBDRIVER,
                        Duration.ofSeconds(Constant.DEFAULT_TIMEOUT)
                );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        WebDriverWait wait =
                new WebDriverWait(
                        Constant.WEBDRIVER,
                        Duration.ofSeconds(Constant.DEFAULT_TIMEOUT)
                );
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    // ===== ACTIONS =====
    public static void click(By locator) {
        waitForVisible(locator);
        scrollToElement(getElement(locator));
        waitForClickable(locator);
        getElement(locator).click();
    }

    public static void type(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    public static void clickByJS(By locator) {
        WebElement element = getElement(locator);
        JavascriptExecutor js =
                (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", element);
    }


    // ===== GET DATA =====
    public static String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    public static String getAttribute(By locator, String attributeName) {
        return waitForVisible(locator).getAttribute(attributeName);
    }

    public static WebElement getElement(By locator) {
        return waitForVisible(locator);
    }

    public static List<WebElement> getElements(By locator) {
        return Constant.WEBDRIVER.findElements(locator);
    }


    // ===== ELEMENT FROM PARENT =====
    public static WebElement getElementFrom(WebElement parent, By childLocator) {
        return parent.findElement(childLocator);
    }

    public static List<WebElement> getElementsFrom(WebElement parent, By childLocator) {
        return parent.findElements(childLocator);
    }


    // ===== UTILITIES =====
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js =
                (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public static boolean isElementDisplayed(By locator) {
        try {
            return Constant.WEBDRIVER.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean waitForTextPresent(By locator,
                                             String expectedText,
                                             int timeoutSeconds) {
        WebDriverWait wait =
                new WebDriverWait(
                        Constant.WEBDRIVER,
                        Duration.ofSeconds(timeoutSeconds)
                );
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(locator, expectedText)
        );
    }

    public static WebElement waitFluentElement(By locator) {
        Wait<WebDriver> waitFluent =
                new FluentWait<>(Constant.WEBDRIVER)
                        .withTimeout(Duration.ofSeconds(Constant.DEFAULT_TIMEOUT))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(NoSuchElementException.class);

        return waitFluent.until(driver -> driver.findElement(locator));
    }


    // ===== CLEAR / TYPE =====
    public static void clearAndType(WebElement element, String content) {
        element.clear();
        element.sendKeys(content);
    }

    public static void clearAndType(By locator, String content) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(content);
    }

    public static void clearElement(WebElement element) {
        element.clear();
    }


    // ===== BROWSER =====
    public static void refreshWindow() {
        Constant.WEBDRIVER.navigate().refresh();
    }

    public static void getUrl(String url) {
        Constant.WEBDRIVER.get(url);
    }
}
