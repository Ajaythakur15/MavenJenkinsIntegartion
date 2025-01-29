package com.ajay.qa.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aj.qa.base.TestBase;

public class RequestITPage extends TestBase {

    // Locators
    @FindBy(xpath = "//a[contains(text(),'Request IT Devices')]")
   
    private WebElement requestITLink;

    @FindBy(xpath = "//a[@id='showhide']")
    @CacheLookup
    private WebElement newRequestButton;

    @FindBy(xpath = "//button[@id='submitButton']")
    @CacheLookup
    private WebElement submitButton;

    @FindBy(xpath = "//input[@value='Reset']")
    @CacheLookup
    private WebElement resetButton;

    @FindBy(xpath = "//h1[contains(text(),'Request IT Devices')]")
    private WebElement requestITPageTitle;

    // Constructor
    public RequestITPage() {
        PageFactory.initElements(driver, this);
    }

 // Verify page title
    public String getPageTitleWithWait(String expectedTitle, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        return driver.getTitle();
    }

    // Navigate to the Request IT Devices page
    public void navigateToRequestITPage() {
        clickElement(requestITLink, Duration.ofSeconds(20));
        System.out.println("Current URL: " + driver.getCurrentUrl());
        //System.out.println("Navigated to Request IT Page.");
    }


    // Click New Request Form
    public void openNewRequestForm() {
        clickElement(newRequestButton, Duration.ofSeconds(20));
    }

    // Submit the form
    public void submitForm() {
        WebElement submit = waitForElementToBeClickable(submitButton, Duration.ofSeconds(10));
        scrollIntoView(submit);
        verifyAndClickElement(submit, "Submit Button");
    }

    // Reset the form
    public void resetForm() {
        clickElement(resetButton, Duration.ofSeconds(15));
    }
    
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));
    }

    // Helper methods

    private void clickElement(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        int attempts = 0;
        while (attempts < 3) { // Retry logic
            try {
                WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
                clickableElement.click();
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element reference, retrying... Attempt: " + (attempts + 1));
                attempts++;
            }
        }
        throw new RuntimeException("Failed to click element after multiple attempts due to stale reference.");
    }


    private WebElement waitForElementToBeClickable(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void verifyAndClickElement(WebElement element, String elementName) {
        if (element.isDisplayed() && element.isEnabled()) {
            element.click();
        } else {
            throw new RuntimeException(elementName + " is not visible or enabled!");
        }
    }
}