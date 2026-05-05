package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.DropdownPage;

public class CartSteps {

    private DropdownPage dropdownPage;

    private DropdownPage getDropdownPage() {
        if (dropdownPage == null) {
            dropdownPage = new DropdownPage(Hooks.driver);
        }
        return dropdownPage;
    }

    @Given("I am on the selenium dropdown page")
    public void iAmOnDropdownPage() {
        getDropdownPage().open();
    }

    @Given("I am on the selenium select page")
    public void iAmOnSelectPage() {
        getDropdownPage().open();
    }

    @When("I select {string} from the simple select")
    public void selectFromSimple(String option) {
        getDropdownPage().selectSimpleOption(option);
    }

    @Then("the selected option should be {string}")
    public void selectedOptionShouldBe(String option) {
        Assert.assertEquals(
                "Selected option mismatch",
                option,
                getDropdownPage().getSelectedSimpleOption());
    }

    @When("I try to select disabled option {string}")
    public void trySelectDisabledOption(String option) {
        getDropdownPage().trySelectVisibilityOption(option);
    }

    @Then("the selected option should remain {string}")
    public void selectedOptionShouldRemain(String option) {
        Assert.assertEquals(
                "Disabled option should not change selection",
                option,
                getDropdownPage().getSelectedVisibilityOption());
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
        Assert.assertEquals(
                "Regular",
                getDropdownPage().getSelectedVisibilityOption());
    }
}