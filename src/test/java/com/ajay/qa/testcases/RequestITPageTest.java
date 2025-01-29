package com.ajay.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aj.qa.base.TestBase;
import com.ajay.qa.pages.HomePage;
import com.ajay.qa.pages.LoginPage;
import com.ajay.qa.pages.RequestITPage;

public class RequestITPageTest extends TestBase {

    private LoginPage loginPage;
    private HomePage homePage;
    private RequestITPage requestITPage;

    public RequestITPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initializeDriver();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        requestITPage = new RequestITPage();
        requestITPage.navigateToRequestITPage();
    }

    @Test(priority = 1,enabled = true)
    public void verifyRequestITPageTitleTest() {
        requestITPage.navigateToRequestITPage();
        try {
        	requestITPage.waitForPageLoad();
            String actualTitle = requestITPage.getPageTitleWithWait("Request IT Devices", Duration.ofSeconds(30));
            Assert.assertEquals(actualTitle, "Request IT Devices", "Page title does not match!");
        }catch(NoSuchElementException e) {
        	 System.out.println("Page Title: " + driver.getTitle());
        	 System.out.println("Element not found"+e.getMessage());
             //Assert.assertEquals(actualTitle, "Request IT Devices", "Page title does not match!");
         }
     }
    @Test(priority = 2, enabled = false)
    public void clickOnNewRequestFormTest() {
        requestITPage.openNewRequestForm();
        // Add assertion or additional verification logic here if necessary.
        Assert.assertNotNull(requestITPage, "Failed to navigate to New Request IT Devices page.");
    }

    @Test(priority = 3, enabled = false)
    public void submitFormTest() {
        requestITPage.openNewRequestForm();
        try {
        	requestITPage.submitForm();
        }catch(NoSuchElementException e){
        	 
        	 // Add additional assertions or checks to verify successful form submission.
            System.out.println("Element not found"+e.getMessage());
        }
    }
    
    @Test(priority = 4, enabled = false)
    public void clickOnNew1RequestFormTest() {
        requestITPage.openNewRequestForm();
        // Add assertion or additional verification logic here if necessary.
        Assert.assertNotNull(requestITPage, "Failed to navigate to New Request IT Devices page.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}