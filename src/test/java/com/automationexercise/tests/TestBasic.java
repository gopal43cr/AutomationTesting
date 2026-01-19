package com.automationexercise.tests;

import com.automationexercise.utils.BrowserManager;
import com.automationexercise.utils.PropertiesLoader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBasic {

    protected static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeMethod
    public void setup() throws IOException {
        String url = PropertiesLoader.loadProperty("url");
        WebDriver driver = BrowserManager.doBrowserSetup();
        tdriver.set(driver);
        getDriver().get(url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Take screenshot only if test failed
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) getDriver();
                File source = screenshot.getScreenshotAs(OutputType.FILE);

                // Create screenshots directory with timestamp
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotDir = "test-output/screenshots/" + timestamp;
                new File(screenshotDir).mkdirs();

                String destination = screenshotDir + "/" + result.getName() + ".png";
                FileUtils.copyFile(source, new File(destination));
                System.out.println("Screenshot saved: " + destination);
            } catch (IOException e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

        getDriver().quit();
        tdriver.remove();
    }
}
