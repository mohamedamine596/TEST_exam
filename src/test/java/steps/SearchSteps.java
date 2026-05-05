package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.InputsPage;

public class SearchSteps {

    private InputsPage inputsPage;

    private InputsPage getInputsPage() {
        if (inputsPage == null) {
            inputsPage = new InputsPage(Hooks.driver);
        }
        return inputsPage;
    }

    @Given("I am on the selenium inputs page")
    public void iAmOnInputsPage() {
        getInputsPage().open();
    }

    @When("I type {string} into the input field")
    public void typeIntoInput(String text) {
        getInputsPage().enterText(text);
    }

    @Then("the input field value should be {string}")
    public void inputValueShouldBe(String expected) {
        String actual = getInputsPage().getTextValue();
        Assert.assertEquals(
                "Input value mismatch", expected, actual);
    }

    @When("I type {string} into the number input")
    public void typeIntoNumberInput(String text) {
        getInputsPage().enterNumber(text);
    }

    @Then("the number input value should be empty")
    public void numberInputShouldBeEmpty() {
        String actual = getInputsPage().getNumberValue();
        Assert.assertTrue(
                "Number input should be empty for invalid text",
                actual == null || actual.isEmpty());
    }

    @When("I type {string} into the email input")
    public void typeIntoEmailInput(String text) {
        getInputsPage().enterEmail(text);
    }

    @Then("the email input should be invalid")
    public void emailInputShouldBeInvalid() {
        Assert.assertFalse(
                "Email input should be invalid",
                getInputsPage().isEmailValid());
    }
}