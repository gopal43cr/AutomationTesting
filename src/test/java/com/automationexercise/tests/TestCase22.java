package com.automationexercise.tests;

import com.automationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase22 extends TestBasic {
    @Test(description = "Test Case 22: Add to cart from Recommended items")
                public void AddToCartFromRecommendedItems() {
        verifyRecommendedItemsAreVisible();
        verifyThatProductIsDisplayedInCartPage();
    }

    private void verifyRecommendedItemsAreVisible() {
        String recommendedItemsText = new HomePage(getDriver())
                .getRecommendedItems()
                .getText();
        Assert.assertEquals(recommendedItemsText, "RECOMMENDED ITEMS", "Verify 'RECOMMENDED ITEMS' are visible");
    }

    private void verifyThatProductIsDisplayedInCartPage() {
        List<String> productsNames = new HomePage(getDriver())
                .blueTopAddToCartButtonClick()
                .viewCartButtonClick()
                .getProductsNames();
        Assert.assertEquals(productsNames.get(0), "Blue Top", "Verify that product is displayed in cart page");
    }
}
