package com.automationexercise.tests;

import com.automationexercise.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase17 extends TestBasic {

    @Test(description = "Test Case 17: Remove Products From Cart")
                public void removeProductsFromCart() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase14.verifyThatCartPageIsDisplayed();
        verifyThatProductIsRemovedFromTheCart();
    }

    private void verifyThatProductIsRemovedFromTheCart() {
        String emptyCartText = new CartPage(getDriver())
                .xButtonClick()
                .getEmptyCartSpan()
                .getText();
        Assert.assertEquals(emptyCartText, "Cart is empty! Click here to buy products.", "Verify that product is removed from the cart");
    }
}
