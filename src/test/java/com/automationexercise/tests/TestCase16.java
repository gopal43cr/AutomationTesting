package com.automationexercise.tests;

import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoggedHomePage;
import com.automationexercise.utils.JSONReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase16 extends TestBasic {

    @Test(description = "Test Case 16: Place Order: Login before Checkout")
                public void placeOrderLoginBeforeCheckout() throws IOException, ParseException {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        new HomePage(getDriver())
                .signupLoginClick()
                .fillCorrectLogin(JSONReader.existingUser("email"), JSONReader.existingUser("password"));
        verifyLoggedInAsUsernameAtTop();
        TestCase14.verifyThatCartPageIsDisplayed();
        new CartPage(getDriver()).proceedToCheckoutButtonClick();
        //TestCase14.verifyAddressDetailsAndReviewYourOrder();
        //TestCase14.verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed();
    }

    private void verifyLoggedInAsUsernameAtTop() throws IOException, ParseException {
        String username = new LoggedHomePage(getDriver())
                .getUsername()
                .getText();
        Assert.assertEquals(username, JSONReader.existingUser("name"), "Verify 'Logged in as username' at top");
    }
}
