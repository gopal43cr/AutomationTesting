package com.bdd.stepDefinations;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import com.automationexercise.pages.ProductDetailPage;
import com.automationexercise.tests.TestBasic;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class AllProductsSteps {

    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;

    @When("user clicks on Products button")
    public void user_clicks_on_products_button() {
        homePage = new HomePage(TestBasic.getDriver()); 
        productsPage = homePage.productsButtonClick();
    }

    @Then("All Products page should be visible")
    public void all_products_page_should_be_visible() {
        Assert.assertTrue(productsPage.getTitleTextCenter().isDisplayed());
    }

    @When("user clicks on View Product of first product")
    public void user_clicks_on_view_product_of_first_product() {
        productDetailPage =
                productsPage.viewProductOfFirstProductButtonClick();
    }

    @Then("Product detail page should be displayed")
    public void product_detail_page_should_be_displayed() {
        Assert.assertTrue(productDetailPage.getProductName().isDisplayed());
        Assert.assertTrue(productDetailPage.getProductPrice().isDisplayed());
        Assert.assertTrue(productDetailPage.getProductCategory().isDisplayed());
        Assert.assertTrue(productDetailPage.getProductAvailability().isDisplayed());
        Assert.assertTrue(productDetailPage.getProductCondition().isDisplayed());
        Assert.assertTrue(productDetailPage.getProductBrand().isDisplayed());
    }
}
