package com.automationexercise.tests;

import com.automationexercise.pages.ContactUsPage;
import com.automationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase6 extends TestBasic {

    @Test(description = "Test Case 6: Contact Us Form")
                public void contactUsForm() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyGetInTouchIsVisible();
        verifySuccessMessageSuccessYourDetailsHaveBeenSubmittedSuccessfullyIsVisible();
        clickHomeButtonAndVerifyThatLandedToHomePageSuccessfully();
    }

    private void verifyGetInTouchIsVisible() {
        String getGetInTouchText = new HomePage(getDriver())
                .contactUsButtonClick()
                .getGetInTouch()
                .getText();
        Assert.assertEquals(getGetInTouchText, "GET IN TOUCH", "Verify 'GET IN TOUCH' is visible");
    }

    private void verifySuccessMessageSuccessYourDetailsHaveBeenSubmittedSuccessfullyIsVisible() {
        String alertSuccessText = new ContactUsPage(getDriver())
                .fillForm()
                .submitButtonClick()
                .okButtonClick()
                .getAlertSuccess()
                .getText();
        Assert.assertEquals(alertSuccessText, "Success! Your details have been submitted successfully.", "Verify success message 'Success! Your details have been submitted successfully.' is visible");
    }

    private void clickHomeButtonAndVerifyThatLandedToHomePageSuccessfully() {
        boolean homePageVisible = new ContactUsPage(getDriver())
                .homePageButtonClick()
                .homePageIsVisible()
                .isDisplayed();
        Assert.assertTrue(homePageVisible, "Click 'Home' button and verify that landed to home page successfully");
    }
}
