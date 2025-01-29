package com.ajay.qa.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aj.qa.base.TestBase;

public class LoginPage extends TestBase {
    
    // Page Factory - OR (Object Repository)
    @FindBy(name = "user_name")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;

    @FindBy(linkText = "Sign Up")
    WebElement signUpBtn;

    @FindBy(css="img[src*='/img/uploads/Logo.png']") // Use CSS Selector
    WebElement hrmsLogo;

    // Initializing the Page Objects
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    // Actions (Methods)

    // Validate the title of the login page
    public String validateLoginPageTitle() {
        return driver.getTitle();
    }

    // Validate the CRM logo is displayed
    public boolean validateCRMImage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Wait up to 10 seconds
        try {
            wait.until(ExpectedConditions.visibilityOf(hrmsLogo));
            return hrmsLogo.isDisplayed();
        } catch (Exception e) {
            System.out.println("CRM Logo not visible: " + e.getMessage());
            return false;
        }
    }

    // Login to the application
    public HomePage login(String un, String pwd) {
        username.sendKeys(un); // Enter username
        password.sendKeys(pwd); // Enter password
        
        // Perform click using JavaScript Executor for reliability
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginBtn);
        
        return new HomePage(); // Navigate to HomePage
    }
}