package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By firstProduct = By.cssSelector(".s-result-item h2 a");
    private By addToCartButton = By.id("add-to-cart-button");
    private By cartCount = By.id("nav-cart-count");
    private By cartIcon = By.id("nav-cart");
    private By emptyCartMessage = By.cssSelector(".sc-your-amazon-cart-is-empty");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void searchAndOpenFirstProduct(String keyword) {
        type(searchBox, keyword);
        click(searchButton);
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public String getCartCount() {
        return getText(cartCount);
    }

    public void goToCart() {
        click(cartIcon);
    }

    public boolean isCartEmpty() {
        return isDisplayed(emptyCartMessage);
    }
}