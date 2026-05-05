package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputsPage extends BasePage {

    private static final String URL =
            "https://www.selenium.dev/selenium/web/inputs.html";
        private By textInput = By.name("text_input");
        private By numberInput = By.name("number_input");
        private By emailInput = By.name("email_input");

    public InputsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
        waitForElement(textInput);
    }

    public void enterText(String text) {
        type(textInput, text);
    }

    public String getTextValue() {
        return driver.findElement(textInput).getAttribute("value");
    }

    public void enterNumber(String text) {
        WebElement el = waitForElement(numberInput);
        el.clear();
        el.sendKeys(text);
    }

    public String getNumberValue() {
        return driver.findElement(numberInput).getAttribute("value");
    }

    public void enterEmail(String text) {
        WebElement el = waitForElement(emailInput);
        el.clear();
        el.sendKeys(text);
    }

    public boolean isEmailValid() {
        WebElement el = waitForElement(emailInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return Boolean.TRUE.equals(
                js.executeScript("return arguments[0].checkValidity();", el));
    }
}