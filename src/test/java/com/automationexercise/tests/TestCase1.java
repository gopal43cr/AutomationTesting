package com.automationexercise.tests;

import com.automationexercise.pages.*;
import com.automationexercise.utils.Util;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase1 extends TestBasic {

    String name = "name" + Util.generateCurrentDateAndTime();
    String email = "email" + Util.generateCurrentDateAndTime() + "@o2.pl";

    @Test(description = "Test Case 1: Register User")
    public void registerUser() throws IOException, ParseException {
        verifyThatHomePageIsVisibleSuccessfully();
        verifyNewUserSignupIsVisible();
        verifyThatEnterAccountInformationIsVisible();
        verifyThatAccountCreatedIsVisible();
        verifyThatLoggedInAsUsernameIsVisible();
        verifyThatAccountDeletedIsVisibleAndClickContinueButton();
    }

    public static void verifyThatHomePageIsVisibleSuccessfully() {
        boolean homePageVisible = new HomePage(getDriver())
                .homePageIsVisible()
                .isDisplayed();
        Assert.assertTrue(homePageVisible, "Verify that home page is visible successfully");
    }

    public static void verifyNewUserSignupIsVisible() {
        String newUserSignupText = new HomePage(getDriver())
                .signupLoginClick()
                .getNewUserSignup()
                .getText();
        Assert.assertEquals(newUserSignupText, "New User Signup!", "Verify 'New User Signup!' is visible");
    }

    private void verifyThatEnterAccountInformationIsVisible() {
        String enterAccountInformationText = new LoginSignupPage(getDriver())
                .fillCorrectSignup(name, email)
                .getEnterAccountInformation()
                .getText();
        Assert.assertEquals(enterAccountInformationText, "ENTER ACCOUNT INFORMATION",
                "Verify that 'ENTER ACCOUNT INFORMATION' is visible");
    }

    private void verifyThatAccountCreatedIsVisible() throws IOException, ParseException {
        String accountCreatedText = new EnterAccountInformationPage(getDriver())
                .fillAccountDetails()
                .getAccountCreated()
                .getText();
        Assert.assertEquals(accountCreatedText, "ACCOUNT CREATED!", "Verify that 'ACCOUNT CREATED!' is visible");
    }

    private void verifyThatLoggedInAsUsernameIsVisible() {
        String username = new AccountCreatedPage(getDriver())
                .continueButtonClick()
                .getUsername()
                .getText();
        Assert.assertEquals(username, name, "Verify that 'Logged in as username' is visible");
    }

    public static void verifyThatAccountDeletedIsVisibleAndClickContinueButton() {
        String accountDeletedText = new LoggedHomePage(getDriver())
                .deleteAccountButtonClick()
                .getAccountDeleted()
                .getText();
        Assert.assertEquals(accountDeletedText, "ACCOUNT DELETED!", "Verify that 'ACCOUNT DELETED!' is visible");
        new AccountDeletedPage(getDriver()).continueButtonClick();
    }
}
