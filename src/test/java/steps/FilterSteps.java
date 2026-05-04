package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;

public class FilterSteps {

    private WebDriverWait getWait() {
        return new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
    }

    @Given("I am on the selenium dynamic table page")
    public void iAmOnDynamicTablePage() {
        Hooks.driver.get(
                "https://www.selenium.dev/selenium/web/tables.html");
        getWait().until(ExpectedConditions.urlContains("tables"));
    }

    @When("the table loads")
    public void tableLoads() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("table")));
    }

    @Then("the table should contain at least one row")
    public void tableShouldHaveRows() {
        List<WebElement> rows = Hooks.driver.findElements(
                By.cssSelector("table tbody tr"));
        Assert.assertTrue("Table should have rows", rows.size() >= 1);
    }

    @Then("the table header row should be present")
    public void tableHeaderShouldBePresent() {
        List<WebElement> headers = Hooks.driver.findElements(
                By.cssSelector("table th"));
        Assert.assertTrue("Header should exist", headers.size() >= 1);
    }

    @When("I refresh the page")
    public void refreshPage() {
        Hooks.driver.navigate().refresh();
        getWait().until(ExpectedConditions.urlContains("tables"));
    }

    @Then("the table should still be present after refresh")
    public void tableShouldBePresentAfterRefresh() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("table")));
        Assert.assertTrue(
                Hooks.driver.findElement(By.tagName("table")).isDisplayed());
    }
}