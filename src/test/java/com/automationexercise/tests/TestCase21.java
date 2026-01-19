package com.automationexercise.tests;

import com.automationexercise.pages.ProductDetailPage;
import com.automationexercise.pages.ProductsPage;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase21 extends TestBasic {

    @Test(description = "Test Case 21: Add review on product")
                public void addReviewOnProduct() throws IOException, ParseException {
        TestCase8.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        verifyWriteYourReviewIsVisible();
        verifySuccessMessageThankYouForYourReview();
    }

    private void verifyWriteYourReviewIsVisible() {
        String writeYourReviewText = new ProductsPage(getDriver())
                .viewProductOfFirstProductButtonClick()
                .getWriteYourReview()
                .getText();
        Assert.assertEquals(writeYourReviewText, "WRITE YOUR REVIEW", "Verify 'Write Your Review' is visible");
    }

    private void verifySuccessMessageThankYouForYourReview() throws IOException, ParseException {
        String successMessageText = new ProductDetailPage(getDriver())
                .fillReview()
                .getSuccessMessage()
                .getText();
        Assert.assertEquals(successMessageText, "Thank you for your review.", "Verify success message 'Thank you for your review.'");
    }
}
