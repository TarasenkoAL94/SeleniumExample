package com.exampletestautomation.test.exceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class ExceptionTests {

    private WebDriver driver;
    private final Logger LOG = LoggerFactory.getLogger(ExceptionTests.class);

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        LOG.info("Running tests in: " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                LOG.warn("Configuration for {} browser is missing. Running tests in Chrome by default", browser);
                driver = new ChromeDriver();
        }
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        LOG.info("browser is closed");
    }

    @Test
    public void testNseException(){
        WebElement addBtn = driver.findElement(By.xpath("//button[@id='add_btn']"));
        addBtn.click();
        WebElement row2 = new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']//input")));
        Assert.assertTrue(row2.isDisplayed());
    }


}
