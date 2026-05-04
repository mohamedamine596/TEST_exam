package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormSteps {

    private WebDriverWait getWait() {
        return new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
    }

    @Given("I am on the selenium web form")
    public void iAmOnWebForm() {
        Hooks.driver.get(
                "https://www.selenium.dev/selenium/web/web-form.html");
        getWait().until(ExpectedConditions.titleIs("Web form"));
    }

    @When("I enter text {string} in the text field")
    public void enterText(String text) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("my-text-id"))).clear();
        Hooks.driver.findElement(By.id("my-text-id")).sendKeys(text);
    }

    @When("I enter password {string} in the password field")
    public void enterPassword(String password) {
        Hooks.driver.findElement(By.name("my-password")).clear();
        Hooks.driver.findElement(By.name("my-password")).sendKeys(password);
    }

    @When("I clear all form fields")
    public void clearAllFields() {
        Hooks.driver.findElement(By.id("my-text-id")).clear();
        Hooks.driver.findElement(By.name("my-password")).clear();
        Hooks.driver.findElement(By.name("my-textarea")).clear();
    }

    @When("I submit the form")
    public void submitForm() {
        Hooks.driver.findElement(
                By.cssSelector("button[type='submit']")).click();
    }

    @Then("I should see the success message {string}")
    public void seeSuccessMessage(String message) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("message")));
        String actual = Hooks.driver.findElement(By.id("message")).getText();
        Assert.assertFalse("Page should show a message", actual.isEmpty());
    }
}