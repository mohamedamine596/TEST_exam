Feature: Select Menu Interaction

  Scenario: Valid - Select a valid option
    Given I am on the selenium select page
    When I select "two" from the simple select
    Then the selected option should be "two"

  Scenario: Invalid - Try selecting a disabled option
    Given I am on the selenium select page
    When I try to select disabled option "Disabled"
    Then the selected option should remain "Regular"

  Scenario: Invalid - Navigate to nonexistent page
    Given I navigate to a nonexistent selenium page
    Then the page should show an error or not found message