Feature: Input Field Validation

  Scenario: Valid - Text input accepts normal text
    Given I am on the selenium inputs page
    When I type "Hello World" into the input field
    Then the input field value should be "Hello World"

  Scenario: Invalid - Input field with numbers only
    Given I am on the selenium inputs page
    When I type "12345" into the input field
    Then the input field value should be "12345"

  Scenario: Invalid - Input field with special characters
    Given I am on the selenium inputs page
    When I type "!@#$%^" into the input field
    Then the input field value should be "!@#$%^"