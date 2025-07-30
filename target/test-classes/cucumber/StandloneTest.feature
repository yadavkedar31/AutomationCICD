@tag
Feature: purchase the order Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    Examples: 
      | name                  | password | productName  |
      | 31kedaryadav@gmail.com| Ked@r123 | ZARA COAT 3  |
