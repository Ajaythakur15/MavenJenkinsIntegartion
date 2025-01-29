package com.ajay.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aj.qa.base.TestBase;
import com.ajay.qa.pages.HomePage;
import com.ajay.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
    private LoginPage loginPage;
    private HomePage homePage;

    // Constructor to initialize properties
    public LoginPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initializeDriver(); // Initialize the driver and launch the application
        loginPage = new LoginPage(); // Initialize LoginPage object
    }

    @Test(priority = 1, description = "Validate the login page title.")
    public void loginPageTitleTest() {
        String actualTitle = loginPage.validateLoginPageTitle();
        String expectedTitle = "#1 Free CRM for Any Business: Online Customer Relationship Software";
        Assert.assertEquals(actualTitle, "Login");

    }

    @Test(priority = 2, description = "Verify the CRM logo is displayed on the login page.")
    public void hrmsLogoImageTest() {
        boolean isLogoDisplayed = loginPage.validateCRMImage();
        Assert.assertTrue(isLogoDisplayed, "CRM Logo is not displayed on the login page.");
    }

    @Test(priority = 3, description = "Perform login with valid credentials.")
    public void loginTest() {
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        homePage = loginPage.login(username, password);
        Assert.assertNotNull(homePage, "Login failed. HomePage object is null.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Ensure driver cleanup to avoid memory leaks
        }
    }
}