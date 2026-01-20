package com.bdd.stepDefinations;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoggedHomePage;
import com.automationexercise.pages.LoginSignupPage;
import com.automationexercise.tests.TestBasic;
import com.automationexercise.utils.JSONReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutSteps extends TestBasic {
	
	HomePage homePage;
    LoginSignupPage loginSignupPage;
    LoggedHomePage loggedHomePage;
	
	@Given("user launches the browser")
	public void user_launches_the_browser() {
		// Browser is already launched by Hooks (@Before)
		
	}

	@Given("user navigates to automation exercise website")
	public void user_navigates_to_automation_exercise_website() {
	    // Write code here that turns the phrase above into concrete actions
		getDriver().get("https://automationexercise.com/");
	}

	@Given("home page is visible")
	public void home_page_is_visible() {
	    // Write code here that turns the phrase above into concrete actions
		homePage = new HomePage(getDriver());
        Assert.assertTrue(
                homePage.homePageIsVisible().isDisplayed(),
                "Home page is not visible"
        );
	}

	@When("user clicks on Signup Login button")
	public void user_clicks_on_signup_login_button() {
	    // Write code here that turns the phrase above into concrete actions
		loginSignupPage = homePage.signupLoginClick();
	}

	@When("user logs in with valid credentials")
	public void user_logs_in_with_valid_credentials() throws IOException, ParseException {
	    // Write code here that turns the phrase above into concrete actions
		loggedHomePage = loginSignupPage.fillCorrectLogin(
                JSONReader.existingUser("email"),
                JSONReader.existingUser("password")
        );
		
	}

	@Then("user should be logged in")
	public void user_should_be_logged_in() throws IOException, ParseException {
	    // Write code here that turns the phrase above into concrete actions
		String username = loggedHomePage.getUsername().getText();

        Assert.assertEquals(
                username,
                JSONReader.existingUser("name"),
                "Logged in username mismatch"
        );
	}

	@When("user clicks on Logout button")
	public void user_clicks_on_logout_button() {
	    // Write code here that turns the phrase above into concrete actions
		loginSignupPage = loggedHomePage.logoutButtonClick();
	}

	@Then("user should be navigated to login page")
	public void user_should_be_navigated_to_login_page() {
	    // Write code here that turns the phrase above into concrete actions
		String loginText = loginSignupPage
                .getLoginToYourAccount()
                .getText();

        Assert.assertEquals(
                loginText,
                "Login to your account",
                "User is not navigated to login page"
        );
	}
}
