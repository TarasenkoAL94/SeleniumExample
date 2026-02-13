package com.exampleautomation.utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static com.exampleautomation.utilities.Waiter.Timeout.DEFAULT_TIME_OUT;

public class Waiter {
    /**
     * Standard timeouts in seconds
     */
    public enum Timeout {
        NOMINAL_TIME_OUT(Duration.ofSeconds(1)),
        MINIMUM_TIME_OUT(Duration.ofSeconds(5)),
        LOADING_TIME_OUT(Duration.ofSeconds(15)),
        DEFAULT_TIME_OUT(Duration.ofSeconds(30)),
        AVERAGE_TIME_OUT(Duration.ofSeconds(60)),
        EXTENDED_TIME_OUT(Duration.ofSeconds(100)),
        MAXIMUM_TIME_OUT(Duration.ofSeconds(180));

        private final Duration duration;

        Timeout(Duration duration) {
            this.duration = duration;
        }

        public Duration getDuration() {
            return duration;
        }

        @Override
        public String toString() {
            return "Timeout [" + name() +
                    "] seconds = " + duration;
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(Waiter.class);

    /**
     * Waits for the element to be clickable, returning the element.
     * see {@link Waiter#waitForElementToBeClickable(WebDriver,WebElement)} for method with internally specified timeout
     *
     * @param element The WebElement object
     * @param timeout Timeout from {@link Timeout}
     *
     * @return the WebElement once it is located and clickable (visible and enabled), null if element was not found, or not clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, Timeout timeout) {
        LOG.debug("Wait for element to be clickable with locator: '{}' for {} seconds", element, timeout);
        return (new WebDriverWait(driver, timeout.getDuration())
                .until(ExpectedConditions.elementToBeClickable(element)));
    }

    /**
     * Waits for the element to be clickable, returning the element.
     * see {@link Waiter#waitForElementToBeClickable(WebDriver,By)} for method with internally specified timeout
     *
     * @param element The By locator of the element
     * @param timeout Timeout from {@link Timeout}
     *
     * @return the WebElement once it is located and clickable (visible and enabled), null if element was not found, or not clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By element, Timeout timeout) {
        LOG.debug("Wait for element to be clickable with locator: '{}' for {} seconds", element, timeout);
        return (new WebDriverWait(driver, timeout.getDuration())
                .until(ExpectedConditions.elementToBeClickable(element)));
    }

    /**
     * Waits for the element to be clickable, returning the element.
     * See {@link Waiter#waitForElementToBeClickable(WebDriver, WebElement, Timeout)} for method where you can specify another timeout
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param element The WebElement object
     * @param driver The WebDriver object
     *
     * @return the WebElement once it is located and clickable (visible and enabled), null if element was not found, or not clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        return waitForElementToBeClickable(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the element to be clickable, returning the element.
     * See {@link Waiter#waitForElementToBeClickable(WebDriver, By, Timeout)} for method where you can specify another timeout
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param element The By locator of the element
     * @param driver WebDriver object
     *
     * @return the WebElement once it is located and clickable (visible and enabled), null if element was not found, or not clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By element) {
        return waitForElementToBeClickable(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the element to be visible, returning the element.
     * see {@link Waiter#waitForElementToBeVisible(WebDriver,WebElement)} for method with internally specified timeout
     *
     * @param driver The WebDriver object
     * @param element The WebElement object
     * @param timeout Timeout from {@link Timeout}
     *
     * @return the (same) WebElement once it is visible, null if element was not found, or not visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver,WebElement element, Timeout timeout) {
        LOG.debug("Wait for element to be visible with locator: '{}' for {} seconds", element, timeout);
        return (new WebDriverWait(driver, timeout.getDuration())
                .until(ExpectedConditions.visibilityOf(element)));
    }

    /**
     * Waits for the element to be visible, returning the element.
     * see {@link Waiter#waitForElementToBeVisible(WebDriver,By)} for method with internally specified timeout
     *
     * @param driver WebDriver object
     * @param element The By locator of the element
     * @param timeout Timeout from {@link Timeout}
     *
     * @return the (same) WebElement once it is visible, null if element was not found, or not visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By element, Timeout timeout) {
        LOG.debug("Wait for element to be displayed with locator: '{}' for {} seconds", element, timeout);
        return (new WebDriverWait(driver, timeout.getDuration()))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /**
     * Waits for the element to be visible, returning the element.
     * see {@link Waiter#waitForElementToBeVisible(WebDriver, WebElement, Timeout)} for method where you can specify another timeout
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param driver The WebDriver object
     * @param element The WebElement object
     *
     * @return the (same) WebElement once it is visible, null if element was not found, or not visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
        return waitForElementToBeVisible(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the element to be visible, returning the element.
     * see {@link Waiter#waitForElementToBeVisible(WebDriver, By, Timeout)} for method where you can specify another timeout
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     *
     * @return the (same) WebElement once it is visible, null if element was not found, or not visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By element) {
        return waitForElementToBeVisible(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the elements to be visible, returning the elements list.
     *
     * @param driver The LWebDriver objects
     * @param elements The List of WebElement objects
     * @param timeout  Timeout from {@link Timeout}
     *
     * @return the list of WebElements once they are located, empty list if elements were not found, or not visible
     */
    public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, List<WebElement> elements, Timeout timeout) {
        LOG.debug("Wait for all elements to be displayed with locator: '{}' for {} seconds", elements, timeout);
        return (new WebDriverWait(driver, timeout.getDuration()))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Waits for the elements to be visible, returning the elements list.
     *
     * @param driver The WebDriver object
     * @param elements The array of WebElement objects or elements divided with coma, like (timeout, element1, element2, element3)
     * @param timeout  Timeout from {@link Timeout}
     *
     * @return the list of WebElements once they are located, empty list if elements were not found, or not visible
     */
    public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, Timeout timeout, WebElement... elements) {
        LOG.debug("Wait for all elements to be displayed with locator: '{}' for {} seconds", elements, timeout);
        return (new WebDriverWait(driver, timeout.getDuration()))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Waits for the elements to be visible, returning the elements list.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param driver The WebDriver object
     * @param elements The List of WebElement objects
     *
     * @return the list of WebElements once they are located, empty list if elements were not found, or not visible
     */
    public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, List<WebElement> elements) {
        return waitForAllElementsToBeVisible(driver, elements, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the elements to be visible, returning the elements list.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param driver The WebDriver object
     * @param elements The array of WebElement objects or elements divided with coma, like (element1, element2, element3)
     *
     * @return the list of WebElements once they are located, empty list if elements were not found, or not visible
     */
    public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, WebElement... elements) {
        return waitForAllElementsToBeVisible(driver, DEFAULT_TIME_OUT, elements);
    }

    /**
     * Waits for the elements to be visible, returning the elements list.
     *
     * @param driver The WebDriver object
     * @param element The common locator of elements (one or more)
     * @param timeout Timeout from {@link Timeout}
     *
     * @return the list of WebElements once they are located, empty list if elements were not found, or not visible
     */
    public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, By element, Timeout timeout) {
        LOG.debug("Wait for all elements to be displayed with locator: '{}' for {} seconds", element, timeout);
        return (new WebDriverWait(driver, timeout.getDuration()))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    /**
     * Waits for the elements to be visible, returning the elements list.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param driver The WebDriver object
     * @param element The common locator of elements (one or more)
     *
     * @return the list of WebElements once they are located, empty list if elements were not found, or not visible
     */
    public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, By element) {
        return waitForAllElementsToBeVisible(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the element to be invisible, returning Boolean.
     *
     * @param driver The WebDriver object
     * @param element The WebElement object
     * @param timeout Timeout from {@link Timeout}, see {@link Waiter#waitForElementToBeInvisible(WebDriver,WebElement)} for method with internally specified timeout
     *
     * @return Boolean true when element is not visible anymore, false otherwise
     */
    public static Boolean waitForElementToBeInvisible(WebDriver driver, WebElement element, Timeout timeout) {
        LOG.debug("Wait for element to NOT be displayed with locator: '{}' for {} seconds", element, timeout);
        return (new WebDriverWait(driver, timeout.getDuration()))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Waits for the element to be invisible, returning Boolean.
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     * @param timeout Timeout from {@link Timeout}, see {@link Waiter#waitForElementToBeInvisible(WebDriver,By)} for method with internally specified timeout
     *
     * @return Boolean true when element is not visible anymore, false otherwise
     */
    public static Boolean waitForElementToBeInvisible(WebDriver driver, By element, Timeout timeout) {
        LOG.debug("Wait for element to NOT be displayed with locator: '{}' for {} seconds", element, timeout);
        return (new WebDriverWait(driver, timeout.getDuration()))
                .until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    /**
     * Waits for the element to be invisible, returning Boolean.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param driver The WebDriver object
     * @param element The WebElement object
     *
     * @return Boolean true when element is not visible anymore, false otherwise
     */
    public static Boolean waitForElementToBeInvisible(WebDriver driver, WebElement element) {
        return waitForElementToBeInvisible(driver, element, DEFAULT_TIME_OUT);

    }

    /**
     * Waits for the element to be invisible, returning Boolean.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     *
     * @return Boolean true when element is not visible anymore, false otherwise
     */
    public static Boolean waitForElementToBeInvisible(WebDriver driver, By element) {
        return waitForElementToBeInvisible(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the elements to be invisible, returning Boolean.
     *
     * @param driver The WebDriver object
     * @param elements The list of WebElements
     * @param timeout  Timeout from {@link Timeout}
     *
     * @return Boolean true when all elements are not visible anymore, false otherwise
     */
    public static Boolean waitForElementsToBeInvisible(WebDriver driver, List<WebElement> elements, Timeout timeout) {
        return (new WebDriverWait(driver, timeout.getDuration()))
                .until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    /**
     * Waits for the elements to be invisible, returning Boolean.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     *
     * @param driver The WebDriver object
     * @param elements The list of WebElements
     *
     * @return Boolean true when all elements are not visible anymore, false otherwise
     */
    public static Boolean waitForElementsToBeInvisible(WebDriver driver, List<WebElement> elements) {
        return waitForElementsToBeInvisible(driver, elements, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the element to be present, returning the element.
     * see {@link Waiter#waitForElementToBePresent(WebDriver,By)} for method with internally specified timeout
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     * @param timeout Timeout from {@link Timeout}
     *
     * @return the WebElement once it is present, null if element was not found
     */
    public static WebElement waitForElementToBePresent(WebDriver driver, By element, Timeout timeout) {
        LOG.debug("Wait for element to be present with locator: '{}' for {} seconds", element, timeout);
        return (new WebDriverWait(driver, timeout.getDuration())
                .until(ExpectedConditions.presenceOfElementLocated(element)));
    }

    /**
     * Waits for the element to be present, returning the element.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     * see {@link Waiter#waitForElementToBePresent(WebDriver, By, Timeout)} for method where you can specify another timeout
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     *
     * @return the WebElement once it is present, null if element was not found
     */
    public static WebElement waitForElementToBePresent(WebDriver driver, By element) {
        return waitForElementToBePresent(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the elements to be present, returning the list of elements.
     * see {@link Waiter#waitForAllElementsToBePresent(WebDriver,By)} for method with internally specified timeout
     *
     * @param element The By locator of the element
     * @param driver The WebDriver  object
     * @param timeout Timeout from {@link Timeout}
     *
     * @return the WebElement once it is present, empty list if elements were not found
     */
    public static List<WebElement> waitForAllElementsToBePresent(WebDriver driver, By element, Timeout timeout) {
        return (new WebDriverWait(driver, timeout.getDuration()))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    /**
     * Waits for the element to be present, returning the list of element.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     * see {@link Waiter#waitForAllElementsToBePresent(WebDriver,By, Timeout)} for method where you can specify another timeout
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     *
     * @return the WebElement List once the elements present, empty list if elements were not found
     */
    public static List<WebElement> waitForAllElementsToBePresent(WebDriver driver, By element) {
        return waitForAllElementsToBePresent(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the element to be not present, returning the Boolean.
     *
     * @param driver The WebDriver object
     * @param element The WebElement object
     * @param timeout Timeout from {@link Timeout}, see {@link Waiter#waitForElementToBeNotPresent(WebDriver,WebElement)} for method with internally specified timeout
     *
     * @return false if the element is still attached to the DOM, true otherwise.
     */
    public static Boolean waitForElementToBeNotPresent(WebDriver driver, WebElement element, Timeout timeout) {
        return (new WebDriverWait(driver, timeout.getDuration())
                .until(ExpectedConditions.stalenessOf(element)));
    }

    /**
     * Waits for the element to be not present, returning the Boolean.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     * see {@link Waiter#waitForElementToBeNotPresent(WebDriver, WebElement, Timeout)} for method with internally specified timeout
     *
     * @param driver The WebDriver object
     * @param element The WebElement object
     *
     * @return false if the element is still attached to the DOM, true otherwise.
     */
    public static Boolean waitForElementToBeNotPresent(WebDriver driver, WebElement element) {
        return waitForElementToBeNotPresent(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the element to be selected, returning Boolean.
     * Make sure the locator actually locates an input for valid result.
     * see {@link Waiter#waitForElementToBeSelected(WebDriver, By)} for method with internally specified timeout
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     * @param timeout Timeout from {@link Timeout}
     *
     * @return true if the element is selected, false otherwise
     */
    public static Boolean waitForElementToBeSelected(WebDriver driver, By element, Timeout timeout) {
        LOG.debug("Wait for element to be present with locator: '{}' for {} seconds", element, timeout);
        return new WebDriverWait(driver, timeout.getDuration())
                .until(ExpectedConditions.elementToBeSelected(element));
    }

    /**
     * Waits for the element to be selected, returning Boolean.
     * Make sure the locator actually locates an input for valid result.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     * see {@link Waiter#waitForElementToBeSelected(WebDriver, By, Timeout)} for method where you can specify another timeout
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     *
     * @return true if the element is selected, false otherwise
     */
    public static Boolean waitForElementToBeSelected(WebDriver driver, By element) {
        return waitForElementToBeSelected(driver, element, DEFAULT_TIME_OUT);
    }

    /**
     * Waits for the element to be deselected, returning Boolean.
     * Make sure the locator actually locates an input for valid result.
     * see {@link Waiter#waitForElementToBeNotSelected(WebDriver, By)} for method with internally specified timeout
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     * @param timeout Timeout from {@link Timeout}
     *
     * @return false if the element is selected, true otherwise
     */
    public static Boolean waitForElementToBeNotSelected(WebDriver driver, By element, Timeout timeout) {
        LOG.debug("Wait for element to be present with locator: '{}' for {} seconds", element, timeout);
        return new WebDriverWait(driver, timeout.getDuration())
                .until(ExpectedConditions.elementSelectionStateToBe(element, false));
    }

    /**
     * Waits for the element to be deselected, returning Boolean.
     * Make sure the locator actually locates an input for valid result.
     * The default timeout is {@link Timeout#DEFAULT_TIME_OUT}
     * see {@link Waiter#waitForElementToBeNotSelected(WebDriver, By, Timeout)} for method where you can specify another timeout
     *
     * @param driver The WebDriver object
     * @param element The By locator of the element
     *
     * @return false if the element is selected, true otherwise
     */
    public static Boolean waitForElementToBeNotSelected(WebDriver driver, By element) {
        return waitForElementToBeNotSelected(driver, element, DEFAULT_TIME_OUT);
    }
}