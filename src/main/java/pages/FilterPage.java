package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class FilterPage extends BasePage {

    // Using: https://www.amazon.com
    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By sortDropdown = By.id("s-result-sort-select");
    private By resultItems = By.cssSelector(".s-result-item[data-component-type='s-search-result']");
    private By priceFilter = By.cssSelector("span[data-action='s-navigation-node']");

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void searchFor(String keyword) {
        type(searchBox, keyword);
        click(searchButton);
    }

    public void sortBy(String option) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(option);
    }

    public int getResultCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultItems));
        return driver.findElements(resultItems).size();
    }

    public String getCurrentSortValue() {
        Select select = new Select(driver.findElement(sortDropdown));
        return select.getFirstSelectedOption().getText();
    }
}