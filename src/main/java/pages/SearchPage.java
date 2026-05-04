package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class SearchPage extends BasePage {

    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By results = By.cssSelector(".s-result-item h2 span");
    private By noResults = By.cssSelector(".a-spacing-medium .a-color-state");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void searchFor(String keyword) {
        type(searchBox, keyword);
        click(searchButton);
    }

    public boolean hasResults() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(results));
            List<WebElement> items = driver.findElements(results);
            return !items.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasNoResultsMessage() {
        return isDisplayed(noResults);
    }

    public String getFirstResultTitle() {
        return getText(results);
    }
}