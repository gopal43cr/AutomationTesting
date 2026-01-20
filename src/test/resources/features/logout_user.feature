Feature: Logout User

  Scenario: Logout user successfully
    Given user launches the browser
    And user navigates to automation exercise website
    And home page is visible
    When user clicks on Signup Login button
    And user logs in with valid credentials
    Then user should be logged in
    When user clicks on Logout button
    Then user should be navigated to login page
