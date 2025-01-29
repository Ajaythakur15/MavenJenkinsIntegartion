package com.aj.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aj.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
    // Make the driver static to access it in static context.
    public static WebDriver driver;
    public static Properties prop;

    // Constructor to load properties file
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/java/java/com/aj/qa/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException("Configuration file not found or could not be loaded", e);
        }
    }

    /**
     * Initializes the WebDriver, applies the WebEventListener, and configures browser settings.
     */
    public static void initializeDriver() {
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
        driver.get(prop.getProperty("url"));
    }

    /**
     * Cleans up WebDriver resources and quits the browser session.
     */
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Clear the reference to avoid memory leaks
        }
    }
}