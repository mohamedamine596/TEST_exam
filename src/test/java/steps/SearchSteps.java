package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchSteps {

    private WebDriverWait getWait() {
        return new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
    }

    @Given("I am on the selenium inputs page")
    public void iAmOnInputsPage() {
        Hooks.driver.get(
                "https://www.selenium.dev/selenium/web/inputs.html");
        getWait().until(ExpectedConditions.urlContains("inputs"));
    }

    @When("I type {string} into the input field")
    public void typeIntoInput(String text) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='text']"))).clear();
        Hooks.driver.findElement(
                By.cssSelector("input[type='text']")).sendKeys(text);
    }

    @Then("the input field value should be {string}")
    public void inputValueShouldBe(String expected) {
        String actual = Hooks.driver.findElement(
                By.cssSelector("input[type='text']")).getAttribute("value");
        Assert.assertEquals(
                "Input value mismatch", expected, actual);
    }
}