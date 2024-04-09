
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test feom submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message displayed on confirmation

    Examples: 
      | username  | password | productName  |
      | crazyadmin@gmail.com |    Icecream@123 | ZARA COAT 3 |
