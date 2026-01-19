package com.automationexercise.tests;

import com.automationexercise.pages.PaymentPage;
import com.automationexercise.utils.Util;
import com.automationexercise.utils.VerifyDownload;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase24 extends TestBasic {

    String name = "name" + Util.generateCurrentDateAndTime();
    String email = "email" + Util.generateCurrentDateAndTime() + "@o2.pl";

    @Test(description = "Test Case 24: Download Invoice after purchase order")
                public void downloadInvoiceAfterPurchaseOrder() throws IOException, ParseException {
        TestCase1.verifyThatHomePageIsVisibleSuccessfully();
        TestCase14.verifyThatCartPageIsDisplayed();
        TestCase14.verifyAccountCreatedAndClickContinueButton(name, email);
        TestCase14.verifyLoggedInAsUsernameAtTop(name);
        TestCase14.verifyAddressDetailsAndReviewYourOrder();
        TestCase14.verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed();
        clickDownloadInvoiceButtonAndVerifyInvoiceIsDownloadedSuccessfully();
        new PaymentPage(getDriver()).continueButtonClick();
        TestCase1.verifyThatAccountDeletedIsVisibleAndClickContinueButton();
    }

    private void clickDownloadInvoiceButtonAndVerifyInvoiceIsDownloadedSuccessfully() throws IOException {
        new PaymentPage(getDriver()).downloadInvoiceButtonClick();
        boolean isFileDownloaded = VerifyDownload.isFileDownloaded("invoice", "txt", 5000);
        Assert.assertTrue(isFileDownloaded, "Verify invoice is downloaded successfully.");
    }
}
