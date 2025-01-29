package com.aj.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aj.qa.base.TestBase;

public class TestUtil extends TestBase {

    public static final long PAGE_LOAD_TIMEOUT = 20;
    public static final long IMPLICIT_WAIT = 20;
    public static final String TESTDATA_SHEET_PATH = System.getProperty("user.dir") 
            + "/src/main/java/java/com/aj/qa/testdata/FreeCrmTestData.xlsx";

    private static Workbook book;
    private static Sheet sheet;
    private JavascriptExecutor js;

    /**
     * Switch to the "mainpanel" frame.   
     */
    public void switchToFrame() {
        driver.switchTo().frame("mainpanel");
    }

    /**
     * Fetches test data from the specified Excel sheet.
     * 
     * @param sheetName The name of the sheet.
     * @return A 2D array containing the test data.
     */
    public Object[][] getTestData(String sheetName) {
        try (FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH)) {
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " not found in " + TESTDATA_SHEET_PATH);
            }

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
                }
            }
            return data;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading test data from file: " + e.getMessage());
        }
    }

    /**
     * Captures a screenshot and saves it in the "screenshots" folder.
     * 
     * @throws IOException If an error occurs during file operations.
     */
    public void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(scrFile, new File(screenshotPath));
    }

    /**
     * Displays runtime information using jQuery growl notifications.
     * 
     * @param messageType The type of message (e.g., "error", "info", "warning").
     * @param message     The message to display.
     */
    public void runTimeInfo(String messageType, String message) {
        js = (JavascriptExecutor) driver;

        js.executeScript("if (!window.jQuery) {"
                + "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
                + "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
                + "document.getElementsByTagName('head')[0].appendChild(jquery);}");

        js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js');");
        js.executeScript("$('head').append('<link rel=\"stylesheet\" "
                + "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" type=\"text/css\" />');");

        switch (messageType.toLowerCase()) {
            case "error":
                js.executeScript("$.growl.error({ title: 'ERROR', message: '" + message + "' });");
                break;
            case "info":
                js.executeScript("$.growl.notice({ title: 'Notice', message: '" + message + "' });");
                break;
            case "warning":
                js.executeScript("$.growl.warning({ title: 'Warning!', message: '" + message + "' });");
                break;
            default:
                System.out.println("Invalid message type: " + messageType);
        }
    }
}