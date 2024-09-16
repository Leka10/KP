package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LoggerUtils;
import utils.PropertiesUtils;
import utils.WebDriverUtils;

import java.time.Duration;

public abstract class BasePageClass extends LoggerUtils {

    protected WebDriver driver;

    public BasePageClass(WebDriver driver) {
        Assert.assertFalse(WebDriverUtils.hasDriverQuit(driver), "Driver instance has quit!");
        this.driver = driver;
    }

    protected String getPageUrl(String sPath) {
        log.trace("getPageUrl(" + sPath + ")");
        return PropertiesUtils.getBaseUrl() + sPath;
    }
    protected void openUrl(String url) {
        log.trace("getPageRrl(" + url + ")");
        driver.get(url);
    }
    protected boolean waitForExactUrl(String url, int timeout) {
        log.trace("waitForExactUrl(" + url + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.urlToBe(url));
    }

    protected boolean waitUntilPageIsReady(int timeout) {
        log.trace("waitUntilPageIsReady(" + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        return wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
    protected void waitUntilClickable(WebElement element) {
        log.trace("waitUntilClickable(" + element.toString() + ")");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement getWebElement(By locator) {
        log.trace("getWebElement(" + locator +")");
        return driver.findElement(locator);
    }
    protected WebElement getWebElement(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    protected void typeTextToWebElement(WebElement element, String text) {
        log.trace("GetWebElement(" + element + ", " + text + ")");
        element.sendKeys(text);
    }
    protected void clearAndTypeTextToWebElement(WebElement element, String text) {
        log.trace("GetWebElement(" + element + ", " + text + ")");
        element.clear();
        element.sendKeys(text);
    }
    protected String getAttributeFromWebElement(WebElement element, String attribute) {
        log.trace("getAttributeFromWebElement(" + element + ", " + attribute + ")");
        return element.getAttribute(attribute);
    }
    protected String getAttributeFromWebElement(WebElement element) {
        log.trace("getValueFromWebElement(" + element + ")" );
        return getAttributeFromWebElement(element, "value");
    }

    public static WebElement waitUntilElementIsVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
