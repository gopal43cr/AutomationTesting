package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginSignupPage;
import com.automationexercise.utils.JSONReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase2 extends TestBasic {

    @Test(description = "Test Case 2: Login User with correct email and password")
    public static void loginUserWithCorrectEmailAndPassword() throws IOException, ParseException {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyLoginToYourAccountIsVisible();
        verifyThatLoggedInAsUsernameIsVisible();
    }

    public static void verifyLoginToYourAccountIsVisible() {
        String loginToYourAccountText = new HomePage(getDriver())
                .signupLoginClick()
                .getLoginToYourAccount()
                .getText();
        Assert.assertEquals(loginToYourAccountText, "Login to your account",
                "Verify 'Login to your account' is visible");
    }

    private static void verifyThatLoggedInAsUsernameIsVisible() throws IOException, ParseException {
        String username = new LoginSignupPage(getDriver())
                .fillCorrectLogin(JSONReader.existingUser("email"), JSONReader.existingUser("password"))
                .getUsername()
                .getText();
        Assert.assertEquals(username, JSONReader.existingUser("name"),
                "Verify that 'Logged in as username' is visible");
    }
}
