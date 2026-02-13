package com.exampletestautomation.test.exceptions;

import com.exampleautomation.utilities.DriverProvider;
import com.exampleautomation.utilities.ReadProperties;
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
import java.util.Objects;


public class ExceptionTests {

    private WebDriver driver;
    private final Logger LOG = LoggerFactory.getLogger(ExceptionTests.class);

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        LOG.info("Running tests in: " + browser);
        driver = DriverProvider.getDriver(browser);
        driver.get(Objects.requireNonNull(ReadProperties.getProp("initialPage")));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverProvider.quitDriver();
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
