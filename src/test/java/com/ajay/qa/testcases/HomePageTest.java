package com.ajay.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aj.qa.base.TestBase;
import com.aj.qa.util.TestUtil;
import com.ajay.qa.pages.HomePage;
import com.ajay.qa.pages.LeavePolicyPage;
import com.ajay.qa.pages.LoginPage;
import com.ajay.qa.pages.ProfilePage;
import com.ajay.qa.pages.RequestITPage;

public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	RequestITPage requestITPagePage;
	//NewRequestPage clickOnNewRequestLink;

	public HomePageTest() {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initializeDriver();
		testUtil = new TestUtil();
		requestITPagePage = new RequestITPage();
		loginPage = new LoginPage();
		//clickOnNewRequestLink =new NewRequestPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=1,enabled = true)
	public void verifyHomePageTitleTest() {
        // Ensure the page has fully loaded before verifying the title
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleIs("Employee Panel"));

        // Use the correct title that matches the actual page title
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Employee Panel", "The home page title is incorrect.");
	}
	
	@Test(priority=2,enabled = true)
	public void verifyCorrectUserNameTest() {
	    HomePage homePage = new HomePage();
	    boolean isUserNameDisplayed = homePage.verifyCorrectUserName();
	    Assert.assertTrue(isUserNameDisplayed, "Username is not displayed.");
	}
	
	@Test(priority=3,enabled = true)
	public void clickOnRequestITLinkTest() {
	    HomePage homePage = new HomePage();
	    RequestITPage requestITPage = homePage.clickOnRequestITLink();
	    Assert.assertNotNull(requestITPage, "Failed to navigate to Request IT Devices page.");
	}
	
	@Test(priority=4,enabled = true)
	public void clickOnHomeLinkTest() {
	    HomePage homePage = new HomePage();
	    HomePage homePageAfterClick = homePage.clickOnHomeLink();
	    Assert.assertNotNull(homePageAfterClick, "Failed to navigate back to Home page.");
	}
	
	@Test(priority=5,enabled = true)
	public void clickOnLeavePolicyLinkTest() {
	    HomePage homePage = new HomePage();
	    LeavePolicyPage leavePolicyPage = homePage.clickOnLeavePolicyLink();
	    Assert.assertNotNull(leavePolicyPage, "Failed to navigate to Leave Policy page.");
	}

	@Test(priority=6,enabled = true)
	public void clickOnProfileLinkTest() {
	    HomePage homePage = new HomePage();
	    ProfilePage profilePage = homePage.clickOnProfileLink();
	    Assert.assertNotNull(profilePage, "Failed to navigate to Profile page.");
	}

	@Test(priority=7,enabled = true)
	public void navigateToHomePageTest() {
	    HomePage homePage = new HomePage();
	    homePage.navigateToHomePage();
	    String currentUrl = driver.getCurrentUrl();
	    Assert.assertEquals(currentUrl, "http://hrms.trainingncr.info/employee/dashboard", "URL mismatch");
	}

	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
