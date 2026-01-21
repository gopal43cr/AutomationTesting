package com.bdd.stepDefinations;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.TestCasesPage;
import com.automationexercise.tests.TestBasic;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class TestCasesPageSteps {

    HomePage homePage;
    TestCasesPage testCasesPage;

    @When("user clicks on Test Cases button")
    public void user_clicks_on_test_cases_button() {
        homePage = new HomePage(TestBasic.getDriver()); // ✅ CORRECT
        testCasesPage = homePage.testCasesButtonClick();
    }

    @Then("Test Cases page should be displayed successfully")
    public void test_cases_page_should_be_displayed_successfully() {
        Assert.assertTrue(TestBasic.getDriver()
                .getCurrentUrl()
                .contains("test_cases"));
    }
}

