package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase7 extends TestBasic {

    @Test(description = "Test Case 7: Verify Test Cases Page")
                public void verifyTestCasesPage() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyUserIsNavigatedToTestCasesPageSuccessfully();
    }

    private void verifyUserIsNavigatedToTestCasesPageSuccessfully() {
        String testCasesText = new HomePage(getDriver())
                .testCasesButtonClick()
                .getTestCases()
                .getText();
        Assert.assertEquals(testCasesText, "TEST CASES", "Verify user is navigated to test cases page successfully");
    }
}
