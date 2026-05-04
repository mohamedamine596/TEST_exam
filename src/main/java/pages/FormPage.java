package pages;

import org.openqa.selenium.*;

public class FormPage extends BasePage {

    // Using a public test form: https://www.selenium.dev/selenium/web/web-form.html
    private By textInput = By.id("my-text-id");
    private By passwordInput = By.name("my-password");
    private By textareaInput = By.name("my-textarea");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By successMessage = By.id("message");

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void fillForm(String text, String password, String textarea) {
        if (text != null) type(textInput, text);
        if (password != null) type(passwordInput, password);
        if (textarea != null) type(textareaInput, textarea);
    }

    public void submitForm() {
        click(submitButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isSuccessDisplayed() {
        return isDisplayed(successMessage);
    }
}