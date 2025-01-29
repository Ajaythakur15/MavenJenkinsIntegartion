package com.aj.qa.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebEventListener implements WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
        System.out.println("Trying to click on: " + element);
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println("Clicked on: " + element);
    }

    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Before navigating to: '" + url + "'");
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigated to: '" + url + "'");
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println("Trying to find Element By: " + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("Found Element By: " + locator);
    }

    // Handle exceptions in the TestBase class or directly within the test
}