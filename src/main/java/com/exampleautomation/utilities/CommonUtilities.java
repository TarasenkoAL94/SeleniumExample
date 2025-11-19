package com.exampleautomation.utilities;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CommonUtilities {

    public static final Logger LOG = LoggerFactory.getLogger(CommonUtilities.class);

    public static boolean isElementPresent(WebDriver driver, By locator){
        LOG.info("Check if element is present by locator[{}]", locator);
        List<WebElement> results = new ArrayList<>();
        try{
            results = driver.findElements(locator);
        }catch (NoSuchElementException | StaleElementReferenceException e){
            LOG.info("Not found any elements by locator[{}]", locator);
        }
        for(WebElement result : results){
            LOG.debug("Found element of class [{}] and text [{}]", result.getAttribute("class"), result.getText());
        }
        return !results.isEmpty();
    }

    public static boolean isElementVisible(WebDriver driver, By locator){
        if(!isElementPresent(driver, locator)){
            return false;
        }
        WebElement element = driver.findElement(locator);
        scrollUntilElementIsFullyVisible(driver, element);
        try {
            return element.isDisplayed();
        } catch (StaleElementReferenceException e){
            return false;
        }
    }


    public static void scrollUntilElementIsFullyVisible(WebDriver driver, WebElement element){
        LOG.debug("Scroll until element [{}] is as close to the center of the screen as possible", element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String scrollToElementIntoMiddle = "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})";
        executor.executeScript(scrollToElementIntoMiddle, element);
    }
    /**
     * Replace "%s" in locator with the arguments to generate a new locator
     *
     * @param origLocator
     * @param args
     *
     * @return
     */
    public static By getDynamicLocator(By origLocator, Object... args) {
        if (args.length < 1) {
            return origLocator;
        }
        String xPathPrefix = "By.xpath:";
        String cssSelectorPrefix = "By.cssSelector:";
        String idPrefix = "By.id:";

        String origLocatorStr = origLocator.toString();

        if (origLocatorStr.startsWith(xPathPrefix)) {
            String xpathExpression = origLocatorStr.replace(xPathPrefix, "");
            xpathExpression = String.format(xpathExpression, args).trim();
            LOG.trace("xpath: " + xpathExpression);
            return By.xpath(xpathExpression);
        }
        if (origLocatorStr.startsWith(cssSelectorPrefix)) {
            String selector = origLocatorStr.replace(cssSelectorPrefix, "");
            selector = String.format(selector, args).trim();
            LOG.trace("cssSelector: " + selector);
            return By.cssSelector(selector);
        }
        if (origLocatorStr.startsWith(idPrefix)) {
            String id = origLocatorStr.replace(idPrefix, "");
            id = String.format(id, args).strip();
            LOG.trace("id: " + id);
            return By.id(id);
        }
        throw new RuntimeException("Locators with types other than xpath, css selectors and id are not supported! Tried to transform locator: " + origLocatorStr);
    }

    /**
     * Stringifies the  locator
     *
     * @param origLocator
     *
     * @return locator value as String
     */
    public static String getLocatorString(By origLocator) {
        String xPathPrefix = "By.xpath:";
        String cssSelectorPrefix = "By.cssSelector:";
        String idPrefix = "By.id:";

        String origLocatorStr = origLocator.toString();

        if (origLocatorStr.startsWith(xPathPrefix)) {
            return origLocatorStr.replace(xPathPrefix, "").trim();
        }
        if (origLocatorStr.startsWith(cssSelectorPrefix)) {
            return origLocatorStr.replace(cssSelectorPrefix, "").trim();
        }
        if (origLocatorStr.startsWith(idPrefix)) {
            return origLocatorStr.replace(idPrefix, "").trim();
        }
        throw new RuntimeException("Locators with types other than xpath, css selectors and id are not supported! Tried to transform locator: " + origLocatorStr);
    }
}
