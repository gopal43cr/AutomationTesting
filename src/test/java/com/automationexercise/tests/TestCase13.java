package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductDetailPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase13 extends TestBasic {

    @Test(description = "Test Case 13: Verify Product quantity in Cart")
                public void verifyProductQuantityInCart() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyProductDetailIsOpened();
        verifyThatProductIsDisplayedInCartPageWithExactQuantity();
    }

    private void verifyProductDetailIsOpened() {
        new HomePage(getDriver()).viewProduct1ButtonClick();
        Assert.assertEquals(getDriver().getTitle(), "Automation Exercise - Product Details", "Verify product detail is opened");
    }

    private void verifyThatProductIsDisplayedInCartPageWithExactQuantity() {
        List<String> quantity = new ProductDetailPage(getDriver()).increaseQuantity("4")
                .addToCartButtonClick()
                .viewCartButtonClick().getQuantity();
        Assert.assertEquals(quantity.get(0), "4", "Verify that product is displayed in cart page with exact quantity");
    }
}
