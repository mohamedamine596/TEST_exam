package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicTablePage extends BasePage {

    private static final String URL =
            "https://www.selenium.dev/selenium/web/tables.html";
    private By table = By.tagName("table");
    private By rows = By.cssSelector("table tbody tr");
    private By headers = By.cssSelector("table th");

    public DynamicTablePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
        waitForElement(table);
    }

    public void waitForTable() {
        waitForElement(table);
    }

    public int getRowCount() {
        return driver.findElements(rows).size();
    }

    public int getHeaderCount() {
        return driver.findElements(headers).size();
    }

    public boolean headerContains(String text) {
        String expected = text.toLowerCase();
        for (var header : driver.findElements(headers)) {
            if (header.getText().toLowerCase().contains(expected)) {
                return true;
            }
        }
        return false;
    }

    public boolean tableContains(String text) {
        String expected = text.toLowerCase();
        return waitForElement(table).getText().toLowerCase().contains(expected);
    }

    public void refresh() {
        driver.navigate().refresh();
        waitForElement(table);
    }

    public boolean isTableDisplayed() {
        return waitForElement(table).isDisplayed();
    }
}