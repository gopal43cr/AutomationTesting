package com.automationexercise.tests;

import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase12 extends TestBasic {

    @Test(description = "Test Case 12: Add Products in Cart")
                public void addProductsInCart() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        verifyBothProductsAreAddedToCart();
        verifyTheirPricesQuantityAndTotalPrice();
    }

    private void verifyBothProductsAreAddedToCart() {
        List<String> productNames = new HomePage(getDriver())
                .productsButtonClick()
                .addProductsToCart()
                .getProductsNames();
        Assert.assertEquals(productNames.size(), 2, "Verify both products are added to Cart");
    }

    private void verifyTheirPricesQuantityAndTotalPrice() {
        List<String> prices = new CartPage(getDriver()).getPrices();
        List<String> quantity = new CartPage(getDriver()).getQuantity();
        List<String> totalPrices = new CartPage(getDriver()).getTotalPrices();

        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(totalPrices.get(i), prices.get(i), "Verify their prices and total price");
            Assert.assertEquals(quantity.get(i), "1", "Verify their quantity");
            System.out.println(i + ". Prices = Total Prices | " + prices.get(i) + " = " + totalPrices.get(i));
            System.out.println(i + ". Quantity = 1 | " + quantity.get(i).equals("1"));
        }
    }
}
