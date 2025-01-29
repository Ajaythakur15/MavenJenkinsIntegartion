package com.ajay.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FreeCrmTest {

    private WebDriver driver;
    private JavascriptExecutor js;
    private static final String BASE_URL = "https://www.freecrm.com/index.html";
    private static final String SCREENSHOT_PATH = "C:\\Users\\Techpro-LP1\\eclipse-workspace\\AjayPOMJava\\MavenJenkinsIntegartion\\screenshots";

    @BeforeMethod
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait for element loading
        driver.get(BASE_URL);
    }

    @Test
    public void freeCrmTitleTest() throws IOException {
        String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
        logRunTimeInfo("info", actualTitle);

        String expectedTitle = "Free CRM Software - Customer Relationship Management";

        if (actualTitle.equals(expectedTitle)) {
            logRunTimeInfo("info", "Title is correct! Test Passed!");
            Assert.assertTrue(true, "Title matches as expected.");
        } else {
            logRunTimeInfo("error", "Title is incorrect! Expected: " + expectedTitle + ", but got: " + actualTitle);
            takeScreenshot("freecrm_login_page");
            Assert.fail("Title validation failed. Expected: " + expectedTitle + ", but got: " + actualTitle);
        }
    }

    private void logRunTimeInfo(String messageType, String message) {
        try {
            // Ensure jQuery is loaded
            js.executeScript("if (!window.jQuery) {" +
                    "var jquery = document.createElement('script'); jquery.type = 'text/javascript';" +
                    "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';" +
                    "document.getElementsByTagName('head')[0].appendChild(jquery);" +
                    "}");
            Thread.sleep(2000); // Wait for jQuery to load

            // Add growl notification scripts and CSS
            js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js');");
            js.executeScript("$('head').append('<link rel=\"stylesheet\" " +
                    "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " +
                    "type=\"text/css\" />');");
            Thread.sleep(2000); // Wait for growl scripts to load

            // Display runtime info
            switch (messageType.toLowerCase()) {
                case "error":
                    js.executeScript("$.growl.error({ title: 'ERROR', message: '" + message + "' });");
                    break;
                case "info":
                    js.executeScript("$.growl.notice({ title: 'INFO', message: '" + message + "' });");
                    break;
                case "warning":
                    js.executeScript("$.growl.warning({ title: 'WARNING', message: '" + message + "' });");
                    break;
                default:
                    js.executeScript("$.growl({ title: 'LOG', message: '" + message + "' });");
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeScreenshot(String fileName) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(SCREENSHOT_PATH + fileName + ".png");
        FileUtils.copyFile(srcFile, destFile);
        System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}