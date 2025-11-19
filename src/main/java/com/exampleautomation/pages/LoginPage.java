package com.exampleautomation.pages;

import com.exampleautomation.interfaces.PageElements;
import com.exampleautomation.utilities.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public enum Element implements PageElements {
        LOGIN_FIELD(By.xpath("//input[@id='username']")),
        PASSWORD_FIELD(By.xpath("//input[@id='password']")),
        SUBMIT_BUTTON(By.xpath("//button[@id='submit']")),
        LOGOUT_BUTTON(By.linkText("Log out"))
        ;

        private final By by;

        Element(By locator){this.by = locator;}

        @Override
        public By by(){
            return by;
        }

    }


    public LoginPage(WebDriver passedDriver){
        this.driver = passedDriver;
    }

    public void fillLoginField(String value){
        Waiter.waitForElementToBeVisible(driver, Element.LOGIN_FIELD.by()).sendKeys(value);
    }

    public void fillPasswordField(String value){
        Waiter.waitForElementToBeVisible(driver, Element.PASSWORD_FIELD.by()).sendKeys(value);
    }

    public void clickSubmitButton(){
        Waiter.waitForElementToBeClickable(driver, Element.SUBMIT_BUTTON.by()).click();
    }

    public void clickLogOutButton(){
        Waiter.waitForElementToBeClickable(driver, Element.LOGOUT_BUTTON.by()).click();
    }
}
