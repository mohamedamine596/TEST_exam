Feature: Input Field Validation

  Scenario: Valid - Text input accepts normal text
    Given I am on the selenium inputs page
    When I type "Hello World" into the input field
    Then the input field value should be "Hello World"

  Scenario: Invalid - Number input rejects letters
    Given I am on the selenium inputs page
    When I type "abc" into the number input
    Then the number input value should be empty

  Scenario: Invalid - Email input without at sign
    Given I am on the selenium inputs page
    When I type "invalid-email" into the email input
    Then the email input should be invalid