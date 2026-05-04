Feature: Dropdown Menu Interaction

  Scenario: Valid - Select a valid menu option
    Given I am on the selenium dropdown page
    When I hover over the dropdown menu
    And I click menu item "1"
    Then the page should still be accessible

  Scenario: Invalid - Navigate to nonexistent page
    Given I navigate to a nonexistent selenium page
    Then the page should show an error or not found message

  Scenario: Invalid - Access dropdown without hovering
    Given I am on the selenium dropdown page
    Then the dropdown items should not be visible initially