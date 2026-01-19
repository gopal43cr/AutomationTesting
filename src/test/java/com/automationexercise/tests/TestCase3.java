package com.automationexercise.tests;

import com.automationexercise.pages.LoginSignupPage;
import com.automationexercise.utils.Util;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase3 extends TestBasic {

    @Test(description = "Test Case 3: Login User with incorrect email and password")
    public void loginUserWithIncorrectEmailAndPassword() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase2.verifyLoginToYourAccountIsVisible();
        verifyErrorYourEmailOrPasswordIsIncorrectIsVisible();
    }

    private void verifyErrorYourEmailOrPasswordIsIncorrectIsVisible() {
        String email = "email" + Util.generateCurrentDateAndTime() + "@incorrect.pl";
        String password = "pass" + Util.generateCurrentDateAndTime();

        String errorLoginText = new LoginSignupPage(getDriver())
                .fillIncorrectLogin(email, password)
                .getErrorLogin()
                .getText();
        Assert.assertEquals(errorLoginText, "Your email or password is incorrect!",
                "Verify error 'Your email or password is incorrect!' is visible");
    }
}
