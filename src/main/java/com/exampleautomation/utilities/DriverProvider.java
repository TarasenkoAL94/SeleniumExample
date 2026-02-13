package com.exampleautomation.utilities;

import com.exampleautomation.utilities.enums.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverProvider {

    private static WebDriver driver;
    private static  final Logger LOG = LoggerFactory.getLogger(DriverProvider.class);

    private DriverProvider(){

    }

    private static void startDriver(String type){
        switch (DriverTypes.fromString(type)){
            case FIREFOX -> driver = new FirefoxDriver();
            case EDGE -> driver = new EdgeDriver();
            default -> driver = new ChromeDriver();
        }
    }

    public static WebDriver getDriver(String type){
        if(driver == null){
            startDriver(type);
            driver.manage().window().maximize();
            return driver;
        }
        return driver;
    }

    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
        LOG.info("browser is closed");
    }
}
