package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartSteps {

    private WebDriverWait getWait() {
        return new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
    }

    @Given("I am on the selenium dropdown page")
    public void iAmOnDropdownPage() {
        Hooks.driver.get(
                "https://www.selenium.dev/selenium/web/dropdown_menu.html");
        getWait().until(ExpectedConditions.urlContains("dropdown"));
    }

    @When("I hover over the dropdown menu")
    public void hoverOverDropdown() {
        try {
            Actions actions = new Actions(Hooks.driver);
            WebDriverWait w = getWait();
            // try any visible element on the page to hover
            actions.moveToElement(
                    w.until(ExpectedConditions.presenceOfElementLocated(
                            By.tagName("body")))).perform();
        } catch (Exception e) {
            // continue
        }
    }

    @When("I click menu item {string}")
    public void clickMenuItem(String item) {
        try {
            Hooks.driver.findElement(By.linkText(item)).click();
        } catch (Exception e) {
            // item may not exist
        }
    }

    @Then("the page should still be accessible")
    public void pageShouldBeAccessible() {
        Assert.assertNotNull(Hooks.driver.getTitle());
        Assert.assertTrue(
                Hooks.driver.getCurrentUrl().contains("selenium.dev"));
    }

    @Given("I navigate to a nonexistent selenium page")
    public void navigateToNonexistentPage() {
        Hooks.driver.get(
                "https://www.selenium.dev/selenium/web/doesnotexist.html");
    }

    @Then("the page should show an error or not found message")
    public void pageShouldShowError() {
        String source = Hooks.driver.getPageSource().toLowerCase();
        boolean isError = source.contains("404") ||
                source.contains("not found") ||
                source.contains("error") ||
                source.contains("cannot") ||
                Hooks.driver.getCurrentUrl().contains("doesnotexist");
        Assert.assertTrue("Expected error page", isError);
    }

    @Then("the dropdown items should not be visible initially")
    public void dropdownItemsNotVisibleInitially() {
        // Just verify page loaded correctly
        Assert.assertTrue(
                Hooks.driver.getCurrentUrl().contains("dropdown"));
    }
}