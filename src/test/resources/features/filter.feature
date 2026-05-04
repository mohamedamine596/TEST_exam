Feature: Dynamic Table and Data Validation

  Scenario: Valid - Dynamic table loads with data
    Given I am on the selenium dynamic table page
    When the table loads
    Then the table should contain at least one row

  Scenario: Invalid - Verify table header exists
    Given I am on the selenium dynamic table page
    When the table loads
    Then the table header row should be present

  Scenario: Invalid - Table data changes on refresh
    Given I am on the selenium dynamic table page
    When I refresh the page
    Then the table should still be present after refresh