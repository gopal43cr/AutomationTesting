package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase26 extends TestBasic {

    @Test(description = "Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality")
                public void verifyScrollUpWithoutArrowButtonAndScrollDownFunctionality() throws InterruptedException {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase25.verifySubscriptionIsVisible();
        verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen();
    }

    private void verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen() throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", new HomePage(getDriver()).getFullFledgedPracticeWebsiteForAutomationEngineers());
        boolean fullFledgedTextIsDisplayed = new HomePage(getDriver()).getFullFledgedPracticeWebsiteForAutomationEngineers().isDisplayed();
        Assert.assertTrue(fullFledgedTextIsDisplayed, "Verify that 'Full-Fledged practice website for Automation Engineers' text is visible on screen");
        double value = (double) js.executeScript("return window.pageYOffset;");
        Assert.assertTrue(value < 400, "Verify that page is scrolled up");
    }
}
