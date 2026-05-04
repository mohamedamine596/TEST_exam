Feature: Form Submission and Validation

  Scenario: Valid form submission with all fields filled
    Given I am on the selenium web form
    When I enter text "John Doe" in the text field
    And I enter password "secret123" in the password field
    And I submit the form
    Then I should see the success message "Form submitted"

  Scenario: Invalid - Submit form without filling any field
    Given I am on the selenium web form
    When I clear all form fields
    And I submit the form
    Then I should see the success message "Form submitted"

  Scenario: Invalid - Enter only spaces in text field
    Given I am on the selenium web form
    When I enter text "   " in the text field
    And I submit the form
    Then I should see the success message "Form submitted"