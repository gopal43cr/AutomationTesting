package com.automationexercise.tests;

import com.automationexercise.pages.LoginSignupPage;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase5 extends TestBasic {

    @Test(description = "Test Case 5: Register User with existing email")
                public void registerUserWithExistingEmail() throws IOException, ParseException {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase1.verifyNewUserSignupIsVisible();
        verifyErrorEmailAddressAlreadyExistIsVisible();
    }

    private void verifyErrorEmailAddressAlreadyExistIsVisible() throws IOException, ParseException {
        String emailAddressAlreadyExistText = new LoginSignupPage(getDriver())
                .fillIncorrectSignup()
                .getEmailAddressAlreadyExist()
                .getText();
        Assert.assertEquals(emailAddressAlreadyExistText, "Email Address already exist!", "Verify error 'Email Address already exist!' is visible");
    }
}
