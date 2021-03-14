Feature: Card Compare

  Scenario: Setup card properties
    Given  A user navigates to DBS Home page
    When page contain link as "Cards"
    And page contain link as "Credit Cards"
    And select card container
    And compare credit card values
    Then verify card has values
    Then verify both has same "Card Type"



