Feature: Product management

  Scenario: Create a new product
    Given I have a product with name "Burger" and price 15
    When I create the product
    Then the product should be saved successfully