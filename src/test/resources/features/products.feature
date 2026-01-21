Feature: Verify Products Page and Product Details

  @TC8
  Scenario: User verifies all products and product detail page
    Given user launches the browser
    And user navigates to automation exercise website
    And home page is visible
    When user clicks on Products button
    Then All Products page should be visible
    When user clicks on View Product of first product
    Then Product detail page should be displayed
