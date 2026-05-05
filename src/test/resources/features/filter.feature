Feature: Dynamic Table and Data Validation

  Scenario: Valid - Dynamic table loads with data
    Given I am on the selenium dynamic table page
    When the table loads
    Then the table should contain at least one row

  Scenario: Invalid - Header should not contain fake column
    Given I am on the selenium dynamic table page
    When the table loads
    Then the table header row should not contain "NotAHeader"

  Scenario: Invalid - Table contains unexpected data
    Given I am on the selenium dynamic table page
    When the table loads
    Then the table should not contain the text "NotInTable"