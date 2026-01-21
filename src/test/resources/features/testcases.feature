Feature: Verify Test Cases Page

  @TC7
  Scenario: User verifies Test Cases page
    Given user launches the browser
    And user navigates to automation exercise website
    And home page is visible
    When user clicks on Test Cases button
    Then Test Cases page should be displayed successfully
