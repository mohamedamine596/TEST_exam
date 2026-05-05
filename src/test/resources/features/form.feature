Feature: Form Submission and Validation

  Scenario: Valid form submission with all fields filled
    Given I am on the selenium web form
    When I enter text "John Doe" in the text field
    And I enter password "secret123" in the password field
    And I submit the form
    Then I should see the success message "Form submitted"

  Scenario: Invalid - Attempt to type into disabled input
    Given I am on the selenium web form
    When I attempt to type "Disabled" into the disabled input
    Then the disabled input value should remain empty

  Scenario: Invalid - Attempt to edit readonly input
    Given I am on the selenium web form
    When I attempt to type "Changed" into the readonly input
    Then the readonly input value should remain "Readonly input"