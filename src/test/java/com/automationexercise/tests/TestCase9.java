package com.automationexercise.tests;

import com.automationexercise.pages.ProductsPage;
import com.automationexercise.utils.PropertiesLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TestCase9 extends TestBasic {

    static String search;

    static {
        try {
            search = PropertiesLoader.loadProperty("search.product.input");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Test Case 9: Search Product")
                public void searchProduct() {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase8.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        verifySearchedProductsIsVisible();
        verifyAllTheProductsRelatedToSearchAreVisible();
    }

    public static void verifySearchedProductsIsVisible() {
        String searchedProductsText = new ProductsPage(getDriver())
                .fillSearchProductInput(search)
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(searchedProductsText, "SEARCHED PRODUCTS", "Verify 'SEARCHED PRODUCTS' is visible");
    }

    public static List<String> verifyAllTheProductsRelatedToSearchAreVisible() {
        List<String> productsNames = new ProductsPage(getDriver()).getProductsSearchNames();

        for (int i = 0; i < productsNames.size(); i++) {
            Assert.assertTrue(productsNames.get(i).toLowerCase().contains(search.toLowerCase()));
            System.out.println(i + ". " + productsNames.get(i) + " - contain: " + search);
        }
        return productsNames;
    }
}
