package com.exampletestautomation.test.login;

import com.exampleautomation.pages.LoginPage;
import com.exampleautomation.utilities.CommonUtilities;
import com.exampleautomation.utilities.DriverProvider;
import com.exampleautomation.utilities.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.slf4j.Logger;

import java.util.Objects;


public class LoginTests {

    private WebDriver driver;
    private final Logger LOG = LoggerFactory.getLogger(LoginTests.class);

    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        LOG.info("Running tests in: " + browser);
        driver = DriverProvider.getDriver(browser);
        driver.get(Objects.requireNonNull(ReadProperties.getProp("initialPage")));
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverProvider.quitDriver();
    }


    @Test(groups = {"positive", "regression", "smoke"})
    @Parameters({"username", "password"})
    public void testLoginFunctionality(String username, String password) {
        LOG.info("Starting testLoginFunctionality");
        //Type username student into Username field
        LOG.info("input username: {}", username);
        loginPage.fillLoginField(username);
        //Type password Password123 into Password field
        LOG.info("input password: {}", password);
        loginPage.fillPasswordField(password);
        //Push Submit button
        LOG.info("click submit");
        loginPage.clickSubmitButton();
        LOG.info("verify login functionality");
        //Verify new page contains expected text ('Congratulations' or 'successfully logged in')
        Assert.assertTrue(Objects.requireNonNull(driver.getPageSource()).contains("Congratulations") || driver.getPageSource().contains("successfully logged in"));
        //Verify button Log out is displayed on the new page
        Assert.assertTrue(CommonUtilities.isElementVisible(driver, LoginPage.Element.LOGOUT_BUTTON.by()));
    }

    @Test(groups = {"negative", "regression"}, dataProvider = "testdata")
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        //Type username into Username field
        LOG.info("input username: {}", username);
        loginPage.fillLoginField(username);
        //Type password into Password field
        LOG.info("input password: {}", password);
        loginPage.fillPasswordField(password);
        //Push Submit button
        LOG.info("click submit");
        loginPage.clickSubmitButton();
        LOG.info("verify error messages");
        //Verify the page contains expected text
        Assert.assertTrue(Objects.requireNonNull(driver.getPageSource()).contains(expectedErrorMessage));
    }

    @DataProvider(name = "testdata")
    public Object[][] testData() {
//        Faker faker = new Faker();
        return new Object[][]{
                {"asdasdasd", "Password123", "Your username is invalid!"},
                {"student", "asdasdasdasd", "Your password is invalid!"}
        };
    }
}
