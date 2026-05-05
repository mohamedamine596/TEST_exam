package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.DynamicTablePage;

public class FilterSteps {

    private DynamicTablePage tablePage;

    private DynamicTablePage getTablePage() {
        if (tablePage == null) {
            tablePage = new DynamicTablePage(Hooks.driver);
        }
        return tablePage;
    }

    @Given("I am on the selenium dynamic table page")
    public void iAmOnDynamicTablePage() {
        getTablePage().open();
    }

    @When("the table loads")
    public void tableLoads() {
        getTablePage().waitForTable();
    }

    @Then("the table should contain at least one row")
    public void tableShouldHaveRows() {
        Assert.assertTrue(
            "Table should have rows",
            getTablePage().getRowCount() >= 1);
    }

    @Then("the table header row should be present")
    public void tableHeaderShouldBePresent() {
        Assert.assertTrue(
            "Header should exist",
            getTablePage().getHeaderCount() >= 1);
    }

    @Then("the table header row should not contain {string}")
    public void tableHeaderShouldNotContain(String text) {
        Assert.assertFalse(
                "Header should not contain: " + text,
                getTablePage().headerContains(text));
    }

    @Then("the table should not contain the text {string}")
    public void tableShouldNotContainText(String text) {
        Assert.assertFalse(
                "Table should not contain: " + text,
                getTablePage().tableContains(text));
    }
}