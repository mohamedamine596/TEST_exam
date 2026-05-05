package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.FormPage;

public class FormSteps {

    private FormPage formPage;

    private FormPage getFormPage() {
        if (formPage == null) {
            formPage = new FormPage(Hooks.driver);
        }
        return formPage;
    }

    @Given("I am on the selenium web form")
    public void iAmOnWebForm() {
        getFormPage().open();
    }

    @When("I enter text {string} in the text field")
    public void enterText(String text) {
        getFormPage().enterText(text);
    }

    @When("I enter password {string} in the password field")
    public void enterPassword(String password) {
        getFormPage().enterPassword(password);
    }

    @When("I clear all form fields")
    public void clearAllFields() {
        getFormPage().clearAllFields();
    }

    @When("I attempt to type {string} into the disabled input")
    public void attemptTypeIntoDisabled(String text) {
        getFormPage().attemptTypeDisabled(text);
    }

    @Then("the disabled input value should remain empty")
    public void disabledInputShouldRemainEmpty() {
        Assert.assertTrue(
                "Disabled input should remain empty",
                getFormPage().getDisabledValue().isEmpty());
    }

    @When("I attempt to type {string} into the readonly input")
    public void attemptTypeIntoReadonly(String text) {
        getFormPage().attemptTypeReadonly(text);
    }

    @Then("the readonly input value should remain {string}")
    public void readonlyInputShouldRemain(String expected) {
        Assert.assertEquals(
                "Readonly input should not change",
                expected,
                getFormPage().getReadonlyValue());
    }

    @When("I submit the form")
    public void submitForm() {
        getFormPage().submitForm();
    }

    @Then("I should see the success message {string}")
    public void seeSuccessMessage(String message) {
        String actual = getFormPage().getSuccessMessage();
        Assert.assertFalse("Page should show a message", actual.isEmpty());
    }
}