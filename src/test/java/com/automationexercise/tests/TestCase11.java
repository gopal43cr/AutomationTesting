package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase11 extends TestBasic {

    @Test(description = "Test Case 11: Verify Subscription in Cart page")
                public void verifySubscriptionInCartPage() throws IOException, ParseException {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        new HomePage(getDriver()).cartButtonClick();
        TestCase10.verifyTextSubscription();
        TestCase10.verifySuccessMessageYouHaveBeenSuccessfullySubscribedIsVisible();
    }
}
