package com.ajay.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aj.qa.base.TestBase;

public class HomePage extends TestBase {

    // Page Factory - OR (Object Repository)
    @FindBy(xpath = "//td[contains(text(),'AJAY SINGH')]")
    @CacheLookup
    WebElement userNameLabel;

    @FindBy(xpath = "//a[contains(text(),'Request IT Devices')]")
    WebElement requestITLink;

    @FindBy(xpath = "//a[contains(text(),'Leave policy')]")
    WebElement leavePolicyLink;

    @FindBy(xpath = "//a[contains(text(),'Home')]")
    WebElement homeLink;

    @FindBy(xpath = "//header/div[4]/div[1]/nav[1]/div[1]/nav[1]/div[2]/ul[2]/li[1]/a[1]")
    WebElement profileLink;

    // Initializing the Page Objects:
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // Action Methods:

    // Verify HomePage Title
    public String verifyHomePageTitle() {
        return driver.getTitle();
    }

    // Verify Correct Username is Displayed
    public boolean verifyCorrectUserName() {
        return userNameLabel.isDisplayed();
    }

    // Click on Request IT Devices Link and return RequestITPage
    public RequestITPage clickOnRequestITLink() {
        requestITLink.click();
        return new RequestITPage();
    }

    // Click on Home Link and return HomePage
    public HomePage clickOnHomeLink() {
        homeLink.click();
        return new HomePage();
    }

    // Click on Leave Policy Link and return LeavePolicyPage
    public LeavePolicyPage clickOnLeavePolicyLink() {
        leavePolicyLink.click();
        return new LeavePolicyPage();
    }
   

    // Additional action to click on Profile Link
    public ProfilePage clickOnProfileLink() {
        profileLink.click();
        return new ProfilePage();
    }

    // Navigate to the Home Page URL (Explicit URL navigation)
    public HomePage navigateToHomePage() {
        driver.get("http://hrms.trainingncr.info/employee/dashboard");
        return new HomePage();
    }
}