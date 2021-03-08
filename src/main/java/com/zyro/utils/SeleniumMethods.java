package com.zyro.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SeleniumMethods {

    @Autowired
    public WebDriver driver;
    @Value("${url.ui-url}")
    public String startPage;
    @Autowired
    private WebDriverWait driverWait;

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Extract By (CSS or Xpath) depending on element locator format
     *
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     * @return - By CSS or Xpath depending on element locator format
     */
    @SafeVarargs
    private <T> By extractBy(String elementLocator, T... args) {
        String locator = String.format(elementLocator, args);
        if (locator.startsWith("//") || locator.startsWith("(//")) {
            return By.xpath(locator);
        } else {
            return By.cssSelector(locator);
        }
    }

    public Actions action() {
        return new Actions(driver);
    }

    /**
     * Clear all cookies from current browser
     */
    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     * @param baseURL - start of URL from application.yml file
     * @param endURL  - end of URL of needed website
     */
    public void navigateToURL(String baseURL, String endURL) {
        driver.navigate().to(baseURL + endURL);
    }

    /**
     * Get URL of currently opened website
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     * @return - returns web element
     */
    @SafeVarargs
    public final <T> WebElement findElement(String elementLocator, T... args) {
        log.info(elementLocator + " is available");
        return driver.findElement(extractBy(String.format(elementLocator, args)));
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     * @return - returns list of web elements
     */
    @SafeVarargs
    public final <T> List<WebElement> findElements(String elementLocator, T... args) {
        log.info(elementLocator + " are available");
        return driver.findElements(extractBy(String.format(elementLocator, args)));
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void waitForElementToBeVisible(String elementLocator, T... args) {
        log.info("Waiting for element: " + elementLocator);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(extractBy(String.format(elementLocator, args))));
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void waitForElementToBeInvisible(String elementLocator, T... args) {
        log.info("Waiting for element to be invisible " + elementLocator);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(extractBy(String.format(elementLocator, args))));
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void waitForElementToBeClickable(String elementLocator, T... args) {
        log.info("Waiting for element to be clickable: " + elementLocator);
        driverWait.until(ExpectedConditions.elementToBeClickable(extractBy(String.format(elementLocator, args))));
    }

    /**
     * Enters text to input field
     *
     * @param elementLocator - String locator in Xpath or CSS format
     * @param keysToSend     - text to enter in site
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void enterText(String elementLocator, String keysToSend, T... args) {
        waitForElementToBeClickable(elementLocator, args);
        deleteText(elementLocator, args);
        findElement(elementLocator, args).sendKeys(keysToSend);
    }

    /**
     * Sends path to file to input field
     *
     * @param elementLocator - String locator in Xpath or CSS format
     * @param pathToFile     - path to file
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void sendPathToFile(String elementLocator, String pathToFile, T... args) {
        findElement(elementLocator, args).sendKeys(pathToFile);
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     * @return - returns text from element
     */
    @SafeVarargs
    public final <T> String getText(String elementLocator, T... args) {
        waitForElementToBeVisible(elementLocator, args);
        log.info(findElement(elementLocator, args).getText());
        return findElement(elementLocator, args).getText();
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param attributeName  - name of attribute value is needed
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     * @return - returns value of needed attribute
     */
    @SafeVarargs
    public final <T> String getAttributeValue(String elementLocator, String attributeName, T... args) {
        waitForElementToBeVisible(elementLocator, args);
        return findElement(elementLocator, args).getAttribute(attributeName);
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void click(String elementLocator, T... args) {
        waitForElementToBeVisible(elementLocator, args);
        WebElement element = findElement(elementLocator, args);
        getCoordinates(element);
        waitForElementToBeClickable(elementLocator, args);
        log.info("Press element: " + elementLocator);
        action().click(findElement(elementLocator, args)).perform();
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void deleteText(String elementLocator, T... args) {
        log.info("Removing element: " + elementLocator);
        waitForElementToBeClickable(elementLocator, args);
        findElement(elementLocator, args).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        findElement(elementLocator, args).sendKeys(Keys.DELETE);
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void pasteText(String elementLocator, T... args) {
        deleteText(elementLocator, args);
        findElement(elementLocator, args).sendKeys(Keys.chord(Keys.CONTROL, "v"));
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void doubleClick(String elementLocator, T... args) {
        waitForElementToBeVisible(elementLocator, args);
        action().doubleClick(findElement(elementLocator, args)).perform();
    }

    /**
     * @param elementLocator - String locator in Xpath or CSS format
     * @param args           - any quantity of arguments
     * @param <T>            - is referred to as any type
     */
    @SafeVarargs
    public final <T> void mouseHover(String elementLocator, T... args) {
        Actions action = new Actions(driver);
        WebElement we = findElement(elementLocator, args);
        action.moveToElement(we).build().perform();
    }

    /**
     * Opens new tab, focuses it and navigates to URL
     *
     * @param baseURL - start of URL from application.yml file
     * @param endURL  - end of URL of needed website
     */
    public final void openURLInNewTab(String baseURL, String endURL) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        navigateToURL(baseURL, endURL);
    }

    /**
     * Closes oldest tab in browser
     */
    public final void closeTab() {
        driver.close();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    /**
     * Save session cookies to be able to navigate in website using endURLs
     */
    public void saveSessionCookies() {
        Set<Cookie> allCookies = driver.manage().getCookies();
        for (Cookie cookie : allCookies) {
            driver.manage().addCookie(cookie);
        }
    }

    private void getCoordinates(WebElement element) {
        Coordinates coordinate = ((Locatable) element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
    }

    protected void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * Simulate pressing "ENTER" on the keyboard
     */
    @SafeVarargs
    public final <T> void pressEnter(String elementLocator, T... args) {
        waitForElementToBeVisible(elementLocator, args);
        findElement(elementLocator).sendKeys(Keys.ENTER);
    }

    /**
     * Temporary, will be removed
     */
    @SafeVarargs
    public final <T> boolean isElementDisplayed(String locator, T... args) {
        try {
           log.info(locator + " is visible");
           return findElement(locator, args).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
