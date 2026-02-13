package com.exampleautomation.utilities;

import com.exampleautomation.utilities.enums.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverProvider {

    private static WebDriver driver;

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
    }
}
