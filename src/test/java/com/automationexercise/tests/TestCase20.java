package com.automationexercise.tests;

import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import com.automationexercise.utils.JSONReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TestCase20 extends TestBasic {

    @Test(description = "Test Case 20: Search Products and Verify Cart After Login")
                public void searchProductsAndVerifyCartAfterLogin() throws IOException, ParseException, InterruptedException {
        TestCase8.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        TestCase9.verifySearchedProductsIsVisible();
        List<String> productsNames = TestCase9.verifyAllTheProductsRelatedToSearchAreVisible();
        new ProductsPage(getDriver()).addAllProducts();
        clickCartButtonAndVerifyThatProductsAreVisibleInCart(productsNames);
        new HomePage(getDriver())
                .signupLoginClick()
                .fillCorrectLogin(JSONReader.existingUser("email"), JSONReader.existingUser("password"));
        verifyThatThoseProductsAreVisibleInCartAfterLoginAsWell(productsNames);
        verifyThatCartIsEmpty();
    }

    private void clickCartButtonAndVerifyThatProductsAreVisibleInCart(List<String> productsNames) {
        List<String> productsNamesAdded = new HomePage(getDriver())
                .cartButtonClick()
                .getProductsNames();
        for (int i = 0; i < productsNames.size(); i++) {
            Assert.assertEquals(productsNames.get(i), productsNamesAdded.get(i), "Verify that products are visible in cart");
            System.out.println("Search: " + productsNames.get(i) + " = Added: " + productsNamesAdded.get(i));
        }
    }

    private void verifyThatThoseProductsAreVisibleInCartAfterLoginAsWell(List<String> productsNames) {
        clickCartButtonAndVerifyThatProductsAreVisibleInCart(productsNames);
    }

    private void verifyThatCartIsEmpty() throws InterruptedException {
        String emptyCartText = new CartPage(getDriver())
                .deleteAllAddedProducts()
                .getEmptyCartSpan()
                .getText();
        Assert.assertEquals(emptyCartText, "Cart is empty! Click here to buy products.", "Verify 'Cart is empty! Click here to buy products.' is visible");
    }
}
